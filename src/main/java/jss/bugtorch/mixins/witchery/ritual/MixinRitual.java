package jss.bugtorch.mixins.witchery.ritual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.emoniph.witchery.ritual.Circle;
import com.emoniph.witchery.ritual.RiteRegistry;
import com.emoniph.witchery.ritual.RiteRegistry.Ritual;
import com.emoniph.witchery.ritual.RitualTraits;
import com.emoniph.witchery.ritual.Sacrifice;

import net.minecraft.world.World;

@Mixin(value = Ritual.class, remap = false)
public class MixinRitual {

    @Shadow(remap = false)
    @Final
    Sacrifice initialSacrifice;
    
    @Shadow(remap = false)
    @Final
    EnumSet traits;

    @Shadow(remap = false)
    @Final
    Circle[] circles;
    
    @Shadow(remap = false)
    private int getMaxDistance() {
        return 0;
    }

    @Overwrite(remap = false)
    public boolean isMatch(World world, int posX, int posY, int posZ, Circle[] nearbyCircles, ArrayList entities, ArrayList grassperStacks, boolean isDaytime, boolean isRaining, boolean isThundering) {
        if(
                (!this.traits.contains(RitualTraits.ONLY_AT_NIGHT) || !isDaytime) &&
                (!this.traits.contains(RitualTraits.ONLY_AT_DAY) || isDaytime) &&
                (!this.traits.contains(RitualTraits.ONLY_IN_RAIN) || isRaining) &&
                (!this.traits.contains(RitualTraits.ONLY_IN_STROM) || !isDaytime /*isThundering*/) &&
                (!this.traits.contains(RitualTraits.ONLY_OVERWORLD) || world.provider.dimensionId == 0)
           ) {
           if(this.circles.length > 0) {
              ArrayList circlesToFind = new ArrayList(Arrays.asList(this.circles));
              Circle[] arr$ = nearbyCircles;
              int len$ = nearbyCircles.length;

              for(int i$ = 0; i$ < len$; ++i$) {
                 Circle circle = arr$[i$];
                 circle.removeIfRequired(circlesToFind);
                 if(circlesToFind.size() == 0) {
                    break;
                 }
              }
              if(circlesToFind.size() > 0) {
                 return false;
              }
           }
           return this.initialSacrifice.isMatch(world, posX, posY, posZ, this.getMaxDistance(), entities, grassperStacks);
        } else {
           return false;
        }
     }
    
}
