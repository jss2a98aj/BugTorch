package jss.bugtorch.mixins.minecraft.client.gui;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.MapItemRenderer;

@Mixin(value = MapItemRenderer.class)
public class MixinMapItemRenderer {

    //TODO
    //MC-3416
    //Player location marker invisible on map (wrong Z-Order)
    //Swap line 151 "++i;" to "--i;" it is a part of func_148237_a

}
