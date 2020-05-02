package jss.bugtorch.mixins.blocks;

import java.util.ArrayList;
import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

@Mixin(BlockHugeMushroom.class)
public class MixinBlockHugeMushroom implements IShearable {

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z))));
    }

}
