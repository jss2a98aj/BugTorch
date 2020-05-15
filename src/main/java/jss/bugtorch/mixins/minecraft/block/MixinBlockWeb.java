package jss.bugtorch.mixins.minecraft.block;

import java.util.ArrayList;
import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BlockWeb;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

@Mixin(BlockWeb.class)
public class MixinBlockWeb implements IShearable {

    /**
     * @author jss2a98aj
     * @reason Allows spiderwebs to be sheared without the need for silk touch
     */
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Blocks.web, 1, 0)));
    }

}
