package jss.bugtorch.mixins.xstr.client.renderer.entity;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
/*
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*/

import jss.bugtorch.util.XSTR;
import net.minecraft.client.renderer.entity.RenderItem;

@Mixin(RenderItem.class)
public class MixinRenderItem {

    /**
     *XSRT is faster than Random
     */
    private Random random = new XSTR();

    /*
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onRenderItemInit(CallbackInfo ci) {
        if(random instanceof XSTR) {
            System.out.println("RenderItem is using XSTR for Random");
         } else {
             System.out.println("RenderItem is NOT using XSTR for Random");
         }
    }
    */

}
