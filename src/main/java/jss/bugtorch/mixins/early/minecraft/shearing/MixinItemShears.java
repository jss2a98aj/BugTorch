package jss.bugtorch.mixins.early.minecraft.shearing;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.IShearable;

@Mixin(value = ItemShears.class)
public abstract class MixinItemShears extends Item {

	/**
	 * @author jss2a98aj
	 * @reason Makes Shears take damage when breaking blocks and stops shearable logic from running in creative mode.
	 */
	@Overwrite(remap = false)
	public boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player) {
		if (player.worldObj.isRemote || player.capabilities.isCreativeMode) {
			return false;
		}
		Block block = player.worldObj.getBlock(x, y, z);
		if (block instanceof IShearable) {
			IShearable target = (IShearable)block;
			if (target.isShearable(itemStack, player.worldObj, x, y, z)) {
				ArrayList<ItemStack> drops = target.onSheared(itemStack, player.worldObj, x, y, z, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemStack));
				for(ItemStack stack : drops) {
					float f = 0.7F;
					// Swap to itemRand rather than making a new random instance every time shears break an IShearable block
					double dx  = (double)(itemRand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double dy = (double)(itemRand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					double dz = (double)(itemRand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
					EntityItem entityitem = new EntityItem(player.worldObj, (double)x + dx, (double)y + dy, (double)z + dz, stack);
					entityitem.delayBeforeCanPickup = 10;
					player.worldObj.spawnEntityInWorld(entityitem);
				}
				player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(block)], 1);
			}
		}
		itemStack.damageItem(1, player);
		return false;
	}

}
