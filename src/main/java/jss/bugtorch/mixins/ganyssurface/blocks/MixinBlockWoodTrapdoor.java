package jss.bugtorch.mixins.ganyssurface.blocks;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganyssurface.blocks.BlockWoodTrapdoor;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

@Mixin(value = BlockWoodTrapdoor.class)
public abstract class MixinBlockWoodTrapdoor extends BlockTrapDoor {

	@Shadow(remap = false)
	@Final
	public int woodMeta = 0;

	protected MixinBlockWoodTrapdoor(Material material) {
		super(material);
	}

	/**
	 * @author jss2a98aj
	 * @reason Makes trapdoors from Gany's Surface have the correct back texture when open
	 */
	@Overwrite()
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (func_150118_d(meta)) {
			switch (meta & 3) {
			case 0:// North/South
			case 1:
				if (side == 2 | side == 3)
					return super.getIcon(side, meta);
				break;
			case 2:// East/West
			case 3:
				if (side == 4 | side == 5)
					return super.getIcon(side, meta);
				break;
			}
			return Blocks.planks.getIcon(side, woodMeta);
		}
		return side == 0 || side == 1 ? super.getIcon(side, meta) : Blocks.planks.getIcon(side, woodMeta);
	}

}
