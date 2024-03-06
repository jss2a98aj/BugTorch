package jss.bugtorch.mixins.early.minecraft.worldgen;

import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.IPlantable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

@Mixin(value = StructureComponent.class)
public abstract class MixinStructureComponent {

    /**
     * @author jss2a98aj
     * @reason Makes func_151554_b replace blocks that are considered replaceable.
     */
    @Overwrite()
    protected void func_151554_b(World world, Block block, int metadata, int structX, int structY, int structZ, StructureBoundingBox sbb) {
        final int x = getXWithOffset(structX, structZ);
        int y = getYWithOffset(structY);
        final int z = getZWithOffset(structX, structZ);

        if(sbb.isVecInside(x, y, z)) {
            Block otherBlock;
            Material material;
            do {
                otherBlock = world.getBlock(x, y, z);
                material = otherBlock.getMaterial();
                if(y > 1 && (material.isReplaceable() || material.isLiquid() || otherBlock instanceof IPlantable || world.isAirBlock(x, y, z))) {
                    world.setBlock(x, y, z, block, metadata, 2);
                    --y;
                } else {
                    break;
                }
            } while(true);
        }
    }

    /**
     * @author jss2a98aj
     * @reason Makes clearCurrentPositionBlocksUpwards treat logs and leaves as air.
     */
    @Overwrite()
    protected void clearCurrentPositionBlocksUpwards(World world, int structX, int structY, int structZ, StructureBoundingBox sbb) {
        final int x = this.getXWithOffset(structX, structZ);
        int y = this.getYWithOffset(structY);
        final int z = this.getZWithOffset(structX, structZ);

        if(!sbb.isVecInside(x, y, z)) {
            return;
        }
        while(y < 255) {
            Block block = world.getBlock(x, y, z);
            if(block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block instanceof BlockLog || block.getMaterial() == Material.vine) {
                return;
            }
            world.setBlock(x, y, z, Blocks.air, 0, 2);
            ++y;
        }
    }

    @Shadow
    protected abstract int getXWithOffset(int x, int z);
    @Shadow
    protected abstract int getYWithOffset(int y);
    @Shadow
    protected abstract int getZWithOffset(int x, int z);

}
