package jss.bugtorch.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.List;
import java.util.Optional;

public class ButtonManager {
    public static final ButtonManager INSTANCE = new ButtonManager();

    @SuppressWarnings("unchecked")
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if(event.gui instanceof GuiOptions) {
            //Super Secret Settings
            Optional<GuiButton> optionalSuperSecretSettings = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 8675309).findFirst();
            if(optionalSuperSecretSettings.isPresent()) {
                GuiButton superSecretSettings = optionalSuperSecretSettings.get();
                superSecretSettings.visible = BugTorchConfig.showSuperSecretSettingsButton != -1;
                superSecretSettings.enabled = BugTorchConfig.showSuperSecretSettingsButton == 1;
            }
            //Broadcast Settings
            Optional<GuiButton> optionalBroadcastSettings = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 107).findFirst();
            if(optionalBroadcastSettings.isPresent()) {
                GuiButton broadcastSettings = optionalBroadcastSettings.get();
                broadcastSettings.visible = BugTorchConfig.showBroadcastSettingsButton != -1;
                broadcastSettings.enabled = BugTorchConfig.showBroadcastSettingsButton == 1;
                //If Broadcast Settings is not visible move Super Secret Settings to its place
                if(!broadcastSettings.visible && optionalSuperSecretSettings.isPresent()) {
                    GuiButton superSecretSettings = optionalSuperSecretSettings.get();
                    superSecretSettings.xPosition = broadcastSettings.xPosition;
                    superSecretSettings.yPosition = broadcastSettings.yPosition;
                }
            }
        } else if(BugTorchConfig.showBroadcastSettingsButton != 1 && event.gui instanceof GuiIngameMenu) {
            //Open to LAN
            Optional<GuiButton> optionalOpenToLan = ((List<GuiButton>)event.buttonList).stream().filter(button -> button.id == 7).findFirst();
            if(optionalOpenToLan.isPresent()) {
                GuiButton openToLan = optionalOpenToLan.get();
                openToLan.visible = BugTorchConfig.showOpenToLanButton != -1;
                openToLan.enabled = BugTorchConfig.showOpenToLanButton == 1;
            }
        }
    }

}
