package jss.bugtorch.mixins.minecraft.block;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.RegistryNamespaced;

@Mixin(Block.class)
public class MixinBlock {

    @Shadow
    @Final
    public static RegistryNamespaced blockRegistry;

    /**
     * @author jss2a98aj
     * @reason Most calls have 0 (air), this makes those calls faster
     */
    @Overwrite
    public static Block getBlockById(int blockId) {
       if(blockId == 0) return Blocks.air;
        Block ret = (Block)blockRegistry.getObjectById(blockId);
        return ret == null ? Blocks.air : ret;
    }

}
