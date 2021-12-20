package jss.bugtorch.mixins.random.client.particle;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.client.particle.EffectRenderer;

@Mixin(value = EffectRenderer.class)
public abstract class MixinEffectRenderer {

	/**
	 * Xoshiro256** is faster than Random
	 */
	public Random rand = new RandomXoshiro256StarStar();

}
