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

@Mixin(Path.class)
public class MixinStructureVillagePieces_Path extends StructureVillagePieces.Road {

    /**
     * @author jss2a98aj
     * @reason Keeps flowers and grass from being left above village paths
     */
    @Overwrite
    public boolean addComponentParts(World worldIn, Random random, StructureBoundingBox boundingBox) {
        Block block = this.func_151558_b(Blocks.gravel, 0);

        for (int x = this.boundingBox.minX; x <= this.boundingBox.maxX; ++x) {
            for (int z = this.boundingBox.minZ; z <= this.boundingBox.maxZ; ++z) {
                if (boundingBox.isVecInside(x, 64, z)) {
                    int y = worldIn.getTopSolidOrLiquidBlock(x, z) - 1;
                    worldIn.setBlock(x, y, z, block, 0, 2);
                    if(!worldIn.getBlock(x, y + 1, z).canBlockStay(worldIn, x, y + 1, z)) {
                        worldIn.setBlock(x, y + 1, z, Blocks.air, 0, 2);
                    }
                }
            }
        }
        return true;
    }
    
}
