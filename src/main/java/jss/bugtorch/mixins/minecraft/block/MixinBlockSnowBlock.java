package jss.bugtorch.mixins.minecraft.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockSnowBlock;
import net.minecraft.world.World;

@Mixin(value = BlockSnowBlock.class)
public class MixinBlockSnowBlock {

    /**
     * @author jss2a98aj
     * @reason Makes this block's ticks much faster. (This block was never intended to tick)
     */
    @Overwrite()
    public void updateTick(World world, int x, int y, int z, Random random) {
    }
    
}
