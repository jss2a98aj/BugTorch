package jss.bugtorch.mixins.xstr.entity;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import jss.bugtorch.util.XSTR;
import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class MixinEntity {

    @Redirect(method = "<init>*", at = @At(value = "NEW", target = "java/util/Random"))
    private Random redirectInitRandom() {
        return new XSTR();
    }

}
