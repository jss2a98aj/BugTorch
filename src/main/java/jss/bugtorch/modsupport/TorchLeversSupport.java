package jss.bugtorch.modsupport;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class TorchLeversSupport {

    public static void enableSupport() {
        //Bugfixes
        String torchLevers = "torchLevers";
        Block block = GameRegistry.findBlock(torchLevers, "bookButton");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "paintingDoor");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor1");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor2");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor3");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor4");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor5");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor6");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor7");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
        block = GameRegistry.findBlock(torchLevers, "carpetDoor8");
        if(block != null) {
            block.stepSound = Block.soundTypeWood;
        }
    }

}
