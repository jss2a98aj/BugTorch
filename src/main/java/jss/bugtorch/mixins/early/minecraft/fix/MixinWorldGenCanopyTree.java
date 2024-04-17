package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WorldGenCanopyTree.class)
public abstract class MixinWorldGenCanopyTree extends WorldGenAbstractTree {

    /**
     * @author jss2a98aj
     * @reason Prevent unexpected breakage of blocks
     */
    @Redirect(
        method = "generate(Lnet/minecraft/world/World;Ljava/util/Random;III)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/gen/feature/WorldGenCanopyTree;setBlockAndNotifyAdequately(Lnet/minecraft/world/World;IIILnet/minecraft/block/Block;I)V",
            ordinal = 4
        )
    )
    void ifReplaceableSetBlockAndNotifyAdequately(WorldGenCanopyTree gen, World world, int x, int y, int z, Block block, int meta) {
        if(isReplaceable(world, x, y, z)) {
            setBlockAndNotifyAdequately(world, x, y, z, block, meta);
        }
    }

    private MixinWorldGenCanopyTree(boolean doBlockNotify) {
        super(doBlockNotify);
    }

}
