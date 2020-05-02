package jss.bugtorch.mixins.entity;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.player.EntityPlayer;

@Mixin(EntityPlayer.class)
public class MixinEntityPlayer {

    //getBreakSpeed
    //MC-48760
    
}
