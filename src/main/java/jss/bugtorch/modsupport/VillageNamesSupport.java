package jss.bugtorch.modsupport;

import jss.bugtorch.config.BugTorchConfig;

public class VillageNamesSupport {

    public static int nitwit = 5;

	public static void enableSupport() {
		//Tweaks
		if(BugTorchConfig.enableVillageNamesMetadataSensitiveTrades) {
			astrotibs.villagenames.VillageNames.canVillagerTradesDistinguishMeta = true;
		}
	}

}
