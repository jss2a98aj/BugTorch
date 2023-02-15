package jss.bugtorch.mixins.early.minecraft.tweaks.entitylivingbase;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityLivingBase.class)
public abstract class MixinScalingDrowningDamage {

    /**
     * @author jss2a98aj
     * @reason Makes drowning damage scale with max health on players.
     */
    @Redirect(
            method = "onEntityUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityLivingBase;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z",
                    ordinal = 1
            )
    )
    private boolean scalingDrowningDamage(EntityLivingBase entity, DamageSource source, float damage) {
        return entity.attackEntityFrom(source, (entity instanceof EntityPlayer) ? BugTorchConfig.scaledDrowningDamageMaxHealthMult * entity.getMaxHealth() : damage);
    }

}
