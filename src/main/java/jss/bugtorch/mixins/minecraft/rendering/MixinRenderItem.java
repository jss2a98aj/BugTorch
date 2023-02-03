package jss.bugtorch.mixins.minecraft.rendering;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;

@Mixin(value = RenderItem.class)
public abstract class MixinRenderItem {

	@Shadow
	public float zLevel;

	/**
	 * @author jss2a98aj
	 * @reason Makes renderGlint faster and fixes glBlendFunc being left with the wrong values.
	 */
	@Overwrite
    private void renderGlint(int unused, int posX, int posY, int width, int height) {
        final float timeUVSpeed = 0.00390625F;
        final Tessellator tessellator = Tessellator.instance;
        final long time = Minecraft.getSystemTime();

        float layerUVNoise = 4.0F;

        OpenGlHelper.glBlendFunc(772, 1, 0, 0);

        for (int layer = 0; layer < 2; ++layer) {
        	final int timeUVDenominator = 3000 + layer * 1873;
            final float timeUVNoise = (float)(time % (long)timeUVDenominator) / (float)timeUVDenominator * 256F;

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(
            	(double)posX, (double)(posY + height), (double)zLevel,
            	(double)((timeUVNoise + (float)height * layerUVNoise) * timeUVSpeed), (double)((float)height * timeUVSpeed)
            );
            tessellator.addVertexWithUV(
            	(double)(posX + width), (double)(posY + height), (double)zLevel,
            	(double)((timeUVNoise + (float)width + (float)height * layerUVNoise) * timeUVSpeed), (double)((float)height * timeUVSpeed)
            );
            tessellator.addVertexWithUV(
            	(double)(posX + width), (double)posY, (double)zLevel,
            	(double)((timeUVNoise + (float)width) * timeUVSpeed), 0D
            );
            tessellator.addVertexWithUV(
            	(double)posX, (double)posY, (double)zLevel,
            	(double)(timeUVNoise * timeUVSpeed), 0D
            );
            tessellator.draw();

            layerUVNoise = -1.0F;
        }

        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    }

}
