package jss.bugtorch.mixins.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import jss.bugtorch.core.ducks.IOffsetDuck;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

//Doesn't work because the class I need to target just to happens to be a subclass, and static
//And one of those stops the mixin from functioning...
@Mixin(value = StructureMineshaftPieces.Room.class)
public class MixinStructureMineshaftPieces$Room extends StructureComponent implements IOffsetDuck {
	
	@Shadow
	private List<StructureBoundingBox> roomsLinkedToTheRoom;
	
	public void offset(int x, int y, int z) {
	    Iterator linkedComponentsIterator = this.roomsLinkedToTheRoom.iterator();
	    while (linkedComponentsIterator.hasNext()) {
	        StructureBoundingBox bb = (StructureBoundingBox) linkedComponentsIterator.next();
	        bb.offset(x, y, z);
	    }
	}

	@Shadow
	protected void func_143012_a(NBTTagCompound p_143012_1_) {
	}
	@Shadow
	protected void func_143011_b(NBTTagCompound p_143011_1_) {
	}
	@Shadow
	public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
		return false;
	}

}
