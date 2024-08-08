package jss.bugtorch.mixins.early.minecraft.fastrandom;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.client.renderer.entity.RenderItem;

@Mixin(value = RenderItem.class)
public abstract class MixinRenderItem {

	/**
	 * Xoshiro256** is faster than Random.
	 */
	@SuppressWarnings("unused")
    private Random random = new RandomXoshiro256StarStar();

}
