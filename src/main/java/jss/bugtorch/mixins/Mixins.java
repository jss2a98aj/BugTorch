package jss.bugtorch.mixins;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import jss.bugtorch.config.BugTorchConfig;

import javax.annotation.Nonnull;

public enum Mixins implements IMixins {

    AETHER_II_RENDER_PLAYER_LEAK_FIX(new MixinBuilder()
        .addRequiredMod(TargetedMod.AETHER_II)
        .addClientMixins("aetherii.optimization.MixinClientEventHandler")
        .setApplyIf(() -> BugTorchConfig.reuseAetherIIRenderPlayer)
        .setPhase(Phase.LATE)),
    CRAYFISH_FURNITURE_DISABLE_ACHIEVEMENTS(new MixinBuilder()
        .addRequiredMod(TargetedMod.CRAYFISH_FURNITURE)
        .addCommonMixins("crayfishfurniture.tweak.MixinFurnitureAchievements")
        .setApplyIf(() -> BugTorchConfig.disableCrayfishFurnitureAchievements)
        .setPhase(Phase.LATE)),
    CRAYFISH_FURNITURE_ISIDEDINVENTORY_NPE(new MixinBuilder()
        .addRequiredMod(TargetedMod.CRAYFISH_FURNITURE)
        .addCommonMixins(
            "crayfishfurniture.fix.MixinTileEntityDishwasher",
            "crayfishfurniture.fix.MixinTileEntityMicrowave",
            "crayfishfurniture.fix.MixinTileEntityWashingMachine"
        )
        .setApplyIf(() -> BugTorchConfig.fixCrayfishFurnitureNullPointerException)
        .setPhase(Phase.LATE)),
    EXTRA_UTILITIES_DARKNESS_DAMAGE_MODIFIER(new MixinBuilder()
        .addRequiredMod(TargetedMod.EXTRA_UTILITIES)
        .addRequiredMod(TargetedMod.VILLAGE_NAMES)
        .addCommonMixins("extrautils.tweaks.damage.MixinDarknessDamage")
        .setApplyIf(() ->
            BugTorchConfig.scaledExtraUtilitiesDarknessDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledExtraUtilitiesDarknessDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.LATE)),
    EXTRA_UTILITIES_GOLDEN_LASSO_BLACKLIST(new MixinBuilder()
        .addRequiredMod(TargetedMod.EXTRA_UTILITIES)
        .addCommonMixins("extrautils.tweaks.MixinItemGoldenLasso")
        .setApplyIf(() -> BugTorchConfig.extraUtilitiesGoldenLassoBlacklist.length > 0)
        .setPhase(Phase.LATE)),
    EXTRA_UTILITIES_VILLAGE_NAMES_TRADING_POST_NITWIT_FILTER(new MixinBuilder()
        .addRequiredMod(TargetedMod.EXTRA_UTILITIES)
        .addRequiredMod(TargetedMod.VILLAGE_NAMES)
        .addCommonMixins("extrautils.tweaks.MixinTileEntityTradingPost")
        .setApplyIf(() -> BugTorchConfig.extraUtilitiesTradingPostVillageNamesNitwitFilter)
        .setPhase(Phase.LATE)),
    GANYS_SURFACE_TRAPDOOR_BACK_TEXTURE(new MixinBuilder()
        .addRequiredMod(TargetedMod.GANYS_SURFACE)
        .addClientMixins("ganyssurface.rendering.MixinBlockWoodTrapdoor")
        .setApplyIf(() -> BugTorchConfig.fixGanysSurfaceOpenTrapdoorBackTexture)
        .setPhase(Phase.LATE)),
    L_LIBRARY_MALFORMED_JSON_CRASH(new MixinBuilder()
        .addRequiredMod(TargetedMod.L_LIBRARY)
        .addCommonMixins("llibrary.fix.MixinWebUtils")
        .setApplyIf(() -> BugTorchConfig.fixLLibraryMalformedJsonCrash && !BugTorchConfig.proxyLLibraryPastebin)
        .setPhase(Phase.LATE)),
    L_LIBRARY_PROXY_PASTEBIN(new MixinBuilder()
        .addRequiredMod(TargetedMod.L_LIBRARY)
        .addCommonMixins("llibrary.tweak.MixinWebUtils")
        .setApplyIf(() -> BugTorchConfig.proxyLLibraryPastebin)
        .setPhase(Phase.LATE)),
    MC_COBWEB_SHEARING(new MixinBuilder()
        .addCommonMixins("minecraft.backport.MixinBlockWeb")
        .setApplyIf(() -> BugTorchConfig.cobwebsCanBeSheared)
        .setPhase(Phase.EARLY)),
    MC_CREATIVE_MODE_ENDER_PEARL_THROWING(new MixinBuilder()
        .addCommonMixins("minecraft.backport.MixinItemEnderPearl")
        .setApplyIf(() -> BugTorchConfig.throwEnderPearlsInCreativeMode)
        .setPhase(Phase.EARLY)),
    MC_DEAD_BUSHES_DROP_STICKS(new MixinBuilder()
        .addCommonMixins("minecraft.backport.MixinBlockDeadBush")
        .setApplyIf(() -> BugTorchConfig.deadBushesDropSticks)
        .setPhase(Phase.EARLY)),
    MC_FIRE_ARROW_TNT_CART_DETONATION(new MixinBuilder()
        .addCommonMixins("minecraft.backport.MixinEntityMinecartTNT")
        .setApplyIf(() -> BugTorchConfig.fireArrowsDetonateTNTCarts)
        .setPhase(Phase.EARLY)),
    MC_ANVIL_STEP_SOUND(new MixinBuilder()
        .addClientMixins("minecraft.fix.block.MixinSoundTypeAnvil")
        .setApplyIf(() -> BugTorchConfig.fixAnvilSoundTypeStepSound)
        .setPhase(Phase.EARLY)),
    MC_DARK_OAK_CHECK_BLOCK_REMOVAL(new MixinBuilder()
        .addCommonMixins("minecraft.fix.MixinWorldGenCanopyTree")
        .setApplyIf(() -> BugTorchConfig.fixDarkOakRemovingBlocks)
        .setPhase(Phase.EARLY)),
    MC_ENCHANTMENT_BLEND_FUNCTION(new MixinBuilder()
        .addExcludedMod(TargetedMod.ANGELICA)
        .addExcludedMod(TargetedMod.NOTFINE)
        .addClientMixins("minecraft.rendering.MixinRenderItem")
        .setApplyIf(() -> BugTorchConfig.fixEnchantmentBlendFunc)
        .setPhase(Phase.EARLY)),
    MC_FIRE_CHARGE_USE_SOUND(new MixinBuilder()
        .addRequiredMod(TargetedMod.TX_LOADER)
        .addClientMixins("minecraft.backport.MixinItemFireball")
        .setApplyIf(() -> BugTorchConfig.fixFireChargeUseSound)
        .setPhase(Phase.EARLY)),
    MC_LAVA_HISS_ON_BLOCK_REPLACE(new MixinBuilder()
        .addClientMixins("minecraft.fix.MixinBlockLiquid")
        .setApplyIf(() -> BugTorchConfig.fixLavaHissOnAirReplace)
        .setPhase(Phase.EARLY)),
    MC_LEAD_ON_MORE_FENCES(new MixinBuilder()
        .addCommonMixins("minecraft.fix.MixinEntityLeashKnot")
        .setApplyIf(() -> BugTorchConfig.fixLeadsBreakingOnSomeFenceInstances)
        .setPhase(Phase.EARLY)),
    MC_LEAF_DECAY_RANGE(new MixinBuilder()
        .addCommonMixins("minecraft.fix.MixinBlockLeaves")
        .setApplyIf(() -> BugTorchConfig.fixLeafDecayCheckRange)
        .setPhase(Phase.EARLY)),
    MC_MERGE_ITEM_STACK_COFH_DUPE_PREVENTION(new MixinBuilder()
        .addRequiredMod(TargetedMod.COFH_CORE)
        .addCommonMixins("cofhcore.fix.MixinInventoryHelper")
        .setApplyIf(() -> BugTorchConfig.fixMergeItemStack)
        .setPhase(Phase.EARLY)),
    MC_MERGE_ITEM_STACK_IMPROVEMENT(new MixinBuilder()
        .addExcludedMod(TargetedMod.COFH_CORE)
        .addCommonMixins("minecraft.fix.MixinContainer")
        .setApplyIf(() -> BugTorchConfig.fixMergeItemStack)
        .setPhase(Phase.EARLY)),
    MC_LILY_PAD_SERVER_SIDE_PLACEMENT(new MixinBuilder()
        .addCommonMixins("minecraft.fix.MixinItemLilyPad")
        .setApplyIf(() -> BugTorchConfig.fixLilyPadPlacementSide)
        .setPhase(Phase.EARLY)),
    MC_MINESHAFT_AIR_POCKETS(new MixinBuilder()
        .addCommonMixins(
            "minecraft.worldgen.MixinStructureStart",
            "minecraft.worldgen.MixinStructureMineshaftPieces$Room"
        )
        .setApplyIf(() -> BugTorchConfig.fixMineshaftAirPockets)
        .setPhase(Phase.EARLY)),
    MC_NETTY_CONNECTION_FAILURE_LEAK_PREVENTION(new MixinBuilder()
        .addCommonMixins("minecraft.logcleanup.MixinNioSocketChannel")
        .setApplyIf(() -> BugTorchConfig.fixNettyConnectionFailureResourceLeak)
        .setPhase(Phase.EARLY)),
    MC_PARTICLE_DEPTH_SORTING(new MixinBuilder()
        .addExcludedMod(TargetedMod.ANGELICA)
        .addExcludedMod(TargetedMod.NOTFINE)
        .addClientMixins("minecraft.rendering.MixinEffectRenderer")
        .setApplyIf(() -> BugTorchConfig.fixParticleDepthSorting)
        .setPhase(Phase.EARLY)),
    MC_PLACE_PUMPKIN_LIKE_NORMAL_BLOCK(new MixinBuilder()
        .addCommonMixins("minecraft.placement.MixinBlockPumpkin")
        .setApplyIf(() -> BugTorchConfig.fixPumpkinPlacementCheck)
        .setPhase(Phase.EARLY)),
    MC_REDSTONE_TORCH_MEMORY_LEAK(new MixinBuilder()
        .addExcludedMod(TargetedMod.HODGEPODGE)
        .addCommonMixins("minecraft.optimization.MixinBlockRedstoneTorch")
        .setApplyIf(() -> BugTorchConfig.fixRedstoneTorchMemoryLeak)
        .setPhase(Phase.EARLY)),
    MC_SILVERFISH_STONE_DOUBLE_SPAWN(new MixinBuilder()
        .addCommonMixins("minecraft.fix.MixinBlockSilverfish")
        .setApplyIf(() -> BugTorchConfig.fixStoneMonsterEggDoubleSpawns)
        .setPhase(Phase.EARLY)),
    MC_STRUCTURE_COMPONENT_FILL_REPLACE(new MixinBuilder()
        .addCommonMixins("minecraft.worldgen.MixinStructureComponent")
        .setApplyIf(() -> BugTorchConfig.fixStructureComponentFillReplacement)
        .setPhase(Phase.EARLY)),
    MC_TALL_GRASS_SHEAR_DUPE(new MixinBuilder()
        .addCommonMixins("minecraft.shearing.MixinBlockTallGrass")
        .setApplyIf(() -> BugTorchConfig.fixShearedGrassDropDupe)
        .setPhase(Phase.EARLY)),
    MC_LEAVES_SHEAR_DUPE(new MixinBuilder()
        .addCommonMixins("minecraft.shearing.MixinBlockLeaves")
        .setApplyIf(() -> BugTorchConfig.fixShearedLeavesDropDupe)
        .setPhase(Phase.EARLY)),
    MC_SHEAR_DAMAGE_NORMALLY(new MixinBuilder()
        .addCommonMixins("minecraft.shearing.MixinItemShears")
        .setApplyIf(() -> BugTorchConfig.fixShearsNotTakingDamageFromNormalBlocks)
        .setPhase(Phase.EARLY)),
    MC_SIGN_PACKET_CHAT_MESSAGE(new MixinBuilder()
        .addClientMixins("minecraft.logcleanup.MixinNetHandlerPlayClient")
        .setApplyIf(() -> BugTorchConfig.fixSignPacketChatMessages)
        .setPhase(Phase.EARLY)),
    MC_VILLAGE_PATH_PLANTS(new MixinBuilder()
        .addCommonMixins("minecraft.worldgen.MixinStructureVillagePieces_Path")
        .setApplyIf(() -> BugTorchConfig.fixVillagePathsHavePlantsOnTop)
        .setPhase(Phase.EARLY)),
    MC_VILLAGER_TRADE_META(new MixinBuilder()
        .addCommonMixins(
            "minecraft.villagertrademeta.MixinSlotMerchantResult",
            "minecraft.villagertrademeta.MixinMerchantRecipe"
        )
        .setApplyIf(() -> BugTorchConfig.fixVillagerTradeMetadataDetection)
        .setPhase(Phase.EARLY)),
    MC_VILLAGE_SEIGE(new MixinBuilder()
        .addCommonMixins("minecraft.fix.MixinVillageSiege")
        .setApplyIf(() -> BugTorchConfig.fixVillageSieges)
        .setPhase(Phase.EARLY)),
    MC_DESERT_WELL_MATERIAL(new MixinBuilder()
        .addCommonMixins("minecraft.worldgen.MixinStructureVillagePieces_Well")
        .setApplyIf(() -> BugTorchConfig.fixVillageWellDesertMaterial)
        .setPhase(Phase.EARLY)),
    MC_SOUND_MANAGER_RACE_CONDTION(new MixinBuilder()
        .addExcludedMod(TargetedMod.LWJGL3IFY)
        .addClientMixins("minecraft.fix.MixinSoundManager")
        .setApplyIf(() -> BugTorchConfig.fixLWJGL2OpenALCrash)
        .setPhase(Phase.EARLY)),
    MC_BROKEN_CHEST_UNSPLIT_STACK_DROPS(new MixinBuilder()
        .addCommonMixins("minecraft.optimization.MixinBlockChest")
        .setApplyIf(() -> BugTorchConfig.brokenChestsDontSplitStacks)
        .setPhase(Phase.EARLY)),
    MC_BROKEN_HOPPER_UNSPLIT_STACK_DROPS(new MixinBuilder()
        .addCommonMixins("minecraft.optimization.MixinBlockHopper")
        .setApplyIf(() -> BugTorchConfig.brokenHoppersDontSplitStacks)
        .setPhase(Phase.EARLY)),
    MC_DROPPED_ITEM_FULL_STACK_EARLY_STOP(new MixinBuilder()
        .addCommonMixins("minecraft.optimization.MixinEntityItem")
        .setApplyIf(() -> BugTorchConfig.fasterDroppedItemStackingChecks)
        .setPhase(Phase.EARLY)),
    MC_FASTER_ENTITY_LIVING_BASE_POTION_ACTIVE_AND_SET_AIR(new MixinBuilder()
        .addCommonMixins("minecraft.optimization.MixinEntityLivingBase")
        .setApplyIf(() -> BugTorchConfig.fasterEntityLivingBaseIsPotionActiveAndSetAir)
        .setPhase(Phase.EARLY)),
    MC_FASTER_AIR_BLOCK_BY_ID(new MixinBuilder()
        .addCommonMixins("minecraft.optimization.MixinBlock")
        .setApplyIf(() -> BugTorchConfig.fasterGetBlockByIdForAirBlocks)
        .setPhase(Phase.EARLY)),
    MC_FASTER_OPTION_INTERACTIONS(new MixinBuilder()
        .addExcludedMod(TargetedMod.OPTIFINE)
        .addClientMixins(
            "minecraft.optimization.gamesettings.MixinFasterSetOptions",
            "minecraft.optimization.MixinGameSettings_Options"
        )
        .setApplyIf(() -> BugTorchConfig.fasterOptionInteractions)
        .setPhase(Phase.EARLY)),
    MC_FASTER_OPTION_LOADING(new MixinBuilder()
        .addExcludedMod(TargetedMod.OPTIFINE)
        .addClientMixins("minecraft.optimization.gamesettings.MixinFasterLoadOptions")
        .setApplyIf(() -> BugTorchConfig.fasterOptionLoading)
        .setPhase(Phase.EARLY)),
    MC_FASTER_SNOW_BLOCK_TICK(new MixinBuilder()
        .addCommonMixins("minecraft.optimization.MixinBlockSnowBlock")
        .setApplyIf(() -> BugTorchConfig.fasterSnowBlockTicks)
        .setPhase(Phase.EARLY)),
    MC_MORE_ACCURATE_LAYERED_SNOW_FACE_CULLING(new MixinBuilder()
        .addExcludedMod(TargetedMod.ANGELICA)
        .addExcludedMod(TargetedMod.NOTFINE)
        .addClientMixins("minecraft.optimization.MixinBlockSnow")
        .setApplyIf(() -> BugTorchConfig.moreAccurateLayeredSnowFaceCulling)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_EFFECT_RENDER(new MixinBuilder()
        .addClientMixins("minecraft.fastrandom.MixinEffectRenderer")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInEffectRenderer)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_ENTITY(new MixinBuilder()
        .addCommonMixins("minecraft.fastrandom.MixinEntity")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInEntity)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_ITEM(new MixinBuilder()
        .addCommonMixins("minecraft.fastrandom.MixinItem")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInItem)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_MINECRAFT_SERVER(new MixinBuilder()
        .addCommonMixins("minecraft.fastrandom.MixinMinecraftServer")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInMinecraftServer)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_MINECRAFT_RENDER_ITEM(new MixinBuilder()
        .addClientMixins("minecraft.fastrandom.MixinRenderItem")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInRenderItem)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_WORLD(new MixinBuilder()
        .addCommonMixins("minecraft.fastrandom.MixinWorld")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInWorld)
        .setPhase(Phase.EARLY)),
    MC_FASTER_RANDOM_IN_WORLD_CLIENT(new MixinBuilder()
        .addExcludedMod(TargetedMod.ANGELICA)
        .addExcludedMod(TargetedMod.NOTFINE)
        .addClientMixins("minecraft.fastrandom.MixinWorldClient")
        .setApplyIf(() -> BugTorchConfig.replaceRandomInWorldClient)
        .setPhase(Phase.EARLY)),
    MC_SKIP_INITIAL_WORLD_CHUNK_LOAD(new MixinBuilder()
        .addClientMixins("minecraft.optimization.MixinMinecraftServer")
        .setApplyIf(() -> BugTorchConfig.skipInitialWorldChunkLoad)
        .setPhase(Phase.EARLY)),
    MC_ENCHANT_PARTICLES_CHECK_ENCHANT_POWER(new MixinBuilder()
        .addExcludedMod(TargetedMod.ANGELICA)
        .addExcludedMod(TargetedMod.NOTFINE)
        .addClientMixins("minecraft.rendering.MixinBlockEnchantmentTable")
        .setApplyIf(() -> BugTorchConfig.enchantmentParticlesForPowerAboveZero)
        .setPhase(Phase.EARLY)),
    MC_EXCLUDE_LOGS_FROM_TOP_SOLID_OR_LIQUID_CHECK(new MixinBuilder()
        .addCommonMixins("minecraft.worldgen.MixinWorld")
        .setApplyIf(() -> BugTorchConfig.excludeLogsFromTopSolidOrLiquidBlock)
        .setPhase(Phase.EARLY)),
    MC_FARMLAND_HYDROPONICS(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.blockfarmland.MixinHydroponics")
        .setApplyIf(() -> BugTorchConfig.farmlandHydroponics)
        .setPhase(Phase.EARLY)),
    MC_FARMLAND_UNIQUE_TEXTURES(new MixinBuilder()
        .addClientMixins("minecraft.tweaks.blockfarmland.MixinNewTextures")
        .setApplyIf(() -> BugTorchConfig.farmlandNewTextures)
        .setPhase(Phase.EARLY)),
    MC_FARMLAND_NO_TRAMPLE(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.blockfarmland.MixinNoTrample")
        .setApplyIf(() -> BugTorchConfig.farmlandNoTrample)
        .setPhase(Phase.EARLY)),
    MC_LAN_PORT_OVERRIDE(new MixinBuilder()
        .addExcludedMod(TargetedMod.HODGEPODGE)
        .addClientMixins("minecraft.tweaks.MixinIntegratedServer")
        .setApplyIf(() -> BugTorchConfig.lanPortOverride)
        .setPhase(Phase.EARLY)),
    MC_END_PORTAL_PLACEMENT_ANYWHERE(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.MixinBlockEndPortal")
        .setApplyIf(() -> BugTorchConfig.placeEndPortalsAnywhere)
        .setPhase(Phase.EARLY)),
    MC_PRESSURE_PLATES_ON_ANY_WALL_OR_FENCE(new MixinBuilder()
        .addCommonMixins("minecraft.placement.MixinBlockBasePressurePlate")
        .setApplyIf(() -> BugTorchConfig.placePressurePlatesOnAnyWallOrFence)
        .setPhase(Phase.EARLY)),
    MC_PRESSURE_TORCHES_ON_ANY_FENCE(new MixinBuilder()
        .addCommonMixins("minecraft.placement.MixinBlockFence")
        .setApplyIf(() -> BugTorchConfig.placeTorchesOnAnyFence)
        .setPhase(Phase.EARLY)),
    MC_PRESSURE_TORCHES_ON_ANY_WALL(new MixinBuilder()
        .addCommonMixins("minecraft.placement.MixinBlockWall")
        .setApplyIf(() -> BugTorchConfig.placeTorchesOnAnyWall)
        .setPhase(Phase.EARLY)),
    MC_POTION_PARTICLES_CLEAR_ON_SELF(new MixinBuilder()
        .addExcludedMod(TargetedMod.HODGEPODGE)
        .addClientMixins("minecraft.tweaks.entitylivingbase.MixinTranslucentClientPotionEffects")
        .setApplyIf(() -> BugTorchConfig.potionParticlesAreClearForClientPlayer)
        .setPhase(Phase.EARLY)),
    MC_LIGHTNING_VOLUME(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.MixinEntityLightningBolt")
        .setApplyIf(() -> BugTorchConfig.reduceLightningVolume < 10000f)
        .setPhase(Phase.EARLY)),
    MC_ENTITY_DUPLICATE_EXTENDED_PROPERTIES_IDENTIFIER_SPAM(new MixinBuilder()
        .addCommonMixins("minecraft.logcleanup.MixinEntity")
        .setApplyIf(() -> BugTorchConfig.removeEntityDuplicateExtendedPropertiesIdentifierSpam)
        .setPhase(Phase.EARLY)),
    MC_DROWNING_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.entitylivingbase.MixinScalingDrowningDamage")
        .setApplyIf(() ->
            BugTorchConfig.scaledDrowningDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledDrowningDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_LAVA_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.damage.MixinLavaDamage")
        .setApplyIf(() ->
            BugTorchConfig.scaledLavaDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledLavaDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_FIRE_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins(
            "minecraft.tweaks.damage.MixinFireDamage_Entity",
            "minecraft.tweaks.damage.MixinFireDamage_EntityPlayer"
        )
        .setApplyIf(() ->
            BugTorchConfig.scaledFireDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledFireDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_STARVATION_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.MixinFoodStats")
        .setApplyIf(() ->
            BugTorchConfig.scaledStarvationDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledStarvationDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_SUFFOCATION_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.entitylivingbase.MixinScalingSuffocationDamage")
        .setApplyIf(() ->
            BugTorchConfig.scaledSuffocationDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledSuffocationDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_POISON_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.potion.MixinsPotionPoison")
        .setApplyIf(() ->
            BugTorchConfig.scaledPoisonDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledPoisonDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_WITHER_DAMAGE_MODIFIER(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.potion.MixinsPotionWither")
        .setApplyIf(() ->
            BugTorchConfig.scaledWitherDamageMaxHealthFlat > 0f ||
            BugTorchConfig.scaledWitherDamageMaxHealthMult > 0f
        )
        .setPhase(Phase.EARLY)),
    MC_ANY_DYE_ON_LEATHER_ARMOR(new MixinBuilder()
        .addCommonMixins("minecraft.tweaks.MixinRecipeArmorDyes")
        .setApplyIf(() -> BugTorchConfig.useAnyDyeOnLeatherArmor
        )
        .setPhase(Phase.EARLY)),
    SMART_RENDER_DISABLE_HANDLER(new MixinBuilder()
        .addCommonMixins("smartrender.MixinRenderingRegistry")
        .setApplyIf(() -> BugTorchConfig.disableSmartRenderHandler
        )
        .setPhase(Phase.EARLY)),
    THAUMCRAFT_CANDLE_COLOR_NPE(new MixinBuilder()
        .addRequiredMod(TargetedMod.THAUMCRAFT)
        .addClientMixins("thaumcraft.sanitizearrayaccess.MixinBlockCandleRenderer")
        .addCommonMixins("thaumcraft.sanitizearrayaccess.MixinBlockCandle")
        .setApplyIf(() -> BugTorchConfig.fixThaumcraftCandleColorArrayOutOfBounds)
        .setPhase(Phase.LATE)),
    WITCHERY_GARLIC_GARLAND_BOUNDS(new MixinBuilder()
        .addRequiredMod(TargetedMod.WITCHERY)
        .addCommonMixins("witchery.fix.MixinBlockGarlicGarland")
        .setApplyIf(() -> BugTorchConfig.fixWitcheryGarlicGarlandBlockBounds)
        .setPhase(Phase.LATE)),
    WITCHERY_LEAF_RENDERING(new MixinBuilder()
        .addRequiredMod(TargetedMod.WITCHERY)
        .addExcludedMod(TargetedMod.NOTFINE)
        .addClientMixins("witchery.rendering.MixinBlockWitchLeaves")
        .setApplyIf(() -> BugTorchConfig.fixWitcheryLeavesOptifineRendering)
        .setPhase(Phase.LATE)),
    WITCHERY_LEAF_SHEAR_DUPE(new MixinBuilder()
        .addRequiredMod(TargetedMod.WITCHERY)
        .addCommonMixins("witchery.shearing.MixinBlockWitchLeaves")
        .setApplyIf(() -> BugTorchConfig.fixWitcheryLeavesShearDupe)
        .setPhase(Phase.LATE));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }




}
