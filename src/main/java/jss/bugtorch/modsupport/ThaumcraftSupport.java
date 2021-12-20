package jss.bugtorch.modsupport;

import cpw.mods.fml.common.registry.GameRegistry;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.common.config.ConfigBlocks;

public class ThaumcraftSupport {

	public static void enableSupport() {
		ItemStack ancientStone = new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 11);

		//Ore dictionary
		if(BugTorchConfig.registerThaumcraftLeavesToTheOreDictionary) {
			OreDictionary.registerOre("treeLeaves", new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, OreDictionary.WILDCARD_VALUE));
		}

		if(BugTorchConfig.registerThaumcraftThaumiumBlockToTheOreDictionary) {
			OreDictionary.registerOre("blockThaumium", new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 4));
		}

		if(BugTorchConfig.registerThaumcraftWoodStairsToTheOreDictionary) {
			OreDictionary.registerOre("stairWood", new ItemStack(ConfigBlocks.blockStairsSilverwood));
			OreDictionary.registerOre("stairWood", new ItemStack(ConfigBlocks.blockStairsGreatwood));
		}

		//Tweaks
		if(BugTorchConfig.craftThaumcraftAncientStoneSlabsAndStairs) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ConfigBlocks.blockSlabStone, 6, 1), "XXX", Character.valueOf('X'), ancientStone));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ConfigBlocks.blockStairsEldritch, 4), "X  ", "XX ", "XXX", Character.valueOf('X'), ancientStone));
		}

		if(BugTorchConfig.reverseCraftThaumcraftSlabs) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), "X", "X", Character.valueOf('X'), new ItemStack(ConfigBlocks.blockSlabWood, 1, 0)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7), "X", "X", Character.valueOf('X'), new ItemStack(ConfigBlocks.blockSlabWood, 1, 1)));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7), "X", "X", Character.valueOf('X'), new ItemStack(ConfigBlocks.blockSlabStone, 1, 0)));
			if(BugTorchConfig.craftThaumcraftAncientStoneSlabsAndStairs) {
				GameRegistry.addRecipe(new ShapedOreRecipe(ancientStone, "X", "X", Character.valueOf('X'), new ItemStack(ConfigBlocks.blockSlabStone, 1, 1)));
			}
		}
	}

}
