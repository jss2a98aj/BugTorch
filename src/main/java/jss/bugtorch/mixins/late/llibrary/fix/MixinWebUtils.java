package jss.bugtorch.mixins.late.llibrary.fix;

import net.ilexiconn.llibrary.server.util.WebUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = WebUtils.class, remap = false)
public abstract class MixinWebUtils {

    /**
     * @author jss2a98aj
     * @reason Fixes a crash caused by invalid data being provided for unknown reasons.
     */
    @Overwrite
    public static String readPastebin(String pasteID) {
        if(pasteID.equals("aLjMgBAV")) {
            return "[]";
        }
        return readURL("http://pastebin.com/raw.php?i=" + pasteID);
    }

    @Shadow
    public static String readURL(String url) {
        return null;
    }


}
