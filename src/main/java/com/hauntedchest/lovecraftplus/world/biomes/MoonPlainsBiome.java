package com.hauntedchest.lovecraftplus.world.biomes;

import com.hauntedchest.lovecraftplus.registries.BlockHandler;
import com.hauntedchest.lovecraftplus.registries.EntityTypeHandler;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class MoonPlainsBiome extends Biome {
    public MoonPlainsBiome() {
        super(new Builder()
                .scale(1f)
                .temperature(0f)
                .waterColor(0xfffff5)
                .waterFogColor(0xfffff5)
                .surfaceBuilder(SurfaceBuilder.DEFAULT,
                        new SurfaceBuilderConfig(
                                BlockHandler.MOON_BLOCK.get().getDefaultState(),
                                BlockHandler.MOONSTONE.get().getDefaultState(),
                                BlockHandler.MOON_BLOCK.get().getDefaultState()))
                .category(Biome.Category.PLAINS)
                .downfall(0.0001f)
                .depth(0.125f)
                .parent(null)
                .precipitation(Biome.RainType.RAIN));

        addSpawn(EntityClassification.CREATURE, new SpawnListEntry(
                EntityType.COW, 8, 4, 4));

        addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(
                EntityType.ENDERMAN, 10, 1, 4));

        DefaultBiomeFeatures.addCarvers(this);
    }
}
