package jss.bugtorch.mixins.minecraft.blocks;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

@Mixin(BlockDeadBush.class)
public class MixinBlockDeadBush extends BlockBush {

    /**
     * @author jss2a98aj
     * @reason Make dead bushes drop sticks
     */
    @Overwrite
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return Items.stick;
    }

    public int quantityDropped(Random random) {
        return random.nextInt(3);
    }

}
