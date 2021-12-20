package jss.bugtorch.util;

import cpw.mods.fml.common.Loader;

public class LoadedMods {

	public static boolean pamsTemperatePlantsLoaded;
	public static boolean thaumcraftLoaded;
	public static boolean witcheryLoaded;
	public static boolean villageNamesLoaded;

	public static void detectLoadedMods() {
		pamsTemperatePlantsLoaded = Loader.isModLoaded("temperateplants");
		thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
		witcheryLoaded = Loader.isModLoaded("witchery");
		villageNamesLoaded = Loader.isModLoaded("VillageNames");
	}

}
