package jss.bugtorch.mixins.minecraft.placement;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.world.World;

@Mixin(value = BlockPumpkin.class)
public abstract class MixinBlockPumpkin {

	/**
	 * @author jss2a98aj
	 * @reason Allows pumpkins to be placed without a solid block below them.
	 */
	@Overwrite()
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).isReplaceable(world, x, y, z);
	}

}
