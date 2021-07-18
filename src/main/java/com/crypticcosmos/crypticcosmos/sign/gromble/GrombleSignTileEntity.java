package com.crypticcosmos.crypticcosmos.sign.gromble;

import com.crypticcosmos.crypticcosmos.register.SignRegistry;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nonnull;

public class GrombleSignTileEntity extends SignTileEntity {
    @Nonnull
    @Override
    public TileEntityType<GrombleSignTileEntity> getType() {
        return SignRegistry.GROMBLE_SIGN.get();
    }
}
