package jss.bugtorch.mixins.minecraft.fix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

@Mixin(value = BlockLiquid.class)
public abstract class MixinBlockLiquid {

	/**
	 * @author jss2a98aj
	 * @reason Lava should only hiss when mixing with water.
	 */
	@Overwrite()
	protected void func_149799_m(World worldIn, int x, int y, int z) {
		Block block = worldIn.getBlock(x, y, z);
		if (block == Blocks.cobblestone | block == Blocks.obsidian | block == Blocks.stone) {
			worldIn.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
			for (int l = 0; l < 8; ++l) {
				worldIn.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

}

// I would like to do this instead, but the mixin fails to apply?
/*
@Mixin(value = BlockLiquid.class)
public class MixinBlockLiquid extends Block {

	MixinBlockLiquid(Material worldIn) {
		super(worldIn);
	}

	@Shadow
	protected void func_149799_m(World worldIn, int x, int y, int z) {
	}

	@Overwrite()
	private void func_149805_n(World worldIn, int x, int y, int z) {
		if (worldIn.getBlock(x, y, z) == this) {
			if (this.blockMaterial == Material.lava) {
				if (worldIn.getBlock(x, y, z - 1).getMaterial() == Material.water ||
					worldIn.getBlock(x, y, z + 1).getMaterial() == Material.water ||
					worldIn.getBlock(x - 1, y, z).getMaterial() == Material.water ||
					worldIn.getBlock(x + 1, y, z).getMaterial() == Material.water ||
					worldIn.getBlock(x, y + 1, z).getMaterial() == Material.water ) {
					int l = worldIn.getBlockMetadata(x, y, z);

					if (l == 0) {
						worldIn.setBlock(x, y, z, Blocks.obsidian);
						this.func_149799_m(worldIn, x, y, z);
					} else if (l <= 4) { //Paper Spigot does else if (l > 0)
						worldIn.setBlock(x, y, z, Blocks.cobblestone);
						this.func_149799_m(worldIn, x, y, z);
					}
				}
			}
		}
	}

}
*/
