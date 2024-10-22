package jss.bugtorch.modsupport;

import astrotibs.villagenames.VillageNames;
import jss.bugtorch.config.BugTorchConfig;

public class VillageNamesSupport {

    public static int nitwit = 5;

	public static void enableSupport() {
		//Tweaks
		if(BugTorchConfig.enableVillageNamesMetadataSensitiveTrades) {
			VillageNames.canVillagerTradesDistinguishMeta = true;
		}
	}

}
