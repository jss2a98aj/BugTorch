package jss.bugtorch.listeners;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jss.bugtorch.util.StaticUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class SquidSoundFixer {

    @SubscribeEvent
    public void onSquidLive(LivingUpdateEvent event) {
        if (isPass(event)) {

            EntitySquid sq = (EntitySquid) event.entityLiving;

            String tag = sq.getCustomNameTag();
            if (tag != null && tag.equals("Nelly")) {

                Vec3 a = sq.getLookVec().normalize();
                sq.fallDistance = 0;
                sq.addVelocity(a.xCoord * 0.08f, 0.08f, a.zCoord * 0.08f);
                sq.motionY = 0.08f;
                sq.velocityChanged = true;
            }

            if (sq.livingSoundTime == 0 && sq.isInWater()) {
                if (StaticUtils.getRandom(sq, 0.05)) {
                    StaticUtils.playSoundAtEntityRng("entity.squid.say", sq);
                }
            }
        }
    }

    @SubscribeEvent
    public void onSquidDeath(LivingDeathEvent event) {
        if (isPass(event)) {
            EntitySquid sq = (EntitySquid) event.entityLiving;
            StaticUtils.playSoundAtEntityRng("entity.squid.death", sq);
        }
    }

    @SubscribeEvent
    public void onSquidHurt(LivingHurtEvent event) {
        if (isPass(event)) {
            EntitySquid sq = (EntitySquid) event.entityLiving;
            StaticUtils.playSoundAtEntityRng("entity.squid.hurt", sq);
            if (StaticUtils.getRandom(sq, 0.15)) {
                doInk(sq);
            }
        }
    }

    private boolean isPass(LivingEvent event) {
        return !event.entity.worldObj.isRemote && EntitySquid.class.equals(event.entity.getClass());
    }

    private void doInk(EntitySquid sq) {

        World w = sq.worldObj;

        if (w instanceof WorldServer serverWorld && sq.isInWater()) {
            Random r = serverWorld.rand;

            Vec3 boxC = StaticUtils.findBBCCenter(sq);
            List<EntityLivingBase> around = StaticUtils.getEntitiesAround(boxC, 3, w, EntityLivingBase.class);

            if (around.isEmpty()) {
                return;
            }

            EntityLivingBase target = around.get(r.nextInt(around.size()));

            if (target != null && target != sq) {

                StaticUtils.addParticles("largesmoke", serverWorld, boxC, 5, 0.08f);
                StaticUtils.playSoundAtEntityRng("entity.squid.shoot", sq);

                if (target.isInWater()) {
                    addBlindnessEffect(target, w.rand.nextInt(300) + 20);
                }
            }
        }
    }

    private void addBlindnessEffect(EntityLivingBase e, int duration) {

        if (e == null || duration == 0) {
            return;
        }

        // Stack duration
        PotionEffect activeEff = e.getActivePotionEffect(Potion.blindness);

        int extraTime = 0;

        // Add to existing effect
        if (activeEff != null) {

            // Wow already too blind
            if (activeEff.getAmplifier() > 0) {
                return;
            }

            extraTime = activeEff.getDuration();
        }
        // Stack time
        e.addPotionEffect(new PotionEffect(Potion.blindness.id, duration + extraTime));
    }
}
