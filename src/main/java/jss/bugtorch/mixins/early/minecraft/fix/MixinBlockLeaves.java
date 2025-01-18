package jss.bugtorch.mixins.early.minecraft.fix;

import java.util.IdentityHashMap;
import java.util.Map;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = BlockLeaves.class, priority = 100)
public abstract class MixinBlockLeaves extends BlockLeavesBase {
    protected MixinBlockLeaves(Material p_i45433_1_, boolean p_i45433_2_) {
        super(p_i45433_1_, p_i45433_2_);
    }

    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    @ModifyVariable(method = "updateTick", at = @At(value = "STORE"), ordinal = 0)
    private byte editLeafDecayVal(byte value,
                                  @Local(argsOnly = true) World world,
                                  @Local(argsOnly = true, ordinal = 0) int x,
                                  @Local(argsOnly = true, ordinal = 1) int y,
                                  @Local(argsOnly = true, ordinal = 2) int z,
                                  @Local(ordinal = 3) int meta) {
        meta &= 3;
        if(this.equals(Blocks.leaves) && meta == 3) { //Jungle leaves
            return 6;
        }
        return value;
    }
}
