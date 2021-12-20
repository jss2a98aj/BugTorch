package jss.bugtorch.mixins.minecraft.block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

@Mixin(value = BlockFarmland.class)
public abstract class MixinBlockFarmland extends Block {

	protected MixinBlockFarmland(Material material) {
		super(material);
	}

	@Shadow
	@SideOnly(Side.CLIENT)
	private IIcon field_149824_a;// wet

	@Shadow
	@SideOnly(Side.CLIENT)
	private IIcon field_149823_b;// dry

	@SideOnly(Side.CLIENT)
	private IIcon sideWet;

	@SideOnly(Side.CLIENT)
	private IIcon sideDry;

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta > 0 ? (side == 1 ? field_149824_a : sideWet) : (side == 1 ? field_149823_b : sideDry);
	}

	/**
	 * @author jss2a98aj
	 * @reason Hydroponics
	 */
	@Overwrite()
	private boolean func_149821_m(World world, int x, int y, int z) {
		if (world.getBlock(x, y - 1, z).getMaterial() == Material.water) {
			return true;
		}
		for (int xSearch = x - 4; xSearch <= x + 4; ++xSearch) {
			for (int zSearch = z - 4; zSearch <= z + 4; ++zSearch) {
				if (world.getBlock(xSearch, y, zSearch).getMaterial() == Material.water) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @author jss2a98aj
	 * @reason I don't like crops getting trampled
	 */
	@Overwrite()
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
	}

	/**
	 * @author jss2a98aj
	 * @reason New side/bottom textures
	 */
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.field_149824_a = iconRegister.registerIcon(this.getTextureName() + "_wet");
		this.field_149823_b = iconRegister.registerIcon(this.getTextureName() + "_dry");
		this.sideWet = iconRegister.registerIcon(this.getTextureName() + "_wet_side");
		this.sideDry = iconRegister.registerIcon(this.getTextureName() + "_dry_side");
	}

}
