package com.github.hauntedchest.lovecraftplus.world.biomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

@SuppressWarnings("NullableProblems")
public class MoonPlainsBiome extends Biome {
    public MoonPlainsBiome(Biome.Builder builder) {
        super(builder);

        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addTaigaRocks(this);
        DefaultBiomeFeatures.addDeadBushes(this);
    }

    @Override
    public void addSpawn(EntityClassification type, SpawnListEntry spawnListEntry) {
        super.addSpawn(type, spawnListEntry);
    }
}
