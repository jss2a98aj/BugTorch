package jss.bugtorch.mixins.mods.ganyssurface.blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganyssurface.blocks.BlockWoodTrapdoor;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

@Mixin(BlockWoodTrapdoor.class)
public class MixinBlockWoodTrapdoor extends BlockTrapDoor {

    @Shadow public final int woodMeta;
    
    protected MixinBlockWoodTrapdoor(Material material) {
        super(material);
        woodMeta = 1;
    }

    @Overwrite
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        boolean isOpen = func_150118_d(meta);
        if (isOpen) {
            switch (meta & 3) {
                case 0:
                    if (side == 2)
                        return super.getIcon(side, meta);
                    break;
                case 1:
                    if (side == 3)
                        return super.getIcon(side, meta);
                    break;
                case 2:
                    if (side == 4)
                        return super.getIcon(side, meta);
                    break;
                case 3:
                    if (side == 5)
                        return super.getIcon(side, meta);
                    break;
            }
            return Blocks.planks.getIcon(side, woodMeta);
        }

        return side == 0 || side == 1 ? super.getIcon(side, meta) : Blocks.planks.getIcon(side, woodMeta);
    }

}
