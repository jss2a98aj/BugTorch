package jss.bugtorch.mixins.early.minecraft.tweaks.damage;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityPlayer.class)
public abstract class MixinLavaDamage extends EntityLivingBase {

    /**
     * @author jss2a98aj
     * @reason Makes lava damage scale with max health.
     */
    @Override
    protected void setOnFireFromLava() {
        if(!isImmuneToFire) {
            attackEntityFrom(DamageSource.lava,
                BugTorchConfig.scaledLavaDamageMaxHealthMult * getMaxHealth() + BugTorchConfig.scaledLavaDamageMaxHealthFlat
            );
            setFire(15);
        }
    }

    public MixinLavaDamage(World world) {
        super(world);
    }

}
