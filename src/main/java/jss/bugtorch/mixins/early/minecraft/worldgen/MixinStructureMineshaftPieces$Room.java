package jss.bugtorch.mixins.early.minecraft.worldgen;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import jss.bugtorch.ducks.IOffsetDuck;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

@Mixin(value = StructureMineshaftPieces.Room.class)
public abstract class MixinStructureMineshaftPieces$Room extends StructureComponent implements IOffsetDuck {

	@Shadow
	private List<StructureBoundingBox> roomsLinkedToTheRoom;

	/**
	 * Used to move Mineshaft air pockets to the intended place.
	 */
	public void offset(int x, int y, int z) {
	    for(StructureBoundingBox bb : (List<StructureBoundingBox>)roomsLinkedToTheRoom) {
	        bb.offset(x, y, z);
	    }
	}

}
