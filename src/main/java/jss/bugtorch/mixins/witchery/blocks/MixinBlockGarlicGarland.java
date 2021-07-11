package jss.bugtorch.mixins.witchery.blocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.BlockGarlicGarland;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

@Mixin(value = BlockGarlicGarland.class, remap = false)
public class MixinBlockGarlicGarland extends BlockBaseContainer {
    
    public MixinBlockGarlicGarland() {
        super(Material.circuits, null);
    }

    @Overwrite()
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        switch(meta) {
        case 2:
            this.setBlockBounds(0.1F, 0.8F, 1.0F, 0.9F, 1.0F, 0.85F);
            break;
        case 3:
            this.setBlockBounds(0.1F, 0.8F, 0.0F, 0.9F, 1.0F, 0.15F);
            break;
        case 4:
            this.setBlockBounds(1.0F, 0.8F, 0.1F, 0.85F, 1.0F, 0.9F);
            break;
        case 5:
            this.setBlockBounds(0.0F, 0.8F, 0.1F, 0.15F, 1.0F, 0.9F);
            break;
        default:
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
     }

}
