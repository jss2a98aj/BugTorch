package jss.bugtorch.mixins.late.crayfishfurniture.fix;

import com.mrcrayfish.furniture.tileentity.TileEntityWashingMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TileEntityWashingMachine.class)
public abstract class MixinTileEntityWashingMachine {

    /**
     * @author jss2a98aj
     * @reason Stop crashing vanilla and modded content that looks for ISidedInventory.
     */
    @Overwrite
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[]{};
    }

}
