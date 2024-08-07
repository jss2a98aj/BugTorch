package jss.bugtorch.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class BugTorchConfig {

	//Base backports
	public static boolean enableFloatingTrapDoors;

	//Base bugfixes
	public static boolean fixBlockSounds;
	public static boolean fixCarpetBlocksRandomlyTicking;
	public static boolean fixJackOLanternBlocksRandomlyTicking;
	public static boolean fixPumpkinBlocksRandomlyTicking;
	public static boolean fixSnowBlocksRandomlyTicking;
	public static boolean fixTorchBlocksRandomlyTicking;

	//Base tweaks
	public static int showBroadcastSettingsButton;
	public static int showOpenToLanButton;
	public static int showSuperSecretSettingsButton;

	//Mod bugfixes
	public static boolean fixExtraUtilitiesBlockSounds;
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
	public static boolean throwEnderPearlsInCreativeMode;

	//Mixin bugfixes
	public static boolean fixAnvilSoundTypeStepSound;
	public static boolean fixDarkOakRemovingBlocks;
	public static boolean fixEnchantmentBlendFunc;
	public static boolean fixFireChargeUseSound;
	public static boolean fixLavaHissOnAirReplace;
	public static boolean fixLeadsBreakingOnSomeFenceInstances;
	public static boolean fixLeafDecayCheckRange;
	public static boolean fixMergeItemStack;
	public static boolean fixMineshaftAirPockets;
	public static boolean fixNettyConnectionFailureResourceLeak;
	public static boolean fixParticleDepthSorting;
	public static boolean fixPumpkinPlacementCheck;
	public static boolean fixRedstoneTorchMemoryLeak;
	public static boolean fixShearedGrassDropDupe;
	public static boolean fixShearedLeavesDropDupe;
	public static boolean fixShearsNotTakingDamageFromNormalBlocks;
	public static boolean fixSignPacketChatMessages;
	public static boolean fixStoneMonsterEggDoubleSpawns;
	public static boolean fixStructureComponentFillReplacement;
	public static boolean fixVillagePathsHavePlantsOnTop;
	public static boolean fixVillagerTradeMetadataDetection;
	public static boolean fixVillageSieges;
	public static boolean fixVillageWellDesertMaterial;

	//Mixin performance improvements
	public static boolean brokenChestsDontSplitStacks;
	public static boolean brokenHoppersDontSplitStacks;
	public static boolean fasterDroppedItemStackingChecks;
	public static boolean fasterEntityLivingBaseIsPotionActiveAndSetAir;
	public static boolean fasterGetBlockByIdForAirBlocks;
	public static boolean fasterOptionInteractions;
	public static boolean fasterOptionLoading;
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
	public static boolean enchantmentParticlesForPowerAboveZero;
	public static boolean excludeLogsFromTopSolidOrLiquidBlock;
	public static boolean farmlandHydroponics;
	public static boolean farmlandNewTextures;
	public static boolean farmlandNoTrample;
	public static boolean lanPortOverride;
	public static int lanPortToUseForOverride;
	public static boolean placeEndPortalsAnywhere;
	public static boolean placePressurePlatesOnAnyWallOrFence;
	public static boolean placeTorchesOnAnyFence;
	public static boolean placeTorchesOnAnyWall;
	public static boolean potionParticlesAreClearForClientPlayer;
	public static float reduceLightningVolume;
	public static boolean removeEntityDuplicateExtendedPropertiesIdentifierSpam;
	public static float scaledDrowningDamageMaxHealthFlat;
	public static float scaledDrowningDamageMaxHealthMult;
	public static float scaledFireDamageMaxHealthFlat;
	public static float scaledFireDamageMaxHealthMult;
	public static float scaledLavaDamageMaxHealthFlat;
	public static float scaledLavaDamageMaxHealthMult;
	public static float scaledPoisonDamageMaxHealthFlat;
	public static float scaledPoisonDamageMaxHealthMult;
	public static float scaledStarvationDamageMaxHealthFlat;
	public static float scaledStarvationDamageMaxHealthMult;
	public static float scaledSuffocationDamageMaxHealthFlat;
	public static float scaledSuffocationDamageMaxHealthMult;
	public static float scaledWitherDamageMaxHealthFlat;
	public static float scaledWitherDamageMaxHealthMult;

	public static boolean useAnyDyeOnLeatherArmor;

	//Mixin mod bugfixes
	public static boolean fixCrayfishFurnitureNullPointerException;
	public static boolean fixGanysSurfaceOpenTrapdoorBackTexture;
	public static boolean fixThaumcraftCandleColorArrayOutOfBounds;
	public static boolean fixWitcheryGarlicGarlandBlockBounds;
	public static boolean fixWitcheryLeavesOptifineRendering;
	public static boolean fixWitcheryLeavesShearDupe;
	public static boolean reuseAetherIIRenderPlayer;

	//Mixin mod tweaks
	public static boolean disableCrayfishFurnitureAchievements;
	public static boolean extraUtilitiesTradingPostVillageNamesNitwitFilter;
	public static String[] extraUtilitiesGoldenLassoBlacklist;
	public static boolean proxyLLibraryPastebin;
	public static boolean fixLLibraryMalformedJsonCrash;
	public static float scaledExtraUtilitiesDarknessDamageMaxHealthFlat;
	public static float scaledExtraUtilitiesDarknessDamageMaxHealthMult;

	//Category names
	static final String categoryBackport = "backported features";
	static final String categoryBugfixes = "bug fixes";
	static final String categoryOreDictionary = "ore dictionary";
	static final String categoryPerformance = "performance improvements";
	static final String categoryTweaks = "tweaks";

	public static void loadBaseConfig(File configFile) {
		Configuration config = new Configuration(configFile);

		//Backports
		enableFloatingTrapDoors = config.getBoolean("freeFloatingTrapDoors", categoryBackport, true, "Trapdoors no longer require attachment blocks.\nFrom MC 1.9");

		//Bugfixes
		fixBlockSounds = config.getBoolean("fixBlockSounds", categoryBugfixes, true, "Assigns the correct sound types to some blocks.");
		fixCarpetBlocksRandomlyTicking = config.getBoolean("fixCarpetBlocksRandomlyTicking", categoryBugfixes, true, "Carpet blocks will no longer randomly tick.");
		fixJackOLanternBlocksRandomlyTicking = config.getBoolean("fixJackOLanternBlocksRandomlyTicking", categoryBugfixes, true, "Jack O' Lantern blocks will no longer randomly tick.");
		fixPumpkinBlocksRandomlyTicking = config.getBoolean("fixPumpkinBlocksRandomlyTicking", categoryBugfixes, true, "Pumpkin blocks will no longer randomly tick.");
		fixSnowBlocksRandomlyTicking = config.getBoolean("fixSnowBlocksRandomlyTicking", categoryBugfixes, true, "Non-layered snow blocks will no longer randomly tick.\nFrom MC 1.14, fixes MC-88097");
		fixTorchBlocksRandomlyTicking = config.getBoolean("fixTorchBlocksRandomlyTicking", categoryBugfixes, true, "Torch blocks will no longer randomly tick.");

		//Tweaks
		showBroadcastSettingsButton = config.getInt("showBroadcastSettingsButton", categoryTweaks, 1, -1, 1, "Show (1), disable(0), or remove(-1) the Broadcast Settings button in the options menu.");
		showOpenToLanButton = config.getInt("showOpenToLanButton", categoryTweaks, 1, -1, 1, "Show (1), disable(0), or remove(-1) the Open to LAN button in the escape menu.");
		showSuperSecretSettingsButton = config.getInt("showSuperSecretSettingsButton", categoryTweaks, 1, -1, 1, "Show (1), disable(0), or remove(-1) the Super Secret Settings button in the options menu.");

		//Update old config options
		if(config.hasKey(categoryTweaks, "removeBroadcastSettingsButton")) {
			boolean show = config.getBoolean("removeBroadcastSettingsButton", categoryTweaks, true, "");
			showBroadcastSettingsButton = show ? 1 : -1;
			config.getCategory(categoryTweaks).get("showBroadcastSettingsButton").set(show);
			config.getCategory(categoryTweaks).remove("removeBroadcastSettingsButton");
		}

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static void loadModdedConfig(File configFile) {
		Configuration config = new Configuration(configFile);

		//Bugfixes
		fixExtraUtilitiesBlockSounds = config.getBoolean("fixExtraUtilitiesBlockSounds", categoryBugfixes, true, "Assigns the correct sound types to some blocks from Extra Utilities.");
		fixPamsTemperatePlantsBlockSounds = config.getBoolean("fixPamsTemperatePlantsBlockSounds", categoryBugfixes, true, "Assigns the correct sound types to some blocks from Pam's Temperate Plants.");
		fixWitcheryBlockSounds = config.getBoolean("fixWitcheryBlockSounds", categoryBugfixes, true, "Assigns the correct sound types to some blocks from Witchery.");

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
		Configuration config = new Configuration(configFile);

		//Backports
		cobwebsCanBeSheared = config.getBoolean("cobwebsCanBeSheared", categoryBackport, true, "Cobwebs can be collected with Shears without Silk Touch.\nFrom MC 1.9, fixes MC-93001");
		deadBushesDropSticks = config.getBoolean("deadBushesDropSticks", categoryBackport, true, "Dead Bushes drop 0-2 Sticks.\nFrom MC 1.9");
		fireArrowsDetonateTNTCarts = config.getBoolean("fireArrowsDetonateTNTCarts", categoryBackport, true, "Minecarts with TNT explode when hit by fire arrows.\nFrom MC 1.8, fixes MC-8987");
		throwEnderPearlsInCreativeMode = config.getBoolean("throwEnderPearlsInCreativeMode", categoryBackport, true, "Ender Pearls can be thrown in creative mode.\nFrom MC 1.9, fixes MC-438");

		//Bugfixes
		fixAnvilSoundTypeStepSound = config.getBoolean("fixAnvilSoundTypeStepSound", categoryBugfixes, true, "Makes the anvil sound type step a valid sound\nAlso prevents log errors when walking on anvils.");
		fixDarkOakRemovingBlocks = config.getBoolean("fixDarkOakRemovingBlocks", categoryBugfixes, true, "Makes Dark Oak trees check if replacement is allowed before putting down logs.");
		fixEnchantmentBlendFunc = config.getBoolean("fixEnchantmentBlendFunc", categoryBugfixes, true, "Fixes rendering issues caused by enchantments changing glBlendFunc and never reverting it.\nDisabled if NotFine is loaded, it implements this fix.");
		fixFireChargeUseSound = config.getBoolean("fixFireChargeUseSound", categoryBugfixes, true, "Fire Charges have the correct use sound.\nFrom MC 1.8, fixes MC-1831");
		fixLavaHissOnAirReplace = config.getBoolean("fixLavaHissOnAirReplace", categoryBugfixes, true, "Lava will only hiss when mixing with water.\nFrom MC 1.8, fixes MC-32301");
		fixLeadsBreakingOnSomeFenceInstances = config.getBoolean("fixLeadsBreakingOnSomeFenceInstances", categoryBugfixes, true, "Fixes Leads breaking when placed on some modded fences.");
		fixLeafDecayCheckRange = config.getBoolean("fixLeafDecayCheckRange", categoryBugfixes, true, "Stops vanilla and some modded leaves from decaying when part of some larger naturally occurring trees.");
		fixMergeItemStack = config.getBoolean("fixMergeItemStack", categoryBugfixes, true, "Fixes edge case bugs when shift clicking item stacks.\nAn alternate version is used if CoFHCore is installed to fix dupes and item deletion it introduces.");
		fixMineshaftAirPockets = config.getBoolean("fixMineshaftAirPockets", categoryBugfixes, true, "Fixes the air bubbles mineshafts create above their dirt rooms, affects all terrain but very noticeable in oceans.\\nThese air pockets were supposed to be in the dirt rooms so this also fixes the dirt rooms having blocked off entrances to some branches.\\nFrom MC 1.8, fixes MC-954");
		fixNettyConnectionFailureResourceLeak = config.getBoolean("fixNettyConnectionFailureResourceLeak", categoryBugfixes, true, "Fixes improperly terminated client connections sometimes causing a severe resource leak.");
		fixParticleDepthSorting = config.getBoolean("fixParticleDepthSorting", categoryBugfixes, true, "Fixes particle depth being incorrectly calculated.\nDisabled if NotFine is loaded, it implements this fix.");
		fixPumpkinPlacementCheck = config.getBoolean("fixPumpkinPlacementCheck", categoryBackport, true, "Pumpkins and Jack O' Lanterns can be placed without a solid block below them.\nFrom MC 1.13, fixes MC-1947");
		fixRedstoneTorchMemoryLeak = config.getBoolean("fixRedstoneTorchMemoryLeak", categoryBackport, true, "Stops Redstone Torches from causing a memory leak by making them use a weak hash map to store burnt out torches.\nFixes MC-101233");
		fixShearedGrassDropDupe = config.getBoolean("fixShearedGrassDropDupe", categoryBugfixes, true, "Shearing tall grass will not give drops in addition to itself.");
		fixShearedLeavesDropDupe = config.getBoolean("fixShearedLeavesDropDupe", categoryBugfixes, true, "Shearing leaves will not give drops in addition to itself.");
		fixShearsNotTakingDamageFromNormalBlocks = config.getBoolean("fixShearsNotTakingDamageFromNormalBlocks", categoryBugfixes, true, "Shears will take damage when used to mine any block.\nAlso stops Forge shearing logic from dropping things in creative mode.\nFrom MC 1.9, fixes MC-8180");
		fixSignPacketChatMessages = config.getBoolean("fixSignPacketChatMessages", categoryBugfixes, true, "Sign update packets for signs in unloaded chunks will not send chat messages.\nFrom MC 1.9, fixes MC-3564");
		fixStoneMonsterEggDoubleSpawns = config.getBoolean("fixStoneMonsterEggDoubleSpawns", categoryBugfixes, true, "Stone Monster Eggs only spawn one Silverfish when broken.\nFrom MC 1.8, fixes MC-31081");
		fixStructureComponentFillReplacement = config.getBoolean("fixStructureComponentFillReplacement", categoryBugfixes, true, "Makes structure component filling also replace blocks flagged as replaceable and not partially trees.\nMostly prevents tall grass and flowers from embedding in structure foundations and keeps trees from having random holes.");
		fixVillagePathsHavePlantsOnTop = config.getBoolean("fixVillagePathsHavePlantsOnTop", categoryBugfixes, true, "Village paths will not have flowers or grass on top of them.\nFrom MC 1.10, fixes MC-3437");
		fixVillagerTradeMetadataDetection = false; config.getBoolean("fixVillagerTradeMetadataDetection", categoryBugfixes, true, "Villager trades will respect metadata.\nCurrently unfinished and disabled internally.\nFrom MC 1.8");
		fixVillageSieges = config.getBoolean("fixVillageSieges", categoryBugfixes, true, "Zombies will siege villages that are large enough at night.\nFrom MC 1.8, fixes MC-7432 and MC-7488");
		fixVillageWellDesertMaterial = config.getBoolean("fixVillageWellDesertMaterial", categoryBugfixes, true, "Wells in desert villages will use the correct material.\nFrom MC 1.8, fixes MC-32514");

		//Performance
		brokenChestsDontSplitStacks = config.getBoolean("brokenChestsDontSplitStacks", categoryPerformance, false, "Broken chests don't split apart dropped item stacks.");
		brokenHoppersDontSplitStacks = config.getBoolean("brokenHoppersDontSplitStacks", categoryPerformance, false, "Broken hoppers don't split apart dropped item stacks.");
		fasterDroppedItemStackingChecks = config.getBoolean("fasterDroppedItemStackingChecks", categoryPerformance, true, "Dropped item nearby stack checks are faster for full stacks.");
		fasterEntityLivingBaseIsPotionActiveAndSetAir = config.getBoolean("fasterEntityLivingBaseIsPotionActiveAndSetAir", categoryPerformance, true, "isPotionActive returns immediately if there are no active potions.\nsetAir only updates its datawatcher when needed.");
		fasterGetBlockByIdForAirBlocks = config.getBoolean("fasterGetBlockByIdForAirBlocks", categoryPerformance, true, "When something gets air blocks from ID it will return faster.");

		fasterOptionInteractions = config.getBoolean("fasterOptionInteractions", categoryPerformance, false, "Makes several functions used by option buttons faster.\nDisabled if Optifine is loaded to prevent conflicts.");
		fasterOptionLoading = config.getBoolean("fasterOptionLoading", categoryPerformance, false, "Makes the function that reads options.txt much faster.\nDisabled if Optifine is loaded to prevent conflicts.");

		moreAccurateLayeredSnowFaceCulling = config.getBoolean("moreAccurateLayeredSnowFaceCulling", categoryPerformance, true, "The faces of layered snow get culled more accurately when chunk meshes are created.\nDisabled if NotFine is loaded, it has a superior implementation.");
		fasterSnowBlockTicks = config.getBoolean("fasterSnowBlockTicks", categoryPerformance, true, "Non-layered snow block ticking is faster.");
		replaceRandomInEffectRenderer = config.getBoolean("replaceRandomInEffectRenderer", categoryPerformance, true, "Makes EffectRenderer.class use a faster implementation of random.");
		replaceRandomInEntity = config.getBoolean("replaceRandomInEntity", categoryPerformance, true, "Makes Entity.class use a faster implementation of random.");
		replaceRandomInItem = config.getBoolean("replaceRandomInItem", categoryPerformance, true, "Makes Item.class use a faster implementation of random.");
		replaceRandomInMinecraftServer = config.getBoolean("replaceRandomInMinecraftServer", categoryPerformance, true, "Makes MinecraftServer.class use a faster implementation of random.");
		replaceRandomInRenderItem = config.getBoolean("replaceRandomInRenderItem", categoryPerformance, true, "Makes RenderItem.class use a faster implementation of random.");
		replaceRandomInWorld = config.getBoolean("replaceRandomInWorld", categoryPerformance, false, "Makes World.class use a faster implementation of random.\n!This impacts world generation slightly!");
		replaceRandomInWorldClient = config.getBoolean("replaceRandomInWorldClient", categoryPerformance, true, "Makes WorldClient.class use a faster implementation of random.\nDisabled if NotFine is loaded, it implements this optimization.");
		skipInitialWorldChunkLoad = config.getBoolean("skipInitialWorldChunkLoad", categoryPerformance, true, "Speeds up initial world loading by not waiting for chunks to preload.");

		//Tweaks
		enchantmentParticlesForPowerAboveZero = config.getBoolean("enchantmentParticlesForPowerAboveZero", categoryTweaks, true, "Makes Enchantment Tables emit particles for any block with enchantment power.\nDisabled if NotFine is loaded, it implements this fix.");
		excludeLogsFromTopSolidOrLiquidBlock = config.getBoolean("excludeLogsFromTopSolidOrLiquidBlock", categoryTweaks, true, "Makes getTopSolidOrLiquidBlock treat logs as non-solid.\nShould prevent structures and from generating in trees among other issues.");
		farmlandHydroponics = config.getBoolean("farmlandHydroponics", categoryTweaks, false, "Farmland can use hydroponics.");
		farmlandNewTextures = config.getBoolean("farmlandNewTextures", categoryTweaks, false, "New side textures for both wet and dry farmland.");
		farmlandNoTrample = config.getBoolean("farmlandNoTrample", categoryTweaks, false, "Farmland will no longer get trampled.");
		lanPortOverride = config.getBoolean("lanPortOverride", categoryTweaks, false, "Override the port used when opening singleplayer to LAN.\nDisabled if Hodgepodge is loaded, use its defaultLanPort setting instead.");
		placeEndPortalsAnywhere = config.getBoolean("placeEndPortalsAnywhere", categoryTweaks, false, "Place End Portals outside of the overworld without them getting removed.");
		placePressurePlatesOnAnyWallOrFence = config.getBoolean("placePressurePlatesOnAnyWallOrFence", categoryTweaks, true, "Place pressure plates on almost any wall or fence.");
		placeTorchesOnAnyFence = config.getBoolean("placeTorchesOnAnyFence", categoryTweaks, true, "Place torches on almost any fence.");
		placeTorchesOnAnyWall = config.getBoolean("placeTorchesOnAnyWall", categoryTweaks, true, "Place torches on almost any wall.");
		potionParticlesAreClearForClientPlayer = config.getBoolean("potionParticlesAreClearForClientPlayer", categoryTweaks, false, "Potion particles coming off of the player entity you control are always clear.");
		reduceLightningVolume = config.getFloat("reduceLightningVolume", categoryTweaks, 10000f, 2f, 10000f, "Reduces lightning volume and effective range.\nSet to 10,000 to disable.");
		removeEntityDuplicateExtendedPropertiesIdentifierSpam = config.getBoolean("removeEntityDuplicateExtendedPropertiesIdentifierSpam", categoryTweaks, true, "Removes \"An attempt was made to register extended properties using an existing key\" log spam caused by some mods.");
		scaledDrowningDamageMaxHealthFlat = config.getFloat("scaledDrowningDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each drowning tick.\nSet to 0 to disable.");
		scaledDrowningDamageMaxHealthMult = config.getFloat("scaledDrowningDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each drowning tick.\nSet to 0 to disable.");
		scaledFireDamageMaxHealthFlat = config.getFloat("scaledFireDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each fire tick.\nSet to 0 to disable.");
		scaledFireDamageMaxHealthMult = config.getFloat("scaledFireDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each fire tick.\nSet to 0 to disable.");
		scaledLavaDamageMaxHealthFlat = config.getFloat("scaledLavaDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each lava tick.\nSet to 0 to disable.");
		scaledLavaDamageMaxHealthMult = config.getFloat("scaledLavaDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each lava tick.\nSet to 0 to disable.");
		scaledPoisonDamageMaxHealthFlat = config.getFloat("scaledPoisonDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each poison effect tick.\nSet to 0 to disable.");
		scaledPoisonDamageMaxHealthMult = config.getFloat("scaledPoisonDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each poison effect tick.\nSet to 0 to disable.");
		scaledStarvationDamageMaxHealthFlat = config.getFloat("scaledStarvationDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each starvation tick.\nSet to 0 to disable.");
		scaledStarvationDamageMaxHealthMult = config.getFloat("scaledStarvationDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each starvation tick.\nSet to 0 to disable.");
		scaledSuffocationDamageMaxHealthFlat = config.getFloat("scaledSuffocationDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each suffocation tick.\nSet to 0 to disable.");
		scaledSuffocationDamageMaxHealthMult = config.getFloat("scaledSuffocationDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each suffocation tick.\nSet to 0 to disable.");
		scaledWitherDamageMaxHealthFlat = config.getFloat("scaledWitherDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each wither effect tick.\nSet to 0 to disable.");
		scaledWitherDamageMaxHealthMult = config.getFloat("scaledWitherDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each wither effect tick.\nSet to 0 to disable.");
		useAnyDyeOnLeatherArmor = config.getBoolean("useAnyDyeOnLeatherArmor", categoryTweaks, true, "Allows Leather Armor to be dyed using any properly tagged dye.");

		lanPortToUseForOverride = config.getInt("lanPortToUseForOverride", categoryTweaks, 25565, 1024 , 49151, "Port to use for lanPortOverride.");

		//Update old config options
		if(config.hasKey(categoryBugfixes, "fixStructureComponentDownfillReplacement")) {
			fixStructureComponentFillReplacement = config.getBoolean("fixStructureComponentDownfillReplacement", categoryBugfixes, true, "");
			config.getCategory(categoryBugfixes).get("fixStructureComponentFillReplacement").set(fixStructureComponentFillReplacement);
			config.getCategory(categoryBugfixes).remove("fixStructureComponentDownfillReplacement");
		}

		if(config.hasChanged()) {
			config.save();
		}
	}

	public static void loadModdedMixinConfig(File configFile) {
		Configuration config = new Configuration(configFile);

		//Bugfixes
		fixCrayfishFurnitureNullPointerException = config.getBoolean("fixCrayfishFurnitureNullPointerExceptions", categoryBugfixes, true, "Makes several TEs from MrCrayfish's Furniture Mod implement ISidedInventory correctly.");
		fixGanysSurfaceOpenTrapdoorBackTexture = config.getBoolean("fixGanysSurfaceOpenTrapdoorBackTexture", categoryBugfixes, true, "Makes Gany's Surface trapdoors use the correct back texture when open.");
		fixLLibraryMalformedJsonCrash = config.getBoolean("fixLLibraryMalformedJsonCrash", categoryBugfixes, true, "Prevents LLibrary from trying to get non-json text from pastebin that it would then try to parse as JSON.");
		fixThaumcraftCandleColorArrayOutOfBounds = config.getBoolean("fixThaumcraftCandleColorArrayOutOfBounds", categoryBugfixes, true, "Makes Thaumcraft candles not cause an array out of bounds exception if rendered with metadata greater than 15.");
		fixWitcheryGarlicGarlandBlockBounds = config.getBoolean("fixWitcheryGarlicGarlandBlockBounds", categoryBugfixes, true, "Makes Witchery Garlic Garlands use correct block bounds on every rotation.");
		fixWitcheryLeavesOptifineRendering = config.getBoolean("fixWitcheryLeavesOptifineRendering", categoryBugfixes, true, "Makes Witchery Leaves respect Optifine-like render settings.\nDisabled if NotFine is loaded, it implements this fix.");
		fixWitcheryLeavesShearDupe = config.getBoolean("fixWitcheryLeavesShearDupe", categoryBugfixes, true, "Partially fixes a Forge shearing bug that impacts Witchery Leaves.");
		reuseAetherIIRenderPlayer = config.getBoolean("reuseAetherIIRenderPlayer", categoryPerformance, true, "Makes Aether II reuse the same player renderer object across frames.");

		//Tweaks
		disableCrayfishFurnitureAchievements = config.getBoolean("disableCrayfishFurnitureAchievements", categoryTweaks, false, "Disables MrCrayfish's Furniture Mod achievements.");
		extraUtilitiesGoldenLassoBlacklist = config.getStringList("extraUtilitiesGoldenLassoBlacklist", categoryTweaks, new String[] {
			"noppes.npcs.entity.EntityCustomNpc"
		}, "Blacklist entities from Extra Utilities Golden Lasso.");
		extraUtilitiesTradingPostVillageNamesNitwitFilter = config.getBoolean("extraUtilitiesTradingPostVillageNamesNitwitFilter", categoryTweaks, true, "Filters Village Names Nitwit villagers from Extra Utilities Trading Post.");
		proxyLLibraryPastebin = config.getBoolean("proxyLLibraryPastebin", categoryTweaks, false, "Use a pastebin proxy to keep LLibrary from crashing with some regional blocks.");
		scaledExtraUtilitiesDarknessDamageMaxHealthFlat = config.getFloat("scaledExtraUtilitiesDarknessDamageMaxHealthFlat", categoryTweaks, 0f, 0f, 20000f, "Amount of flat player health to remove each darkness tick.\nSet to 0 to disable.");
		scaledExtraUtilitiesDarknessDamageMaxHealthMult = config.getFloat("scaledExtraUtilitiesDarknessDamageMaxHealthMult", categoryTweaks, 0f, 0f, 1f, "Portion of max player health to remove each darkness tick.\nSet to 0 to disable.");

		if(config.hasChanged()) {
			config.save();
		}
	}

}
