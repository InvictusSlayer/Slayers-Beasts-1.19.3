package net.invictusslayer.slayersbeasts.world.gen;

import net.invictusslayer.slayersbeasts.world.feature.ModPlacedFeatures;
import net.invictusslayer.slayersbeasts.world.feature.ModTreeFeatures;
import net.invictusslayer.slayersbeasts.world.feature.ModTreePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Set;

public class ModTreeGeneration {
    /*
    public static void generateTrees(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

        if (types.contains(BiomeDictionary.Type.JUNGLE)) {
            base.add(ModTreePlacement.CAJOLE_PLACED);
        } else if (types.contains(BiomeDictionary.Type.SAVANNA)) {
            base.add(ModTreePlacement.EUCALYPTUS_PLACED);
        }
    }*/
}
