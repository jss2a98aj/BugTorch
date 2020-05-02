package jss.bugtorch.mixins.client.gui;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.MapItemRenderer;

@Mixin(MapItemRenderer.class)
public class MixinMapItemRenderer {

    //MC-3416
    //Player location marker invisible on map (wrong Z-Order)
    //Swap line 151 "++i;" to "--i;" it is a part of func_148237_a
    
}
