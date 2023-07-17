package net.invictusslayer.slayersbeasts.entity;

import com.google.common.collect.Lists;
import net.invictusslayer.slayersbeasts.block.entity.AnthillBlockEntity;
import net.invictusslayer.slayersbeasts.datagen.tags.SBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.pathfinder.Path;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractAnt extends PathfinderMob {
    private static final EntityDataAccessor<Integer> DATA_ANT_TYPE = SynchedEntityData.defineId(AbstractAnt.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_CARGO_TYPE = SynchedEntityData.defineId(AbstractAnt.class, EntityDataSerializers.INT);
    private int cooldownToEnterNest;
    private int cooldownToLocateNest;
    private int failedForagingTime;
    BlockPos nestPos;
    AntGoToNestGoal antGoToNestGoal;

    public AbstractAnt(EntityType<? extends AbstractAnt> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AntEnterNestGoal(this));
        this.goalSelector.addGoal(2, new AntLocateNestGoal(this));
        this.antGoToNestGoal = new AntGoToNestGoal(this);
        this.goalSelector.addGoal(3, this.antGoToNestGoal);
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new AntAttackedGoal(this).setAlertOthers());
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.nestPos != null) {
            pCompound.put("NestPos", NbtUtils.writeBlockPos(this.nestPos));
        }

        pCompound.putInt("CooldownToEnterNest", this.cooldownToEnterNest);
        pCompound.putInt("FailedForagingTime", this.failedForagingTime);
        pCompound.putInt("CargoType", this.getCargoType());
        pCompound.putInt("AntType", this.getAntType());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.nestPos = null;
        if (pCompound.contains("NestPos")) {
            this.nestPos = NbtUtils.readBlockPos(pCompound.getCompound("NestPos"));
        }

        this.setCooldownToEnterNest(pCompound.getInt("CooldownToEnterNest"));
        this.failedForagingTime = pCompound.getInt("FailedForagingTime");
        this.setCargoType(pCompound.getInt("CargoType"));
        this.setAntType(pCompound.getInt("AntType"));
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ANT_TYPE, 0);
        this.entityData.define(DATA_CARGO_TYPE, 0);
    }

    public void setCooldownToEnterNest(int cooldown) {
        this.cooldownToEnterNest = cooldown;
    }

    public int getAntType() {
        return this.entityData.get(DATA_ANT_TYPE);
    }
    public void setAntType(int pTinyAntType) {
        this.entityData.set(DATA_ANT_TYPE, pTinyAntType);
    }

    public int getCargoType() {
        return this.entityData.get(DATA_CARGO_TYPE);
    }
    public void setCargoType(int cargoType) {
        if (cargoType != 99) {
            failedForagingTime = 0;
        }
        this.entityData.set(DATA_CARGO_TYPE, cargoType);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (cooldownToEnterNest > 0) {
            --cooldownToEnterNest;
        }

        if (cooldownToLocateNest > 0) {
            --cooldownToLocateNest;
        }

        if (this.getCargoType() == 99) {
            ++failedForagingTime;
        }

        if (this.tickCount % 20 == 0 && !this.hasValidNest()) {
            this.nestPos = null;
        }
    }

    @SuppressWarnings("deprecation")
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason,
                                        @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        int randomAntType = this.getRandomAntType(pLevel);
        if (pSpawnData instanceof AntGroupData) {
            randomAntType = ((AntGroupData) pSpawnData).tinyAntType;
        } else {
            pSpawnData = new AntGroupData(randomAntType);
        }

        this.setAntType(randomAntType);
        return pSpawnData;
    }

    private int getRandomAntType(LevelAccessor pLevel) {
        Holder<Biome> holder = pLevel.getBiome(this.blockPosition());
        int i = pLevel.getRandom().nextInt(3);
        if (holder.is(BiomeTags.IS_FOREST) && !holder.is(Biomes.GROVE)) {
            return 0; // Wood Ant
        } else if (holder.is(BiomeTags.IS_JUNGLE) || holder.is(BiomeTags.IS_SAVANNA)) {
            return 1; // Leafcutter Ant
        } else if (holder.is(Biomes.PLAINS) || holder.is(Biomes.MEADOW) || holder.is(Biomes.SUNFLOWER_PLAINS)) {
            return 2; // Meadow Ant
        } else {
            return i;
        }
    }

    boolean hasValidNest() {
        if (this.nestPos == null) {
            return false;
        } else {
            BlockEntity blockEntity = this.level().getBlockEntity(this.nestPos);
            return blockEntity instanceof AnthillBlockEntity;
        }
    }

    private boolean nestHasSpace(BlockPos pNestPos) {
        BlockEntity blockEntity = level().getBlockEntity(pNestPos);
        if (blockEntity instanceof AnthillBlockEntity anthillBlockEntity) {
            return !anthillBlockEntity.isFull() && (anthillBlockEntity.getInhabitantType() == 99 ||
                    anthillBlockEntity.getInhabitantType() == getAntType());
        }
        return false;
    }

    protected boolean wantsToEnterNest(AbstractAnt ant) {
        if (this.cooldownToEnterNest <= 0) {
            return failedForagingTime > 3600 || getCargoType() != 99 || level().isRaining() || ant instanceof QueenAnt;
        }
        return false;
    }

    boolean closerThan(BlockPos pPos, double pDistance) {
        return pPos.closerThan(this.blockPosition(), pDistance);
    }

    public static class AntGroupData implements SpawnGroupData {
        public final int tinyAntType;
        private AntGroupData(int pAntType) {
            this.tinyAntType = pAntType;
        }
    }

    static class AntAttackedGoal extends HurtByTargetGoal {
        AntAttackedGoal(AbstractAnt mob) {
            super(mob);
        }

        public boolean canContinueToUse() {
            return mob instanceof SoldierAnt ant && ant.isAngry() && super.canContinueToUse();
        }

        protected void alertOther(Mob pMob, LivingEntity pTarget) {
            if (pMob instanceof SoldierAnt soldier && mob.hasLineOfSight(pTarget) &&
                    ((AbstractAnt)mob).getAntType() == soldier.getAntType()) {
                pMob.setTarget(pTarget);
            }
        }
    }

    static class AntGoToNestGoal extends Goal {
        int travellingTicks;
        final List<BlockPos> blacklistedTargets = Lists.newArrayList();
        @Nullable
        private Path lastPath;
        private int timeStuck;
        private final AbstractAnt mob;
        
        AntGoToNestGoal(AbstractAnt pMob) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
            this.mob = pMob;
            this.travellingTicks = mob.level().random.nextInt(10);
        }

        public boolean canUse() {
            return mob.nestPos != null && !mob.hasRestriction() && mob.wantsToEnterNest(mob) && mob.nestHasSpace(mob.nestPos)
                    && !this.hasReachedTarget(mob.nestPos) && mob.level().getBlockState(mob.nestPos).is(SBTags.Blocks.ANTHILLS);
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public void start() {
            this.travellingTicks = 0;
            this.timeStuck = 0;
        }

        public void stop() {
            this.travellingTicks = 0;
            this.timeStuck = 0;
            mob.navigation.stop();
        }

        public void tick() {
            if (mob.nestPos != null) {
                ++this.travellingTicks;
                if (this.travellingTicks > this.adjustedTickDelay(600)) {
                    this.blacklistNest();
                } else if (!mob.navigation.isInProgress()) {
                    if (!mob.closerThan(mob.nestPos, 16)) {
                        if (!mob.closerThan(mob.nestPos, 32)) {
                            this.dropNest();
                        } else {
                            this.moveToNest(mob.nestPos);
                        }
                    } else {
                        boolean flag = this.moveToNest(mob.nestPos);
                        if (!flag) {
                            this.blacklistNest();
                        } else if (this.lastPath != null && this.lastPath.sameAs(mob.navigation.getPath())) {
                            ++this.timeStuck;
                            if (this.timeStuck > 60) {
                                this.dropNest();
                                this.timeStuck = 0;
                            }
                        } else {
                            this.lastPath = mob.navigation.getPath();
                        }
                    }
                }
            }
        }

        private boolean moveToNest(BlockPos pPos) {
            mob.navigation.moveTo(mob.navigation.createPath(new BlockPos(pPos), 0), 1D);
            return mob.navigation.getPath() != null && mob.navigation.getPath().canReach();
        }

        private boolean hasReachedTarget(BlockPos target) {
            return mob.position().distanceToSqr(target.getX() + 0.5D, target.getY() + 1D, target.getZ() + 0.5D) <= 0.4D;
        }

        private void dropNest() {
            mob.nestPos = null;
            mob.cooldownToLocateNest = 200;
        }

        private void blacklistNest() {
            if (mob.nestPos != null) {
                this.blacklistedTargets.add(mob.nestPos);
            }
            while (this.blacklistedTargets.size() > 3) {
                this.blacklistedTargets.remove(0);
            }
            this.dropNest();
        }

        boolean isTargetBlacklisted(BlockPos pPos) {
            return this.blacklistedTargets.contains(pPos);
        }

        void clearBlacklist() {
            this.blacklistedTargets.clear();
        }
    }
    
    static class AntEnterNestGoal extends Goal {
        private final AbstractAnt mob;

        public AntEnterNestGoal(AbstractAnt pMob) {
            this.setFlags(EnumSet.of(Flag.MOVE));
            this.mob = pMob;
        }

        public boolean canUse() {
            return mob.nestPos != null && mob.wantsToEnterNest(mob) && mob.nestHasSpace(mob.nestPos) && mob.position()
                    .distanceToSqr(mob.nestPos.getX() + 0.5D, mob.nestPos.getY() + 1D, mob.nestPos.getZ() + 0.5D) <= 0.4D;
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            BlockEntity blockEntity = mob.level().getBlockEntity(mob.nestPos);
            if (blockEntity instanceof AnthillBlockEntity anthillBlockEntity) {
                anthillBlockEntity.addOccupant(mob, mob.getAntType(), mob.getCargoType() != 99);
            }
        }
    }

    static class AntLocateNestGoal extends Goal {
        private final AbstractAnt mob;

        public AntLocateNestGoal(AbstractAnt pMob) {
            this.mob = pMob;
        }

        public boolean canUse() {
            return mob.cooldownToLocateNest == 0 && mob.nestPos == null && mob.wantsToEnterNest(mob);
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            mob.cooldownToLocateNest = 200;
            List<BlockPos> list = this.findNestWithSpace();
            if (!list.isEmpty()) {
                for (BlockPos blockPos : list) {
                    if (!mob.antGoToNestGoal.isTargetBlacklisted(blockPos)) {
                        mob.nestPos = blockPos;
                        return;
                    }
                }
                mob.antGoToNestGoal.clearBlacklist();
                mob.nestPos = list.get(0);
            }
        }

        private List<BlockPos> findNestWithSpace() {
            BlockPos blockPos = mob.blockPosition();
            PoiManager poiManager = ((ServerLevel) mob.level()).getPoiManager();
            Stream<PoiRecord> stream = poiManager.getInRange(poiType ->
                    poiType.is(SBTags.PoiTypes.ANT_HOME), blockPos, 20, PoiManager.Occupancy.ANY);
            return stream.map(PoiRecord::getPos).filter(mob::nestHasSpace).sorted(Comparator.comparingDouble(key ->
                    key.distSqr(blockPos))).collect(Collectors.toList());
        }
    }
}
