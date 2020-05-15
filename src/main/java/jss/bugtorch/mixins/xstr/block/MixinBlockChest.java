package jss.bugtorch.mixins.xstr.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
/*
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*/

import jss.bugtorch.util.XSTR;
import net.minecraft.block.BlockChest;

@Mixin(BlockChest.class)
public class MixinBlockChest {

    @Final
    private Random field_149955_b = new XSTR();

    /*
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void onBlockChestInit(CallbackInfo ci) {
        if(field_149955_b instanceof XSTR) {
            System.out.println("BlockChest is using XSTR for Random");
        } else {
            System.out.println("BlockChest is NOT using XSTR for Random");
        }
    }
    */

}
