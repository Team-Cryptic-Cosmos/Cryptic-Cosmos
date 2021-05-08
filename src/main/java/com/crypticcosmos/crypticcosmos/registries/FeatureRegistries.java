package com.crypticcosmos.crypticcosmos.registries;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import static com.crypticcosmos.crypticcosmos.registries.BlockRegistries.MONDROVE_LEAVES;
import static com.crypticcosmos.crypticcosmos.registries.BlockRegistries.MONDROVE_LOG;

public class FeatureRegistries {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MONDROVE_TREE = registerFeature("mondrove",
            Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(MONDROVE_LOG.get().defaultBlockState()),
                    new SimpleBlockStateProvider(MONDROVE_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(FeatureSpread.fixed(2),
                            FeatureSpread.fixed(0), 3),
                    new StraightTrunkPlacer(4, 2, 0),
                    new TwoLayerFeature(1, 0, 1))
                    .ignoreVines()
                    .build()
            ));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> OSMINSTEM_TREE = registerFeature("osminstem",
            Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(BlockRegistries.OSMINSTEM_LOG.get().defaultBlockState()),
                    new SimpleBlockStateProvider(BlockRegistries.OSMINSTEM_CAP.get().defaultBlockState()),
                    new AcaciaFoliagePlacer(FeatureSpread.fixed(2),
                            FeatureSpread.fixed(0)),
                    new ForkyTrunkPlacer(5, 2, 2),
                    new TwoLayerFeature(1, 0, 2))
                    .ignoreVines()
                    .build()
            ));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GROMBLE_TREE = registerFeature("gromble_tree",
            Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(BlockRegistries.GROMBLE_LOG.get().defaultBlockState()),
                    new SimpleBlockStateProvider(BlockRegistries.GROMBLE_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(FeatureSpread.fixed(6),
                            FeatureSpread.fixed(3), 15),
                    new StraightTrunkPlacer(4, 8, 12),
                    new TwoLayerFeature(6, 12, 1))).ignoreVines().build()));


    public static final ConfiguredFeature<?, ?> MONDROVE_FUNGUS = registerFeature("mondrove_fungus",
            Feature.FLOWER.configured(new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider()
                    .add(BlockRegistries.MONDROVE_FUNGUS.get().defaultBlockState(), 2),
                    new SimpleBlockPlacer())
                    .tries(64)
                    .build()
            )
    );

    public static final ConfiguredFeature<?, ?> STINKY_OSMIN = registerFeature("stinky_osmin",
            Feature.FLOWER.configured(new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider()
                    .add(BlockRegistries.STINKY_OSMIN.get().defaultBlockState(), 2),
                    new SimpleBlockPlacer())
                    .tries(64)
                    .build()
            )
    );

    public static final ConfiguredFeature<?, ?> GIANT_GROMBLE_BERRY_PATCH = registerFeature("giant_gromble_berry_patch",
            Feature.FLOWER.configured(new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider()
                    .add(BlockRegistries.GIANT_GROMBLE_BERRY.get().defaultBlockState(), 2),
                    new SimpleBlockPlacer())
                    .tries(64)
                    .build()
            )
    );


    private static <T extends IFeatureConfig> ConfiguredFeature<T, ?> registerFeature(String path, ConfiguredFeature<T, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, CrypticCosmos.id(path), feature);
    }
}