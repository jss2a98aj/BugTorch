package jss.bugtorch.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.launchwrapper.Launch;

public class BugTorchMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        BugTorchConfig.initMixinConfig(new File(Launch.minecraftHome, "config" + File.separator + BugTorchCore.MODID + File.separator + "mixins.cfg"));
        List<String> mixins = new ArrayList<>();
        
        //Backports
        if(BugTorchConfig.cobwebsCanBeSheared)  mixins.add("blocks.MixinBlockWeb");
        if(BugTorchConfig.deadBushesDropSticks) mixins.add("blocks.MixinBlockDeadBush");
        if(BugTorchConfig.fireArrowsDetonateTNTCarts) mixins.add("entity.MixinEntityMinecraftTNT");
        if(BugTorchConfig.throwEnderPearlsInCrativeMode) mixins.add("items.MixinItemEnderPearl");

        //Bugfixes
        if(BugTorchConfig.fixFireChargeUseSound) mixins.add("items.MixinItemFireball");
        if(BugTorchConfig.fixLavaHissOnAirReplace) mixins.add("blocks.MixinBlockLiquid");
        if(BugTorchConfig.fixPumpkinPlacementCheck) mixins.add("blocks.MixinBlockPumpkin");
        if(BugTorchConfig.fixStoneMonsterEggDoubleSpawns) mixins.add("blocks.MixinBlockSilverfish");
        if(BugTorchConfig.fixShearedBlocksDropExtraItems) mixins.add("blocks.MixinBlock");
        if(BugTorchConfig.fixSignPacketChatMessages) mixins.add("client.network.MixinNetHandlerPlayClient");//Confirm
        if(BugTorchConfig.fixVillageSieges) mixins.add("village.MixinVillageSiege");//Confirm

        //Performance
        if(BugTorchConfig.skipInitialWorldChunkLoad) mixins.add("server.MixinMinecraftServer");
        
        //Tweaks
        //if(BugTorchConfig.adjustGanysSurfaceTrapdoorSpineTextures) mixins.add("mods.ganyssurface.blocks.MixinBlockWoodTrapdoor");
        
        //mixins.add("entity.passive.MixinEntityAnimal");
        //mixins.add("blocks.MixinBlockHugeMushroom");

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

}
