package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLilyPad;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemLilyPad.class)
public abstract class MixinItemLilyPad {

    /**
     * @author jss2a98aj
     * @reason Prevent client side ghost placement
     */
    @Inject(
        method = "onItemRightClick(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;",
        at = @At(value = "HEAD"),
        cancellable = true
    )
    void serverAuthoritativeOnItemRightClick(ItemStack itemStack, World world, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir) {
        if(world.isRemote) {
            cir.setReturnValue(itemStack);
        }
    }

}
