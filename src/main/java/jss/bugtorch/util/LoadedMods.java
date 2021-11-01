package jss.bugtorch.util;

import cpw.mods.fml.common.Loader;

public class LoadedMods {

	public static boolean pamsTemperatePlantsLoaded;
    public static boolean thaumcraftLoaded;
    public static boolean villageNamesLoaded;

    public static void detectLoadedMods() {
    	pamsTemperatePlantsLoaded = Loader.isModLoaded("temperateplants");
        thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
        villageNamesLoaded = Loader.isModLoaded("VillageNames");
    }

}
