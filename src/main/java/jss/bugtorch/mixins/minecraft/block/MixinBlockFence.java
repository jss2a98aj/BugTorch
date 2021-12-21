package jss.bugtorch.mixins.minecraft.block;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockFence;
import net.minecraft.world.World;

@Mixin(value = BlockFence.class)
public class MixinBlockFence {

	/**
	 * @author jss2a98aj
	 * @reason Allows torches to be placed on anything that extends BlockFence
	 */
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return true;
    }

}