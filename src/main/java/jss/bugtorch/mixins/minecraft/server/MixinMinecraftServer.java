package jss.bugtorch.mixins.minecraft.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.server.MinecraftServer;

@Mixin(value = MinecraftServer.class)
public class MixinMinecraftServer {

    /**
     * @author jss2a98aj
     * @reason Makes initial world loading faster
     */
    @Overwrite()
    public void initialWorldChunkLoad() {
    }

}
