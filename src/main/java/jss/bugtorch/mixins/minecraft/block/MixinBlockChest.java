package jss.bugtorch.mixins.minecraft.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

@Mixin(BlockChest.class)
public abstract class MixinBlockChest extends BlockContainer {

    protected MixinBlockChest(Material material) {
        super(material);
    }

    @Shadow
    @Final
    private Random field_149955_b;

    /**
     * @author jss2a98aj
     * @reason Splitting dropped item stacks just for them to stack right after is almost pointless. It looks nice(?) but is slow.
     */
    @Overwrite
    public void breakBlock(World worldIn, int x, int y, int z, Block blockIn, int p_149749_6_) {
        TileEntityChest tileentitychest = (TileEntityChest)worldIn.getTileEntity(x, y, z);
        if (tileentitychest != null) {
            for (int i1 = 0; i1 < tileentitychest.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileentitychest.getStackInSlot(i1);
                if (itemstack != null) {
                    float f = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem = new EntityItem(worldIn, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), itemstack);
                    float f3 = 0.05F;
                    entityitem.motionX = (double)((float)this.field_149955_b.nextGaussian() * f3);
                    entityitem.motionY = (double)((float)this.field_149955_b.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double)((float)this.field_149955_b.nextGaussian() * f3);
                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }
                    worldIn.spawnEntityInWorld(entityitem);
                }
            }
            worldIn.func_147453_f(x, y, z, blockIn);
        }
        super.breakBlock(worldIn, x, y, z, blockIn, p_149749_6_);
    }

}
