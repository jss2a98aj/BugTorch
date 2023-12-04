package jss.bugtorch.mixins.early.minecraft.worldgen;

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

    @Shadow
    protected int getXWithOffset(int x, int z) {
        return 0;
    }

    @Shadow
    protected int getYWithOffset(int y) {
        return 0;
    }

    @Shadow
    protected int getZWithOffset(int x, int z) {
        return 0;
    }

    /**
     * @author jss2a98aj
     * @reason Makes func_151554_b replace blocks that are considered replaceable.
     */
    @Overwrite()
    protected void func_151554_b(World world, Block block, int metadata, int x, int y, int z, StructureBoundingBox sbb) {
        final int xWithOffset = getXWithOffset(x, z);
        int yWithOffset = getYWithOffset(y);
        final int zWithOffset = getZWithOffset(x, z);

        if(sbb.isVecInside(xWithOffset, yWithOffset, zWithOffset)) {
            Material material;
            Block otherBlock;
            boolean stopPointFound = false;
            do {
                otherBlock = world.getBlock(xWithOffset, yWithOffset, zWithOffset);
                material = otherBlock.getMaterial();
                if(yWithOffset > 1 && (material.isReplaceable() || material.isLiquid() || otherBlock instanceof IPlantable || world.isAirBlock(xWithOffset, yWithOffset, zWithOffset))) {
                     world.setBlock(xWithOffset, yWithOffset, zWithOffset, otherBlock, metadata, 2);
                     --yWithOffset;
                } else {
                    stopPointFound = true;
                }
            } while(!stopPointFound);
        }
    }

}
