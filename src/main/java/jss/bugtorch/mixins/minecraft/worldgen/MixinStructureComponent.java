package jss.bugtorch.mixins.minecraft.worldgen;

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
        final int i1 = getXWithOffset(x, z);
        int j1 = getYWithOffset(y);
        final int k1 = getZWithOffset(x, z);

        if (sbb.isVecInside(i1, j1, k1)) {
            Material material;
            boolean stopPointFound = false;
            do {
                material = world.getBlock(i1, j1, k1).getMaterial();
                if(j1 > 1 && (material.isReplaceable() || material.isLiquid() || world.isAirBlock(i1, j1, k1))) {
                     world.setBlock(i1, j1, k1, block, metadata, 2);
                     --j1;
                } else {
                    stopPointFound = true;
                }
            } while(!stopPointFound);
        }
    }

}
