package jss.bugtorch.mixins.early.minecraft.fastrandom;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.entity.Entity;

@Mixin(value = Entity.class)
public abstract class MixinEntity {

	/**
	 * @author jss2a98aj
	 * @reason Xoshiro256** is faster than Random.
	 */
	@Redirect(method = "<init>*", at = @At(value = "NEW", target = "java/util/Random"))
	private Random redirectInitRandom() {
		return new RandomXoshiro256StarStar();
	}

}
