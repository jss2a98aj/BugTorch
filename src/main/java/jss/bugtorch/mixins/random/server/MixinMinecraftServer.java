package jss.bugtorch.mixins.random.server;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import jss.util.RandomXoshiro256StarStar;
import net.minecraft.server.MinecraftServer;

@Mixin(value = MinecraftServer.class)
public class MixinMinecraftServer {

    /**
     *Xoshiro256** is faster than Random
     */
    @Final
    private Random field_147146_q = new RandomXoshiro256StarStar();

}
