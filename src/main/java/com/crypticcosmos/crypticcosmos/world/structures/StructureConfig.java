package com.crypticcosmos.crypticcosmos.world.structures;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import com.crypticcosmos.crypticcosmos.register.ConfiguredStructureRegistries;
import com.crypticcosmos.crypticcosmos.register.StructureRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.world.biome.Biome.Category.NETHER;
import static net.minecraft.world.biome.Biome.Category.THEEND;

public class StructureConfig {
    public static void addCustomStructures(final BiomeLoadingEvent event) {
        /*
         * Add our structure to all biomes including other modded biomes.
         * You can skip or add only to certain biomes based on stuff like biome category,
         * temperature, scale, precipitation, mod id, etc. All kinds of options!
         *
         * You can even use the BiomeDictionary as well! To use BiomeDictionary, do
         * RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName()) to get the biome's
         * registrykey. Then that can be fed into the dictionary to get the biome's types.
         */

        /*
         * Prevent spawning in the end or nether.
         */
        if (!(event.getCategory() == NETHER || event.getCategory() == THEEND)) {
            event.getGeneration().getStructures().add(() -> ConfiguredStructureRegistries.CONFIGURED_MONDROVE_BUNDLE);
        }
    }

    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        IWorld world = event.getWorld();
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            /*
             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunk generator.
             * They will handle your structure spacing for your if you add to WorldGenRegistries.NOISE_GENERATOR_SETTINGS in your structure's registration.
             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
             */
            try {
                final Object codec = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "codec")
                        .invoke(serverWorld.getChunkSource().generator);

                if (codec instanceof Codec) {
                    //noinspection unchecked
                    final Codec<? extends ChunkGenerator> chunkGeneratorCodec = (Codec<? extends ChunkGenerator>) codec;

                    ResourceLocation chunkGeneratorKey = Registry.CHUNK_GENERATOR.getKey(chunkGeneratorCodec);
                    if (chunkGeneratorKey != null && chunkGeneratorKey.getNamespace().equals("terraforged")) return;
                }
            } catch (Exception e) {
                CrypticCosmos.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                serverWorld.dimension().equals(World.OVERWORLD)) return;

            /*
             * putIfAbsent so people can override the spacing with dimension data packs themselves if they wish to customize spacing more precisely per dimension.
             *
             * NOTE: if you add per-dimension spacing configs, you can't use putIfAbsent as WorldGenRegistries.NOISE_GENERATOR_SETTINGS in FMLCommonSetupEvent
             * already added your default structure spacing to some dimensions. You would need to override the spacing with .put(...)
             * And if you want to do dimension blacklisting, you need to remove the spacing entry entirely from the map below to prevent generation safely.
             */
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            tempMap.putIfAbsent(StructureRegistries.MONDROVE_BUNDLE.get(), DimensionStructuresSettings.DEFAULTS.get(StructureRegistries.MONDROVE_BUNDLE.get()));
            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}