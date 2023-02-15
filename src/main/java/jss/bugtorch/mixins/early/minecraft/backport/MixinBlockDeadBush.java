package jss.bugtorch.mixins.early.minecraft.backport;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.world.World;

@Mixin(value = BlockDeadBush.class)
public abstract class MixinBlockDeadBush extends BlockBush {

	/**
	 * @author jss2a98aj
	 * @reason Make dead bushes drop sticks.
	 */
	@Overwrite()
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Items.stick;
	}

	/**
	 * @author jss2a98aj
	 * @reason Make dead bushes drop 0-2 items.
	 */
	public int quantityDropped(Random random) {
		return random.nextInt(3);
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
