package jss.bugtorch.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.List;

public class SuperSecretSettingsDisable {

    public static final SuperSecretSettingsDisable INSTANCE = new SuperSecretSettingsDisable();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if(event.gui instanceof GuiOptions) {
            GuiButton superSecretSettings = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 8675309).findFirst().get();
            superSecretSettings.enabled = false;
        }
    }

}
