package jss.bugtorch.mixins.minecraft.logcleanup;

import java.util.HashMap;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.IExtendedEntityProperties;

@Mixin(value = Entity.class)
public abstract class MixinEntity {

	@Shadow(remap = false)
	protected HashMap<String, IExtendedEntityProperties> extendedProperties;

	/**
	 * @author jss2a98aj
	 * @reason Stops log spam caused by some mods.
	 */
	@Overwrite(remap = false)
	public String registerExtendedProperties(String identifier, IExtendedEntityProperties properties) {
		if (identifier == null) {
			FMLLog.warning("Someone is attempting to register extended properties using a null identifier.  This is not allowed.  Aborting.  This may have caused instability.");
			return "";
		}
		if (properties == null) {
			FMLLog.warning("Someone is attempting to register null extended properties.  This is not allowed.  Aborting.  This may have caused instability.");
			return "";
		}

		String baseIdentifier = identifier;
		int identifierModCount = 1;
		while (this.extendedProperties.containsKey(identifier)) {
			identifier = String.format("%s%d", baseIdentifier, identifierModCount++);
		}

		this.extendedProperties.put(identifier, properties);
		return identifier;
	}

}
