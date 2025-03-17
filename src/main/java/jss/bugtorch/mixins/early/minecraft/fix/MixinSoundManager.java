package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.client.audio.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public abstract class MixinSoundManager {
	@Inject(method = "reloadSoundSystem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/audio/SoundManager;loadSoundSystem()V"))
	public void snooze(CallbackInfo ctx) throws InterruptedException {
		Thread.sleep(333);
	}
}
