package jss.bugtorch;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import jss.bugtorch.listeners.BroadcastSettingsRemover;
import jss.bugtorch.listeners.LANButtonRemover;
import jss.bugtorch.modsupport.*;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jss.bugtorch.config.BugTorchConfig;

@Mod(
		modid = BugTorch.MODID,
		name = BugTorch.NAME,
		version = BugTorch.VERSION,
		acceptableRemoteVersions = "*",
		dependencies = "required-after:gtnhmixins@[2.0.0,);after:Thaumcraft;after:temperateplants;after:VillageNames;after:witchery;"
	)
public class BugTorch {

	public static final String MODID = "bugtorch";
	public static final String NAME = "BugTorch";
	public static final String VERSION = "GRADLETOKEN_VERSION";
	public static final Logger logger = LogManager.getLogger(NAME);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		String configFolder =  event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator;
		BugTorchConfig.loadBaseConfig(new File(configFolder + "base.cfg"));
		BugTorchConfig.loadModdedConfig(new File(configFolder + "modSupport.cfg"));

		VanillaSupport.enableSupport();

		if(event.getSide() == Side.CLIENT) {
			if(BugTorchConfig.removeBroadcastSettingsButton) {
				FMLCommonHandler.instance().bus().register(BroadcastSettingsRemover.INSTANCE);
				MinecraftForge.EVENT_BUS.register(BroadcastSettingsRemover.INSTANCE);
			}
		}
        if(event.getSide() == Side.CLIENT) {
            if(BugTorchConfig.removeLANButton) {
                FMLCommonHandler.instance().bus().register(LANButtonRemover.INSTANCE);
                MinecraftForge.EVENT_BUS.register(LANButtonRemover.INSTANCE);
            }
        }

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if(Loader.isModLoaded("ExtraUtilities")) {
			ExtraUtilitiesSupport.enableSupport();
		}

		if(Loader.isModLoaded("temperateplants")) {
			PamsTemperatePlantsSupport.enableSupport();
		}

		if(Loader.isModLoaded("Thaumcraft")) {
			ThaumcraftSupport.enableSupport();
		}

		if(Loader.isModLoaded("torchLevers")) {
			TorchLeversSupport.enableSupport();
		}

		if(Loader.isModLoaded("witchery")) {
			WitcherySupport.enableSupport();
		}

		if(Loader.isModLoaded("VillageNames")) {
			VillageNamesSupport.enableSupport();
		}
	}

}
