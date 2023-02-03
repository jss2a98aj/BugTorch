package jss.bugtorch.mixins.minecraft.tweaks;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.effect.EntityLightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = EntityLightningBolt.class)
public abstract class MixinEntityLightningBolt {

    @ModifyConstant(method = "onUpdate", constant = @Constant(floatValue = 10000.0F))
    private static float reduceLightningVolume(float old) {
        return BugTorchConfig.reduceLightningVolume;
    }

}
