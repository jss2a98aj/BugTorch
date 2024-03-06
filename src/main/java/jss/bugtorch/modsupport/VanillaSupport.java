package jss.bugtorch.modsupport;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.init.Blocks;

public class VanillaSupport {

	public static void enableSupport() {
		//Backports
		if(BugTorchConfig.enableFloatingTrapDoors) {
			BlockTrapDoor.disableValidation = true;
		}

		//Bugfixes
		if(BugTorchConfig.fixBlockSounds) {
			Blocks.bed.setStepSound(Block.soundTypeCloth);
			Blocks.tripwire.setStepSound(Block.soundTypeCloth);
			Blocks.web.setStepSound(Block.soundTypeCloth);

			Blocks.redstone_wire.setStepSound(Block.soundTypeSand);

			Blocks.jukebox.setStepSound(Block.soundTypeWood);
			Blocks.noteblock.setStepSound(Block.soundTypeWood);
			Blocks.tripwire_hook.setStepSound(Block.soundTypeWood);

			Blocks.heavy_weighted_pressure_plate.setStepSound(Block.soundTypeMetal);
			Blocks.light_weighted_pressure_plate.setStepSound(Block.soundTypeMetal);
		}

        if(BugTorchConfig.fixCarpetBlocksRandomlyTicking) {
            Blocks.carpet.setTickRandomly(false);
        }

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
