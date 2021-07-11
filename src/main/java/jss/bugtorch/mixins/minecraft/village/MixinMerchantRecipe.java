package jss.bugtorch.mixins.minecraft.village;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraftforge.oredict.OreDictionary;

@Mixin(value = MerchantRecipe.class)
public class MixinMerchantRecipe {

    @Shadow
    private ItemStack itemToBuy;
    @Shadow
    private ItemStack secondItemToBuy;
    @Shadow
    private ItemStack itemToSell;

    private boolean areStacksEqual(ItemStack first, ItemStack second) {
        int firstMeta = first.getItemDamage();
        int secondMeta = second.getItemDamage();
        return first.getItem() == second.getItem() && (firstMeta == secondMeta || firstMeta == OreDictionary.WILDCARD_VALUE || secondMeta == OreDictionary.WILDCARD_VALUE);
    }

    /**
     * @author jss2a98aj
     * @reason Villager trades will now respect metadata
     */
    @Overwrite()
    public boolean hasSameIDsAs(MerchantRecipe trade) {
        return areStacksEqual(this.itemToBuy, trade.getItemToBuy())
                && areStacksEqual(this.itemToSell, trade.getItemToSell())
                ? this.secondItemToBuy == null && trade.getSecondItemToBuy() == null
                || this.secondItemToBuy != null && trade.getSecondItemToBuy() != null
                &&  areStacksEqual(this.secondItemToBuy, trade.getSecondItemToBuy())
                : false;
    }

}
