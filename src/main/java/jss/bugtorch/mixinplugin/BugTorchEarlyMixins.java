package jss.bugtorch.mixinplugin;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import jss.bugtorch.BugTorch;
import jss.bugtorch.config.BugTorchConfig;
import jss.bugtorch.mixins.Mixins;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.Name("BugTorchEarlyMixins")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class BugTorchEarlyMixins implements IFMLLoadingPlugin, IEarlyMixinLoader {

	public static boolean txLoaderPresent;

	@Override
	public String getMixinConfig() {
		return "mixins.bugtorch.early.json";
	}

	@Override
	public List<String> getMixins(Set<String> loadedCoreMods) {
		String configFolder = "config" + File.separator + BugTorch.MODID + File.separator;
		BugTorchConfig.loadBaseMixinConfig(new File(Launch.minecraftHome, configFolder + "mixins.cfg"));
		BugTorchConfig.loadModdedMixinConfig(new File(Launch.minecraftHome, configFolder + "mixinsModSupport.cfg"));
		return IMixins.getEarlyMixins(Mixins.class, loadedCoreMods);
	}

	@Override
	public String[] getASMTransformerClass() {
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
