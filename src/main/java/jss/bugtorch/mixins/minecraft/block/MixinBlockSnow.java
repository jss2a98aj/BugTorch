package jss.bugtorch.mixins.minecraft.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

@Mixin(value = BlockSnow.class)
public class MixinBlockSnow extends Block  {
	
    protected MixinBlockSnow(Material material) {
		super(material);
	}

    /**
     * @author jss2a98aj
     * @reason Culling for layered snow block sides
     */
    @Overwrite()
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
    	Block otherBlock = world.getBlock(x, y, z);
    	//If the other block is also layered snow.
    	if (otherBlock == Blocks.snow_layer) {
    		int otherMeta = world.getBlockMetadata(x, y, z);
            switch(side) {
            case 0://-y
            	return false;
            case 1://+y
                if (world.getBlockMetadata(x, y - 1, z) == 7) return false;
                break;
            case 2://-z
                if (world.getBlockMetadata(x, y, z + 1) <= otherMeta) return false;
                break;
            case 3://+z
                if (world.getBlockMetadata(x, y, z - 1) <= otherMeta) return false;
                break;
            case 4://-x
                if (world.getBlockMetadata(x + 1, y, z) <= otherMeta) return false;
                break;
            case 5://+x
                if (world.getBlockMetadata(x - 1, y, z) <= otherMeta) return false;
            }
    	}
    	boolean otherOpaque = otherBlock.isOpaqueCube();
    	//If this is any side but the top and the other block is opaque.
    	if(side != 1 & otherOpaque) {
    		return false;
    	}
    	//If this is the top and the other block is opaque and this is full height.
    	if (side == 1 & otherOpaque && world.getBlockMetadata(x, y - 1, z) == 7) {
    		return false;
    	}
    	return true;
    }

}
