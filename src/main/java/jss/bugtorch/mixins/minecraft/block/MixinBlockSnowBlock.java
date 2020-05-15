package jss.bugtorch.mixins.minecraft.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockSnowBlock;
import net.minecraft.world.World;

@Mixin(BlockSnowBlock.class)
public class MixinBlockSnowBlock {

    /**
     * @author jss2a98aj
     * @reason Makes ticks much faster. (This block was never intended to tick)
     */
    @Overwrite
    public void updateTick(World worldIn, int x, int y, int z, Random randomIn) {
    }
    
}
