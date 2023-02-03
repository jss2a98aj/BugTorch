package jss.bugtorch.mixins.minecraft.tweaks.blockfarmland;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BlockFarmland.class)
public abstract class MixinNewTextures extends Block {

    MixinNewTextures(Material material) {
        super(material);
    }

    @Shadow
    private IIcon field_149824_a;// wet
    @Shadow
    private IIcon field_149823_b;// dry

    private IIcon sideWet;
    private IIcon sideDry;

    public IIcon getIcon(int side, int meta) {
        return meta > 0 ? (side == 1 ? field_149824_a : sideWet) : (side == 1 ? field_149823_b : sideDry);
    }

    /**
     * @author jss2a98aj
     * @reason New side/bottom textures.
     */
    public void registerBlockIcons(IIconRegister iconRegister) {
        field_149824_a = iconRegister.registerIcon(getTextureName() + "_wet");
        field_149823_b = iconRegister.registerIcon(getTextureName() + "_dry");
        sideWet = iconRegister.registerIcon(getTextureName() + "_wet_side");
        sideDry = iconRegister.registerIcon(getTextureName() + "_dry_side");
    }

}
