package jss.bugtorch.mixins.late.crayfishfurniture.fix;

import com.mrcrayfish.furniture.tileentity.TileEntityDishwasher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TileEntityDishwasher.class)
public abstract class MixinTileEntityDishwasher {

    /**
     * @author jss2a98aj
     * @reason Stop crashing vanilla and modded content that looks for ISidedInventory.
     */
    @Overwrite
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[]{};
    }

}
