package jss.bugtorch.mixins.early.minecraft.optimization;

import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
@Mixin(value = GameSettings.Options.class)
public abstract class MixinGameSettings_Options {

    /**
     * @author jss2a98aj
     * @reason Makes this function not as unreasonably slow.
     */
    @Overwrite
    public static GameSettings.Options getEnumOptions(int ordinal) {
        GameSettings.Options[] options = GameSettings.Options.values();
        if(ordinal >= options.length | ordinal < 0) {
            return null;
        } else {
            return options[ordinal];
        }
    }

}
