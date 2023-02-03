package jss.bugtorch.mixins.minecraft.shearing;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockLeaves.class)
public abstract class MixinBlockLeaves extends BlockLeavesBase {

    MixinBlockLeaves(Material material, boolean renderFlag) {
        super(material, renderFlag);
    }

    /**
     * @author jss2a98aj
     * @reason Prevents duplicate drops when shearing.
     */
    @Overwrite()
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if (!(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemShears)) {
            super.harvestBlock(world, player, x, y, z, meta);
        }
    }

}
