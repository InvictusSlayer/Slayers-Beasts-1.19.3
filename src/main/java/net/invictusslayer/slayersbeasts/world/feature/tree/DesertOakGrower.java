package net.invictusslayer.slayersbeasts.world.feature.tree;

import net.invictusslayer.slayersbeasts.world.feature.SBConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class DesertOakGrower extends AbstractTreeGrower {
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return SBConfiguredFeatures.DESERT_OAK;
    }
}
