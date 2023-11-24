package jss.bugtorch.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.List;

public class LANButtonDisable {

    public static final LANButtonDisable INSTANCE = new LANButtonDisable();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if(event.gui instanceof GuiIngameMenu) {
            GuiButton openToLANButton = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 7).findFirst().get();
            openToLANButton.enabled = false;
        }
    }
}
