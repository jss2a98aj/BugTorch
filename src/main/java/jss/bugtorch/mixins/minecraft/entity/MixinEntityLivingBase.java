package jss.bugtorch.mixins.minecraft.entity;

import java.util.HashMap;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

@Mixin(value = EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity {

	MixinEntityLivingBase(World world) {
		super(world);
	}

	@Shadow
	@Final
	private HashMap activePotionsMap;

	/**
	 * @author jss2a98aj
	 * @reason If the potion array is empty don't waste time checking it
	 */
	@Overwrite()
	public boolean isPotionActive(int potionID) {
		return activePotionsMap.size() != 0 && activePotionsMap.containsKey(Integer.valueOf(potionID));
	}

	/**
	 * @author jss2a98aj
	 * @reason If the potion array is empty don't waste time checking it
	 */
	@Overwrite()
	public boolean isPotionActive(Potion potion) {
		return activePotionsMap.size() != 0 && activePotionsMap.containsKey(Integer.valueOf(potion.id));
	}

	/**
	 * @author jss2a98aj
	 * @reason Keeps the datawatcher from being updated when no change in air has occurred
	 */
	@Override
	public void setAir(int airAmount) {
		if (getAir() != airAmount) {
			super.setAir(airAmount);
		}
	}

}
