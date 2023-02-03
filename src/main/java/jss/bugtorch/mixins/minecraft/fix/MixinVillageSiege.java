package jss.bugtorch.mixins.minecraft.fix;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;

@Mixin(value = VillageSiege.class)
public abstract class MixinVillageSiege {

	@Shadow
	private World worldObj;
	@Shadow
	private int field_75533_d;
	@Shadow
	private int field_75534_e;
	@Shadow
	private Village theVillage;
	@Shadow
	private int field_75532_g;
	@Shadow
	private int field_75538_h;
	@Shadow
	private int field_75539_i;
	
	/**
	 * @author jss2a98aj
	 * @reason Allows Zombie sieges to start.
	 */
	@Overwrite()
	private boolean func_75529_b() {
		List<EntityPlayer> list = worldObj.playerEntities;
		Iterator iterator = list.iterator();

		while (true) {
			if (!iterator.hasNext()) {
				return false;
			}

			EntityPlayer entityplayer = (EntityPlayer)iterator.next();

			theVillage = worldObj.villageCollectionObj.findNearestVillage((int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ, 1);

			if (theVillage != null && theVillage.getNumVillageDoors() >= 10 && theVillage.getTicksSinceLastDoorAdding() >= 20 && theVillage.getNumVillagers() >= 20) {
				ChunkCoordinates chunkcoordinates = theVillage.getCenter();
				float f = (float)theVillage.getVillageRadius();
				boolean flag = false;

				for (int i = 0; i < 10; ++i) {
					float f1 = worldObj.rand.nextFloat() * (float)Math.PI * 2.0F;
					field_75532_g = chunkcoordinates.posX + (int)((double)(MathHelper.cos(f1) * f) * 0.9D);
					field_75538_h = chunkcoordinates.posY;
					field_75539_i = chunkcoordinates.posZ + (int)((double)(MathHelper.sin(f1) * f) * 0.9D);
					flag = false;
					Iterator iterator1 = worldObj.villageCollectionObj.getVillageList().iterator();

					while (iterator1.hasNext()) {
						Village village = (Village)iterator1.next();

						if (village != theVillage && village.isInRange(field_75532_g, field_75538_h, field_75539_i)) {
							flag = true;
							break;
						}
					}

					if (!flag) {
						break;
					}
				}

				if (flag) {
					return false;
				}

				Vec3 vec3 = func_75527_a(field_75532_g, field_75538_h, field_75539_i);

				if (vec3 != null) {
					break;
				}
			}
		}

		field_75534_e = 0;
		field_75533_d = 20;
		return true;
	}

	/**
	 * @author jss2a98aj
	 * @reason Fixes Zombie spawn location selection.
	 */
	@Overwrite()
	private Vec3 func_75527_a(int x, int y, int z) {
		for (int i = 0; i < 10; ++i) {
			int i1 = x + worldObj.rand.nextInt(16) - 8;
			int j1 = y + worldObj.rand.nextInt(6) - 3;
			int k1 = z + worldObj.rand.nextInt(16) - 8;

			if (theVillage.isInRange(i1, j1, k1) && SpawnerAnimals.canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, worldObj, i1, j1, k1)) {
				return Vec3.createVectorHelper((double)i1, (double)j1, (double)k1);
			}
		}
		return null;
	}

}
