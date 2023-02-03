package jss.bugtorch.mixins.minecraft.villagertrademeta;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;

@Mixin(value = MerchantRecipe.class)
public abstract class MixinMerchantRecipe {

	@Shadow
	private ItemStack itemToBuy;
	@Shadow
	private ItemStack secondItemToBuy;
	@Shadow
	private ItemStack itemToSell;

	/**
	 * @author jss2a98aj
	 * @reason Villager trades will now respect metadata.
	 */
	@Overwrite()
	public boolean hasSameIDsAs(MerchantRecipe trade) {
		return (itemToBuy.getItem() == trade.itemToBuy.getItem() && itemToBuy.getItemDamage() == trade.itemToBuy.getItemDamage()) &&
				(itemToSell.getItem() == trade.itemToSell.getItem() && itemToSell.getItemDamage() == trade.itemToSell.getItemDamage()) ?
				secondItemToBuy == null && trade.secondItemToBuy == null ||
				secondItemToBuy != null && trade.secondItemToBuy != null &&
				(secondItemToBuy.getItem() == trade.secondItemToBuy.getItem() && secondItemToBuy.getItemDamage() == trade.secondItemToBuy.getItemDamage())
				: false;
	}

}
