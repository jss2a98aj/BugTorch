package jss.bugtorch.mixins.minecraft.backport;

import java.util.ArrayList;
import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

@Mixin(value = BlockWeb.class)
public abstract class MixinBlockWeb extends Block implements IShearable {

	protected MixinBlockWeb(Material material) {
		super(material);
	}

	/**
	 * @author jss2a98aj
	 * @reason Allows spiderwebs to be sheared without silk touch.
	 */
	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.web, 1, 0)));
	}

	/**
	 * @author jss2a98aj
	 * @reason Prevents duplicate drops when shearing.
	 */
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		if (!(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemShears)) {
			super.harvestBlock(world, player, x, y, z, meta);
		}
	}

}
