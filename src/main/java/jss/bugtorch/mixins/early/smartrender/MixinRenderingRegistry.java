package jss.bugtorch.mixins.early.smartrender;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// Rejects Smart Render's rendering classes from being registered.
/// This allows users to disable Smart Render's buggy rendering for cases where it may be incompatible with modded armors.
@Mixin(RenderingRegistry.class)
public class MixinRenderingRegistry {
    @Inject(method = "registerEntityRenderingHandler", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void rejectSmartRenderClasses(Class<? extends Entity> entityClass, Render renderer, CallbackInfo ci) {
        String name = renderer.getClass().getName();
        if(name.startsWith("net.smart.render") || name.startsWith("net.smart.moving")) {
            ci.cancel();
        }
    }
}
