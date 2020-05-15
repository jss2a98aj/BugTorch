package jss.bugtorch.core;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jss.bugtorch.config.BugTorchConfig;
import jss.bugtorch.modsupport.ThaumcraftSupport;
import jss.bugtorch.util.LoadedMods;

@Mod(
        modid = BugTorchCore.MODID,
        name = BugTorchCore.NAME,
        version = "1.0.2",
        dependencies = "after:spongemixins@[1.1.0,);after:Thaumcraft;"
    )

public class BugTorchCore {

    public static final String MODID = "bugtorch";
    public static final String NAME = "Bug Torch";

    public static final Logger logger = LogManager.getLogger(MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BugTorchConfig.loadBaseConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator + "base.cfg"));
        BugTorchConfig.loadModdedConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator + "modSupport.cfg"));

        LoadedMods.detectLoadedMods();
        
        VanillaSupport.enableSupport();
        
        if(LoadedMods.thaumcraftLoaded) {
            ThaumcraftSupport.enableSupport();
        }
    }

}
