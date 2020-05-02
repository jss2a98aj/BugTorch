package jss.bugtorch.mixins.entity.passive;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(EntityAnimal.class)
abstract public class MixinEntityAnimal extends EntityAgeable {

    public MixinEntityAnimal(World worldIn) {
        super(worldIn);
    }

    @Shadow
    private int inLove;
    
    @Shadow
    public boolean isBreedingItem(ItemStack itemStack) {
        return true;
    }
    
    @Shadow
    public void func_146082_f(EntityPlayer playerIn) {
    }
    
    
    @Overwrite
    public boolean interact(EntityPlayer playerIn) {
        ItemStack itemstack = playerIn.inventory.getCurrentItem();

        if (itemstack != null && this.isBreedingItem(itemstack)) {
            if (this.getGrowingAge() == 0 && this.inLove <= 0){
                if (!playerIn.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                    if (itemstack.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, (ItemStack)null);
                    }
                }
                this.func_146082_f(playerIn);
                return true;
            } else if (this.isChild()) {
                if (!playerIn.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                    if (itemstack.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, (ItemStack)null);
                    }
                }
                this.addGrowth((int)((float)(-this.getGrowingAge() / 20) * 0.1F));
                return true;
            }
            

        }

        return super.interact(playerIn);
    }
    
}
