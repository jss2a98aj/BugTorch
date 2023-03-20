package jss.bugtorch.mixins.early.minecraft.fix.block;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net/minecraft/block/Block$3")
public abstract class MixinSoundTypeAnvil extends Block.SoundType {

    @Override
    public String getStepResourcePath() {
        return "step.stone";
    }

    MixinSoundTypeAnvil(String name, float volume, float frequency) {
        super(name, volume, frequency);
    }

}
