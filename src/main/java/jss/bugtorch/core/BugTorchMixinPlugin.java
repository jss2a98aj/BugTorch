package jss.bugtorch.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.libraries.org.objectweb.asm.tree.ClassNode;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

public class BugTorchMixinPlugin implements IMixinConfigPlugin {

	static {
		String configFolder =  "config" + File.separator + BugTorchCore.MODID + File.separator;
		BugTorchConfig.loadBaseMixinConfig(new File(Launch.minecraftHome, configFolder + "mixins.cfg"));
		BugTorchConfig.loadModdedMixinConfig(new File(Launch.minecraftHome, configFolder + "mixinsModSupport.cfg"));
	}

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
		List<String> mixins = new ArrayList<>();
		boolean clientSide = BugTorchConfig.clientSide;

		if(!(Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
			if(BugTorchConfig.ganysSurfaceJarName.equals("d")  || !loadJar(BugTorchConfig.ganysSurfaceJarName, "Gany's Surface")) {
				BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture = false;
			}
			if(BugTorchConfig.thaumcraftJarName.equals("d") || !loadJar(BugTorchConfig.thaumcraftJarName, "Thaumcraft")) {
				BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds = false;
			}
			if(BugTorchConfig.witcheryJarName.equals("d") || !loadJar(BugTorchConfig.witcheryJarName, "Witchery")) {
				BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds = false;
				BugTorchConfig.fixWitcheryLeavesOptifineRendering = false;
				BugTorchConfig.fixWitcheryLeavesShearDupe = false;
			}
			if(BugTorchConfig.aetherIIJarName.equals("d") || !loadJar(BugTorchConfig.aetherIIJarName, "The Aether II")) {
				BugTorchConfig.reuseAetherIIRenderPlayer = false;
			}
		}

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
		if(clientSide && BugTorchConfig.fixEnchantmentBlendFunc) {
			mixins.add("minecraft.rendering.MixinRenderItem");
		}
		if(clientSide && BugTorchConfig.fixFireChargeUseSound) {
			mixins.add("minecraft.backport.MixinItemFireball");
		}
		if(clientSide && BugTorchConfig.fixLavaHissOnAirReplace) {
			mixins.add("minecraft.fix.MixinBlockLiquid");
		}
		if(BugTorchConfig.fixMineshaftAirPockets) {
			mixins.add("minecraft.worldgen.MixinStructureStart");
			mixins.add("minecraft.worldgen.MixinStructureMineshaftPieces$Room");
		}
		if(BugTorchConfig.fixNettyConnectionFailureResourceLeak) {
			mixins.add("minecraft.logcleanup.MixinNioSocketChannel");
		}
		if(clientSide && BugTorchConfig.fixParticleDepthSorting) {
			mixins.add("minecraft.rendering.MixinEffectRenderer");
		}
		//mixins.add("minecraft.rendering.MixinEffectRenderer");
		if(BugTorchConfig.fixPumpkinPlacementCheck) {
			mixins.add("minecraft.placement.MixinBlockPumpkin");
		}
		if(BugTorchConfig.fixRedstoneTorchMemoryLeak) {
			mixins.add("minecraft.optimization.MixinBlockRedstoneTorch");
		}
		if(BugTorchConfig.fixStoneMonsterEggDoubleSpawns) {
			mixins.add("minecraft.fix.MixinBlockSilverfish");
		}
		if(BugTorchConfig.fixStructureComponentDownfillReplacement) {
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
		if(clientSide && BugTorchConfig.fixSignPacketChatMessages) {
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
		if(BugTorchConfig.fasterSnowBlockTicks) {
			mixins.add("minecraft.optimization.MixinBlockSnowBlock");
		}
		if(clientSide && BugTorchConfig.moreAccurateLayeredSnowFaceCulling) {
			mixins.add("minecraft.optimization.MixinBlockSnow");
		}
		if(clientSide && BugTorchConfig.replaceRandomInEffectRenderer) {
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
		if(clientSide && BugTorchConfig.replaceRandomInRenderItem) {
			mixins.add("minecraft.fastrandom.MixinRenderItem");
		}
		if(BugTorchConfig.replaceRandomInWorld) {
			BugTorchCore.logger.info("World.class will use a faster Random implementation, this impacts world generation slightly.");
			mixins.add("minecraft.fastrandom.MixinWorld");
		}
		if(clientSide && BugTorchConfig.replaceRandomInWorldClient) {
			mixins.add("minecraft.fastrandom.MixinWorldClient");
		}
		if(clientSide && BugTorchConfig.skipInitialWorldChunkLoad) {
			mixins.add("minecraft.optimization.MixinMinecraftServer");
		}

		//Tweaks
		if(clientSide && BugTorchConfig.enchantmentParticlesForPowerAboveZero) {
			mixins.add("minecraft.rendering.MixinBlockEnchantmentTable");
		}
		if(BugTorchConfig.farmlandHydroponics) {
			mixins.add("minecraft.tweaks.blockfarmland.MixinHydroponics");
		}
		if(clientSide && BugTorchConfig.farmlandNewTextures) {
			mixins.add("minecraft.tweaks.blockfarmland.MixinNewTextures");
		}
		if(BugTorchConfig.farmlandNoTrample) {
			mixins.add("minecraft.tweaks.blockfarmland.MixinNoTrample");
		}
		if(clientSide && BugTorchConfig.lanPortOverride) {
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
		if(clientSide && BugTorchConfig.potionParticlesAreClearForClientPlayer) {
			mixins.add("minecraft.tweaks.entitylivingbase.MixinTranslucentClientPotionEffects");
		}
		if(BugTorchConfig.reduceLightningVolume < 10000f) {
			mixins.add("minecraft.tweaks.MixinEntityLightningBolt");
		}
		if(BugTorchConfig.removeEntityDuplicateExtendedPropertiesIdentifierSpam) {
			mixins.add("minecraft.logcleanup.MixinEntity");
		}
		if(BugTorchConfig.scaledDrowningDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.entitylivingbase.MixinScalingDrowningDamage");
		}
		if(BugTorchConfig.scaledStarvationDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.MixinFoodStats");
		}
		if(BugTorchConfig.scaledDrowningDamageMaxHealthMult > 0f) {
			mixins.add("minecraft.tweaks.entitylivingbase.MixinScalingSuffocationDamage");
		}

		//Mod bugfixes
		if(clientSide && BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture) {
			mixins.add("ganyssurface.rendering.MixinBlockWoodTrapdoor");
		}
		if(BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds) {
			if(clientSide) {
				mixins.add("thaumcraft.sanitizearrayaccess.MixinBlockCandleRenderer");
			}
			mixins.add("thaumcraft.sanitizearrayaccess.MixinBlockCandle");
		}
		if(BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds) {
			mixins.add("witchery.fix.MixinBlockGarlicGarland");
		}
		if(clientSide && BugTorchConfig.fixWitcheryLeavesOptifineRendering) {
			mixins.add("witchery.rendering.MixinBlockWitchLeaves");
		}
		if(BugTorchConfig.fixWitcheryLeavesShearDupe) {
			mixins.add("witchery.shearing.MixinBlockWitchLeaves");
		}
		if(clientSide && BugTorchConfig.reuseAetherIIRenderPlayer) {
			mixins.add("aetherii.optimization.MixinClientEventHandler");
		}

		return mixins;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

	private static boolean loadJar(String jarName, String modName) {
		try {
			File jar = MinecraftURLClassPath.getJarInModPath(jarName);
			if(jar == null) {
				BugTorchCore.logger.info(modName + " jar not found: " + jarName);
				return false;
			}

			BugTorchCore.logger.info("Attempting to add " + jar + " to the URL Class Path");
			if(!jar.exists())
				throw new FileNotFoundException(jar.toString());
			MinecraftURLClassPath.addJar(jar);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
