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
    public static boolean fixSignPacketChatMessages;
    public static boolean fixStoneMonsterEggDoubleSpawns;
    public static boolean fixVillageSieges;

    //Mixin performance improvements
    public static boolean skipInitialWorldChunkLoad;

    //Mixin tweaks
    public static boolean preventCactusesFromDestroyingItems;

    //Mixin mod bugfixes
    public static boolean adjustGanysSurfaceTrapdoorSpineTextures;
    
    //Category names
    static final String categoryBackport = "backported features";
    static final String categoryBugfixes = "bug fixes";
    static final String categoryPerformance = "performance improvements";
    static final String categoryTweaks = "random tweaks";

    public static void initConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        enableFloatingTrapDoors = config.getBoolean("freeFloatingTrapDoors", categoryBackport, true, "From MC 1.9, Trapdoors no longer require attachment blocks");

        if(config.hasChanged()) {
            config.save();
        }
    }

    public static void initMixinConfig(File configFile) {
        Configuration config = new Configuration(configFile);

        cobwebsCanBeSheared = config.getBoolean("cobwebsCanBeSheared", categoryBackport, true, "Fixes MC-93001, From MC 1.9, Cobwebs can be collected with Shears without Silk Touch");
        deadBushesDropSticks = config.getBoolean("deadBushesDropSticks", categoryBackport, true, "From MC 1.9, Dead Bushes drop 0-2 Sticks");
        fireArrowsDetonateTNTCarts = config.getBoolean("fireArrowsDetonateTNTCarts", categoryBackport, true, "Fixes MC-8987, From MC 1.8, Minecarts with TNT explode when hit by fire arrows");
        throwEnderPearlsInCrativeMode = config.getBoolean("throwEnderPearlsInCrativeMode", categoryBackport, true, "Fixes MC-438, From MC 1.9, Ender Pearls can be thrown in creative mode");

        fixFireChargeUseSound = config.getBoolean("fixFireChargeUseSound", categoryBugfixes, true, "Fixes MC-1831, From MC 1.8, Fire Charges have the correct use sound");
        fixLavaHissOnAirReplace = config.getBoolean("fixLavaHissOnAirReplace", categoryBugfixes, true, "Fixes MC-32301, Lava will only hiss when mixing with water");
        fixPumpkinPlacementCheck = config.getBoolean("fixPumpkinPlacementCheck", categoryBackport, true, "Fixes MC-1947, From MC 1.13, Pumpkins and Jack o'Lanterns can be placed without a solid block below them");
        fixShearedBlocksDropExtraItems = config.getBoolean("fixShearedBlocksDropExtraItems", categoryBugfixes, true, "Shearing a block will not give drops in addition to itself");
        fixSignPacketChatMessages = config.getBoolean("fixSignPacketChatMessages", categoryBugfixes, true, "Fixes MC-3564, Sign update packets for signs in unloaded chunks will not send chat messages");
        fixStoneMonsterEggDoubleSpawns = config.getBoolean("fixStoneMonsterEggDoubleSpawns", categoryBugfixes, true, "Fixes MC-31081, From MC 1.8, Stone Monster Eggs only spawn one Silverfish in survival and none in creative");
        fixVillageSieges = config.getBoolean("fixVillageSieges", categoryBugfixes, true, "Fixes MC-7432 and MC-7488, Zombies will seige villages that are large enough at night");

        skipInitialWorldChunkLoad = config.getBoolean("skipInitialWorldChunkLoad", categoryPerformance, true, "Speeds up initial world loading");

        adjustGanysSurfaceTrapdoorSpineTextures = config.getBoolean("adjustGanysSurfaceTrapdoorSpineTextures", categoryTweaks, true, "Gany's Surface trapdoors spine textures will match the rest of the block");

        if(config.hasChanged()) {
            config.save();
        }
    }
    
    
}
