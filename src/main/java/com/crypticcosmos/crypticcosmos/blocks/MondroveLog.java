package com.crypticcosmos.crypticcosmos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Random;

public class MondroveLog extends RotatedPillarBlock implements Infectable {
    public MondroveLog() {
        super(Properties.copy(Blocks.BIRCH_LOG));

        this.registerDefaultState(this.defaultBlockState().setValue(INFECTION_LEVEL, 0));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random rand) {
        this.infect(world, pos);
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(INFECTION_LEVEL);
    }

    @Override
    public BlockState getStateForPlacement(@Nonnull BlockItemUseContext context) {
        //noinspection ConstantConditions
        return super.getStateForPlacement(context).setValue(INFECTION_LEVEL, 0);
    }

    @Override
    public boolean isRandomlyTicking(@Nonnull BlockState state) {
        return true;
    }
}
