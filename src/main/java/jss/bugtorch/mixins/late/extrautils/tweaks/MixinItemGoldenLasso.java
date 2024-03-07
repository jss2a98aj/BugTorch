package jss.bugtorch.mixins.late.extrautils.tweaks;

import com.rwtema.extrautils.item.ItemGoldenLasso;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemGoldenLasso.class)
public abstract class MixinItemGoldenLasso {

    @Inject(
        method = "itemInteractionForEntity(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/EntityLivingBase;)Z",
        at = @At(
            value = "HEAD",
            ordinal = 0
        ),
        cancellable = true
    )
    public void injectBlacklist(ItemStack stack, EntityPlayer player, EntityLivingBase entity, CallbackInfoReturnable<Boolean> cir) {
        if(entity == null) {
           return;
        }
        String entityString = entity.getClass().toString().substring(6);
        for(String entry : BugTorchConfig.extraUtilitiesGoldenLassoBlacklist) {
            if(entry.substring(entry.length() - 1).equals("*")) {
                int compLength = entry.length() - 1;
                if(entityString.length() > compLength && entityString.substring(0, compLength).equals(entry.substring(0, compLength))) {
                    cir.setReturnValue(false);
                    return;
                }
            }
            if(entry.equals(entityString)) {
                cir.setReturnValue(false);
                return;
            }
        }
    }

}
