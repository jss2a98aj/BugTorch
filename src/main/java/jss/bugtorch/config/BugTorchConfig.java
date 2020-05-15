package jss.bugtorch.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class BugTorchConfig {

    //Base backports
    public static boolean enableFloatingTrapDoors;

    //Base bugfixes
    public static boolean fixSnowBlocksRandomlyTicking;

    //Mod bugfixes
    public static boolean registerThaumcraftLeavesToTheOreDictionary;
    public static boolean registerThaumcraftThaumiumBlockToTheOreDictionary;
    public static boolean registerThaumcraftWoodStairsToTheOreDictionary;

    //Mod tweaks
    public static boolean craftThaumcraftAncientStoneSlabsAndStairs;
    public static boolean reverseCraftThaumcraftSlabs;

    //Mixin backports
    public static boolean cobwebsCanBeSheared;
    public static boolean deadBushesDropSticks;
    public static boolean fireArrowsDetonateTNTCarts;
    public static boolean throwEnderPearlsInCrativeMode;

    //Mixin bugfixes
    public static boolean fixFireChargeUseSound;
    public static boolean fixLavaHissOnAirReplace;
    public static boolean fixPumpkinPlacementCheck;
    public static boolean fixShearedBlocksDropExtraItems;
    public static boolean fixShearsNotTakingDamageFromNormalBlocks;
    public static boolean fixSignPacketChatMessages;
    public static boolean fixStoneMonsterEggDoubleSpawns;
    public static boolean fixVillagePathsHavePlantsOnTop;
    public static boolean fixVillageSieges;
    public static boolean fixVillageWellDesertMaterial;

    //Mixin performance improvements
    public static boolean brokenChestsDontSplitStacks;
    public static boolean fasterDroppedItemStackingChecks;
    public static boolean fasterEntityLivingBaseIsPotionActiveAndSetAir;
    public static boolean fasterGetBlockByIdForAirBlocks;
    public static boolean fasterSnowBlockTicks;
    public static boolean replaceRandomWithXSTRInBlockChest;
    public static boolean replaceRandomWithXSTRInEffectRenderer;
    public static boolean replaceRandomWithXSTRInEntity;
    public static boolean replaceRandomWithXSTRInMinecraftServer;
    public static boolean replaceRandomWithXSTRInRenderItem;
    public static boolean replaceRandomWithXSTRInWorld;
    public static boolean replaceRandomWithXSTRInWorldClient;
    public static boolean skipInitialWorldChunkLoad;

    //Mixin tweaks
    public static boolean lanPortOverride;
    public static int lanPortToUSeForOverride;

    //Mixin mod bugfixes
    public static boolean fixGanysSurfaceOpenTrapdoorBackTexture;

    //Category names
    static final String categoryBackport = "backported features";
    static final String categoryBugfixes = "bug fixes";
    static final String categoryOreDictionary = "ore dictionary";
    static final String categoryPerformance = "performance improvements";
    static final String categoryTweaks = "tweaks";

    public static void loadBaseConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        //Backports
        enableFloatingTrapDoors = config.getBoolean("freeFloatingTrapDoors", categoryBackport, true, "Trapdoors no longer require attachment blocks. From MC 1.9");

        //Bugfixes
        fixSnowBlocksRandomlyTicking = config.getBoolean("fixSnowBlocksRandomlyTicking", categoryBugfixes, true, "Non-layered snow blocks will no longer randomly tick. From MC 1.14, fixes MC-88097");

        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void loadModdedConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        //Ore Dictionary
        registerThaumcraftLeavesToTheOreDictionary = config.getBoolean("registerThaumcraftLeavesToTheOreDictionary", categoryOreDictionary, true, "Register Thaumcraft Greatwood and Silverwood leaves as treeLeaves");
        registerThaumcraftThaumiumBlockToTheOreDictionary = config.getBoolean("registerThaumcraftThaumiumBlockToTheOreDictionary", categoryOreDictionary, true, "Register Thaumcraft Thaumium Blocks as blockThaumium");
        registerThaumcraftWoodStairsToTheOreDictionary = config.getBoolean("registerThaumcraftWoodStairsToTheOreDictionary", categoryOreDictionary, true, "Register Thaumcraft Greatwood and Silverwood stairs as stairWood");

        //Tweaks
        craftThaumcraftAncientStoneSlabsAndStairs = config.getBoolean("craftThaumcraftAncientStoneSlabs", categoryTweaks, true, "Craft Thaumcraft Ancient Stone slabs and stairs");
        reverseCraftThaumcraftSlabs = config.getBoolean("reverseCraftThaumcraftSlabs", categoryTweaks, true, "Craft Thaumcraft slabs back into blocks");
        
        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void loadBaseMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);
        
        //Backports
        cobwebsCanBeSheared = config.getBoolean("cobwebsCanBeSheared", categoryBackport, true, "Cobwebs can be collected with Shears without Silk Touch. From MC 1.9, fixes MC-93001");
        deadBushesDropSticks = config.getBoolean("deadBushesDropSticks", categoryBackport, true, "Dead Bushes drop 0-2 Sticks. From MC 1.9");
        fireArrowsDetonateTNTCarts = config.getBoolean("fireArrowsDetonateTNTCarts", categoryBackport, true, "Minecarts with TNT explode when hit by fire arrows. From MC 1.8, fixes MC-8987");
        throwEnderPearlsInCrativeMode = config.getBoolean("throwEnderPearlsInCrativeMode", categoryBackport, true, "Ender Pearls can be thrown in creative mode. From MC 1.9, fixes MC-438");

        //Bugfixes
        fixFireChargeUseSound = config.getBoolean("fixFireChargeUseSound", categoryBugfixes, true, "Fire Charges have the correct use sound. From MC 1.8, fixes MC-1831");
        fixLavaHissOnAirReplace = config.getBoolean("fixLavaHissOnAirReplace", categoryBugfixes, true, "Lava will only hiss when mixing with water. From MC 1.8, fixes MC-32301");
        fixPumpkinPlacementCheck = config.getBoolean("fixPumpkinPlacementCheck", categoryBackport, true, "Pumpkins and Jack o'Lanterns can be placed without a solid block below them. From MC 1.13, fixes MC-1947");
        fixShearedBlocksDropExtraItems = config.getBoolean("fixShearedBlocksDropExtraItems", categoryBugfixes, true, "Shearing a block will not give drops in addition to itself");
        fixShearsNotTakingDamageFromNormalBlocks = config.getBoolean("fixShearsNotTakingDamageFromNormalBlocks", categoryBugfixes, true, "Shears will take damage when used to mine any block. From MC 1.9, fixes MC-8180");
        fixSignPacketChatMessages = config.getBoolean("fixSignPacketChatMessages", categoryBugfixes, true, "Sign update packets for signs in unloaded chunks will not send chat messages. From MC 1.9, fixes MC-3564");
        fixStoneMonsterEggDoubleSpawns = config.getBoolean("fixStoneMonsterEggDoubleSpawns", categoryBugfixes, true, "Stone Monster Eggs only spawn one Silverfish when broken. From MC 1.8, fixes MC-31081");
        fixVillagePathsHavePlantsOnTop = config.getBoolean("fixVillagePathsHavePlantsOnTop", categoryBugfixes, true, "Village paths will not have flowers or grass on top of them. From MC 1.10, fixes MC-3437");
        fixVillageSieges = config.getBoolean("fixVillageSieges", categoryBugfixes, true, "Zombies will seige villages that are large enough at night. From MC 1.8, fixes MC-7432 and MC-7488");
        fixVillageWellDesertMaterial = config.getBoolean("fixVillageWellDesertMaterial", categoryBugfixes, true, "Wells in desert villages will use the correct material. From MC 1.8, fixes MC-32514");

        //Performance
        brokenChestsDontSplitStacks = config.getBoolean("brokenChestsDontSplitStacks", categoryPerformance, true, "Broken chests don't split apart dropped item stacks");
        fasterDroppedItemStackingChecks = config.getBoolean("fasterDroppedItemStackingChecks", categoryPerformance, true, "Dropped item nearby stack checks are faster for full stacks");
        fasterEntityLivingBaseIsPotionActiveAndSetAir = config.getBoolean("fasterEntityLivingBaseIsPotionActiveAndSetAir", categoryPerformance, true, "isPotionActive returns faster with 0 active potions and setAir only updates it's datawatcher when needed");
        fasterGetBlockByIdForAirBlocks = config.getBoolean("fasterGetBlockByIdForAirBlocks", categoryPerformance, true, "When something gets air from using its ID it will return faster");
        fasterSnowBlockTicks = config.getBoolean("fasterSnowBlockTicks", categoryPerformance, true, "Non-layered snow block ticks are faster");
        replaceRandomWithXSTRInBlockChest = config.getBoolean("replaceRandomWithXSTRInBlockChest", categoryPerformance, true, "Makes BlockChest.class use a faster implementation of random");
        replaceRandomWithXSTRInEffectRenderer = config.getBoolean("replaceRandomWithXSTRInEffectRenderer", categoryPerformance, true, "Makes EffectRenderer.class use a faster implementation of random");
        replaceRandomWithXSTRInEntity = config.getBoolean("replaceRandomWithXSTRInEntity", categoryPerformance, true, "Makes Entity.class use a faster implementation of random");
        replaceRandomWithXSTRInMinecraftServer = config.getBoolean("replaceRandomWithXSTRInMinecraftServer", categoryPerformance, true, "Makes MinecraftServer.class use a faster implementation of random");
        replaceRandomWithXSTRInRenderItem = config.getBoolean("replaceRandomWithXSTRInRenderItem", categoryPerformance, true, "Makes RenderItem.class use a faster implementation of random");
        replaceRandomWithXSTRInWorld = config.getBoolean("replaceRandomWithXSTRInWorld", categoryPerformance, false, "!This changes world generation! Makes World.class use a faster implementation of random");
        replaceRandomWithXSTRInWorldClient = config.getBoolean("replaceRandomWithXSTRInWorldClient", categoryPerformance, true, "Makes WorldClient.class use a faster implementation of random");
        skipInitialWorldChunkLoad = config.getBoolean("skipInitialWorldChunkLoad", categoryPerformance, true, "Speeds up initial world loading by not waiting for as many chunks to preload");

        //Tweaks
        lanPortOverride = config.getBoolean("lanPortOverride", categoryTweaks, false, "Override the port used when opening singleplayer to LAN");
        lanPortToUSeForOverride = config.getInt("lanPortToUSeForOverride", categoryTweaks, 25565, 1024 , 49151, "Port to use for lanPortOverride");

        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void loadModdedMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        fixGanysSurfaceOpenTrapdoorBackTexture = config.getBoolean("fixGanysSurfaceOpenTrapdoorBackTexture", categoryBugfixes, true, "The back of Gany's Surface trapdoors use the correct texture");

        if(config.hasChanged()) {
            config.save();
        }
    }

}
