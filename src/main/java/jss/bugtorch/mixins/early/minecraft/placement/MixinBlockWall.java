package jss.bugtorch.mixins.early.minecraft.placement;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockWall;
import net.minecraft.world.World;

@Mixin(value = BlockWall.class)
public class MixinBlockWall {

	/**
	 * @author jss2a98aj
	 * @reason Allows torches to be placed on anything that extends BlockWall.
	 */
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
		return true;
    }

}
