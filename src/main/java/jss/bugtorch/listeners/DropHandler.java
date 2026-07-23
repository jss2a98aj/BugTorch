package jss.bugtorch.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import jss.bugtorch.config.BugTorchConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import jss.bugtorch.BugTorch;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class DropHandler {
    public static final DropHandler INSTANCE = new DropHandler();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void tossItem(ItemTossEvent event) {
        EntityPlayer player = event.player;

        if (!player.worldObj.isRemote && BugTorchConfig.replaceItemTossDropAnimation) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            BugTorch.networkWrapper.sendTo(new PacketSwingArm(player), playerMP);
        }
    }
}
