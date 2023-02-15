package jss.bugtorch.mixins.late.thaumcraft.sanitizearrayaccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import thaumcraft.common.blocks.BlockCandle;
import thaumcraft.common.lib.utils.Utils;

@Mixin(value = BlockCandle.class)
public abstract class MixinBlockCandle extends Block {

	protected MixinBlockCandle(Material material) {
		super(material);
	}

	/**
	 * @author jss2a98aj
	 * @reason Prevents an array out of bounds exception when metadata greater than 15 is used.
	 */
	@Overwrite()
	public int getRenderColor(int meta) {
		return Utils.colors[meta >= Utils.colors.length ? 0 : meta];
	}

	/**
	 * @author jss2a98aj
	 * @reason Prevents an array out of bounds exception when metadata greater than 15 is used.
	 */
	@Overwrite()
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return Utils.colors[meta >= Utils.colors.length ? 0 : meta];
	}

}
