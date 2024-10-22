package jss.bugtorch.modsupport;

import com.pam.temperateplants.temperateplants;

import jss.bugtorch.config.BugTorchConfig;

public class PamsTemperatePlantsSupport {

	public static void enableSupport() {
		//Bugfixes
		if(BugTorchConfig.fixPamsTemperatePlantsBlockSounds) {
			temperateplants.pamtemperatePlant.setStepSound(net.minecraft.block.Block.soundTypeGrass);
		}
	}

}
