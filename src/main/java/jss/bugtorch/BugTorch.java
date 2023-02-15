package jss.bugtorch;

import java.io.File;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import jss.bugtorch.modsupport.VanillaSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jss.bugtorch.config.BugTorchConfig;
import jss.bugtorch.modsupport.PamsTemperatePlantsSupport;
import jss.bugtorch.modsupport.ThaumcraftSupport;
import jss.bugtorch.modsupport.VillageNamesSupport;
import jss.bugtorch.modsupport.WitcherySupport;

@Mod(
		modid = BugTorch.MODID,
		name = BugTorch.NAME,
		version = BugTorch.VERSION,
		acceptableRemoteVersions = "*",
		dependencies = "after:Thaumcraft;after:temperateplants;after:VillageNames;after:witchery;"
	)
public class BugTorch {

	public static final String MODID = "bugtorch";
	public static final String NAME = "BugTorch";
	public static final String VERSION = "GRADLETOKEN_VERSION";
	public static final Logger logger = LogManager.getLogger(MODID);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		String configFolder =  event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator;
		BugTorchConfig.loadBaseConfig(new File(configFolder + "base.cfg"));
		BugTorchConfig.loadModdedConfig(new File(configFolder + "modSupport.cfg"));

		VanillaSupport.enableSupport();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if(Loader.isModLoaded("temperateplants")) {
			PamsTemperatePlantsSupport.enableSupport();
		}

		if(Loader.isModLoaded("Thaumcraft")) {
			ThaumcraftSupport.enableSupport();
		}

		if(Loader.isModLoaded("witchery")) {
			WitcherySupport.enableSupport();
		}

		if(Loader.isModLoaded("VillageNames")) {
			VillageNamesSupport.enableSupport();
		}
	}

}
