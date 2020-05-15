package jss.bugtorch.util;

import cpw.mods.fml.common.Loader;

public class LoadedMods {

    public static boolean thaumcraftLoaded;

    public static void detectLoadedMods() {
        thaumcraftLoaded = Loader.isModLoaded("Thaumcraft");
    }

}
    
