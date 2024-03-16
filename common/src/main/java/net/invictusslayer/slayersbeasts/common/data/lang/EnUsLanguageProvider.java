package net.invictusslayer.slayersbeasts.common.data.lang;

import net.invictusslayer.slayersbeasts.common.block.SBBlockFamily;
import net.invictusslayer.slayersbeasts.common.block.WoodFamily;
import net.invictusslayer.slayersbeasts.common.init.*;
import net.minecraft.data.PackOutput;

public class EnUsLanguageProvider extends SBLanguageProvider {
	public EnUsLanguageProvider(PackOutput output) {
		super(output, "en_us");
	}

	protected void addTranslations() {
		addItem(SBItems.MUSIC_DISC_INKISH, "Music Disc");
		addItemDesc(SBItems.MUSIC_DISC_INKISH, "Sam Newham - inkish");

		addItem(SBItems.JADE, "Jade");
		addItem(SBItems.JADE_SHARD, "Jade Shard");
		addItem(SBItems.CRYSTALLINE_WING, "Crystalline Wing");
		addItem(SBItems.CRYSTALLINE_CLAW, "Crystalline Claw");
		addItem(SBItems.CRYSTALLINE_CARAPACE, "Crystalline Carapace");
		addItem(SBItems.INSECT_WING, "Insect Wing");
		addItem(SBItems.INSECT_CLAW, "Insect Claw");
		addItem(SBItems.INSECT_EYE, "Insect Eye");
		addItem(SBItems.INSECT_LEG, "Insect Leg");
		addItem(SBItems.WITHERBONE, "Witherbone");
		addItem(SBItems.TIED_LEATHER, "Tied Leather");
		addItem(SBItems.TANNED_LEATHER, "Tanned Leather");
		addItem(SBItems.MUD_BALL, "Mud Ball");

		addItem(SBItems.MANTIS_SPAWN_EGG, "Mantis Spawn Egg");
		addItem(SBItems.ANT_WORKER_SPAWN_EGG, "Worker Ant Spawn Egg");
		addItem(SBItems.ANT_SOLDIER_SPAWN_EGG, "Soldier Ant Spawn Egg");
		addItem(SBItems.ANT_QUEEN_SPAWN_EGG, "Queen Ant Spawn Egg");
		addItem(SBItems.WITHER_SPIDER_SPAWN_EGG, "Wither Spider Spawn Egg");
		addItem(SBItems.TYRACHNID_SPAWN_EGG, "Tyrachnid Spawn Egg");
		addItem(SBItems.DAMSELFLY_SPAWN_EGG, "Damselfly Spawn Egg");
		addItem(SBItems.ENT_SPAWN_EGG, "Ent Spawn Egg");
		addItem(SBItems.WUDU_SPAWN_EGG, "Wudu Spawn Egg");
		addItem(SBItems.SPORETRAP_SPAWN_EGG, "Sporetrap Spawn Egg");
		
		add("item.minecraft.potion.effect.paralysis_potion", "Potion of Paralysis");
		add("item.minecraft.splash_potion.effect.paralysis_potion", "Splash Potion of Paralysis");
		add("item.minecraft.lingering_potion.effect.paralysis_potion", "Lingering Potion of Paralysis");
		add("item.minecraft.tipped_arrow.effect.paralysis_potion", "Arrow of Paralysis");
		add("item.minecraft.potion.effect.wither_potion", "Potion of Decay");
		add("item.minecraft.splash_potion.effect.wither_potion", "Splash Potion of Decay");
		add("item.minecraft.lingering_potion.effect.wither_potion", "Lingering Potion of Decay");
		add("item.minecraft.tipped_arrow.effect.wither_potion", "Arrow of Decay");
		
		addBlock(SBBlocks.CRYPTALITH, "Cryptalith");
		addBlock(SBBlocks.INFUSED_CRYPTALITH, "Infused Cryptalith");
		addBlock(SBBlocks.DEPLETED_CRYPTALITH, "Depleted Cryptalith");
		
		addBlock(SBBlocks.JADE_BLOCK, "Jade Block");
		addBlock(SBBlocks.EXOSKELETON_ORE, "Buried Exoskeleton Ore");
		addBlock(SBBlocks.DEEPSLATE_EXOSKELETON_ORE, "Embedded Exoskeleton Ore");
		addBlock(SBBlocks.STYPHIUM, "Styphium");
		addBlock(SBBlocks.DEEPSLATE_STYPHIUM, "Deepslate Styphium");

		addBlock(SBBlocks.BLACK_SAND, "Black Sand");
		addBlockFamily(SBBlockFamily.BLACK_SANDSTONE, "Black Sandstone");
		addBlockFamily(SBBlockFamily.SMOOTH_BLACK_SANDSTONE, "Smooth Black Sandstone");
		addBlockFamily(SBBlockFamily.CUT_BLACK_SANDSTONE, "Cut Black Sandstone");
		
		addBlockFamily(SBBlockFamily.PEGMATITE, "Pegmatite");
		addBlockFamily(SBBlockFamily.POLISHED_PEGMATITE, "Polished Pegmatite");
		
		addBlock(SBBlocks.RUDOSOL, "Rudosol");
		addBlock(SBBlocks.ARIDISOL, "Aridisol");
		addBlock(SBBlocks.ANTHILL, "Anthill");
		addBlock(SBBlocks.ANTHILL_HATCHERY, "Anthill Hatchery");
		addBlock(SBBlocks.OOTHECA, "Ootheca");

		addBlock(SBBlocks.GLEAMING_ICE, "Gleaming Ice");
		addBlock(SBBlocks.ICICLE, "Icicle");
		addBlock(SBBlocks.OBSIDIAN_SPIKE, "Obsidian Spike");
		addBlock(SBBlocks.CRACKED_MUD, "Cracked Mud");
		addBlock(SBBlocks.PEAT, "Peat");
		addBlock(SBBlocks.ALGAE, "Algae");
		addBlock(SBBlocks.TALL_DEAD_BUSH, "Tall Dead Bush");

		addBlock(SBBlocks.BLACK_MUSHROOM, "Black Mushroom");
		addBlock(SBBlocks.BLACK_MUSHROOM_BLOCK, "Black Mushroom Block");
		addBlock(SBBlocks.WHITE_MUSHROOM, "White Mushroom");
		addBlock(SBBlocks.WHITE_MUSHROOM_BLOCK, "White Mushroom Block");
		addBlock(SBBlocks.THIN_MUSHROOM_STEM, "Thin Mushroom Stem");

		addWoodFamily(WoodFamily.ASPEN, "Aspen");
		addWoodFamily(WoodFamily.CAJOLE, "Cajole");
		addWoodFamily(WoodFamily.DESERT_OAK, "Desert Oak");
		addWoodFamily(WoodFamily.EUCALYPTUS, "Eucalyptus");
		addWoodFamily(WoodFamily.KAPOK, "Kapok");
		addWoodFamily(WoodFamily.REDWOOD, "Redwood");
		addWoodFamily(WoodFamily.WILLOW, "Willow");
		addBlock(SBBlocks.WILLOW_BRANCH, "Willow Branch");
		addBlock(SBBlocks.WILLOW_BRANCH_PLANT, "Willow Branch Plant");
		
		addEntityType(SBEntities.MANTIS, "Mantis");
		addEntityType(SBEntities.ANT_WORKER, "Worker Ant");
		addEntityType(SBEntities.ANT_SOLDIER, "Soldier Ant");
		addEntityType(SBEntities.ANT_QUEEN, "Queen Ant");
		addEntityType(SBEntities.WITHER_SPIDER, "Wither Spider");
		addEntityType(SBEntities.TYRACHNID, "Tyrachnid");
		addEntityType(SBEntities.DAMSELFLY, "Damselfly");
		addEntityType(SBEntities.ENT_MEDIUM, "Ent");
		addEntityType(SBEntities.WUDU, "Wudu");
		addEntityType(SBEntities.SPORETRAP, "Sporetrap");
		addEntityType(SBEntities.IRK, "Irk");
		
		add("creative_tab.slayers_tab", "Slayer's Beasts");

		addEffect(SBEffects.PARALYSIS, "Paralysis");

		addSound(SBSounds.MANTIS_AMBIENT, "Mantis chirps");
		addSound(SBSounds.MANTIS_DEATH, "Mantis dies");
		addSound(SBSounds.MANTIS_HURT, "Mantis hurts");
	}
}
