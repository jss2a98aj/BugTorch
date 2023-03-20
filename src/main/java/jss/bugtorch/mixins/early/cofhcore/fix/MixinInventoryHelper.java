package jss.bugtorch.mixins.early.cofhcore.fix;

import cofh.lib.util.helpers.InventoryHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

@Mixin(value = InventoryHelper.class, remap = false)
public abstract class MixinInventoryHelper {

    /**
     * @author jss2a98aj
     * @reason Fix item dupes and inventory loss from CoFHLib by replacing this mergeItemStack with an adaption of MixinsContainer's.
     */
    @Overwrite(remap = false)
    public static boolean mergeItemStack(List<Slot> inventorySlots, ItemStack input, int start, int length, boolean reverse, boolean unused) {
        boolean couldMerge = false;
        int index = reverse ? length - 1 : start;
        int increment = reverse ? -1 : 1;

        Slot slot;
        ItemStack slotStack;

        if(input.isStackable()) {
            while(input.stackSize > 0 && (!reverse && index < length || reverse && index >= start)) {
                slot = inventorySlots.get(index);
                slotStack = slot.getStack();

                if(slotStack != null) {
                    int maxRemovableFromInput = Math.min(input.getMaxStackSize(), slot.getSlotStackLimit());
                    int removeFromInput = Math.min(maxRemovableFromInput, input.stackSize);
                    ItemStack partialStack = input.copy();
                    partialStack.stackSize = removeFromInput;

                    if(slot.isItemValid(partialStack) && slotStack.getItem().equals(input.getItem()) && input.getItemDamage() == slotStack.getItemDamage() && ItemStack.areItemStackTagsEqual(input, slotStack)) {
                        int combinedStackSize = slotStack.stackSize + input.stackSize;

                        if(combinedStackSize <= input.getMaxStackSize()) {
                            slotStack.stackSize = combinedStackSize;
                            slot.onSlotChanged();
                            input.stackSize = 0;
                            couldMerge = true;
                        } else if(slotStack.stackSize < input.getMaxStackSize()) {
                            removeFromInput = maxRemovableFromInput - slotStack.stackSize;
                            slotStack.stackSize = slotStack.getMaxStackSize();
                            slot.onSlotChanged();
                            input.stackSize -= removeFromInput;
                            couldMerge = true;
                        }
                    }
                }
                index += increment;
            }
        }

        index = reverse ? length - 1 : start;

        while(input.stackSize > 0 && (!reverse && index < length || reverse && index >= start)) {
            slot = inventorySlots.get(index);
            slotStack = slot.getStack();

            if(slotStack == null) {
                int removeFromInput = Math.min(slot.getSlotStackLimit(), input.stackSize);
                ItemStack partialStack = input.copy();
                partialStack.stackSize = removeFromInput;

                if(slot.isItemValid(partialStack)) {
                    slot.putStack(partialStack);
                    slot.onSlotChanged();
                    input.stackSize -= removeFromInput;
                    couldMerge = true;
                }
            }
            index += increment;
        }

        return couldMerge;
    }

}
