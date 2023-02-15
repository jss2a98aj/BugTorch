package jss.bugtorch.mixins.early.minecraft.backport;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import jss.bugtorch.BugTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(value = ItemFireball.class)
public abstract class MixinItemFireball extends Item {

	/**
	 * @author jss2a98aj
	 * @reason Corrects the fire charge use sound.
	 */
	@Overwrite()
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			switch(side) {
			case 0:
				--y;
				break;
			case 1:
				++y;
				break;
			case 2:
				--z;
				break;
			case 3:
				++z;
				break;
			case 4:
				--x;
				break;
			case 5:
				++x;
				break;
			}

			if (!player.canPlayerEdit(x, y, z, side, stack)) {
				return false;
			} else {
				if (world.getBlock(x, y, z).getMaterial() == Material.air) {
					world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, BugTorch.MODID + ":item.fireCharge.use", 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
					world.setBlock(x, y, z, Blocks.fire);
				}

				if (!player.capabilities.isCreativeMode) {
					--stack.stackSize;
				}

				return true;
			}
		}
	}

}
