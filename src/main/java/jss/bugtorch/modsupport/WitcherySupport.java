package jss.bugtorch.modsupport;

import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.block.Block;

public class WitcherySupport {

    public static void enableSupport() {
        //Bugfixes
    	if(BugTorchConfig.fixWitcheryIceBlockSound) {
            com.emoniph.witchery.Witchery.Blocks.STOCKADE_ICE.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_DOOR.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_STAIRS.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_FENCE.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE.setStepSound(Block.soundTypeGlass);
            com.emoniph.witchery.Witchery.Blocks.PERPETUAL_ICE_PRESSURE_PLATE.setStepSound(Block.soundTypeGlass);
    	}
    }

}
