package jss.bugtorch.modsupport;

import jss.bugtorch.config.BugTorchConfig;

public class VillageNamesSupport {

	public static void enableSupport() {
		//Tweaks
		if(BugTorchConfig.enableVillageNamesMetadataSensitiveTrades) {
			astrotibs.villagenames.VillageNames.canVillagerTradesDistinguishMeta = true;
		}
	}

}
