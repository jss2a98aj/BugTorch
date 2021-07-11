package jss.bugtorch.mixins.minecraft.block;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.world.World;

@Mixin(value = BlockLeaves.class)
public class MixinBlockLeaves extends BlockLeavesBase {

    protected MixinBlockLeaves(Material material, boolean p_i45433_2_) {
        super(material, p_i45433_2_);
    }
    
    @Shadow()
    int[] field_150128_a;
    
    @Shadow()
    private void removeLeaves(World world, int x, int y, int z) {
    }

    /**
     * @author jss2a98aj
     * @reason Prevents duplicate drops when shearing
     */
    @Overwrite()
    @Override
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if(!(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemShears)) {
            super.harvestBlock(world, player, x, y, z, meta);
        }
    }
    
    @Overwrite()
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);

            if ((meta & 8) != 0 && (meta & 4) == 0) {
                byte decayRange = 8;//Change 4 to 8
                int searchRange = decayRange + 1;
                byte searchRangeMax = 32;
                int SearchRangeSquared = searchRangeMax * searchRangeMax;
                int halfSearchRangeMax = searchRangeMax / 2;

                if (this.field_150128_a == null) {
                    this.field_150128_a = new int[searchRangeMax * searchRangeMax * searchRangeMax];
                }

                int searchX;

                if (world.checkChunksExist(x - searchRange, y - searchRange, z - searchRange, x + searchRange, y + searchRange, z + searchRange)) {
                    int searchY;
                    int searchZ;

                    for (searchX = -decayRange; searchX <= decayRange; ++searchX) {
                        for (searchY = -decayRange; searchY <= decayRange; ++searchY) {
                            for (searchZ = -decayRange; searchZ <= decayRange; ++searchZ) {
                                Block block = world.getBlock(x + searchX, y + searchY, z + searchZ);

                                if (!block.canSustainLeaves(world, x + searchX, y + searchY, z + searchZ)) {
                                    if (block.isLeaves(world, x + searchX, y + searchY, z + searchZ)) {
                                        this.field_150128_a[(searchX + halfSearchRangeMax) * SearchRangeSquared + (searchY + halfSearchRangeMax) * searchRangeMax + searchZ + halfSearchRangeMax] = -2;
                                    } else {
                                        this.field_150128_a[(searchX + halfSearchRangeMax) * SearchRangeSquared + (searchY + halfSearchRangeMax) * searchRangeMax + searchZ + halfSearchRangeMax] = -1;
                                    }
                                } else {
                                    this.field_150128_a[(searchX + halfSearchRangeMax) * SearchRangeSquared + (searchY + halfSearchRangeMax) * searchRangeMax + searchZ + halfSearchRangeMax] = 0;
                                }
                            }
                        }
                    }

                    for (searchX = 1; searchX <= decayRange; ++searchX) {//Change 4 to decayRange
                        for (searchY = -decayRange; searchY <= decayRange; ++searchY) {
                            for (searchZ = -decayRange; searchZ <= decayRange; ++searchZ) {
                                for (int i = -decayRange; i <= decayRange; ++i) {
                                    if (this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax] == searchX - 1) {
                                        if (this.field_150128_a[(searchY + halfSearchRangeMax - 1) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax] == -2) {
                                            this.field_150128_a[(searchY + halfSearchRangeMax - 1) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax] = searchX;
                                        }

                                        if (this.field_150128_a[(searchY + halfSearchRangeMax + 1) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax] == -2) {
                                            this.field_150128_a[(searchY + halfSearchRangeMax + 1) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax] = searchX;
                                        }

                                        if (this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax - 1) * searchRangeMax + i + halfSearchRangeMax] == -2) {
                                            this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax - 1) * searchRangeMax + i + halfSearchRangeMax] = searchX;
                                        }

                                        if (this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax + 1) * searchRangeMax + i + halfSearchRangeMax] == -2) {
                                            this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax + 1) * searchRangeMax + i + halfSearchRangeMax] = searchX;
                                        }

                                        if (this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + (i + halfSearchRangeMax - 1)] == -2) {
                                            this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + (i + halfSearchRangeMax - 1)] = searchX;
                                        }

                                        if (this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax + 1] == -2) {
                                            this.field_150128_a[(searchY + halfSearchRangeMax) * SearchRangeSquared + (searchZ + halfSearchRangeMax) * searchRangeMax + i + halfSearchRangeMax + 1] = searchX;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                searchX = this.field_150128_a[halfSearchRangeMax * SearchRangeSquared + halfSearchRangeMax * searchRangeMax + halfSearchRangeMax];

                if (searchX >= 0) {
                    world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
                } else {
                    this.removeLeaves(world, x, y, z);
                }
            }
        }
    }

}
