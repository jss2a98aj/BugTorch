package jss.bugtorch.mixins.minecraft.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

@Mixin(value = BlockPumpkin.class)
public class MixinBlockPumpkin extends BlockDirectional {

    protected MixinBlockPumpkin(Material material) {
        super(material);
    }

    /**
     * @author jss2a98aj
     * @reason Allows pumpkins to be placed without a solid block below them
     */
    @Overwrite()
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return world.getBlock(x, y, z).isReplaceable(world, x, y, z);
    }

}
