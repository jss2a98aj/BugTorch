package jss.bugtorch.util;

import glowredman.txloader.TXLoaderCore;
import glowredman.txloader.Asset.Source;
import jss.bugtorch.config.BugTorchConfig;

public class AssetLoader {

    public static void load() {
        addAssets("sounds/mob/ghast/fireball4.ogg");

        if (BugTorchConfig.addSquidsSounds) {
            addAssets(
                    "sounds/entity/squid/ambient1.ogg",
                    "sounds/entity/squid/ambient2.ogg",
                    "sounds/entity/squid/ambient3.ogg",
                    "sounds/entity/squid/ambient4.ogg",
                    "sounds/entity/squid/ambient5.ogg",
                    "sounds/entity/squid/death1.ogg",
                    "sounds/entity/squid/death2.ogg",
                    "sounds/entity/squid/death3.ogg",
                    "sounds/entity/squid/hurt1.ogg",
                    "sounds/entity/squid/hurt2.ogg",
                    "sounds/entity/squid/hurt3.ogg",
                    "sounds/entity/squid/hurt4.ogg",
                    "sounds/entity/squid/squirt1.ogg",
                    "sounds/entity/squid/squirt2.ogg",
                    "sounds/entity/squid/squirt3.ogg");
        }
    }

    private static void addAssets(String... resourcePaths) {
        for (String path : resourcePaths) {
            TXLoaderCore.getAssetBuilder("minecraft/" + path)
                    .setOverride("bugtorch/" + path)
                    .setSource(Source.ASSET)
                    .setVersion("1.19.2")
                    .add();
        }
    }
}
