package makeiiiuk.core.packets;

import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

public interface IPacketData {
	public void consume(DataInputStream data) throws Exception;
	public void execute(INetworkManager manager, EntityPlayer player) throws Exception;
}
