package jss.bugtorch.mixins.minecraft.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

@Mixin(value = EntityLivingBase.class)
/**
 * 
 * This is a separate class used to separate the client potion mixin functionality, without constantly checking for
 * if the option is enabled here or lumping it into the main class and combining the mixin checks.
 * 
 * Not to be confused with MixinEntityLivingBase
 * 
 * @author Roadhog360
 *
 */
public class MixinEntityLivingBase2 {

	@ModifyVariable(method = "updatePotionEffects()V", at = @At("STORE"), ordinal = 0)
	private boolean checkIfClient(boolean flag) {
        /*
         * For some reason this has a compile  error unless I cast this to EntityLivingBase
         * even though it is already an instance of EntityLivingBase...???
         * 
         * For this reason, this class no longer extends EntityLivingBase as that was all I'd need it for.
         */
        return flag || (EntityLivingBase)(Object)this instanceof AbstractClientPlayer;
	}
	
}
