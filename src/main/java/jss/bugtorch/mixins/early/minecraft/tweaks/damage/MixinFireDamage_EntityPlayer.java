package jss.bugtorch.mixins.early.minecraft.tweaks.damage;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityPlayer.class)
public abstract class MixinFireDamage_EntityPlayer extends EntityLivingBase {

    public boolean bugTorch$doFireDamage() {
        return attackEntityFrom(DamageSource.onFire,
            BugTorchConfig.scaledFireDamageMaxHealthMult * getMaxHealth() + BugTorchConfig.scaledFireDamageMaxHealthFlat
        );
    }

    public MixinFireDamage_EntityPlayer(World world) {
        super(world);
    }

}
