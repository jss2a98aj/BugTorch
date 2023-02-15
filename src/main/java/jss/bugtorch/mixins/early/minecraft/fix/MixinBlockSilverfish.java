package jss.bugtorch.mixins.early.minecraft.fix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockSilverfish;
import net.minecraft.world.World;

@Mixin(value = BlockSilverfish.class)
public abstract class MixinBlockSilverfish {

	/**
	 * @author jss2a98aj
	 * @reason Stops two Silverfish from spawning when a player breaks infested blocks.
	 */
	@Overwrite()
	public void dropBlockAsItemWithChance(World world, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
	}

}
