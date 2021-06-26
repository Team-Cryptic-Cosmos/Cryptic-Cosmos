package com.crypticcosmos.crypticcosmos.register;

import com.crypticcosmos.crypticcosmos.CrypticCosmos;
import com.crypticcosmos.crypticcosmos.entity.creature.gromble_frog.GrombleFrogEntity;
import com.crypticcosmos.crypticcosmos.entity.creature.gromble_frog.GrombleFrogRender;
import com.crypticcosmos.crypticcosmos.entity.creature.gromble_snatcher.GrombleSnatcherEntity;
import com.crypticcosmos.crypticcosmos.entity.creature.gromble_snatcher.GrombleSnatcherRender;
import com.crypticcosmos.crypticcosmos.entity.creature.makrossa_rambler.MakrossaRamblerEntity;
import com.crypticcosmos.crypticcosmos.entity.creature.makrossa_rambler.MakrossaRamblerRender;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.RandomChanceWithLooting;
import net.minecraft.world.gen.Heightmap;

import static com.crypticcosmos.crypticcosmos.CrypticCosmos.getRegistrate;

@SuppressWarnings("unused")
public class EntityTypeRegistries {
    public static final EntityEntry<MakrossaRamblerEntity> MAKROSSA_RAMBLER = getRegistrate().object("makrossa_rambler")
            .entity(MakrossaRamblerEntity::new, EntityClassification.MONSTER)
            .attributes(MakrossaRamblerEntity::setCustomAttributes)
            .renderer(() -> MakrossaRamblerRender::new)
            .spawnPlacement(PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules)
            .properties(builder -> builder.sized(3f, 2f))

            .loot((lootTables, entity) -> lootTables.add(entity,
                    LootTable.lootTable().withPool(LootPool.lootPool()
                            .setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(ItemRegistries.CRATERED_BONE.get()))
                            .when(RandomChanceWithLooting.randomChanceAndLootingBoost(0.5f, 0.07f)))
            ))

            .spawnEgg(0x65616a, 0x1f1d30).build()
            .register();

    @SuppressWarnings("deprecation")
    public static final EntityEntry<GrombleFrogEntity> GROMBLE_FROG = getRegistrate().object("gromble_frog")
            .entity(GrombleFrogEntity::new, EntityClassification.CREATURE)
            .attributes(GrombleFrogEntity::setCustomAttributes)
            .renderer(() -> GrombleFrogRender::new)
            .spawnPlacement(PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules)
            .properties(builder -> builder.sized(1f, 1f))
            .loot((lootTables, entity) -> lootTables.add(entity, LootTable.lootTable()))
            .spawnEgg(0xc26d7d, 0x9e427e).build()
            .register();

    public static final EntityEntry<GrombleSnatcherEntity> GROMBLE_SNATCHER = getRegistrate().object("gromble_snatcher")
            .entity(GrombleSnatcherEntity::new, EntityClassification.CREATURE)
            .attributes(GrombleSnatcherEntity::setCustomAttributes)
            .renderer(() -> GrombleSnatcherRender::new)
            .spawnPlacement(PlacementType.IN_WATER, Heightmap.Type.WORLD_SURFACE, MonsterEntity::checkMonsterSpawnRules)
            .properties(builder -> builder.sized(1.7f, 1.7f))
            .loot((lootTables, entity) -> lootTables.add(entity, LootTable.lootTable()))
            .spawnEgg(0xa6427b, 0x91524c).build()
            .register();

    public static void init() {
        CrypticCosmos.LOGGER.info("EntityTypeRegistries initialized");
    }
}