package jss.bugtorch.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.launchwrapper.Launch;
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

		if(BugTorchConfig.ganysSurfaceJarName == "d" || !loadJar(BugTorchConfig.ganysSurfaceJarName)) {
			BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture = false;
		}
		if(BugTorchConfig.thaumcraftJarName == "d" || !loadJar(BugTorchConfig.thaumcraftJarName)) {
			BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds = false;
		}
		if(BugTorchConfig.witcheryJarName == "d" || !loadJar(BugTorchConfig.witcheryJarName)) {
			BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds = false;
		}

		//Backports
		if(BugTorchConfig.cobwebsCanBeSheared) mixins.add("minecraft.block.MixinBlockWeb");
		if(BugTorchConfig.deadBushesDropSticks) mixins.add("minecraft.block.MixinBlockDeadBush");
		if(BugTorchConfig.fireArrowsDetonateTNTCarts) mixins.add("minecraft.entity.item.MixinEntityMinecartTNT");
		if(BugTorchConfig.throwEnderPearlsInCrativeMode) mixins.add("minecraft.item.MixinItemEnderPearl");

		//Bugfixes
		if(BugTorchConfig.fixEnchantmentBlendFunc) mixins.add("minecraft.client.renderer.entity.MixinItemRenderer");
		if(BugTorchConfig.fixFireChargeUseSound) mixins.add("minecraft.item.MixinItemFireball");
		if(BugTorchConfig.fixLavaHissOnAirReplace) mixins.add("minecraft.block.MixinBlockLiquid");
		if(BugTorchConfig.fixMineshaftAirPockets) {
			mixins.add("minecraft.world.gen.structure.MixinStructureStart");
			mixins.add("minecraft.world.gen.structure.MixinStructureMineshaftPieces$Room");
		}
		if(BugTorchConfig.fixPumpkinPlacementCheck) mixins.add("minecraft.block.MixinBlockPumpkin");
		if(BugTorchConfig.fixStoneMonsterEggDoubleSpawns) mixins.add("minecraft.block.MixinBlockSilverfish");
		if(BugTorchConfig.fixShearedBlocksDropExtraItems) {
			mixins.add("minecraft.block.MixinBlockLeaves");
			mixins.add("minecraft.block.MixinBlockTallGrass");
		}
		if(BugTorchConfig.fixShearsNotTakingDamageFromNormalBlocks) mixins.add("minecraft.item.MixinItemShears");
		if(BugTorchConfig.fixSignPacketChatMessages) mixins.add("minecraft.client.network.MixinNetHandlerPlayClient");
		if(BugTorchConfig.fixVillagePathsHavePlantsOnTop) mixins.add("minecraft.world.gen.structure.MixinStructureVillagePieces_Path");
		if(BugTorchConfig.fixVillagerTradeMetadataDetection) {
			mixins.add("minecraft.inventory.MixinSlotMerchantResult");
			mixins.add("minecraft.village.MixinMerchantRecipe");
		}
		if(BugTorchConfig.fixVillageSieges) mixins.add("minecraft.village.MixinVillageSiege");
		if(BugTorchConfig.fixVillageWellDesertMaterial) mixins.add("minecraft.world.gen.structure.MixinStructureVillagePieces_Well");

		//Performance
		if(BugTorchConfig.brokenChestsDontSplitStacks) mixins.add("minecraft.block.MixinBlockChest");
		if(BugTorchConfig.brokenHoppersDontSplitStacks) mixins.add("minecraft.block.MixinBlockHopper");
		if(BugTorchConfig.fasterDroppedItemStackingChecks) mixins.add("minecraft.entity.item.MixinEntityItem");
		if(BugTorchConfig.fasterEntityLivingBaseIsPotionActiveAndSetAir) mixins.add("minecraft.entity.MixinEntityLivingBase");
		if(BugTorchConfig.fasterGetBlockByIdForAirBlocks) mixins.add("minecraft.block.MixinBlock");
		if(BugTorchConfig.fasterSnowBlockTicks) mixins.add("minecraft.block.MixinBlockSnowBlock");
		if(BugTorchConfig.moreAccurateLayeredSnowFaceCulling) mixins.add("minecraft.block.MixinBlockSnow");
		if(BugTorchConfig.replaceRandomInEffectRenderer) mixins.add("random.client.particle.MixinEffectRenderer");
		if(BugTorchConfig.replaceRandomInEntity) mixins.add("random.entity.MixinEntity");
		if(BugTorchConfig.replaceRandomInItem) mixins.add("random.item.MixinItem");
		if(BugTorchConfig.replaceRandomInMinecraftServer) mixins.add("random.server.MixinMinecraftServer");
		if(BugTorchConfig.replaceRandomInRenderItem) mixins.add("random.client.renderer.entity.MixinRenderItem");
		if(BugTorchConfig.replaceRandomInWorld) {
			BugTorchCore.logger.info("World.class will use a faster Random implementation, this impacts world generation slightly.");
			mixins.add("random.world.MixinWorld");
		}
		if(BugTorchConfig.replaceRandomInWorldClient) mixins.add("random.client.multiplayer.MixinWorldClient");
		if(BugTorchConfig.skipInitialWorldChunkLoad) mixins.add("minecraft.server.MixinMinecraftServer");

		//Tweaks
		if(BugTorchConfig.farmlandImprovements) mixins.add("minecraft.block.MixinBlockFarmland");
		if(BugTorchConfig.lanPortOverride) mixins.add("minecraft.server.integrated.MixinIntegratedServer");
		if(BugTorchConfig.placeEndPortalsAnywhere) mixins.add("minecraft.block.MixinBlockEndPortal");
		if(BugTorchConfig.placePressurePlatesOnAnyWallOrFence) mixins.add("minecraft.block.MixinBlockBasePressurePlate");
		if(BugTorchConfig.placeTorchesOnAnyFence) mixins.add("minecraft.block.MixinBlockFence");
		if(BugTorchConfig.placeTorchesOnAnyWall) mixins.add("minecraft.block.MixinBlockWall");
		if(BugTorchConfig.potionParticlesAreClearForClientPlayer) mixins.add("minecraft.entity.MixinEntityLivingBase2");
		if(BugTorchConfig.removeEntityDuplicateExtendedPropertiesIdentifierSpam) mixins.add("minecraft.entity.MixinEntity");

		//Mod bugfixes
		if(BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture) mixins.add("ganyssurface.blocks.MixinBlockWoodTrapdoor");
		if(BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds) {
			//mixins.add("thaumcraft.client.renderers.block.MixinBlockCandleRenderer");
			mixins.add("thaumcraft.common.blocks.MixinBlockCandle");
		}
		if(BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds) mixins.add("witchery.blocks.MixinBlockGarlicGarland");

		return mixins;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	private static boolean loadJar(String jarName) {
		try {
			File jar = MinecraftURLClassPath.getJarInModPath(jarName);
			if(jar == null) {
				BugTorchCore.logger.info("Jar not found: " + jarName);
				return false;
			}

			BugTorchCore.logger.info("Attempting to add " + jar.toString() + " to the URL Class Path");
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
