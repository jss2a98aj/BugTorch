package jss.bugtorch.mixins.early.minecraft.tweaks;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
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
	 * @reason Allows a specific port to be selected instead of a random one.
	 */
	@Overwrite()
	public String shareToLAN(WorldSettings.GameType gameType, boolean allowCommands) {
		try {
			int i = BugTorchConfig.lanPortToUseForOverride;
			func_147137_ag().addLanEndpoint((InetAddress)null, i);
			isPublic = true;
			lanServerPing = new ThreadLanServerPing(getMOTD(), i + "");
			lanServerPing.start();
			getConfigurationManager().func_152604_a(gameType);
			getConfigurationManager().setCommandsAllowedForAll(allowCommands);
			return i + "";
		} catch (IOException ex) {
			return null;
		}
	}

}
