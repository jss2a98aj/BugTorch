package jss.bugtorch.mixins.late.thaumcraft.sanitizearrayaccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import thaumcraft.client.renderers.block.BlockCandleRenderer;
import thaumcraft.common.lib.utils.Utils;

@Mixin(value = BlockCandleRenderer.class)
public abstract class MixinBlockCandleRenderer {

	/**
	 * @author jss2a98aj
	 * @reason Prevents an array out of bounds exception when metadata greater than 15 is used.
	 */
	@ModifyVariable(method = "renderInventoryBlock(Lnet/minecraft/block/Block;IILnet/minecraft/client/renderer/RenderBlocks;)V", at = @At("HEAD"), ordinal = 0, remap = false)
	private int sanitizeRenderInventoryBlock(int meta) {
	  return meta >= Utils.colors.length ? 0 : meta;
	}

}
