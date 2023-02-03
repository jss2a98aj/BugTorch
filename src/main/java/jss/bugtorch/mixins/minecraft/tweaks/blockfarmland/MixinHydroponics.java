package jss.bugtorch.mixins.minecraft.tweaks.blockfarmland;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockFarmland.class)
public abstract class MixinHydroponics {

    /**
     * @author jss2a98aj
     * @reason Hydroponics.
     */
    @Overwrite()
    private boolean func_149821_m(World world, int x, int y, int z) {
        if (world.getBlock(x, y - 1, z).getMaterial() == Material.water) {
            return true;
        }
        for (int xSearch = x - 4; xSearch <= x + 4; ++xSearch) {
            for (int zSearch = z - 4; zSearch <= z + 4; ++zSearch) {
                if (world.getBlock(xSearch, y, zSearch).getMaterial() == Material.water) {
                    return true;
                }
            }
        }
        return false;
    }

}
