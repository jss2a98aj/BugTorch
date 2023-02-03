package jss.bugtorch.mixins.aetherii.optimization;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.aetherteam.aether.client.ClientEventHandler;
import net.aetherteam.aether.client.renders.entities.player.RenderPlayerAether;

@Mixin(value = ClientEventHandler.class, remap = false)
public abstract class MixinClientEventHandler {

	RenderPlayerAether reusedRenderPlayerAether;

	/**
	 * @author makamys
	 * @reason There is no reason to create a new RenderPlayer object every frame, and doing so wastes memory and causes horrible performance with RenderPlayerAPI.
	 */
	@Redirect(method = {"onRenderPlayer", "onRenderHand"}, at = @At(value = "NEW", target = "net/aetherteam/aether/client/renders/entities/player/RenderPlayerAether"))
    public RenderPlayerAether redirectNewRenderPlayerAether() {
		if(reusedRenderPlayerAether == null) {
			reusedRenderPlayerAether = new RenderPlayerAether();
		}
		return reusedRenderPlayerAether;
    }

}
