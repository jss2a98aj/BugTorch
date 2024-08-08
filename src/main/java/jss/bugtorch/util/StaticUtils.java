package jss.bugtorch.util;

import java.util.List;
import java.util.Random;
import jss.bugtorch.BugTorch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class StaticUtils {

    public static boolean getRandom(Entity e, double chanceMul) {
        return (e.worldObj.rand.nextDouble() < chanceMul);
    }

    public static <T> List<T> getEntitiesAround(Vec3 centr, int boxSize, World w, Class<T> type) {

        // Make some CUBE
        int half = boxSize / 2;
        AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, boxSize, boxSize, boxSize);
        // Centre box
        box.offset(-half, -half, -half);
        // Offset to cords
        box.offset(centr.xCoord, centr.yCoord, centr.zCoord);

        return w.getEntitiesWithinAABB(type, box);
    }

    public static Vec3 findBBCCenter(Entity e) {

        AxisAlignedBB eBox = e.boundingBox;

        double cx = eBox.maxX - halfDist(eBox.maxX, eBox.minX);
        double cy = eBox.maxY - halfDist(eBox.maxY, eBox.minY);
        double cz = eBox.maxZ - halfDist(eBox.maxZ, eBox.minZ);

        return Vec3.createVectorHelper(cx, cy, cz);
    }

    public static double halfDist(double p1, double p2) {
        return (p1 - p2) / 2;
    }

    public static void playSoundAtEntityRng(String soundName, EntityLivingBase e) {

        if (soundName == null || e == null) {
            return;
        }

        Random r = e.worldObj.rand;
        float v = e.isChild() ? 1.5F : +1.0F;
        float pitch = (r.nextFloat() - r.nextFloat()) * 0.2F + v;

        e.worldObj.playSoundAtEntity(e, BugTorch.MODID + ":" + soundName, 0.4F, pitch);
    }

    public static void addParticles(String name, World w, Vec3 pos, int count, float power) {
        if (w instanceof WorldServer) {
            ((WorldServer) w).func_147487_a(name, pos.xCoord, pos.yCoord, pos.zCoord, count, 0, 0, 0, power);
        }
    }
}
