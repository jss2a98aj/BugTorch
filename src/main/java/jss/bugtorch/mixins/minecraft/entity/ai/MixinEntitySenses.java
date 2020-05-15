package jss.bugtorch.mixins.minecraft.entity.ai;

import java.util.HashSet;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntitySenses;

@Mixin(EntitySenses.class)
public class MixinEntitySenses {
    //TODO: see if this works and implement if so
    //Replace List with HashSet
    HashSet<Entity> seenEntities = new HashSet<Entity>();
    HashSet<Entity> unseenEntities = new HashSet<Entity>();
}
