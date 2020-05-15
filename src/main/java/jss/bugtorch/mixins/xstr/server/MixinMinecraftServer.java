package jss.bugtorch.mixins.xstr.server;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
/*
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*/

import jss.bugtorch.util.XSTR;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    /**
     *XSRT is faster than Random
     */
    @Final
    private Random field_147146_q = new XSTR();

    /*
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onMinecraftServerInit(CallbackInfo ci) {
        if(field_147146_q instanceof XSTR) {
            System.out.println("MinecraftServer is using XSTR for Random");
         } else {
             System.out.println("MinecraftServer is NOT using XSTR for Random");
         }
    }
    */
    
}