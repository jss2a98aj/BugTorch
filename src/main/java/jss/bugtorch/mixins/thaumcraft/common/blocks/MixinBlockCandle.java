package jss.bugtorch.mixins.thaumcraft.common.blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import thaumcraft.common.blocks.BlockCandle;
import thaumcraft.common.lib.utils.Utils;

@Mixin(value = BlockCandle.class)
public class MixinBlockCandle extends Block {

    protected MixinBlockCandle(Material material) {
        super(material);
    }

    /**
     * @author jss2a98aj
     * @reason Prevents an array out of bounds exception when wildcard or greater than 15 metadata is referenced
     */
    @Overwrite()
    public int getRenderColor(int i) {
        return i >= Utils.colors.length ? Utils.colors[0] : Utils.colors[i];
    }
    
    /**
     * @author jss2a98aj
     * @reason Prevents an array out of bounds exception when wildcard or greater than 15 metadata is referenced
     */
    @Overwrite()
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return meta >= Utils.colors.length ? Utils.colors[0] : Utils.colors[meta];
    }

}
