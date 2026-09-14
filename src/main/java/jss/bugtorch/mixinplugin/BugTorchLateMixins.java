package jss.bugtorch.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import jss.bugtorch.mixins.Mixins;

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
        return IMixins.getLateMixins(Mixins.class, loadedMods);
    }

}
