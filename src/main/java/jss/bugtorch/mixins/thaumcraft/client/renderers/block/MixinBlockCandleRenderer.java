package jss.bugtorch.mixins.thaumcraft.client.renderers.block;

import java.awt.Color;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import thaumcraft.client.renderers.block.BlockCandleRenderer;
import thaumcraft.common.lib.utils.Utils;

@Mixin(value = BlockCandleRenderer.class)
public abstract class MixinBlockCandleRenderer {

	/**
	 * @author jss2a98aj
	 * @reason Prevents an array out of bounds exception when metadata greater than 15 is used
	 * UNFINISHED
	 */
	@Redirect(method = "renderInventoryBlock", at = @At(value = "NEW", target = "java/awt/Color", ordinal = 0))
	private Color redirectRenderInventoryBlockColor(Block block, int meta, int modelID, RenderBlocks renderer) {
		return new Color(Utils.colors[meta >= Utils.colors.length ? 0 : meta]);
	}

}
