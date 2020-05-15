package jss.bugtorch.mixins.xstr.client.particle;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
/*
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*/

import jss.bugtorch.util.XSTR;
import net.minecraft.client.particle.EffectRenderer;

@Mixin(EffectRenderer.class)
public class MixinEffectRenderer {

    /**
     *XSRT is faster than Random
     */
    public Random rand = new XSTR();

    /*
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onEffectRendererInit(CallbackInfo ci) {
        if(rand instanceof XSTR) {
            System.out.println("EffectRenderer is using XSTR for Random");
         } else {
             System.out.println("EffectRenderer is NOT using XSTR for Random");
         }
    }
    */

}
