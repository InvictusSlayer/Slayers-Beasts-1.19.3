package net.invictusslayer.slayersbeasts.world.feature;

import net.invictusslayer.slayersbeasts.SlayersBeasts;
import net.invictusslayer.slayersbeasts.init.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, SlayersBeasts.MOD_ID);

    public static final RegistryObject<PlacedFeature> EXOSKELETON_ORE_PLACED = PLACED_FEATURES.register("exoskeleton_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.EXOSKELETON_ORE.getHolder().get(),
                    ModOrePlacement.rareOrePlacement(1,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> LUSH_EXOSKELETON_ORE_PLACED = PLACED_FEATURES.register("lush_exoskeleton_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.EXOSKELETON_ORE.getHolder().get(),
                    ModOrePlacement.commonOrePlacement(10,
                            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)))));

    public static void register(IEventBus bus) {
        PLACED_FEATURES.register(bus);
    }
}
