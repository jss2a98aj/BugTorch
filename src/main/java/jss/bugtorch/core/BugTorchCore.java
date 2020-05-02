package jss.bugtorch.core;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.block.BlockTrapDoor;

@Mod(
        modid = BugTorchCore.MODID,
        name = "Bug Spray with a Lighter",
        version = "1.0.0",
        dependencies = "after:spongemixins@[1.1.0,);"
    )

public class BugTorchCore {
    
    public static final String MODID = "bugtorch";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BugTorchConfig.initConfig(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + MODID + File.separator + "base.cfg"));
        
        if(BugTorchConfig.enableFloatingTrapDoors) {
            BlockTrapDoor.disableValidation = true;
        }
    }
    
}
