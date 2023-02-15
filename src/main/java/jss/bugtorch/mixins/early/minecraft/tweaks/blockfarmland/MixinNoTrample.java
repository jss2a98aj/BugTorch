package jss.bugtorch.mixins.early.minecraft.tweaks.blockfarmland;

import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockFarmland.class)
public abstract class MixinNoTrample {

    /**
     * @author jss2a98aj
     * @reason I don't like crops getting trampled.
     */
    @Overwrite()
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance) {
    }

}
