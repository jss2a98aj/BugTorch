package jss.bugtorch.config;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.config.Configuration;

public class BugTorchConfig {

	//Base backports
	public static boolean enableFloatingTrapDoors;

	//Base bugfixes
	public static boolean fixSnowBlocksRandomlyTicking;

	//Mod bugfixes
	public static boolean fixPamsTemperatePlantsBlockSounds;
	public static boolean fixWitcheryBlockSounds;

	//Mod ore dictionary
	public static boolean registerThaumcraftLeavesToTheOreDictionary;
	public static boolean registerThaumcraftThaumiumBlockToTheOreDictionary;
	public static boolean registerThaumcraftWoodStairsToTheOreDictionary;
	public static boolean registerWitcheryWoodSlabsToTheOreDictionary;

	//Mod tweaks
	public static boolean craftThaumcraftAncientStoneSlabsAndStairs;
	public static boolean enableVillageNamesMetadataSensitiveTrades;
	public static boolean reverseCraftThaumcraftSlabs;

	//Mixin backports
	public static boolean cobwebsCanBeSheared;
	public static boolean deadBushesDropSticks;
	public static boolean fireArrowsDetonateTNTCarts;
	public static boolean throwEnderPearlsInCrativeMode;

	//Mixin bugfixes
	public static boolean fixEnchantmentBlendFunc;
	public static boolean fixFireChargeUseSound;
	public static boolean fixLavaHissOnAirReplace;
	public static boolean fixPumpkinPlacementCheck;
	public static boolean fixShearedBlocksDropExtraItems;
	public static boolean fixShearsNotTakingDamageFromNormalBlocks;
	public static boolean fixSignPacketChatMessages;
	public static boolean fixStoneMonsterEggDoubleSpawns;
	public static boolean fixVillagePathsHavePlantsOnTop;
	public static boolean fixVillagerTradeMetadataDetection;
	public static boolean fixVillageSieges;
	public static boolean fixVillageWellDesertMaterial;
	public static boolean fixMineshaftAirPockets;

	//Mixin performance improvements
	public static boolean brokenChestsDontSplitStacks;
	public static boolean brokenHoppersDontSplitStacks;
	public static boolean fasterDroppedItemStackingChecks;
	public static boolean fasterEntityLivingBaseIsPotionActiveAndSetAir;
	public static boolean fasterGetBlockByIdForAirBlocks;
	public static boolean fasterSnowBlockTicks;
	public static boolean moreAccurateLayeredSnowFaceCulling;
	public static boolean replaceRandomInEffectRenderer;
	public static boolean replaceRandomInEntity;
	public static boolean replaceRandomInItem;
	public static boolean replaceRandomInMinecraftServer;
	public static boolean replaceRandomInRenderItem;
	public static boolean replaceRandomInWorld;
	public static boolean replaceRandomInWorldClient;
	public static boolean skipInitialWorldChunkLoad;

	//Mixin tweaks
	public static boolean farmlandImprovements;
	public static boolean lanPortOverride;
	public static int lanPortToUseForOverride;
	public static boolean placeEndPortalsAnywhere;
	public static boolean removeEntityDuplicateExtendedPropertiesIdentifierSpam;
	public static boolean potionParticlesAreClearForClientPlayer;

	//Mixin mod names
	public static String ganysSurfaceJarName;
	public static String thaumcraftJarName;
	public static String witcheryJarName;

	//Mixin mod bugfixes
	public static boolean fixGanysSurfaceOpenTrapdoorBackTexture;
	public static boolean fixThaumcraftCandleColorArrayOutOfBounds;
	public static boolean fixWitcheryGarlicGarlandBlockBounds;

	//Category names
	static final String categoryBackport = "backported features";
	static final String categoryBugfixes = "bug fixes";
	static final String categoryModNames = "mod file names";
	static final String categoryOreDictionary = "ore dictionary";
	static final String categoryPerformance = "performance improvements";
	static final String categoryTweaks = "tweaks";

	public static void loadBaseConfig(File configFile) {
		Configuration config = new Configuration(configFile);

		//Backports
		enableFloatingTrapDoors = config.getBoolean("freeFloatingTrapDoors", categoryBackport, true, "Trapdoors no longer require attachment blocks.\nFrom MC 1.9");

		//Bugfixes
		fixSnowBlocksRandomlyTicking = config.getBoolean("fixSnowBlocksRandomlyTicking", categoryBugfixes, true, "Non-layered snow blocks will no longer randomly tick.\nFrom MC 1.14, fixes MC-88097");

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static void loadModdedConfig(File configFile) {
		Configuration config = new Configuration(configFile);

		//Bugfixes
		fixPamsTemperatePlantsBlockSounds = config.getBoolean("fixPamsTemperatePlantsBlockSounds", categoryBugfixes, true, "Assigns the correct sound type to blocks from Pam's Temperate Plants.");
		fixWitcheryBlockSounds = config.getBoolean("fixWitcheryBlockSounds", categoryBugfixes, true, "Assigns the correct sound type to blocks from Witchery.");

		//Ore dictionary
		registerThaumcraftLeavesToTheOreDictionary = config.getBoolean("registerThaumcraftLeavesToTheOreDictionary", categoryOreDictionary, true, "Register Thaumcraft Greatwood and Silverwood leaves as treeLeaves.");
		registerThaumcraftThaumiumBlockToTheOreDictionary = config.getBoolean("registerThaumcraftThaumiumBlockToTheOreDictionary", categoryOreDictionary, true, "Register Thaumcraft Thaumium Blocks as blockThaumium.");
		registerThaumcraftWoodStairsToTheOreDictionary = config.getBoolean("registerThaumcraftWoodStairsToTheOreDictionary", categoryOreDictionary, true, "Register Thaumcraft Greatwood and Silverwood stairs as stairWood.");
		registerWitcheryWoodSlabsToTheOreDictionary = config.getBoolean("registerWitcheryWoodSlabsToTheOreDictionary", categoryOreDictionary, true, "Register Witchery wooden slabs as slabWood.");

		//Tweaks
		craftThaumcraftAncientStoneSlabsAndStairs = config.getBoolean("craftThaumcraftAncientStoneSlabs", categoryTweaks, true, "Craft Thaumcraft Ancient Stone slabs and stairs.");
		enableVillageNamesMetadataSensitiveTrades = config.getBoolean("enableVillageNamesMetadataSensitiveTrades", categoryTweaks, true, "Enables metadata sensitive trades in Village Names.\nDoes nothing if fixVillagerTradeMetadataDetection in \"bugtorch\\mixins.cfg\" is not enabled.") && fixVillagerTradeMetadataDetection;
		reverseCraftThaumcraftSlabs = config.getBoolean("reverseCraftThaumcraftSlabs", categoryTweaks, true, "Craft Thaumcraft slabs back into blocks.");

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static void loadBaseMixinConfig(File configFile) {
		boolean serverSide = FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;

		Configuration config = new Configuration(configFile);

		//Backports
		cobwebsCanBeSheared = config.getBoolean("cobwebsCanBeSheared", categoryBackport, true, "Cobwebs can be collected with Shears without Silk Touch.\nFrom MC 1.9, fixes MC-93001");
		deadBushesDropSticks = config.getBoolean("deadBushesDropSticks", categoryBackport, true, "Dead Bushes drop 0-2 Sticks.\nFrom MC 1.9");
		fireArrowsDetonateTNTCarts = config.getBoolean("fireArrowsDetonateTNTCarts", categoryBackport, true, "Minecarts with TNT explode when hit by fire arrows.\nFrom MC 1.8, fixes MC-8987");
		throwEnderPearlsInCrativeMode = config.getBoolean("throwEnderPearlsInCrativeMode", categoryBackport, true, "Ender Pearls can be thrown in creative mode.\nFrom MC 1.9, fixes MC-438");

		//Bugfixes
		fixEnchantmentBlendFunc = config.getBoolean("fixEnchantmentBlendFunc", categoryBugfixes, true, "Fixes rendering issues caused by enchantments changing glBlendFunc and never reverting it.") && !serverSide;
		fixFireChargeUseSound = config.getBoolean("fixFireChargeUseSound", categoryBugfixes, true, "Fire Charges have the correct use sound.\nFrom MC 1.8, fixes MC-1831");
		fixLavaHissOnAirReplace = config.getBoolean("fixLavaHissOnAirReplace", categoryBugfixes, true, "Lava will only hiss when mixing with water.\nFrom MC 1.8, fixes MC-32301");
		fixPumpkinPlacementCheck = config.getBoolean("fixPumpkinPlacementCheck", categoryBackport, true, "Pumpkins and Jack o'Lanterns can be placed without a solid block below them.\nFrom MC 1.13, fixes MC-1947");
		fixShearedBlocksDropExtraItems = config.getBoolean("fixShearedBlocksDropExtraItems", categoryBugfixes, true, "Shearing a block will not give drops in addition to itself.");
		fixShearsNotTakingDamageFromNormalBlocks = config.getBoolean("fixShearsNotTakingDamageFromNormalBlocks", categoryBugfixes, true, "Shears will take damage when used to mine any block.\nAlso stops Forge shearing logic from dropping things in creative mode.\nFrom MC 1.9, fixes MC-8180");
		fixSignPacketChatMessages = config.getBoolean("fixSignPacketChatMessages", categoryBugfixes, true, "Sign update packets for signs in unloaded chunks will not send chat messages.\nFrom MC 1.9, fixes MC-3564") && !serverSide;
		fixStoneMonsterEggDoubleSpawns = config.getBoolean("fixStoneMonsterEggDoubleSpawns", categoryBugfixes, true, "Stone Monster Eggs only spawn one Silverfish when broken.\nFrom MC 1.8, fixes MC-31081");
		fixVillagePathsHavePlantsOnTop = config.getBoolean("fixVillagePathsHavePlantsOnTop", categoryBugfixes, true, "Village paths will not have flowers or grass on top of them.\nFrom MC 1.10, fixes MC-3437");
		fixVillagerTradeMetadataDetection = config.getBoolean("fixVillagerTradeMetadataDetection", categoryBugfixes, true, "Villager trades will respect metadata.\nFrom MC 1.8");
		fixVillageSieges = config.getBoolean("fixVillageSieges", categoryBugfixes, true, "Zombies will seige villages that are large enough at night.\nFrom MC 1.8, fixes MC-7432 and MC-7488");
		fixVillageWellDesertMaterial = config.getBoolean("fixVillageWellDesertMaterial", categoryBugfixes, true, "Wells in desert villages will use the correct material.\nFrom MC 1.8, fixes MC-32514");
		fixMineshaftAirPockets = config.getBoolean("fixVillageWellDesertMaterial", categoryBugfixes, true, "Fixes the air bubbles mineshafts create above their dirt rooms, affects all terrain but very noticeable in oceans.\nThese air pockets were supposed to be in the dirt rooms so this also fixes the dirt rooms having blocked off entrances to some branches.\nFrom MC 1.8, fixes MC-954");

		//Performance
		brokenChestsDontSplitStacks = config.getBoolean("brokenChestsDontSplitStacks", categoryPerformance, false, "Broken chests don't split apart dropped item stacks.");
		brokenHoppersDontSplitStacks = config.getBoolean("brokenHoppersDontSplitStacks", categoryPerformance, false, "Broken hoppers don't split apart dropped item stacks.");
		fasterDroppedItemStackingChecks = config.getBoolean("fasterDroppedItemStackingChecks", categoryPerformance, true, "Dropped item nearby stack checks are faster for full stacks.");
		fasterEntityLivingBaseIsPotionActiveAndSetAir = config.getBoolean("fasterEntityLivingBaseIsPotionActiveAndSetAir", categoryPerformance, true, "isPotionActive returns immediately if there are no active potions.\nsetAir only updates its datawatcher when needed.");
		fasterGetBlockByIdForAirBlocks = config.getBoolean("fasterGetBlockByIdForAirBlocks", categoryPerformance, true, "When something gets air blocks from ID it will return faster.");
		moreAccurateLayeredSnowFaceCulling = config.getBoolean("moreAccurateLayeredSnowFaceCulling", categoryPerformance, true, "The faces of layered snow get culled more accurately when chunk meshes are created.") && !serverSide;
		fasterSnowBlockTicks = config.getBoolean("fasterSnowBlockTicks", categoryPerformance, true, "Non-layered snow block ticking is faster.");
		replaceRandomInEffectRenderer = config.getBoolean("replaceRandomInEffectRenderer", categoryPerformance, true, "Makes EffectRenderer.class use a faster implementation of random.") && !serverSide;
		replaceRandomInEntity = config.getBoolean("replaceRandomInEntity", categoryPerformance, true, "Makes Entity.class use a faster implementation of random.");
		replaceRandomInItem = config.getBoolean("replaceRandomInItem", categoryPerformance, true, "Makes Item.class use a faster implementation of random.");
		replaceRandomInMinecraftServer = config.getBoolean("replaceRandomInMinecraftServer", categoryPerformance, true, "Makes MinecraftServer.class use a faster implementation of random.");
		replaceRandomInRenderItem = config.getBoolean("replaceRandomInRenderItem", categoryPerformance, true, "Makes RenderItem.class use a faster implementation of random.") && !serverSide;
		replaceRandomInWorld = config.getBoolean("replaceRandomInWorld", categoryPerformance, false, "Makes World.class use a faster implementation of random.\n!This impacts world generation slightly!");
		replaceRandomInWorldClient = config.getBoolean("replaceRandomInWorldClient", categoryPerformance, true, "Makes WorldClient.class use a faster implementation of random.") && !serverSide;
		skipInitialWorldChunkLoad = config.getBoolean("skipInitialWorldChunkLoad", categoryPerformance, true, "Speeds up initial world loading by not waiting for chunks to preload.") && !serverSide;

		//Tweaks
		farmlandImprovements = config.getBoolean("farmlandImprovements", categoryTweaks, false, "Farmland will no longer get trampled and hydroponic farms will be possible.\nAlso has new side textures for both wet and dry farmland.\nThis will be moved to a seperate mod at some point.");
		lanPortOverride = config.getBoolean("lanPortOverride", categoryTweaks, false, "Override the port used when opening singleplayer to LAN.") && !serverSide;
		placeEndPortalsAnywhere = config.getBoolean("placeEndPortalsAnywhere", categoryTweaks, false, "Place End Portals outside of the overworld without them getting removed.");
		removeEntityDuplicateExtendedPropertiesIdentifierSpam = config.getBoolean("removeEntityDuplicateExtendedPropertiesIdentifierSpam", categoryTweaks, true, "Removes \"An attempt was made to register exended properties using an existing key\" log spam caused by some mods.");
		potionParticlesAreClearForClientPlayer = config.getBoolean("potionParticlesAreClearForClientPlayer", categoryTweaks, false, "Potion particles coming off of the player entity you control are always clear.") && !serverSide;

		lanPortToUseForOverride = config.getInt("lanPortToUseForOverride", categoryTweaks, 25565, 1024 , 49151, "Port to use for lanPortOverride.");
		if(config.hasKey(categoryTweaks, "lanPortToUSeForOverride")) {
			lanPortToUseForOverride = config.getInt("lanPortToUSeForOverride", categoryTweaks, 25565, 1024 , 49151, "Port to use for lanPortOverride.");
			config.getCategory(categoryTweaks).get("lanPortToUseForOverride").set(lanPortToUseForOverride);
			config.getCategory(categoryTweaks).remove("lanPortToUSeForOverride");
		}

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static void loadModdedMixinConfig(File configFile) {
		Configuration config = new Configuration(configFile);

		//Mod file names
		ganysSurfaceJarName = config.getString("ganysSurfaceJarName", categoryModNames, "Ganys+Surface", "The partial file name assiciated with Gany's Surface.\n!This can break things if the wrong name is used!\nSet to 'd' to disable all mixins for Gany's Surface.");
		thaumcraftJarName = config.getString("thaumcraftJarName", categoryModNames, "Thaumcraft-1.7.10", "The partial file name assiciated with Thaumcraft.\n!This can break things if the wrong name is used!\nSet to 'd' to disable all mixins for Thaumcraft.");
		witcheryJarName = config.getString("witcheryJarName", categoryModNames, "witchery-1.7.10", "The partial file name assiciated with Witchery.\n!This can break things if the wrong name is used!\nSet to 'd' to disable all mixins for Witchery.");

		//Bugfixes
		fixGanysSurfaceOpenTrapdoorBackTexture = config.getBoolean("fixGanysSurfaceOpenTrapdoorBackTexture", categoryBugfixes, true, "Makes Gany's Surface trapdoors use the correct back texture when open.");
		fixThaumcraftCandleColorArrayOutOfBounds = config.getBoolean("fixThaumcraftCandleColorArrayOutOfBounds", categoryBugfixes, true, "Makes Thaumcraft candles not cause an array out of bounds exception if rendered with metadata greater than 15.");
		fixWitcheryGarlicGarlandBlockBounds = config.getBoolean("fixWitcheryGarlicGarlandBlockBounds", categoryBugfixes, true, "Makes Witchery Garlic Garlands use correct block bounds on every rotation.");

		if(config.hasChanged()) {
			config.save();
		}
	}

}
