package jss.bugtorch.mixins.witchery.fix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.emoniph.witchery.blocks.BlockGarlicGarland;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

@Mixin(value = BlockGarlicGarland.class, remap = false)
public abstract class MixinBlockGarlicGarland extends Block {

	protected MixinBlockGarlicGarland(Material material) {
		super(material);
	}

	/**
	 * @author jss2a98aj
	 * @reason Corrects a bound that had a coordinate added to it.
	 */
	@Overwrite()
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch(meta) {
		case 2:
			setBlockBounds(0.1F, 0.8F, 1.0F, 0.9F, 1.0F, 0.85F);
			break;
		case 3:
			setBlockBounds(0.1F, 0.8F, 0.0F, 0.9F, 1.0F, 0.15F);
			break;
		case 4:
			setBlockBounds(1.0F, 0.8F, 0.1F, 0.85F, 1.0F, 0.9F);
			break;
		case 5:
			setBlockBounds(0.0F, 0.8F, 0.1F, 0.15F, 1.0F, 0.9F);
			break;
		default:
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

}
