package jss.bugtorch.mixins.late.witchery.rendering;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import com.emoniph.witchery.blocks.BlockWitchLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

@Mixin(value = BlockWitchLeaves.class, remap = false)
public abstract class MixinBlockWitchLeaves extends BlockLeavesBase {

	MixinBlockWitchLeaves(Material material, boolean renderFlag) {
		super(material, renderFlag);
	}

	@Shadow(remap = false)
	private IIcon[][] iconsForModes;

	/**
	 * @author jss2a98aj
	 * @reason Optifine and CoFH Tweaks compatible fast/fancy rendering.
	 */
	@Overwrite()
	public boolean isOpaqueCube() {
		return Blocks.leaves.isOpaqueCube();
	}

	/**
	 * @author jss2a98aj
	 * @reason Optifine and CoFH Tweaks compatible fast/fancy rendering.
	 */
	@Overwrite()
    public IIcon getIcon (int side, int metadata) {
        return (Blocks.leaves.isOpaqueCube() ? iconsForModes[1] : iconsForModes[0])[metadata % 4];
    }

}
