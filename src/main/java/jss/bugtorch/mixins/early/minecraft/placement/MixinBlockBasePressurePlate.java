package jss.bugtorch.mixins.early.minecraft.placement;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

@Mixin(value = BlockBasePressurePlate.class)
public abstract class MixinBlockBasePressurePlate extends Block {

	MixinBlockBasePressurePlate(Material material) {
		super(material);
	}

	/**
	 * @author jss2a98aj
	 * @reason Allows pressure plates to be placed on anything that extends BlockFence or BlockWall.
	 */
	@Overwrite
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block below = world.getBlock(x, y - 1, z);
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || below instanceof BlockFence || below instanceof BlockWall;
	}

	/**
	 * @author jss2a98aj
	 * @reason Allows pressure plates to be placed on anything that extends BlockFence or BlockWall.
	 */
	@Overwrite
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!canPlaceBlockAt(world, x, y , z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

}
