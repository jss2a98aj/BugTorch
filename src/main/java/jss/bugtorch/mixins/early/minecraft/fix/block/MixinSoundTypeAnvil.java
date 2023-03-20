package jss.bugtorch.mixins.early.minecraft.fix.block;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net/minecraft/block/Block$3")
public abstract class MixinSoundTypeAnvil {

    public String getStepResourcePath() {
        return "step.stone";
    }

}
