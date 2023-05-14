package jss.bugtorch.mixins.early.minecraft.tweaks.damage;

import jss.bugtorch.BugTorch;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Entity.class)
public abstract class MixinFireDamage_Entity {

    /**
     * @author jss2a98aj
     * @reason Makes fire damage scale with max health.
     */
    @Redirect(
            method = "onEntityUpdate()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"
            )
    )
    private boolean bugTorch$scalingFireDamage(Entity entity, DamageSource source, float damage) {
        return bugTorch$doFireDamage();
    }

    public boolean bugTorch$doFireDamage() {
        return attackEntityFrom(DamageSource.onFire, 1.0F);
    }

    @Shadow
    public abstract boolean attackEntityFrom(DamageSource source, float amount);

}
