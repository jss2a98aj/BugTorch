package jss.bugtorch.mixins.minecraft.optimization;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

@Mixin(value = BlockChest.class)
public abstract class MixinBlockChest extends BlockContainer {

	protected MixinBlockChest(Material material) {
		super(material);
	}

	@Shadow()
	@Final
	private Random field_149955_b;

	/**
	 * @author jss2a98aj
	 * @reason Splitting dropped item stacks just for them to stack right after is basically pointless.
	 */
	@Overwrite()
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntityChest tileEntityChest = (TileEntityChest) world.getTileEntity(x, y, z);
		if (tileEntityChest != null) {
			for (int slot = 0; slot < tileEntityChest.getSizeInventory(); ++slot) {
				ItemStack itemStack = tileEntityChest.getStackInSlot(slot);
				if (itemStack != null) {
					float randX = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
					float randY = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
					float randZ = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem = new EntityItem(world, (double) ((float) x + randX),
							(double) ((float) y + randY), (double) ((float) z + randZ), itemStack);
					float motionMult = 0.05F;
					entityitem.motionX = (double) ((float) this.field_149955_b.nextGaussian() * motionMult);
					entityitem.motionY = (double) ((float) this.field_149955_b.nextGaussian() * motionMult + 0.2F);
					entityitem.motionZ = (double) ((float) this.field_149955_b.nextGaussian() * motionMult);
					if (itemStack.hasTagCompound()) {
						entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
					}
					world.spawnEntityInWorld(entityitem);
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

}
