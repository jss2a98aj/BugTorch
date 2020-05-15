package jss.bugtorch.mixins.xstr.world;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
/*
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*/

import jss.bugtorch.util.XSTR;
import net.minecraft.world.World;

@Mixin(World.class)
public class MixinWorld {

    /**
     *XSRT is faster than Random
     */
    public Random rand = new XSTR();

    /*
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onWorldInit(CallbackInfo ci) {
        if(rand instanceof XSTR) {
            System.out.println("World is using XSTR for Random");
        } else {
            System.out.println("World is NOT using XSTR for Random");
        }
    }
    */

}
