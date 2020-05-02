package jss.bugtorch.mixins.blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;

@Mixin(BlockSilverfish.class)
public class MixinBlockSilverfish {

    @Overwrite
    public void dropBlockAsItemWithChance(World worldIn, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
    }

}
