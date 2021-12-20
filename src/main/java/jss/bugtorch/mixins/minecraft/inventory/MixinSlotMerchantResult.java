package jss.bugtorch.mixins.minecraft.inventory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.inventory.SlotMerchantResult;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

@Mixin(value = SlotMerchantResult.class)
public abstract class MixinSlotMerchantResult {

	/**
	 * @author jss2a98aj
	 * @reason Villager trades will now respect metadata
	 */
	@Overwrite()
	private boolean func_75230_a(MerchantRecipe requirements, ItemStack offeredItem1, ItemStack offeredItem2) {
		ItemStack itemstack1 = requirements.getItemToBuy();
		ItemStack itemstack2 = requirements.getSecondItemToBuy();

		if (offeredItem1 != null && offeredItem1.getItem() == itemstack1.getItem() && (offeredItem1.getItemDamage() == itemstack1.getItemDamage())) {
			if (itemstack2 != null && offeredItem2 != null && itemstack2.getItem() == offeredItem2.getItem() && itemstack2.getItemDamage() == offeredItem2.getItemDamage()) {
				offeredItem1.stackSize -= itemstack1.stackSize;
				offeredItem2.stackSize -= itemstack2.stackSize;
				return true;
			}

			if (itemstack2 == null && offeredItem2 == null) {
				offeredItem1.stackSize -= itemstack1.stackSize;
				return true;
			}
		}
		return false;
	}

}
