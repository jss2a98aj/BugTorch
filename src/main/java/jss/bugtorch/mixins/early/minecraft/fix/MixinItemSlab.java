package jss.bugtorch.mixins.early.minecraft.fix;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemSlab.class)
public abstract class MixinItemSlab extends ItemBlock {

    public MixinItemSlab(Block p_i45328_1_) {
        super(p_i45328_1_);
    }

    @Shadow protected boolean field_150948_b;
    @Shadow protected BlockSlab field_150949_c;
    @Shadow protected BlockSlab field_150947_d;

    @Inject(
        method = "onItemUse",
        at = @At("HEAD"),
        cancellable = true
    )
    private void bugtorch$fixSlabMergeReturn(
        ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_,
        CallbackInfoReturnable<Boolean> ctx
    ) {
        if (this.field_150948_b)
        {}
        else if (p_77648_1_.stackSize == 0)
        {}
        else if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {}
        else {
            Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
            int i1 = p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_);
            int j1 = i1 & 7;
            boolean flag = (i1 & 8) != 0;

            if ((p_77648_7_ == 1 && !flag || p_77648_7_ == 0 && flag) && block == this.field_150949_c && j1 == p_77648_1_.getItemDamage()) {
                if (p_77648_3_.checkNoEntityCollision(this.field_150947_d.getCollisionBoundingBoxFromPool(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))) {
                } else {
                    ctx.setReturnValue(false);
                }
            }
        }
    }

}
