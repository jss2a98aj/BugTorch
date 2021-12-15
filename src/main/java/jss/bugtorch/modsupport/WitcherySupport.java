package jss.bugtorch.modsupport;

import com.emoniph.witchery.Witchery;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class WitcherySupport {

    public static void enableSupport() {
        //Bugfixes
        if(BugTorchConfig.fixWitcheryBlockSound) {
        	Witchery.Blocks.BRAMBLE.setStepSound(Block.soundTypeGrass);
            Witchery.Blocks.WOOD_SLAB_SINGLE.setStepSound(Block.soundTypeWood);
            Witchery.Blocks.WOOD_SLAB_DOUBLE.setStepSound(Block.soundTypeWood);
            Witchery.Blocks.STOCKADE.setStepSound(Block.soundTypeWood);
            Witchery.Blocks.STOCKADE_ICE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_DOOR.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_STAIRS.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_SLAB_SINGLE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_SLAB_DOUBLE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_FENCE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_FENCE_GATE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.PERPETUAL_ICE_PRESSURE_PLATE.setStepSound(Block.soundTypeGlass);
            Witchery.Blocks.REFILLING_CHEST.setStepSound(Block.soundTypeWood);
            Witchery.Blocks.LEECH_CHEST.setStepSound(Block.soundTypeWood);
            Witchery.Blocks.COFFIN.setStepSound(Block.soundTypeWood);
        }

        //Ore dictionary
        if(BugTorchConfig.registerWitcheryWoodSlabsToTheOreDictionary) {
        	 OreDictionary.registerOre("slabWood", new ItemStack(Witchery.Blocks.WOOD_SLAB_SINGLE, 1, OreDictionary.WILDCARD_VALUE));
        }
    }

}
