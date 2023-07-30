package jss.bugtorch.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.List;

public class BroadcastSettingsDisable {

    public static final BroadcastSettingsDisable INSTANCE = new BroadcastSettingsDisable();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if(event.gui instanceof GuiOptions) {
            GuiButton broadcastSettings = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 107).findFirst().get();
            broadcastSettings.enabled = false;
        }
    }

}
