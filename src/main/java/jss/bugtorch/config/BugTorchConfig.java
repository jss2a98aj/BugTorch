package jss.bugtorch.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class BugTorchConfig {

    //Regular backports
    public static boolean enableFloatingTrapDoors;

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
    public static boolean skipInitialWorldChunkLoad;
    public static boolean fasterDroppedItemStackingChecks;

    //Mixin tweaks
    public static boolean lanPortOverride;
    public static int lanPortToUSeForOverride;

    //Mixin mod bugfixes
    public static boolean fixGanysSurfaceOpenTrapdoorBackTexture;

    //Category names
    static final String categoryBackport = "backported features";
    static final String categoryBugfixes = "bug fixes";
    static final String categoryPerformance = "performance improvements";
    static final String categoryTweaks = "tweaks";

    public static void loadBaseConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        enableFloatingTrapDoors = config.getBoolean("freeFloatingTrapDoors", categoryBackport, true, "Trapdoors no longer require attachment blocks. From MC 1.9");

        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void loadModdedConfig(File configFile) {  //loadModdedConfig(new File(path + "baseModSupport.cfg"));
        Configuration config = new Configuration(configFile);

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
        fixStoneMonsterEggDoubleSpawns = config.getBoolean("fixStoneMonsterEggDoubleSpawns", categoryBugfixes, true, "Stone Monster Eggs only spawn one Silverfish in survival and none in creative. From MC 1.8, fixes MC-31081");
        fixVillagePathsHavePlantsOnTop = config.getBoolean("fixVillagePathsHavePlantsOnTop", categoryBugfixes, true, "Village paths will not have flowers or grass on top of them. From MC 1.10, fixes MC-3437");
        fixVillageSieges = config.getBoolean("fixVillageSieges", categoryBugfixes, true, "Zombies will seige villages that are large enough at night. From MC 1.8, fixes MC-7432 and MC-7488");
        fixVillageWellDesertMaterial = config.getBoolean("fixVillageWellDesertMaterial", categoryBugfixes, true, "Wells in desert villages will use the correct material. From MC 1.8, fixes MC-32514");

        //Performance
        skipInitialWorldChunkLoad = config.getBoolean("skipInitialWorldChunkLoad", categoryPerformance, true, "Speeds up initial world loading");
        fasterDroppedItemStackingChecks = config.getBoolean("fasterDroppedItemStackingChecks", categoryPerformance, true, "Dropped item stacking checks are much faster for full stacks");

        //Tweaks
        lanPortOverride = config.getBoolean("lanPortOverride", categoryTweaks, true, "Override the port used when opening singleplayer to LAN");
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
