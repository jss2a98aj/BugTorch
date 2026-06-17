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
    public boolean isValidVillager(IMerchant merchant, boolean locationAlreadyChecked) {
        if(!(merchant instanceof EntityLiving living)) {
            return false;
        }
        if(living instanceof EntityVillager villager && villager.getProfession() == VillageNamesSupport.nitwit) {
            return false;
        }
        if(living.isChild()) {
            return false;
        }
        return locationAlreadyChecked || this.getAABB().isVecInside(Vec3.createVectorHelper(living.posX, living.posY, living.posZ));
    }

    @Shadow(remap = false)
    public abstract AxisAlignedBB getAABB();

}
