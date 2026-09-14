package jss.bugtorch.mixins;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;
import cpw.mods.fml.common.versioning.ComparableVersion;

import javax.annotation.Nonnull;

public enum TargetedMod implements ITargetMod {

    AETHER_II("aether"),
    ANGELICA("com.gtnewhorizons.angelica.loading.AngelicaTweaker", "angelica"),
    COFH_CORE("cofh.asm.LoadingPlugin", "CoFHCore"),
    CRAYFISH_FURNITURE("cfm"),
    EXTRA_UTILITIES("ExtraUtilities"),
    GANYS_SURFACE("ganyssurface"),
    HODGEPODGE("com.mitchej123.hodgepodge.core.HodgepodgeCore", "hodgepodge"),
    L_LIBRARY("llibrary"),
    LWJGL3IFY("me.eigenraven.lwjgl3ify.core.Lwjgl3ifyCoremod", "lwjgl3ify"),
    NOTFINE("jss.notfine.mixinplugin.NotFineEarlyMixins", "notfine"),
    OPTIFINE("optifine.OptiFineForgeTweaker", "Optifine"),
    THAUMCRAFT("Thaumcraft"),
    TX_LOADER("txloader", "glowredman.txloader.TXLoaderCore"),
    VILLAGE_NAMES("VillageNames"),
    WITCHERY("witchery");

    private final TargetModBuilder builder;

    TargetedMod(String modId) {
        this(null, modId, null);
    }

    TargetedMod(String coreModClass, String modId) {
        this(coreModClass, modId, null);
    }

    TargetedMod(String coreModClass, String modId, String targetClass) {
        this.builder = new TargetModBuilder().setCoreModClass(coreModClass).setModId(modId).setTargetClass(targetClass);
    }

    @Nonnull
    @Override
    public TargetModBuilder getBuilder() {
        return builder;
    }

    private static boolean isVersionLessThan(String version, String target) {
        return new ComparableVersion(version).compareTo(new ComparableVersion(target)) < 0;
    }
}
