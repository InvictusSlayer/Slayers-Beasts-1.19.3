package net.invictusslayer.slayersbeasts.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlgaeBlock extends BushBlock implements BonemealableBlock {
	private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.5D, 16.0D);

	public AlgaeBlock(Properties properties) {
		super(properties);
	}

	public PlantType getPlantType(BlockGetter level, BlockPos pos) {
		return PlantType.WATER;
	}

	@SuppressWarnings("deprecation")
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@SuppressWarnings("deprecation")
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (level instanceof ServerLevel && entity instanceof Boat) {
			level.destroyBlock(new BlockPos(pos), true, entity);
		}
		super.entityInside(state, level, pos, entity);
	}

	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		FluidState fluid = level.getFluidState(pos);
		FluidState fluidAbove = level.getFluidState(pos.above());
		return fluid.getType() == Fluids.WATER && fluidAbove.getType() == Fluids.EMPTY;
	}

	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return level.getBlockState(pos.above()).isAir();
	}

	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		List<BlockPos> posList = Arrays.asList(pos.north(), pos.east(), pos.south(), pos.west());
		Collections.shuffle(posList);
		for (BlockPos blockPos : posList) {
			FluidState fluidBelow = level.getFluidState(blockPos.below());
			FluidState fluid = level.getFluidState(blockPos);
			if (fluidBelow.getType() == Fluids.WATER && fluid.getType() == Fluids.EMPTY) {
				level.setBlockAndUpdate(blockPos, defaultBlockState());
				return;
			}
		}
	}

	public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
		return false;
	}
}
