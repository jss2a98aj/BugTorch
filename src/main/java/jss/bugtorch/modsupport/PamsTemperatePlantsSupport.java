package jss.bugtorch.modsupport;

import jss.bugtorch.config.BugTorchConfig;

public class PamsTemperatePlantsSupport {

	public static void enableSupport() {
		//Bugfixes
		if(BugTorchConfig.fixPamsTemperatePlantsBlockSounds) {
			com.pam.temperateplants.temperateplants.pamtemperatePlant.setStepSound(net.minecraft.block.Block.soundTypeGrass);
		}
	}

}
