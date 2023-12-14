package jss.bugtorch.mixins.early.minecraft.tweaks;

import jss.bugtorch.BugTorch;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RecipesArmorDyes.class, priority = 1100)
public abstract class MixinRecipeArmorDyes {
    @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
    public void recipesArmorDyesMatchBetter(InventoryCrafting inv, World world, CallbackInfoReturnable<Boolean> ctx) {
        boolean hasArmor = false, hasDye = false;

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            //the ide has no idea how vital this continue is
            if (stack == null) {
                continue;
            } else if (stack.getItem() instanceof ItemArmor && !hasArmor && ((ItemArmor) stack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.CLOTH) {
                hasArmor = true;
            } else {
                boolean pass = false;
                int[] ids = OreDictionary.getOreIDs(stack);
                for (int dyeOreId : BugTorch.dyeOreIds) {
                    if (ArrayUtils.contains(ids, dyeOreId)) {
                        pass = hasDye = true;
                    }
                }
                if (!pass) {
                    ctx.setReturnValue(false);
                    return;
                }
            }
        }
        ctx.setReturnValue(hasArmor && hasDye);
    }

    @Redirect(method = "getCraftingResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", ordinal = 2))
    public Item recipesArmorDyesPreventEarlyReturn(ItemStack stack) {
        //early return prevention
        return Items.dye;
    }

    @Redirect(method = "getCraftingResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemDamage()I"))
    public int recipesArmorDyesMatchDyeToColorMeta(ItemStack stack) {
        int[] od = OreDictionary.getOreIDs(stack);
        for (int i = 0; i < BugTorch.dyeOreIds.length; i++) {
            if(ArrayUtils.contains(od, BugTorch.dyeOreIds[i])) return i;
        }
        return 15;
    }
}
