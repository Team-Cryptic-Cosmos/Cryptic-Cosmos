package com.crypticcosmos.crypticcosmos.blocks;

import com.crypticcosmos.crypticcosmos.registries.BlockRegistries;
import com.crypticcosmos.crypticcosmos.registries.EffectRegistries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class MondroveFungus extends BushBlock {
    public MondroveFungus() {
        super(Properties.create(Material.PLANTS)
                .hardnessAndResistance(0)
                .doesNotBlockMovement()
                .sound(SoundType.PLANT));
    }

    @Override
    public boolean isValidGround(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos) {
        Block block = state.getBlock();
        return block == BlockRegistries.LUNON.get() || block == BlockRegistries.OVERGROWN_LUNON.get();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEntityCollision(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, @Nonnull Entity entity) {
        if (!world.isRemote && entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;

            livingEntity.addPotionEffect(new EffectInstance(EffectRegistries.CORRUPTION.get(), Integer.MAX_VALUE));
        }
    }
}