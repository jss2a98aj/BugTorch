package jss.bugtorch.mixins.late.crayfishfurniture.tweak;

import com.mrcrayfish.furniture.FurnitureAchievements;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = FurnitureAchievements.class)
public abstract class MixinFurnitureAchievements {

    /**
     * @author jss2a98aj
     * @reason Disables achievements.
     */
    @Overwrite(remap = false)
    public static void loadAchievements() {}

    /**
     * @author jss2a98aj
     * @reason Disables achievements.
     */
    @Overwrite(remap = false)
    public static void registerPage() {}

    /**
     * @author jss2a98aj
     * @reason Disables achievements.
     */
    @Overwrite(remap = false)
    public static void triggerAchievement(EntityPlayer player, String name) {}

}
