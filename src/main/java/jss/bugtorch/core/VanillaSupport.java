package jss.bugtorch.core;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.init.Blocks;

public class VanillaSupport {

    public static void enableSupport() {
    	//Backports
        if(BugTorchConfig.enableFloatingTrapDoors) {
            BlockTrapDoor.disableValidation = true;
        }

        //Bugfixes
        if(BugTorchConfig.fixSnowBlocksRandomlyTicking) {
            Blocks.snow.setTickRandomly(false);
        }
    }

}
