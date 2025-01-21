package jss.bugtorch.mixins.early.minecraft.fix;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = BlockLeaves.class, priority = 100)
public abstract class MixinBlockLeaves extends BlockLeavesBase {
    protected MixinBlockLeaves(Material p_i45433_1_, boolean p_i45433_2_) {
        super(p_i45433_1_, p_i45433_2_);
    }

    @ModifyVariable(method = "updateTick", at = @At(value = "STORE"), ordinal = 0)
    private byte editLeafDecayVal(byte value,
                                  @Local(argsOnly = true) World world,
                                  @Local(argsOnly = true, ordinal = 0) int x,
                                  @Local(argsOnly = true, ordinal = 1) int y,
                                  @Local(argsOnly = true, ordinal = 2) int z) {
        if(value == 4) { //Default value
            return 7;
        }
        return value;
    }
}
