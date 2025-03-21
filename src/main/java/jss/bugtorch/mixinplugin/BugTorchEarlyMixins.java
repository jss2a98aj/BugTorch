package jss.bugtorch.mixinplugin;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import jss.bugtorch.BugTorch;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.ArrayList;
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

		boolean client = FMLLaunchHandler.side().isClient();
		List<String> mixins = new ArrayList<>();

		boolean useNotFineOverlap = true;
		if(loadedCoreMods.contains("jss.notfine.mixinplugin.NotFineEarlyMixins") || loadedCoreMods.contains("com.gtnewhorizons.angelica.loading.AngelicaTweaker")) {
			BugTorch.logger.info("NotFine detected, skipping redundant early mixins.");
			useNotFineOverlap = false;
		}

		txLoaderPresent = client && loadedCoreMods.contains("glowredman.txloader.TXLoaderCore");

		//Backports
		if(BugTorchConfig.cobwebsCanBeSheared) {
			mixins.add("minecraft.backport.MixinBlockWeb");
		}
		if(BugTorchConfig.deadBushesDropSticks) {
			mixins.add("minecraft.backport.MixinBlockDeadBush");
		}
		if(BugTorchConfig.fireArrowsDetonateTNTCarts) {
			mixins.add("minecraft.backport.MixinEntityMinecartTNT");
		}
		if(BugTorchConfig.throwEnderPearlsInCreativeMode) {
			mixins.add("minecraft.backport.MixinItemEnderPearl");
		}

		//Bugfixes
		if(client && BugTorchConfig.fixAnvilSoundTypeStepSound) {
			mixins.add("minecraft.fix.block.MixinSoundTypeAnvil");
		}
		if(BugTorchConfig.fixDarkOakRemovingBlocks) {
			mixins.add("minecraft.fix.MixinWorldGenCanopyTree");
		}
		if(client && useNotFineOverlap && BugTorchConfig.fixEnchantmentBlendFunc) {
			mixins.add("minecraft.rendering.MixinRenderItem");
		}
		if(txLoaderPresent && BugTorchConfig.fixFireChargeUseSound) {
			mixins.add("minecraft.backport.MixinItemFireball");
		}
		if(client && BugTorchConfig.fixLavaHissOnAirReplace) {
			mixins.add("minecraft.fix.MixinBlockLiquid");
		}
		if(BugTorchConfig.fixLeadsBreakingOnSomeFenceInstances) {
			mixins.add("minecraft.fix.MixinEntityLeashKnot");
		}
		if(BugTorchConfig.fixLeafDecayCheckRange) {
			mixins.add("minecraft.fix.MixinBlockLeaves");
		}
		if(BugTorchConfig.fixMergeItemStack) {
			if(loadedCoreMods.contains("cofh.asm.LoadingPlugin")) {
				mixins.add("cofhcore.fix.MixinInventoryHelper");
			} else {
				mixins.add("minecraft.fix.MixinContainer");
			}
		}
		if(BugTorchConfig.fixLilyPadPlacementSide) {
			mixins.add("minecraft.fix.MixinItemLilyPad");
		}
		if(BugTorchConfig.fixMineshaftAirPockets) {
			mixins.add("minecraft.worldgen.MixinStructureStart");
			mixins.add("minecraft.worldgen.MixinStructureMineshaftPieces$Room");
		}
		if(BugTorchConfig.fixNettyConnectionFailureResourceLeak) {
			mixins.add("minecraft.logcleanup.MixinNioSocketChannel");
		}
		if(client && useNotFineOverlap && BugTorchConfig.fixParticleDepthSorting) {
			mixins.add("minecraft.rendering.MixinEffectRenderer");
		}
		if(BugTorchConfig.fixPumpkinPlacementCheck) {
			mixins.add("minecraft.placement.MixinBlockPumpkin");
		}
		if(BugTorchConfig.fixRedstoneTorchMemoryLeak) {
			mixins.add("minecraft.optimization.MixinBlockRedstoneTorch");
		}
		if(BugTorchConfig.fixStoneMonsterEggDoubleSpawns) {
			mixins.add("minecraft.fix.MixinBlockSilverfish");
		}
		if(BugTorchConfig.fixStructureComponentFillReplacement) {
			mixins.add("minecraft.worldgen.MixinStructureComponent");
		}
		if(BugTorchConfig.fixShearedGrassDropDupe) {
			mixins.add("minecraft.shearing.MixinBlockTallGrass");
		}
		if(BugTorchConfig.fixShearedLeavesDropDupe) {
			mixins.add("minecraft.shearing.MixinBlockLeaves");
		}
		if(BugTorchConfig.fixShearsNotTakingDamageFromNormalBlocks) {
			mixins.add("minecraft.shearing.MixinItemShears");
		}
		if(client && BugTorchConfig.fixSignPacketChatMessages) {
			mixins.add("minecraft.logcleanup.MixinNetHandlerPlayClient");
		}
		if(BugTorchConfig.fixVillagePathsHavePlantsOnTop) {
			mixins.add("minecraft.worldgen.MixinStructureVillagePieces_Path");
		}
		if(BugTorchConfig.fixVillagerTradeMetadataDetection) {
			mixins.add("minecraft.villagertrademeta.MixinSlotMerchantResult");
			mixins.add("minecraft.villagertrademeta.MixinMerchantRecipe");
		}
		if(BugTorchConfig.fixVillageSieges) {
			mixins.add("minecraft.fix.MixinVillageSiege");
		}
		if(BugTorchConfig.fixVillageWellDesertMaterial) {
			mixins.add("minecraft.worldgen.MixinStructureVillagePieces_Well");
		}

		if (client && BugTorchConfig.fixLWJGL2OpenALCrash && !(loadedCoreMods.contains("me.eigenraven.lwjgl3ify.core.Lwjgl3ifyCoremod"))) {
			mixins.add("minecraft.fix.MixinSoundManager");
		}

		//Performance
		if(BugTorchConfig.brokenChestsDontSplitStacks) {
			mixins.add("minecraft.optimization.MixinBlockChest");
		}
		if(BugTorchConfig.brokenHoppersDontSplitStacks) {
			mixins.add("minecraft.optimization.MixinBlockHopper");
		}
		if(BugTorchConfig.fasterDroppedItemStackingChecks) {
			mixins.add("minecraft.optimization.MixinEntityItem");
		}
		if(BugTorchConfig.fasterEntityLivingBaseIsPotionActiveAndSetAir) {
			mixins.add("minecraft.optimization.MixinEntityLivingBase");
		}
		if(BugTorchConfig.fasterGetBlockByIdForAirBlocks) {
			mixins.add("minecraft.optimization.MixinBlock");
		}
		if(!loadedCoreMods.contains("optifine.OptiFineForgeTweaker")) {
			if(BugTorchConfig.fasterOptionInteractions) {
				mixins.add("minecraft.optimization.gamesettings.MixinFasterSetOptions");
				mixins.add("minecraft.optimization.MixinGameSettings_Options");
			}
			if(BugTorchConfig.fasterOptionLoading) {
				mixins.add("minecraft.optimization.gamesettings.MixinFasterLoadOptions");
			}
		}
		if(BugTorchConfig.fasterSnowBlockTicks) {
			mixins.add("minecraft.optimization.MixinBlockSnowBlock");
		}
		if(client && useNotFineOverlap && BugTorchConfig.moreAccurateLayeredSnowFaceCulling) {
			mixins.add("minecraft.optimization.MixinBlockSnow");
		}
		if(client && BugTorchConfig.replaceRandomInEffectRenderer) {
			mixins.add("minecraft.fastrandom.MixinEffectRenderer");
		}
		if(BugTorchConfig.replaceRandomInEntity) {
			mixins.add("minecraft.fastrandom.MixinEntity");
		}
		if(BugTorchConfig.replaceRandomInItem) {
			mixins.add("minecraft.fastrandom.MixinItem");
		}
		if(BugTorchConfig.replaceRandomInMinecraftServer) {
			mixins.add("minecraft.fastrandom.MixinMinecraftServer");
		}
		if(client && BugTorchConfig.replaceRandomInRenderItem) {
			mixins.add("minecraft.fastrandom.MixinRenderItem");
		}
		if(BugTorchConfig.replaceRandomInWorld) {
			BugTorch.logger.info("World.class will use a faster Random implementation, this impacts world generation slightly.");
			mixins.add("minecraft.fastrandom.MixinWorld");
		}
		if(client && useNotFineOverlap && BugTorchConfig.replaceRandomInWorldClient) {
			mixins.add("minecraft.fastrandom.MixinWorldClient");
		}
		if(client && BugTorchConfig.skipInitialWorldChunkLoad) {
			mixins.add("minecraft.optimization.MixinMinecraftServer");
		}

		//Tweaks
		if(client && useNotFineOverlap && BugTorchConfig.enchantmentParticlesForPowerAboveZero) {
			mixins.add("minecraft.rendering.MixinBlockEnchantmentTable");
		}
		if(BugTorchConfig.excludeLogsFromTopSolidOrLiquidBlock) {
			mixins.add("minecraft.worldgen.MixinWorld");
		}
		if(BugTorchConfig.farmlandHydroponics) {
			mixins.add("minecraft.tweaks.blockfarmland.MixinHydroponics");
		}
		if(client && BugTorchConfig.farmlandNewTextures) {
			mixins.add("minecraft.tweaks.blockfarmland.MixinNewTextures");
		}
		if(BugTorchConfig.farmlandNoTrample) {
			mixins.add("minecraft.tweaks.blockfarmland.MixinNoTrample");
		}
		if(client && BugTorchConfig.lanPortOverride && !loadedCoreMods.contains("com.mitchej123.hodgepodge.core.HodgepodgeCore")) {
			mixins.add("minecraft.tweaks.MixinIntegratedServer");
		}
		if(BugTorchConfig.placeEndPortalsAnywhere) {
			mixins.add("minecraft.tweaks.MixinBlockEndPortal");
		}
		if(BugTorchConfig.placePressurePlatesOnAnyWallOrFence) {
			mixins.add("minecraft.placement.MixinBlockBasePressurePlate");
		}
		if(BugTorchConfig.placeTorchesOnAnyFence) {
			mixins.add("minecraft.placement.MixinBlockFence");
		}
		if(BugTorchConfig.placeTorchesOnAnyWall) {
			mixins.add("minecraft.placement.MixinBlockWall");
		}
		if(client && BugTorchConfig.potionParticlesAreClearForClientPlayer) {
			mixins.add("minecraft.tweaks.entitylivingbase.MixinTranslucentClientPotionEffects");
		}
		if(BugTorchConfig.reduceLightningVolume < 10000f) {
			mixins.add("minecraft.tweaks.MixinEntityLightningBolt");
		}
		if(BugTorchConfig.removeEntityDuplicateExtendedPropertiesIdentifierSpam) {
			mixins.add("minecraft.logcleanup.MixinEntity");
		}
		if(BugTorchConfig.scaledDrowningDamageMaxHealthFlat > 0f || BugTorchConfig.scaledDrowningDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.entitylivingbase.MixinScalingDrowningDamage");
		}
		if(BugTorchConfig.scaledLavaDamageMaxHealthFlat > 0f || BugTorchConfig.scaledLavaDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.damage.MixinLavaDamage");
		}
		if(BugTorchConfig.scaledFireDamageMaxHealthFlat > 0f || BugTorchConfig.scaledFireDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.damage.MixinFireDamage_Entity");
			mixins.add("minecraft.tweaks.damage.MixinFireDamage_EntityPlayer");
		}
		if(BugTorchConfig.scaledStarvationDamageMaxHealthFlat > 0f || BugTorchConfig.scaledStarvationDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.MixinFoodStats");
		}
		if(BugTorchConfig.scaledSuffocationDamageMaxHealthFlat > 0f || BugTorchConfig.scaledSuffocationDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.entitylivingbase.MixinScalingSuffocationDamage");
		}
		if(BugTorchConfig.scaledPoisonDamageMaxHealthFlat > 0f || BugTorchConfig.scaledPoisonDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.potion.MixinsPotionPoison");
		}
		if(BugTorchConfig.scaledWitherDamageMaxHealthFlat > 0f || BugTorchConfig.scaledWitherDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.potion.MixinsPotionWither");
		}
		if(BugTorchConfig.useAnyDyeOnLeatherArmor) {
			mixins.add("minecraft.tweaks.MixinRecipeArmorDyes");
		}

		return mixins;
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
