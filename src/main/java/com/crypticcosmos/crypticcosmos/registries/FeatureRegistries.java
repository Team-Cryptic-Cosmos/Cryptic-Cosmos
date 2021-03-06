package com.crypticcosmos.crypticcosmos.registries;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class FeatureRegistries {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MONDROVE_TREE = registerFeature("mondrove",
            Feature.TREE.configured(new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(BlockRegistries.MONDROVE_LOG.get().defaultBlockState()),
                    new SimpleBlockStateProvider(BlockRegistries.MONDROVE_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(FeatureSpread.fixed(2),
                            FeatureSpread.fixed(0), 3),
                    new StraightTrunkPlacer(4, 2, 0),
                    new TwoLayerFeature(1, 0, 1))
                    .ignoreVines()
                    .build()
            ));

    public static final ConfiguredFeature<?, ?> MONDROVE_FUNGUS = registerFeature("mondrove_fungus",
            Feature.FLOWER.configured(new BlockClusterFeatureConfig.Builder(new WeightedBlockStateProvider()
                    .add(BlockRegistries.MONDROVE_FUNGUS.get().defaultBlockState(), 2),
                    new SimpleBlockPlacer())
                    .tries(64)
                    .build()
            )
    );

    private static <T extends IFeatureConfig> ConfiguredFeature<T, ?> registerFeature(String path, ConfiguredFeature<T, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, CrypticCosmos.id(path), feature);
    }
}