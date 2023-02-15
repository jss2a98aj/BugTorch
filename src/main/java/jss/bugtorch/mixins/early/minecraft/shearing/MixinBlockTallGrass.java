package jss.bugtorch.mixins.early.minecraft.shearing;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.world.World;

@Mixin(value = BlockTallGrass.class)
public abstract class MixinBlockTallGrass extends BlockBush {

	/**
	 * @author jss2a98aj
	 * @reason Prevents duplicate drops when shearing.
	 */
	@Overwrite()
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if (!(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemShears)) {
			super.harvestBlock(world, player, x, y, z, meta);
		}
	}

}
