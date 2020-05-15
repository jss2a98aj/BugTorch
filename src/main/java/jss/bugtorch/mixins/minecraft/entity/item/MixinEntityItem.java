package jss.bugtorch.mixins.minecraft.entity.item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(EntityItem.class)
public abstract class MixinEntityItem extends Entity {

    public MixinEntityItem(World worldIn) {
        super(worldIn);
    }

    /**
     * @author jss2a98aj
     * @reason If an item stack is full don't attempt to stack with nearby stacks
     */
    @Inject(method = "searchForOtherItemsNearby", at = @At("HEAD"), cancellable = true)
    private void onSearchForOtherItemsNearby (CallbackInfo ci) {
        final ItemStack stack = this.getDataWatcher().getWatchableObjectItemStack(10);
        if (stack.stackSize >= stack.getMaxStackSize()) {
            //System.out.println("Cancelled searchForOtherItemsNearby due to max stack size");
            ci.cancel();
        }
    }

}
