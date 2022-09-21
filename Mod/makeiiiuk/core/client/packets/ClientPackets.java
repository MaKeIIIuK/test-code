package makeiiiuk.core.client.packets;

import java.io.DataInputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import makeiiiuk.core.packets.IPacketData;
import makeiiiuk.core.packets.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public enum ClientPackets implements IPacketData {
	NULL{//0
		@Override
		public void consume(DataInputStream data) throws Exception {}

		@Override
		public void execute(INetworkManager manager, EntityPlayer player) throws Exception {}
	};	
	
	public static void sendPacketToServer(int packet,Object... object) {
		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = PacketHandler.channelServer;
		packet250.data = PacketHandler.generateDataPacket(packet, object);
		packet250.length = packet250.data.length;
		PacketDispatcher.sendPacketToServer(packet250);
	}

}
