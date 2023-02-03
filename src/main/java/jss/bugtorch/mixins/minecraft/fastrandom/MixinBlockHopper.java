package jss.bugtorch.mixins.minecraft.fastrandom;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.block.BlockHopper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(value = BlockHopper.class)
public abstract class MixinBlockHopper {

    /**
     * Xoshiro256** is faster than Random.
     */
    @Final
    private Random field_149922_a = new RandomXoshiro256StarStar();

}
