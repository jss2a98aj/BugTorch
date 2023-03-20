package jss.bugtorch.modsupport;

import com.rwtema.extrautils.ExtraUtils;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.block.Block;

public class ExtraUtilitiesSupport {

    public static void enableSupport() {
        //Bugfixes
        if(BugTorchConfig.fixExtraUtilitiesBlockSounds) {
            if(ExtraUtils.chandelier != null) {
                ExtraUtils.chandelier.stepSound = Block.soundTypeWood;
            }
            if(ExtraUtils.curtain != null) {
                ExtraUtils.curtain.stepSound = Block.soundTypeCloth;
            }
            if(ExtraUtils.decorative2 != null) {
                ExtraUtils.decorative2.stepSound = Block.soundTypeGlass;
            }
            if(ExtraUtils.magnumTorch != null) {
                ExtraUtils.magnumTorch.stepSound = Block.soundTypeWood;
            }
        }
    }

}
