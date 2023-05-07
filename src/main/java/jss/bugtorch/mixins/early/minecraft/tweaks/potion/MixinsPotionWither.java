package jss.bugtorch.mixins.early.minecraft.tweaks.potion;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Potion.class)
public abstract class MixinsPotionWither {

    /**
     * @author jss2a98aj
     * @reason Makes wither effect damage scale with max health.
     */
    @Redirect(
            method = "performEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityLivingBase;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z",
                    ordinal = 1
            )
    )
    private boolean bugTorch$scalingWitherEffectDamage(EntityLivingBase entity, DamageSource source, float damage) {
        return entity.attackEntityFrom(source,
                (entity instanceof EntityPlayer)
                        ? BugTorchConfig.scaledWitherDamageMaxHealthMult * entity.getMaxHealth() + BugTorchConfig.scaledWitherDamageMaxHealthFlat
                        : damage);
    }

}
