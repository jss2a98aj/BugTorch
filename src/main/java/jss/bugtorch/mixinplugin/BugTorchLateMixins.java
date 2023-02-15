package jss.bugtorch.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import jss.bugtorch.BugTorch;
import jss.bugtorch.config.BugTorchConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class BugTorchLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.bugtorch.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {

        if(!loadedMods.contains("ganyssurface")) {
            BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture = false;
        }
        if(!loadedMods.contains("Thaumcraft")) {
            BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds = false;
        }
        if(!loadedMods.contains("witchery")) {
            BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds = false;
            BugTorchConfig.fixWitcheryLeavesOptifineRendering = false;
            BugTorchConfig.fixWitcheryLeavesShearDupe = false;
        }
        if(!loadedMods.contains("aether")) {
            BugTorchConfig.reuseAetherIIRenderPlayer = false;
        }

        BugTorch.logger.info("Kicking off BugTorch late mixins.");
        boolean client = FMLLaunchHandler.side().isClient();
        List<String> mixins = new ArrayList<>();

        //Mod bugfixes
        if(client && BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture) {
            mixins.add("ganyssurface.rendering.MixinBlockWoodTrapdoor");
        }
        if(BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds) {
            if(client) {
                mixins.add("thaumcraft.sanitizearrayaccess.MixinBlockCandleRenderer");
            }
            mixins.add("thaumcraft.sanitizearrayaccess.MixinBlockCandle");
        }
        if(BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds) {
            mixins.add("witchery.fix.MixinBlockGarlicGarland");
        }
        if(client && BugTorchConfig.fixWitcheryLeavesOptifineRendering) {
            mixins.add("witchery.rendering.MixinBlockWitchLeaves");
        }
        if(BugTorchConfig.fixWitcheryLeavesShearDupe) {
            mixins.add("witchery.shearing.MixinBlockWitchLeaves");
        }
        if(client && BugTorchConfig.reuseAetherIIRenderPlayer) {
            mixins.add("aetherii.optimization.MixinClientEventHandler");
        }

        return mixins;
    }

}
