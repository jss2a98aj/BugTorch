package jss.bugtorch.mixins.early.minecraft.backport;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import jss.bugtorch.BugTorch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFireball;

@Mixin(ItemFireball.class)
public class MixinItemFireball extends Item {

	@ModifyArg(at = @At(target = "Lnet/minecraft/world/World;playSoundEffect(DDDLjava/lang/String;FF)V", value = "INVOKE"), method = "onItemUse")
	private String getName(String original) {
	    return BugTorch.MODID + ":item.fireCharge.use";
	}
}
