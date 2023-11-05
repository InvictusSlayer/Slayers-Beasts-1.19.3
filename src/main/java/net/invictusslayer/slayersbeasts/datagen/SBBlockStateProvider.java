package net.invictusslayer.slayersbeasts.datagen;

import net.invictusslayer.slayersbeasts.SlayersBeasts;
import net.invictusslayer.slayersbeasts.block.DepletedCryptalithBlock;
import net.invictusslayer.slayersbeasts.block.InfusedCryptalithBlock;
import net.invictusslayer.slayersbeasts.block.SBBlockFamily;
import net.invictusslayer.slayersbeasts.block.SBBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Objects;

public class SBBlockStateProvider extends BlockStateProvider {
	public SBBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, SlayersBeasts.MOD_ID, existingFileHelper);
	}

	protected void registerStatesAndModels() {
		generateBlockFamilies();

		verticalPortal(SBBlocks.SEPULCHRA_PORTAL.get());
		horizontalPortal(SBBlocks.CRYPT_PORTAL.get());
		cubeRandomWithItem(SBBlocks.CRYPTALITH.get(), false);
		infusedCryptalith();
		depletedCryptalith();
		simpleCubeWithItem(SBBlocks.JADE_BLOCK.get());
		simpleCubeWithItem(SBBlocks.EXOSKELETON_ORE.get());
		simpleCubeWithItem(SBBlocks.DEEPSLATE_EXOSKELETON_ORE.get());
		simpleCubeBottomTopWithItem(SBBlocks.STYPHIUM.get());
		simpleCubeBottomTopWithItem(SBBlocks.DEEPSLATE_STYPHIUM.get());

		cubeRandomWithItem(SBBlocks.RUDOSOL.get(), true);
		cubeRandomWithItem(SBBlocks.ARIDISOL.get(), true);
		simpleCubeBottomTopWithItem(SBBlocks.ANTHILL.get());
		simpleCubeBottomTopWithItem(SBBlocks.ANTHILL_HATCHERY.get());
		simpleCubeBottomTopWithItem(SBBlocks.OOTHECA.get());

		simpleCubeWithItem(SBBlocks.GLEAMING_ICE.get());
		dripstone(SBBlocks.ICICLE.get());
		dripstone(SBBlocks.OBSIDIAN_SPIKE.get());
		doubleCrossBlock(SBBlocks.TALL_DEAD_BUSH.get());
		tiltCubeWithItem(SBBlocks.CRACKED_MUD.get());
		simpleCubeWithItem(SBBlocks.PEAT.get());
		flatRandomBlock(SBBlocks.ALGAE.get());

		cubeRandomWithItem(SBBlocks.BLACK_SAND.get(), true);
		cubeBottomTopWithItem(SBBlocks.BLACK_SANDSTONE.get(), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_side"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_bottom"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		slabWithItem(SBBlocks.BLACK_SANDSTONE_SLAB.get(), blockTexture(SBBlocks.BLACK_SANDSTONE.get()), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_side"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_bottom"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		stairWithItem(SBBlocks.BLACK_SANDSTONE_STAIRS.get(), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_side"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_bottom"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		wallWithItem(SBBlocks.BLACK_SANDSTONE_WALL.get(), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_side"));
		cubeWithItem(SBBlocks.SMOOTH_BLACK_SANDSTONE.get(), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		slabWithItem(SBBlocks.SMOOTH_BLACK_SANDSTONE_SLAB.get(), blockTexture(SBBlocks.BLACK_SANDSTONE.get()), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		stairWithItem(SBBlocks.SMOOTH_BLACK_SANDSTONE_STAIRS.get(), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		columnWithItem(SBBlocks.CUT_BLACK_SANDSTONE.get(), blockTexture(SBBlocks.CUT_BLACK_SANDSTONE.get()), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		slabWithItem(SBBlocks.CUT_BLACK_SANDSTONE_SLAB.get(), blockTexture(SBBlocks.CUT_BLACK_SANDSTONE.get()), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"), blockTexture(SBBlocks.CUT_BLACK_SANDSTONE.get()), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));
		columnWithItem(SBBlocks.CHISELED_BLACK_SANDSTONE.get(), blockTexture(SBBlocks.CHISELED_BLACK_SANDSTONE.get()), extend(blockTexture(SBBlocks.BLACK_SANDSTONE.get()), "_top"));

		plantWithPotted(SBBlocks.BLACK_MUSHROOM.get(), SBBlocks.POTTED_BLACK_MUSHROOM.get());
		mushroomBlockWithItem(SBBlocks.BLACK_MUSHROOM_BLOCK.get(),"mushroom_block_dark_inside");
		plantWithPotted(SBBlocks.WHITE_MUSHROOM.get(), SBBlocks.POTTED_WHITE_MUSHROOM.get());
		mushroomBlockWithItem(SBBlocks.WHITE_MUSHROOM_BLOCK.get(), null);
		thinMushroomStemWithItem(SBBlocks.THIN_MUSHROOM_STEM.get(), new ResourceLocation("minecraft:block/mushroom_block_inside"), new ResourceLocation("minecraft:block/mushroom_stem"));

		/* Wood Types */
		logWithItem(SBBlocks.ASPEN_LOG.get());
		logWithItem(SBBlocks.STRIPPED_ASPEN_LOG.get());
		woodWithItem(SBBlocks.ASPEN_WOOD.get(), blockTexture(SBBlocks.ASPEN_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_ASPEN_WOOD.get(), blockTexture(SBBlocks.STRIPPED_ASPEN_LOG.get()));
		simpleCubeWithItem(SBBlocks.ASPEN_LEAVES.get());
		plantWithPotted(SBBlocks.ASPEN_SAPLING.get(), SBBlocks.POTTED_ASPEN_SAPLING.get());
		hangingSign(SBBlocks.ASPEN_HANGING_SIGN.get(), SBBlocks.ASPEN_WALL_HANGING_SIGN.get(), blockTexture(SBBlocks.STRIPPED_ASPEN_LOG.get()));

		logWithItem(SBBlocks.CAJOLE_LOG.get());
		logWithItem(SBBlocks.STRIPPED_CAJOLE_LOG.get());
		woodWithItem(SBBlocks.CAJOLE_WOOD.get(), blockTexture(SBBlocks.CAJOLE_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_CAJOLE_WOOD.get(), blockTexture(SBBlocks.STRIPPED_CAJOLE_LOG.get()));
		simpleCubeWithItem(SBBlocks.CAJOLE_LEAVES.get());
		cross(SBBlocks.CAJOLE_SAPLING.get());

		logWithItem(SBBlocks.DESERT_OAK_LOG.get());
		logWithItem(SBBlocks.STRIPPED_DESERT_OAK_LOG.get());
		woodWithItem(SBBlocks.DESERT_OAK_WOOD.get(), blockTexture(SBBlocks.DESERT_OAK_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_DESERT_OAK_WOOD.get(), blockTexture(SBBlocks.STRIPPED_DESERT_OAK_LOG.get()));
		simpleCubeWithItem(SBBlocks.DESERT_OAK_LEAVES.get());
		plantWithPotted(SBBlocks.DESERT_OAK_SAPLING.get(), SBBlocks.POTTED_DESERT_OAK_SAPLING.get());
		hangingSign(SBBlocks.DESERT_OAK_HANGING_SIGN.get(), SBBlocks.DESERT_OAK_WALL_HANGING_SIGN.get(), blockTexture(SBBlocks.STRIPPED_DESERT_OAK_LOG.get()));

		logWithItem(SBBlocks.EUCALYPTUS_LOG.get());
		logWithItem(SBBlocks.STRIPPED_EUCALYPTUS_LOG.get());
		woodWithItem(SBBlocks.EUCALYPTUS_WOOD.get(), blockTexture(SBBlocks.EUCALYPTUS_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_EUCALYPTUS_WOOD.get(), blockTexture(SBBlocks.STRIPPED_EUCALYPTUS_LOG.get()));
		simpleCubeWithItem(SBBlocks.EUCALYPTUS_LEAVES.get());
		plantWithPotted(SBBlocks.EUCALYPTUS_SAPLING.get(), SBBlocks.POTTED_EUCALYPTUS_SAPLING.get());
		hangingSign(SBBlocks.EUCALYPTUS_HANGING_SIGN.get(), SBBlocks.EUCALYPTUS_WALL_HANGING_SIGN.get(), blockTexture(SBBlocks.STRIPPED_EUCALYPTUS_LOG.get()));

		logWithItem(SBBlocks.KAPOK_LOG.get());
		logWithItem(SBBlocks.STRIPPED_KAPOK_LOG.get());
		woodWithItem(SBBlocks.KAPOK_WOOD.get(), blockTexture(SBBlocks.KAPOK_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_KAPOK_WOOD.get(), blockTexture(SBBlocks.STRIPPED_KAPOK_LOG.get()));
		simpleCubeWithItem(SBBlocks.KAPOK_LEAVES.get());
		plantWithPotted(SBBlocks.KAPOK_SAPLING.get(), SBBlocks.POTTED_KAPOK_SAPLING.get());
		hangingSign(SBBlocks.KAPOK_HANGING_SIGN.get(), SBBlocks.KAPOK_WALL_HANGING_SIGN.get(), blockTexture(SBBlocks.STRIPPED_KAPOK_LOG.get()));

		logWithItem(SBBlocks.REDWOOD_LOG.get());
		logWithItem(SBBlocks.STRIPPED_REDWOOD_LOG.get());
		woodWithItem(SBBlocks.REDWOOD_WOOD.get(), blockTexture(SBBlocks.REDWOOD_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_REDWOOD_WOOD.get(), blockTexture(SBBlocks.STRIPPED_REDWOOD_LOG.get()));
		simpleCubeWithItem(SBBlocks.REDWOOD_LEAVES.get());
		plantWithPotted(SBBlocks.REDWOOD_SAPLING.get(), SBBlocks.POTTED_REDWOOD_SAPLING.get());
		hangingSign(SBBlocks.REDWOOD_HANGING_SIGN.get(), SBBlocks.REDWOOD_WALL_HANGING_SIGN.get(), blockTexture(SBBlocks.STRIPPED_REDWOOD_LOG.get()));

		logWithItem(SBBlocks.WILLOW_LOG.get());
		logWithItem(SBBlocks.STRIPPED_WILLOW_LOG.get());
		woodWithItem(SBBlocks.WILLOW_WOOD.get(), blockTexture(SBBlocks.WILLOW_LOG.get()));
		woodWithItem(SBBlocks.STRIPPED_WILLOW_WOOD.get(), blockTexture(SBBlocks.STRIPPED_WILLOW_LOG.get()));
		simpleCubeWithItem(SBBlocks.WILLOW_LEAVES.get());
		plantWithPotted(SBBlocks.WILLOW_SAPLING.get(), SBBlocks.POTTED_WILLOW_SAPLING.get());
		hangingSign(SBBlocks.WILLOW_HANGING_SIGN.get(), SBBlocks.WILLOW_WALL_HANGING_SIGN.get(), blockTexture(SBBlocks.STRIPPED_WILLOW_LOG.get()));
		cross(SBBlocks.WILLOW_BRANCH.get());
		cross(SBBlocks.WILLOW_BRANCH_PLANT.get());
	}

	private void generateBlockFamilies() {
		SBBlockFamily.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach(family -> {
			Block base = family.getBaseBlock();
			simpleCubeWithItem(base);
			family.getVariants().forEach((variant, block) -> {
				if (variant == BlockFamily.Variant.SLAB) {
					simpleSlabWithItem(block, blockTexture(base), blockTexture(base));
				} else if (variant == BlockFamily.Variant.STAIRS) {
					simpleStairWithItem(block, blockTexture(base));
				} else if (variant == BlockFamily.Variant.FENCE) {
					fenceWithItem(block, blockTexture(base));
				} else if (variant == BlockFamily.Variant.FENCE_GATE) {
					fenceGateWithItem(block, blockTexture(base));
				} else if (variant == BlockFamily.Variant.BUTTON) {
					buttonWithItem(block, blockTexture(base));
				} else if (variant == BlockFamily.Variant.PRESSURE_PLATE) {
					pressurePlateWithItem(block, blockTexture(base));
				} else if (variant == BlockFamily.Variant.DOOR) {
					doorBlockWithRenderType((DoorBlock) block, extend(blockTexture(block), "_bottom"), extend(blockTexture(block), "_top"), "cutout");
				} else if (variant == BlockFamily.Variant.TRAPDOOR) {
					trapdoorWithItem(block);
				} else if (variant == BlockFamily.Variant.WALL) {
					wallWithItem(block, blockTexture(base));
				} else if (variant == BlockFamily.Variant.SIGN) {
					signBlock((StandingSignBlock) block, (WallSignBlock) family.get(BlockFamily.Variant.WALL_SIGN), blockTexture(base));
				}
			});
		});
	}

	private void horizontalPortal(Block block) {
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(models().getBuilder(name(block))
				.texture("portal", blockTexture(block)).texture("particle", blockTexture(block))
				.element().from(0, 6, 0).to(16, 12, 16)
				.face(Direction.UP).texture("#portal").end().face(Direction.DOWN).texture("#portal").end()
				.end().renderType("solid")).build());
	}
	private void verticalPortal(Block block) {
		getVariantBuilder(block).forAllStates(state -> {
			boolean isX = state.getValue(BlockStateProperties.HORIZONTAL_AXIS) == Direction.Axis.X;
			return ConfiguredModel.builder().modelFile(models().getBuilder(name(block) + (isX ? "_ns" : "_ew"))
					.texture("portal", blockTexture(block)).texture("particle", blockTexture(block))
					.element().from(isX ? 0 : 6, 0, isX ? 6 : 0).to(isX ? 16 : 10, 16, isX ? 10 : 16)
					.face(isX ? Direction.NORTH : Direction.EAST).texture("#portal").end().face(isX ? Direction.SOUTH : Direction.WEST).texture("#portal").end()
					.end().renderType("translucent")).build();
		});
	}

	private void doubleCrossBlock(Block block) {
		getVariantBuilder(block).forAllStates(state -> {
			String suffix = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER ? "_bottom" : "_top";
			return ConfiguredModel.builder().modelFile(models().cross(name(block) + suffix, extend(blockTexture(block), suffix)).renderType("cutout")).build();
		});
	}

	private void infusedCryptalith() {
		Block block = SBBlocks.INFUSED_CRYPTALITH.get();
		getVariantBuilder(block).forAllStates(state -> {
			String suffix = state.getValue(BlockStateProperties.EYE) ? "_active" : "";
			return ConfiguredModel.builder().modelFile(models().cubeBottomTop(name(block) + suffix, extend(blockTexture(block),
							"_side"), blockTexture(SBBlocks.CRYPTALITH.get()), extend(blockTexture(block), "_top" + suffix)))
					.rotationY((int) state.getValue(InfusedCryptalithBlock.FACING).toYRot()).build();
		});
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_bottom_top"));
	}
	private void depletedCryptalith() {
		Block block = SBBlocks.DEPLETED_CRYPTALITH.get();
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder().modelFile(models().cubeBottomTop(name(block), extend(blockTexture(block), "_side"),
								blockTexture(SBBlocks.CRYPTALITH.get()), extend(blockTexture(block), "_top")))
						.rotationY((int) state.getValue(DepletedCryptalithBlock.FACING).toYRot()).build());
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_bottom_top"));
	}

	private void dripstone(Block block) {
		getVariantBuilder(block).forAllStates(state -> {
			String suffix = "_" + state.getValue(BlockStateProperties.DRIPSTONE_THICKNESS).getSerializedName() + "_" + state.getValue(BlockStateProperties.VERTICAL_DIRECTION).getSerializedName();
			return ConfiguredModel.builder().modelFile(models().cross(name(block) + suffix, extend(blockTexture(block), suffix)).renderType("cutout")).build();
		});
	}

	private void thinMushroomStemWithItem(Block block, ResourceLocation top, ResourceLocation side) {
		ModelFile model = models().getBuilder(name(block)).texture("top", top).texture("side", side).element().from(4, 0, 4).to(12, 16, 12)
				.allFaces((direction, builder) -> builder.texture(direction.getAxis() == Direction.Axis.Y ? "#top" : "#side")).end();
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
		simpleBlockItem(block, model);
	}

	private void plantWithPotted(Block plant, Block potted) {
		cross(plant);
		simpleBlock(potted, models().singleTexture(name(potted), new ResourceLocation("flower_pot_cross"), "plant", blockTexture(plant)).renderType("cutout"));
	}

	private void mushroomBlockWithItem(Block block, @Nullable String inside) {
		ModelFile outsideModel = models().withExistingParent(name(block), "minecraft:block/template_single_face").texture("texture", blockTexture(block));
		ModelFile insideModel = inside != null ? models().withExistingParent(inside, "minecraft:block/template_single_face").texture("texture", new ResourceLocation(SlayersBeasts.MOD_ID, "block/" + inside)) :
				models().getExistingFile(new ResourceLocation("minecraft:block/mushroom_block_inside"));
		getMultipartBuilder(block).part().modelFile(outsideModel).addModel().condition(BlockStateProperties.NORTH, true).end()
				.part().modelFile(outsideModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.EAST, true).end()
				.part().modelFile(outsideModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true).end()
				.part().modelFile(outsideModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.WEST, true).end()
				.part().modelFile(outsideModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, true).end()
				.part().modelFile(outsideModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.DOWN, true).end()
				.part().modelFile(insideModel).addModel().condition(BlockStateProperties.NORTH, false).end()
				.part().modelFile(insideModel).rotationY(90).uvLock(false).addModel().condition(BlockStateProperties.EAST, false).end()
				.part().modelFile(insideModel).rotationY(180).uvLock(false).addModel().condition(BlockStateProperties.SOUTH, false).end()
				.part().modelFile(insideModel).rotationY(270).uvLock(false).addModel().condition(BlockStateProperties.WEST, false).end()
				.part().modelFile(insideModel).rotationX(270).uvLock(false).addModel().condition(BlockStateProperties.UP, false).end()
				.part().modelFile(insideModel).rotationX(90).uvLock(false).addModel().condition(BlockStateProperties.DOWN, false);
		simpleBlockItem(block, models().withExistingParent(name(block) + "_inventory", "minecraft:block/cube_all").texture("all", blockTexture(block)));
	}

	private void logWithItem(Block block) {
		logBlock((RotatedPillarBlock) block);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_column"));
	}
	private void woodWithItem(Block block, ResourceLocation texture) {
		axisBlock((RotatedPillarBlock) block, texture, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_column"));
	}
	private void cross(Block block) {
		simpleBlock(block, models().cross(name(block), blockTexture(block)).renderType("cutout"));
	}
	private void simpleSlabWithItem(Block block, ResourceLocation full, ResourceLocation texture) {
		slabBlock((SlabBlock) block, full, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/slab"));
	}
	private void slabWithItem(Block block, ResourceLocation full, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		slabBlock((SlabBlock) block, full, side, bottom, top);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/slab"));
	}
	private void simpleStairWithItem(Block block, ResourceLocation texture) {
		stairsBlock((StairBlock) block, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/stairs"));
	}
	private void stairWithItem(Block block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		stairsBlock((StairBlock) block, side, bottom, top);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/stairs"));
	}
	private void fenceWithItem(Block block, ResourceLocation texture) {
		fenceBlock((FenceBlock) block, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/fence_inventory").texture("texture", texture));
	}
	private void fenceGateWithItem(Block block, ResourceLocation texture) {
		fenceGateBlock((FenceGateBlock) block, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/template_fence_gate"));
	}
	private void wallWithItem(Block block, ResourceLocation texture) {
		wallBlock((WallBlock) block, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/wall_inventory").texture("wall", texture));
	}
	private void buttonWithItem(Block block, ResourceLocation texture) {
		buttonBlock((ButtonBlock) block, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/button_inventory"));
	}
	private void pressurePlateWithItem(Block block, ResourceLocation texture) {
		pressurePlateBlock((PressurePlateBlock) block, texture);
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/pressure_plate_up"));
	}
	private void trapdoorWithItem(Block block) {
		trapdoorBlockWithRenderType((TrapDoorBlock) block, blockTexture(block), true, "cutout");
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/template_trapdoor_bottom").texture("texture", blockTexture(block)));
	}
	private void hangingSign(Block sign, Block wallSign, ResourceLocation texture) {
		ModelFile model = models().sign(name(sign), texture);
		simpleBlock(sign, model);
		simpleBlock(wallSign, model);
	}

	private void cubeRandomWithItem(Block block, boolean onlyTop) {
		ModelFile model = models().cubeAll(name(block), blockTexture(block));
		getVariantBuilder(block).forAllStates(state -> onlyTop ? ConfiguredModel.allYRotations(model, 0, false) : ConfiguredModel.allRotations(model, false));
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_all"));
	}

	private void simpleCubeWithItem(Block block) {
		cubeWithItem(block, blockTexture(block));
	}
	private void cubeWithItem(Block block, ResourceLocation texture) {
		simpleBlock(block, models().cubeAll(name(block), texture));
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_all"));
	}

	private void columnWithItem(Block block, ResourceLocation side, ResourceLocation end) {
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(models().cubeColumn(name(block), side, end)).build());
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_column"));
	}

	private void tiltCubeWithItem(Block block) {
		getVariantBuilder(block).forAllStates(state -> {
			String tilt = "_" + state.getValue(BlockStateProperties.TILT).getSerializedName();
			return ConfiguredModel.builder().modelFile(models().cubeAll(name(block) + tilt, extend(blockTexture(block), tilt))).build();
		});
		simpleBlockItem(block, models().withExistingParent(name(block) + "_none", "minecraft:block/cube_all"));
	}

	private void flatRandomBlock(Block block) {
		ModelFile model = models().getBuilder(name(block)).ao(false)
				.texture("texture", blockTexture(block)).texture("particle", blockTexture(block))
				.element().from(0, 0.25F, 0).to(16, 0.25F, 16)
				.face(Direction.UP).texture("#texture").end().face(Direction.DOWN).texture("#texture").end()
				.end().renderType("cutout");
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
	}

	private void simpleCubeBottomTopWithItem(Block block) {
		cubeBottomTopWithItem(block, extend(blockTexture(block), "_side"), extend(blockTexture(block), "_bottom"), extend(blockTexture(block), "_top"));
	}
	private void cubeBottomTopWithItem(Block block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
				.modelFile(models().cubeBottomTop(name(block), side, bottom, top)).build());
		simpleBlockItem(block, models().withExistingParent(name(block), "minecraft:block/cube_bottom_top"));
	}

	private String name(Block block) {
		return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
	}

	private ResourceLocation extend(ResourceLocation location, String suffix) {
		return new ResourceLocation(location.getNamespace(), location.getPath() + suffix);
	}
}
