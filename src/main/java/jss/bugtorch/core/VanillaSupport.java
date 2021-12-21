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
		if(BugTorchConfig.fixJackOLanternBlocksRandomlyTicking) {
			Blocks.lit_pumpkin.setTickRandomly(false);
		}

		if(BugTorchConfig.fixPumpkinBlocksRandomlyTicking) {
			Blocks.pumpkin.setTickRandomly(false);
		}

		if(BugTorchConfig.fixSnowBlocksRandomlyTicking) {
			Blocks.snow.setTickRandomly(false);
		}

		if(BugTorchConfig.fixTorchBlocksRandomlyTicking) {
			Blocks.torch.setTickRandomly(false);
		}
	}

}
