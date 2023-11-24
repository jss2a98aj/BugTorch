package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EntityLeashKnot.class)
public abstract class MixinEntityLeashKnot extends EntityHanging {

    /**
     * @author jss2a98aj
     * @reason Prevent leads from breaking after being used with some modded fences.
     */
    @Overwrite
    public boolean onValidSurface() {
        Block block = worldObj.getBlock(field_146063_b, field_146064_c, field_146062_d);
        return block.getRenderType() == 11 || block instanceof BlockFence;
    }

    public MixinEntityLeashKnot(World worldIn) { super(worldIn); }

}
