package jss.bugtorch.mixins.xstr.client.multiplayer;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import jss.bugtorch.util.XSTR;
import net.minecraft.client.multiplayer.WorldClient;

@Mixin(WorldClient.class)
public abstract class MixinWorldClient {

    @Redirect(method = "doVoidFogParticles", at = @At(value = "NEW", target = "java/util/Random"))
    private Random redirectDoVoidFogParticlesRandom() {
        return new XSTR();
    }

}
