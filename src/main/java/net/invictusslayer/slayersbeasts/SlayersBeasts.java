package net.invictusslayer.slayersbeasts;

import com.mojang.logging.LogUtils;
import net.invictusslayer.slayersbeasts.init.ModEffects;
import net.invictusslayer.slayersbeasts.init.ModBlocks;
import net.invictusslayer.slayersbeasts.init.ModEntities;
import net.invictusslayer.slayersbeasts.init.ModItems;
import net.invictusslayer.slayersbeasts.init.ModSounds;
import net.invictusslayer.slayersbeasts.init.ModStructures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SlayersBeasts.MOD_ID)
public class SlayersBeasts
{
    public static final String MOD_ID = "slayersbeasts";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SlayersBeasts()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntities.register(eventBus);
        ModSounds.register(eventBus);
        ModEffects.register(eventBus);
        ModStructures.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CAJOLE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CAJOLE_SAPLING.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CAJOLE_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CAJOLE_TRAPDOOR.get(), RenderType.cutout());
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
