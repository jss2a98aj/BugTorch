package jss.bugtorch.mixins.early.minecraft.tweaks.entitylivingbase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;

@Mixin(value = EntityLivingBase.class)
public abstract class MixinTranslucentClientPotionEffects {

	/**
	 * @author Roadhog360
	 * @reason Makes potion particles rendering on a player's character translucent.
	 */
	@ModifyVariable(method = "updatePotionEffects()V", at = @At("STORE"), ordinal = 0)
	private boolean checkIfClient(boolean flag) {
		return flag || (EntityLivingBase) (Object) this instanceof AbstractClientPlayer;
	}

}
