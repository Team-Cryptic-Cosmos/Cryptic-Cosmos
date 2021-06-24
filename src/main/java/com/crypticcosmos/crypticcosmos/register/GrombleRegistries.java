package com.crypticcosmos.crypticcosmos.register;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import com.crypticcosmos.crypticcosmos.block.LunaraPlantableBush;
import com.crypticcosmos.crypticcosmos.block.LunaraPlantableSapling;
import com.crypticcosmos.crypticcosmos.block.RottenGrombleBerryBlock;
import com.crypticcosmos.crypticcosmos.util.RegistrationUtils;
import com.crypticcosmos.crypticcosmos.world.feature.GrombleTree;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.*;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

import static com.crypticcosmos.crypticcosmos.CrypticCosmos.getRegistrate;
import static com.crypticcosmos.crypticcosmos.register.ItemRegistries.GROMBLE_BERRY;
import static com.crypticcosmos.crypticcosmos.register.ItemRegistries.ROTTEN_GROMBLE_BERRY;
import static com.tterrag.registrate.util.DataIngredient.items;
import static net.minecraft.block.material.Material.GRASS;
import static net.minecraft.block.material.Material.NETHER_WOOD;
import static net.minecraft.block.material.MaterialColor.TERRACOTTA_LIGHT_BLUE;
import static net.minecraft.data.RecipeProvider.*;
import static net.minecraft.data.loot.BlockLootTables.NORMAL_LEAVES_SAPLING_CHANCES;
import static net.minecraft.data.loot.BlockLootTables.createDoorTable;

@SuppressWarnings("unused")
public class GrombleRegistries {
    public static final Properties GROMBLE_PROPERTIES = Properties.of(NETHER_WOOD, TERRACOTTA_LIGHT_BLUE)
            .strength(2F)
            .sound(SoundType.STEM);

    //gromble blocks
    public static final BlockEntry<LunaraPlantableSapling> GROMBLE_SAPLING = getRegistrate().object("gromble_sapling")
            .block(p -> new LunaraPlantableSapling(new GrombleTree(), p))
            .properties(p -> Properties.copy(Blocks.BIRCH_SAPLING))
            .addLayer(() -> RenderType::cutout)
            .tag(BlockTags.SAPLINGS)
            .blockstate(RegistrationUtils::crossModel)

            .item()
            .model((context, provider) -> provider.generated(context,
                    provider.modLoc("block/" + provider.name(context)))
            )
            .tag(ItemTags.SAPLINGS).build()

            .register();
    public static final BlockEntry<LunaraPlantableBush> GIANT_GROMBLE_BERRY = getRegistrate().object("giant_gromble_berry")
            .block(LunaraPlantableBush::new)
            .properties(p -> p.strength(1.0F)
                    .sound(SoundType.SHROOMLIGHT)
                    .lightLevel(state -> 15)
                    .harvestTool(ToolType.HOE)
                    .requiresCorrectToolForDrops()
            )
            .loot((lootTables, block) -> RegistrationUtils.silkTouchFortune(lootTables, block, GROMBLE_BERRY, 2, 4))
            .recipe((context, provider) -> provider.square(DataIngredient.items(GROMBLE_BERRY), context, true))
            .tag(BlockTags.LEAVES)
            .simpleItem()
            .register();

    public static final BlockEntry<RottenGrombleBerryBlock> GIANT_ROTTEN_GROMBLE_BERRY = getRegistrate().object("giant_rotten_gromble_berry")
            .block(Material.LEAVES, RottenGrombleBerryBlock::new)
            .properties(GrombleRegistries::rottenBerryProperties)
            .loot((lootTables, block) -> RegistrationUtils.silkTouchFortune(lootTables, block, ROTTEN_GROMBLE_BERRY, 1, 4))
            .recipe((context, provider) -> provider.square(DataIngredient.items(ROTTEN_GROMBLE_BERRY), context, true))
            .tag(BlockTags.LEAVES)
            .simpleItem()
            .register();

    public static final BlockEntry<RottenGrombleBerryBlock> GROMBLE_SPROUT = getRegistrate().object("gromble_sprout")
            .block(Material.GRASS, RottenGrombleBerryBlock::new)
            .initialProperties(GRASS, TERRACOTTA_LIGHT_BLUE)
            .properties(GrombleRegistries::grombleStemProperties)
            .addLayer(() -> RenderType::cutout)
            .blockstate(RegistrationUtils::crossModel)

            .item()
            .model((context, provider) -> provider.generated(context,
                    provider.modLoc("block/" + provider.name(context)))
            )
            .build()
            .register();

    public static final BlockEntry<RottenGrombleBerryBlock> GROMBLE_STEM = getRegistrate().object("gromble_stem")
            .block(Material.GRASS, RottenGrombleBerryBlock::new)
            .initialProperties(GRASS, TERRACOTTA_LIGHT_BLUE)
            .properties(GrombleRegistries::grombleStemProperties)
            .addLayer(() -> RenderType::cutout)
            .blockstate(RegistrationUtils::crossModel)

            .item()
            .model((context, provider) -> provider.generated(context,
                    provider.modLoc("block/" + provider.name(context)))
            )
            .build()
            .register();

    public static final BlockEntry<LeavesBlock> GROMBLE_LEAVES = getRegistrate().object("gromble_leaves")
            .block(LeavesBlock::new)
            .initialProperties(Material.LEAVES, TERRACOTTA_LIGHT_BLUE)
            .properties(GrombleRegistries::grombleLeavesProperties)
            .addLayer(() -> RenderType::cutout)
            .loot((lootTables, block) -> lootTables.add(block, BlockLootTables.createLeavesDrops(
                    block, GROMBLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES
            )))
            .tag(BlockTags.LEAVES)
            .blockstate(RegistrationUtils::leavesModel)
            .item().tag(ItemTags.LEAVES).build()
            .register();

    public static final BlockEntry<RotatedPillarBlock> GROMBLE_LOG = getRegistrate().object("gromble_log")
            .block(p -> Blocks.log(MaterialColor.TERRACOTTA_LIGHT_BLUE, MaterialColor.PODZOL))
            .tag(BlockTags.LOGS, TagRegistries.GROMBLE_LOGS)
            .blockstate((context, provider) -> provider.logBlock(context.get()))
            .item().tag(ItemTags.LOGS, TagRegistries.GROMBLE_LOGS_ITEMS).build()
            .register();

    public static final BlockEntry<RotatedPillarBlock> STRIPPED_GROMBLE_LOG = getRegistrate().object("stripped_gromble_log")
            .block(p -> Blocks.log(MaterialColor.TERRACOTTA_LIGHT_BLUE, MaterialColor.TERRACOTTA_LIGHT_BLUE))
            .tag(TagRegistries.GROMBLE_LOGS)
            .blockstate((context, provider) -> provider.logBlock(context.get()))
            .item().tag(TagRegistries.GROMBLE_LOGS_ITEMS).build()
            .register();

    public static final BlockEntry<RotatedPillarBlock> GROMBLE_WOOD = getRegistrate().object("gromble_wood")
            .block(RotatedPillarBlock::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .recipe((context, provider) -> woodFromLogs(provider, context.get(), GROMBLE_LOG.get()))
            .tag(TagRegistries.GROMBLE_LOGS)
            .blockstate((context, provider) -> provider.getVariantBuilder(context.get())
                    .forAllStates(state -> ConfiguredModel.builder().modelFile(
                            provider.models().cubeAll(context.getName(),
                                    provider.blockTexture(GROMBLE_LOG.get()))
                            ).build()
                    ))
            .item().tag(TagRegistries.GROMBLE_LOGS_ITEMS).build()
            .register();

    public static final BlockEntry<RotatedPillarBlock> STRIPPED_GROMBLE_WOOD = getRegistrate().object("stripped_gromble_wood")
            .block(RotatedPillarBlock::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .tag(TagRegistries.GROMBLE_LOGS)
            .blockstate((context, provider) -> provider.getVariantBuilder(context.get())
                    .forAllStates(state -> ConfiguredModel.builder().modelFile(
                            provider.models().cubeAll(context.getName(),
                                    provider.blockTexture(STRIPPED_GROMBLE_LOG.get()))
                            ).build()
                    ))
            .item().tag(TagRegistries.GROMBLE_LOGS_ITEMS).build()
            .register();

    public static final BlockEntry<Block> GROMBLE_PLANKS = getRegistrate().object("gromble_planks")
            .block(Block::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .recipe((context, provider) -> provider.planks(DataIngredient.tag(TagRegistries.GROMBLE_LOGS_ITEMS), context))
            .tag(BlockTags.PLANKS)
            .item().tag(ItemTags.PLANKS).build()
            .register();

    public static final BlockEntry<SlabBlock> GROMBLE_SLAB = getRegistrate().object("gromble_slab")
            .block(SlabBlock::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .loot((lootTables, block) -> lootTables.add(block, BlockLootTables.createSlabItemTable(block)))
            .recipe((context, provider) -> provider.slab(items(GROMBLE_PLANKS), context, "wooden_slab", false))
            .tag(BlockTags.WOODEN_SLABS)
            .blockstate((context, provider) -> provider.slabBlock(
                    context.get(),
                    provider.blockTexture(GROMBLE_PLANKS.get()),
                    provider.blockTexture(GROMBLE_PLANKS.get())
            ))
            .item().tag(ItemTags.WOODEN_SLABS).build()
            .register();

    public static final BlockEntry<StairsBlock> GROMBLE_STAIRS = getRegistrate().object("gromble_stairs")
            .block(p -> new StairsBlock(() -> GROMBLE_PLANKS.get().defaultBlockState(), p))
            .properties(p -> GROMBLE_PROPERTIES)
            .recipe((context, provider) ->
                    provider.stairs(items(GROMBLE_PLANKS), context, "wooden_stairs", false)
            )
            .tag(BlockTags.WOODEN_STAIRS)
            .blockstate((context, provider) -> provider.stairsBlock(
                    context.get(),
                    provider.blockTexture(GROMBLE_PLANKS.get())
            ))
            .item().tag(ItemTags.WOODEN_STAIRS).build()
            .register();

    public static final BlockEntry<DoorBlock> GROMBLE_DOOR = getRegistrate().object("gromble_door")
            .block(DoorBlock::new)
            .properties(p -> GROMBLE_PROPERTIES.strength(3f).noOcclusion())
            .addLayer(() -> RenderType::cutout)
            .loot((lootTables, block) -> lootTables.add(block, createDoorTable(block)))
            .recipe((context, provider) -> provider.door(items(GROMBLE_PLANKS), context, "wooden_door"))
            .tag(BlockTags.WOODEN_DOORS)
            .blockstate(RegistrationUtils::doorModel)

            .item()
            .model((context, provider) -> provider.generated(context))
            .tag(ItemTags.WOODEN_DOORS).build()

            .register();

    public static final BlockEntry<TrapDoorBlock> GROMBLE_TRAPDOOR = getRegistrate().object("gromble_trapdoor")
            .block(TrapDoorBlock::new)
            .properties(p -> Properties.copy(GROMBLE_DOOR.get()))
            .recipe((context, provider) -> provider.trapDoor(items(GROMBLE_PLANKS), context, null))
            .tag(BlockTags.WOODEN_TRAPDOORS)
            .blockstate((context, provider) -> provider.trapdoorBlock(context.get(), provider.blockTexture(context.get()), true))

            .item()
            .model((context, provider) -> provider.blockItem(context::getEntry, "_bottom"))
            .tag(ItemTags.WOODEN_TRAPDOORS)
            .build()

            .register();

    public static final BlockEntry<WoodButtonBlock> GROMBLE_BUTTON = getRegistrate().object("gromble_button")
            .block(WoodButtonBlock::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .tag(BlockTags.WOODEN_BUTTONS)
            .recipe((context, provider) -> woodenButton(provider, context.get(), GROMBLE_PLANKS.get()))
            .blockstate((context, provider) -> RegistrationUtils.buttonModel(context, provider, GROMBLE_PLANKS))

            .item()
            .model((context, provider) -> provider.blockItem(() -> context.get().getBlock(), "_inventory"))
            .tag(ItemTags.WOODEN_BUTTONS)
            .build()

            .register();

    public static final BlockEntry<PressurePlateBlock> GROMBLE_PRESSURE_PLATE = getRegistrate().object("gromble_pressure_plate")
            .block(p -> new PressurePlateBlock(Sensitivity.EVERYTHING, p))
            .properties(p -> GROMBLE_PROPERTIES)
            .recipe((context, provider) -> woodenPressurePlate(provider, context.get(), GROMBLE_PLANKS.get()))
            .tag(BlockTags.WOODEN_PRESSURE_PLATES)
            .blockstate((context, provider) -> RegistrationUtils.pressurePlateModels(context, provider, GROMBLE_PLANKS))
            .item().tag(ItemTags.WOODEN_PRESSURE_PLATES).build()
            .register();

    public static final BlockEntry<FenceBlock> GROMBLE_FENCE = getRegistrate().object("gromble_fence")
            .block(FenceBlock::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .recipe((context, provider) -> provider.fence(items(GROMBLE_PLANKS), context, "wooden_fence"))
            .tag(BlockTags.WOODEN_FENCES)
            .blockstate((context, provider) -> provider.fenceBlock(context.get(), provider.blockTexture(GROMBLE_PLANKS.get())))

            .item().tag(ItemTags.WOODEN_FENCES)
            .model((context, provider) -> provider.fenceInventory(context.getId().getPath(),
                    provider.modLoc("block/" + provider.name(GROMBLE_PLANKS))))
            .build()

            .register();

    public static final BlockEntry<FenceGateBlock> GROMBLE_FENCE_GATE = getRegistrate().object("gromble_fence_gate")
            .block(FenceGateBlock::new)
            .properties(p -> GROMBLE_PROPERTIES)
            .recipe((context, provider) -> provider.fenceGate(items(GROMBLE_PLANKS), context, "wooden_fence_gate"))
            .tag(BlockTags.FENCE_GATES)
            .blockstate((context, provider) -> provider.fenceGateBlock(context.get(), provider.blockTexture(GROMBLE_PLANKS.get())))
            .simpleItem()
            .register();

    public static final ItemEntry<BoatItem> GROMBLE_BOAT = getRegistrate().object("gromble_boat")
            .item(p -> new BoatItem(BoatEntity.Type.OAK, p))
            .recipe((context, provider) ->
                    woodenBoat(provider, context.get(), GROMBLE_PLANKS.get())
            )
            .tag(ItemTags.BOATS)
            .register();

    @Nonnull
    private static Properties rottenBerryProperties(Properties p) {
        return p.strength(0.25F)
                .sound(SoundType.SHROOMLIGHT)
                .lightLevel((p_235439_0_) -> 7)
                .harvestLevel(0)
                .harvestTool(ToolType.HOE)
                .requiresCorrectToolForDrops();
    }

    @Nonnull
    private static Properties grombleLeavesProperties(Properties p) {
        return p
                .strength(0.2F)
                .randomTicks()
                .sound(SoundType.GRASS)
                .noOcclusion();
    }

    @Nonnull
    private static Properties grombleStemProperties(Properties p) {
        return p.strength(0.3F)
                .noCollission()
                .sound(SoundType.GRASS);
    }

    public static void init() {
        CrypticCosmos.LOGGER.info("GrombleRegistries initialized");
    }
}
