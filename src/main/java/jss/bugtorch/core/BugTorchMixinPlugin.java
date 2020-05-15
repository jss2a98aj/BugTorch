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
        if(BugTorchConfig.cobwebsCanBeSheared)  mixins.add("minecraft.block.MixinBlockWeb");
        if(BugTorchConfig.deadBushesDropSticks) mixins.add("minecraft.block.MixinBlockDeadBush");
        if(BugTorchConfig.fireArrowsDetonateTNTCarts) mixins.add("minecraft.entity.item.MixinEntityMinecartTNT");
        if(BugTorchConfig.throwEnderPearlsInCrativeMode) mixins.add("minecraft.item.MixinItemEnderPearl");

        //Bugfixes
        if(BugTorchConfig.fixFireChargeUseSound) mixins.add("minecraft.item.MixinItemFireball");
        if(BugTorchConfig.fixLavaHissOnAirReplace) mixins.add("minecraft.block.MixinBlockLiquid");
        if(BugTorchConfig.fixPumpkinPlacementCheck) mixins.add("minecraft.block.MixinBlockPumpkin");
        if(BugTorchConfig.fixStoneMonsterEggDoubleSpawns) mixins.add("minecraft.block.MixinBlockSilverfish");
        if(BugTorchConfig.fixShearedBlocksDropExtraItems) mixins.add("minecraft.block.MixinBlock_ShearsDupeFix");
        if(BugTorchConfig.fixShearsNotTakingDamageFromNormalBlocks) mixins.add("minecraft.item.MixinItemShears");
        if(BugTorchConfig.fixSignPacketChatMessages) mixins.add("minecraft.client.network.MixinNetHandlerPlayClient");//Never had this myself, but some non-vanilla servers cause it frequently.
        if(BugTorchConfig.fixVillagePathsHavePlantsOnTop) mixins.add("minecraft.world.gen.structure.MixinStructureVillagePieces_Path");
        if(BugTorchConfig.fixVillageSieges) mixins.add("minecraft.village.MixinVillageSiege");//Appears to work without issue, but tests using odd village layouts would be good.
        if(BugTorchConfig.fixVillageWellDesertMaterial) mixins.add("minecraft.world.gen.structure.MixinStructureVillagePieces_Well");

        //Performance
        if(BugTorchConfig.brokenChestsDontSplitStacks) mixins.add("minecraft.block.MixinBlockChest");
        if(BugTorchConfig.fasterDroppedItemStackingChecks) mixins.add("minecraft.entity.item.MixinEntityItem");
        if(BugTorchConfig.fasterEntityLivingBaseIsPotionActiveAndSetAir) mixins.add("minecraft.entity.MixinEntityLivingBase");
        if(BugTorchConfig.fasterGetBlockByIdForAirBlocks) mixins.add("minecraft.block.MixinBlock");
        if(BugTorchConfig.fasterSnowBlockTicks) mixins.add("minecraft.block.MixinBlockSnowBlock");
        if(BugTorchConfig.replaceRandomWithXSTRInBlockChest) mixins.add("xstr.block.MixinBlockChest");
        if(BugTorchConfig.replaceRandomWithXSTRInEffectRenderer) mixins.add("xstr.client.particle.MixinEffectRenderer");
        if(BugTorchConfig.replaceRandomWithXSTRInEntity) mixins.add("xstr.entity.MixinEntity");
        if(BugTorchConfig.replaceRandomWithXSTRInMinecraftServer) mixins.add("xstr.server.MixinMinecraftServer");
        if(BugTorchConfig.replaceRandomWithXSTRInRenderItem) mixins.add("xstr.client.renderer.entity.MixinRenderItem");
        if(BugTorchConfig.replaceRandomWithXSTRInWorld) {
            BugTorchCore.logger.info("World.class will use XSTR in place of Random, this changes world generation slightly");
            mixins.add("xstr.world.MixinWorld");
        }
        if(BugTorchConfig.replaceRandomWithXSTRInWorldClient) mixins.add("xstr.client.multiplayer.MixinWorldClient");
        if(BugTorchConfig.skipInitialWorldChunkLoad) mixins.add("minecraft.server.MixinMinecraftServer");

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
