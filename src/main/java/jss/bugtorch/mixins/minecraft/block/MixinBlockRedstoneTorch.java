package jss.bugtorch.mixins.minecraft.block;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockRedstoneTorch;

@Mixin(value = BlockRedstoneTorch.class)
public class MixinBlockRedstoneTorch {

	/**
	 * @author jss2a98aj
	 * @reason Stops a memory leak by changing a HashMap to a WeakHashMap so worlds can be garbage collected
	 */
	private static Map field_150112_b = new java.util.WeakHashMap();

}
