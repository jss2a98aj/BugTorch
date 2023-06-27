package jss.bugtorch.mixins.late.extrautils.tweaks.damage;

import com.rwtema.extrautils.worldgen.Underdark.DarknessTickHandler;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = DarknessTickHandler.class)
public abstract class MixinDarknessDamage {

    /**
     * @author jss2a98aj
     * @reason Makes drowning damage scale with max health on players.
     */
    @Redirect(
        method = "tickStart(Lcpw/mods/fml/common/gameevent/TickEvent$PlayerTickEvent;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/EntityPlayerMP;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"
        ),
        require = 1
    )
    private boolean bugTorch$scalingDarknessDamage(EntityPlayerMP entity, DamageSource source, float damage) {
        return entity.attackEntityFrom(source,
            BugTorchConfig.scaledExtraUtilitiesDarknessDamageMaxHealthMult * entity.getMaxHealth() + BugTorchConfig.scaledExtraUtilitiesDarknessDamageMaxHealthFlat
        );
    }

}
