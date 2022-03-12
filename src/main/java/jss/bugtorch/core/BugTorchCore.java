package jss.bugtorch.core;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jss.bugtorch.config.BugTorchConfig;
import jss.bugtorch.modsupport.PamsTemperatePlantsSupport;
import jss.bugtorch.modsupport.ThaumcraftSupport;
import jss.bugtorch.modsupport.VillageNamesSupport;
import jss.bugtorch.modsupport.WitcherySupport;
import jss.bugtorch.util.LoadedMods;

@Mod(
		modid = BugTorchCore.MODID,
		name = BugTorchCore.NAME,
		version = Tags.VERSION,
		dependencies = "required-after:spongemixins@[1.3.0,);after:Thaumcraft;after:temperateplants;after:VillageNames;after:witchery;"
	)
public class BugTorchCore {

	public static final String MODID = "bugtorch";
	public static final String NAME = "BugTorch";

	public static final Logger logger = LogManager.getLogger(MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		String configFolder =  event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator;
		BugTorchConfig.loadBaseConfig(new File(configFolder + "base.cfg"));
		BugTorchConfig.loadModdedConfig(new File(configFolder + "modSupport.cfg"));

		LoadedMods.detectLoadedMods();

		VanillaSupport.enableSupport();

		if(LoadedMods.pamsTemperatePlantsLoaded) {
			PamsTemperatePlantsSupport.enableSupport();
		}

		if(LoadedMods.thaumcraftLoaded) {
			ThaumcraftSupport.enableSupport();
		}

		if(LoadedMods.witcheryLoaded) {
			WitcherySupport.enableSupport();
		}

		if(LoadedMods.villageNamesLoaded) {
			VillageNamesSupport.enableSupport();
		}
	}

}
