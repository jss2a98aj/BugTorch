package jss.bugtorch.mixins.witchery.blocks;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.emoniph.witchery.blocks.BlockWitchLeaves;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@Mixin(value = BlockWitchLeaves.class, remap = false)
public abstract class MixinBlockWitchLeaves extends BlockLeavesBase {

	MixinBlockWitchLeaves(Material material, boolean flag) {
		super(material, flag);
	}

	@Shadow(remap = false)
	@SideOnly(Side.CLIENT)
	private IIcon[][] iconsForModes;

	/**
	 * @author jss2a98aj
	 * @reason Optifine and CoFH Tweaks compatible fast/fancy rendering
	 */
	@Overwrite()
	public boolean isOpaqueCube() {
		return Blocks.leaves.isOpaqueCube();
	}

	/**
	 * @author jss2a98aj
	 * @reason Optifine and CoFH Tweaks compatible fast/fancy rendering
	 */
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered (IBlockAccess world, int x, int y, int z, int side) {
        return !world.getBlock(x, y, z).isOpaqueCube();
    }

	/**
	 * @author jss2a98aj
	 * @reason Optifine and CoFH Tweaks compatible fast/fancy rendering
	 */
	@Overwrite()
	@SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata) {
        return (Blocks.leaves.isOpaqueCube() ? iconsForModes[1] : iconsForModes[0])[metadata % 4];
    }

	/**
	 * @author jss2a98aj
	 * @reason Prevents duplicate drops when shearing
	 */
	@Overwrite()
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if (!(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemShears)) {
			super.harvestBlock(world, player, x, y, z, meta);
		}
	}

}
