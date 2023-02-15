package jss.bugtorch.mixins.early.minecraft.fastrandom;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.block.BlockChest;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(value = BlockChest.class)
public abstract class MixinBlockChest {

    /**
     * Xoshiro256** is faster than Random.
     */
    @Final
    private Random field_149955_b = new RandomXoshiro256StarStar();

}
