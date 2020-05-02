package jss.bugtorch.mixins.blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

@Mixin(BlockPumpkin.class)
public class MixinBlockPumpkin extends BlockDirectional {

    protected MixinBlockPumpkin(Material material) {
        super(material);
    }

    @Overwrite
    @Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return worldIn.getBlock(x, y, z).isReplaceable(worldIn, x, y, z);
    }

}
