package jss.bugtorch.mixins.minecraft.world.gen.structure;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Path;

@Mixin(value = Path.class)
public abstract class MixinStructureVillagePieces_Path extends StructureVillagePieces.Road {

	/**
	 * @author jss2a98aj
	 * @reason Keeps flowers and grass from being left above village paths
	 */
	@Overwrite()
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structBoundingBox) {
		Block block = func_151558_b(Blocks.gravel, 0);

		for (int x = boundingBox.minX; x <= boundingBox.maxX; ++x) {
			for (int z = boundingBox.minZ; z <= boundingBox.maxZ; ++z) {
				if (structBoundingBox.isVecInside(x, 64, z)) {
					int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
					world.setBlock(x, y, z, block, 0, 2);
					if(!world.getBlock(x, y + 1, z).canBlockStay(world, x, y + 1, z)) {
						world.setBlock(x, y + 1, z, Blocks.air, 0, 2);
					}
				}
			}
		}
		return true;
	}

}
