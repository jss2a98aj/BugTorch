package jss.bugtorch.mixins.minecraft.world.gen.structure;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.House2;

@Mixin(House2.class)
public abstract class MixinStructureVillagePieces_House2 extends StructureVillagePieces.Village {

    @Inject(method = "addComponentParts", at = @At(value = "RETURN", ordinal = 1))
    protected void onAddComponentParts(World worldIn, Random random, StructureBoundingBox boundingBox, CallbackInfo ci) {
        this.placeBlockAtCurrentPosition(worldIn, Blocks.anvil, 0, 8, 1, 1, boundingBox);
    }
    
}
