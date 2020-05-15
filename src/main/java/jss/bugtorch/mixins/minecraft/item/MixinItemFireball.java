package jss.bugtorch.mixins.minecraft.item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import jss.bugtorch.core.BugTorchCore;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(ItemFireball.class)
public class MixinItemFireball extends Item {

    /**
     * @author jss2a98aj
     * @reason Corrects the fire charge use sound
     */
    @Overwrite
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            switch(side) {
            case 0:
                --y;
                break;
            case 1:
                ++y;
                break;
            case 2:
                --z;
                break;
            case 3:
                ++z;
                break;
            case 4:
                --x;
                break;
            case 5:
                ++x;
                break;
            }

            if (!playerIn.canPlayerEdit(x, y, z, side, stack)) {
                return false;
            } else {
                if (worldIn.getBlock(x, y, z).getMaterial() == Material.air) {
                    worldIn.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, BugTorchCore.MODID + ":item.fireCharge.use", 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
                    worldIn.setBlock(x, y, z, Blocks.fire);
                }

                if (!playerIn.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }

                return true;
            }
        }
    }

}
