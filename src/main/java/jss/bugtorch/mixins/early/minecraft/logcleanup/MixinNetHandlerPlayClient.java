package jss.bugtorch.mixins.early.minecraft.logcleanup;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;

@Mixin(value = NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient {

	@Shadow
	private Minecraft gameController;

	/**
	 * @author jss2a98aj
	 * @reason Stops a chat message from being sent to players when they receive an invalid sign packet.
	 */
	@Overwrite()
	public void handleUpdateSign(S33PacketUpdateSign updatePacket) {
		if (this.gameController.theWorld.blockExists(updatePacket.func_149346_c(), updatePacket.func_149345_d(), updatePacket.func_149344_e())) {
			TileEntity tileentity = this.gameController.theWorld.getTileEntity(updatePacket.func_149346_c(), updatePacket.func_149345_d(), updatePacket.func_149344_e());

			if (tileentity instanceof TileEntitySign) {
				TileEntitySign tileentitysign = (TileEntitySign) tileentity;

				if (tileentitysign.func_145914_a()) {
					for (int i = 0; i < 4; ++i) {
						tileentitysign.signText[i] = updatePacket.func_149347_f()[i];
					}
					tileentitysign.markDirty();
				}
			}
		}
	}

}
