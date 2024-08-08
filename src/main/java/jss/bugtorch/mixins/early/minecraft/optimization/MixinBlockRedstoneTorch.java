package jss.bugtorch.mixins.early.minecraft.optimization;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.world.World;

@Mixin(value = BlockRedstoneTorch.class)
public abstract class MixinBlockRedstoneTorch {

	/**
	 * @author jss2a98aj
	 * @reason Stops a memory leak by changing a HashMap to a WeakHashMap so worlds can be garbage collected
	 */
	@SuppressWarnings("unused")
    private static Map<World, List<?>> field_150112_b = new WeakHashMap<>();

}
