package jss.bugtorch.mixins.early.minecraft.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = World.class)
public abstract class MixinWorld implements IBlockAccess {

    /**
     * @author jss2a98aj
     * @reason Stops structures and mobs from spawning on trees.
     */
    @Overwrite
	public int getTopSolidOrLiquidBlock(int x, int z) {
		Chunk chunk = getChunkFromBlockCoords(x, z);
		int chunkX = x &= 15;
		int chunkZ = z &= 15;
		for(int y = chunk.getTopFilledSegment() + 15; y > 0; --y) {
			Block block = chunk.getBlock(chunkX, y, chunkZ);
			if(block.getMaterial().blocksMovement() && !block.isLeaves(this, x, y, z) && !block.isFoliage(this, x, y, z) && !(block instanceof BlockLog)) {
				return y + 1;
			}
		}
		return -1;
	}

	@Shadow
	public abstract Chunk getChunkFromBlockCoords(int x, int z);

}
