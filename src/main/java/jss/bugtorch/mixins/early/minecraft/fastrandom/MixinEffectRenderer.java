package jss.bugtorch.mixins.early.minecraft.fastrandom;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.client.particle.EffectRenderer;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(value = EffectRenderer.class)
public abstract class MixinEffectRenderer {

	/**
	 * Xoshiro256** is faster than Random.
	 */
	public Random rand = new RandomXoshiro256StarStar();

}
