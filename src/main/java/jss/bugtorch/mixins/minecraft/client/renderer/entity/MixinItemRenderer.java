package jss.bugtorch.mixins.minecraft.client.renderer.entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;

@Mixin(value = RenderItem.class)
public abstract class MixinItemRenderer {

	@Shadow
	public float zLevel;

	/**
	 * @author jss2a98aj
	 * @reason Makes renderGlint faster and fixes glBlendFunc being left with the wrong values
	 */
	@Overwrite
	private void renderGlint(int p_77018_1_, int p_77018_2_, int p_77018_3_, int p_77018_4_, int p_77018_5_) {
		OpenGlHelper.glBlendFunc(772, 1, 0, 0);
		final float f = 0.00390625F;
		final float f1 = 0.00390625F;
		final float f3 = 0.0F;
		final Tessellator tessellator = Tessellator.instance;
		final long systemTime = Minecraft.getSystemTime();

		for (int j1 = 0; j1 < 2; ++j1) {
			final float f2 = (float) (systemTime % (long) (3000 + j1 * 1873)) / (3000.0F + (float) (j1 * 1873))
					* 256.0F;
			float f4 = 4.0F;
			if (j1 == 1) {
				f4 = -1.0F;
			}

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV((double) (p_77018_2_ + 0), (double) (p_77018_3_ + p_77018_5_), (double) zLevel, (double) ((f2 + (float) p_77018_5_ * f4) * f), (double) ((f3 + (float) p_77018_5_) * f1));
			tessellator.addVertexWithUV((double) (p_77018_2_ + p_77018_4_), (double) (p_77018_3_ + p_77018_5_), (double) zLevel, (double) ((f2 + (float) p_77018_4_ + (float) p_77018_5_ * f4) * f), (double) ((f3 + (float) p_77018_5_) * f1));
			tessellator.addVertexWithUV((double) (p_77018_2_ + p_77018_4_), (double) (p_77018_3_ + 0), (double) zLevel, (double) ((f2 + (float) p_77018_4_) * f), (double) ((f3 + 0.0F) * f1));
			tessellator.addVertexWithUV((double) (p_77018_2_ + 0), (double) (p_77018_3_ + 0), (double) zLevel, (double) ((f2 + 0.0F) * f), (double) ((f3 + 0.0F) * f1));
			tessellator.draw();
		}
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	}

}
