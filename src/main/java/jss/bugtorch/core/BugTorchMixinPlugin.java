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
        BugTorchConfig.loadBaseMixinConfig(new File(Launch.minecraftHome, "config" + File.separator + BugTorchCore.MODID + File.separator + "mixins.cfg"));
        BugTorchConfig.loadModdedMixinConfig(new File(Launch.minecraftHome, "config" + File.separator + BugTorchCore.MODID + File.separator + "mixinsModSupport.cfg"));
        List<String> mixins = new ArrayList<>();
        
        //Backports
        if(BugTorchConfig.cobwebsCanBeSheared)  mixins.add("minecraft.blocks.MixinBlockWeb");
        if(BugTorchConfig.deadBushesDropSticks) mixins.add("minecraft.blocks.MixinBlockDeadBush");
        if(BugTorchConfig.fireArrowsDetonateTNTCarts) mixins.add("minecraft.entity.item.MixinEntityMinecartTNT");
        if(BugTorchConfig.throwEnderPearlsInCrativeMode) mixins.add("minecraft.items.MixinItemEnderPearl");

        //Bugfixes
        if(BugTorchConfig.fixFireChargeUseSound) mixins.add("minecraft.items.MixinItemFireball");
        if(BugTorchConfig.fixLavaHissOnAirReplace) mixins.add("minecraft.blocks.MixinBlockLiquid");
        if(BugTorchConfig.fixPumpkinPlacementCheck) mixins.add("minecraft.blocks.MixinBlockPumpkin");
        if(BugTorchConfig.fixStoneMonsterEggDoubleSpawns) mixins.add("minecraft.blocks.MixinBlockSilverfish");
        if(BugTorchConfig.fixShearedBlocksDropExtraItems) mixins.add("minecraft.blocks.MixinBlock");
        if(BugTorchConfig.fixShearsNotTakingDamageFromNormalBlocks) mixins.add("minecraft.items.MixinItemShears");
        if(BugTorchConfig.fixSignPacketChatMessages) mixins.add("minecraft.client.network.MixinNetHandlerPlayClient");//Confirm
        if(BugTorchConfig.fixVillagePathsHavePlantsOnTop) mixins.add("minecraft.world.gen.structure.MixinStructureVillagePieces_Path");
        if(BugTorchConfig.fixVillageSieges) mixins.add("minecraft.village.MixinVillageSiege");//Confirm
        if(BugTorchConfig.fixVillageWellDesertMaterial) mixins.add("minecraft.world.gen.structure.MixinStructureVillagePieces_Well");

        //Performance
        if(BugTorchConfig.skipInitialWorldChunkLoad) mixins.add("minecraft.server.MixinMinecraftServer");
        if(BugTorchConfig.fasterDroppedItemStackingChecks) mixins.add("minecraft.entity.item.MixinEntityItem");
        
        //Tweaks
        if(BugTorchConfig.lanPortOverride) mixins.add("minecraft.server.integrated.MixinIntegratedServer");

        //Mod bugfixes
        if(BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture) mixins.add("ganyssurface.blocks.MixinBlockWoodTrapdoor");

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

}
