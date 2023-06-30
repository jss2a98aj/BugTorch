package jss.bugtorch.mixins.late.extrautils.tweaks;

import com.rwtema.extrautils.tileentity.TileEntityTradingPost;
import jss.bugtorch.modsupport.VillageNamesSupport;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = TileEntityTradingPost.class)
public abstract class MixinTileEntityTradingPost {

    /**
     * @author jss2a98aj
     * @reason Filter out invalid Nitwit trades from Village Names
     */
    @Overwrite(remap = false)
    public boolean isValidVillager(IMerchant villager, boolean locationAlreadyChecked) {
        if(!(villager instanceof EntityLiving)) {
            return false;
        }
        EntityVillager entity = (EntityVillager)villager;
        boolean mature = !entity.isChild() && entity.getProfession() != VillageNamesSupport.nitwit;
        return mature && (locationAlreadyChecked || getAABB().isVecInside(Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ)));
    }

    @Shadow
    public AxisAlignedBB getAABB() { return null; }

}
