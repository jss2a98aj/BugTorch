package jss.bugtorch.mixins.early.minecraft.tweaks;

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

@Mixin(RecipesArmorDyes.class)
public abstract class MixinRecipeArmorDyes {
    @Inject(method = "matches", at = @At("HEAD"), cancellable = true)
    public void matchBetter(InventoryCrafting inv, World world, CallbackInfoReturnable<Boolean> ctx) {
        boolean hasArmor = false, hasDye = false;
        //every OD'd dye should be in this
        int dyeAny = OreDictionary.getOreID("dye");
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            //the ide has no idea how vital this llwyd continue is
            if (stack == null) continue;
            else if (stack.getItem() instanceof ItemArmor && ((ItemArmor) stack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.CLOTH) {
                hasArmor = true;
            } else if (ArrayUtils.contains(OreDictionary.getOreIDs(stack), dyeAny)) {
                hasDye = true;
            } else {
                ctx.setReturnValue(false);
                return;
            }
        }
        ctx.setReturnValue(hasArmor && hasDye);
    }

    @Redirect(method = "getCraftingResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", ordinal = 2))
    public Item pretendo64(ItemStack stack) {
        //early return prevention
        return Items.dye;
    }

    @Redirect(method = "getCraftingResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemDamage()I"))
    public int spinTheWheelAndLaughAtGod(ItemStack stack) {
        int[] od = OreDictionary.getOreIDs(stack);
        //dye ore ids could be cached externally but this codebase is 👽 to me
        String[] dyes = {
            "dyeBlack",
            "dyeRed",
            "dyeGreen",
            "dyeBrown",
            "dyeBlue",
            "dyePurple",
            "dyeCyan",
            "dyeLightGray",
            "dyeGray",
            "dyePink",
            "dyeLime",
            "dyeYellow",
            "dyeLightBlue",
            "dyeMagenta",
            "dyeOrange",
            "dyeWhite"
        };
        for (int i = 0; i < dyes.length; i++) {
            if (ArrayUtils.contains(od, OreDictionary.getOreID(dyes[i]))) return i;
        }
        return 15;
    }
}
