package com.github.hauntedchest.lovecraftplus.client;

import com.github.hauntedchest.lovecraftplus.LovecraftPlus;
import com.github.hauntedchest.lovecraftplus.registries.DimensionRegistries;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LovecraftPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventBusSubscriber {
    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event) {
        if (DimensionType.byName(DimensionRegistries.MOON_DIM_TYPE) == null) {
            DimensionManager.registerDimension(
                    DimensionRegistries.MOON_DIM_TYPE,
                    DimensionRegistries.MOON_DIM.get(),
                    null,
                    true
            );

            LovecraftPlus.LOGGER.debug("Dimensions registered to DimensionManager!");
        }
    }
}
