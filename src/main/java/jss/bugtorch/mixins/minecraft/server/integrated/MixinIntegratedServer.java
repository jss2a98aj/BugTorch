package jss.bugtorch.mixins.minecraft.server.integrated;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;

import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.WorldSettings;

@Mixin(value = IntegratedServer.class)
public abstract class MixinIntegratedServer extends MinecraftServer {

    @Shadow
    private boolean isPublic;
    @Shadow
    private ThreadLanServerPing lanServerPing;
    
    public MixinIntegratedServer(File file, Proxy proxy) {
        super(file, proxy);
    }

    /**
     * @author jss2a98aj
     * @reason Allows a specific port to be selected instead of a random one
     */
    @Overwrite()
    public String shareToLAN(WorldSettings.GameType gameType, boolean allowCommands) {
        try {
            int i = BugTorchConfig.lanPortToUseForOverride;
            this.func_147137_ag().addLanEndpoint((InetAddress)null, i);
            this.isPublic = true;
            this.lanServerPing = new ThreadLanServerPing(this.getMOTD(), i + "");
            this.lanServerPing.start();
            this.getConfigurationManager().func_152604_a(gameType);
            this.getConfigurationManager().setCommandsAllowedForAll(allowCommands);
            return i + "";
        } catch (IOException ioexception1) {
            return null;
        }
    }

}
