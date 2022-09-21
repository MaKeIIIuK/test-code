package makeiiiuk.core.packets;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler {
	private static final HashMap packetsList = new HashMap();
	public static final String channelClient = "Core_C";
	public static final String channelServer = "Core_S";

	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		try{
		DataInputStream data = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(packet.data))));

		IPacketData packetData = ((IPacketData[])this.packetsList.get(packet.channel))[data.readInt()];

		packetData.consume(data);
		packetData.execute(manager, (EntityPlayer)player);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void packetsRegistry(String channel, IPacketData[] packets){
		packetsList.put(channel, packets);
	}
	
	public static byte[] generateDataPacket(int ordinal, Object[] object) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		try{
		 DataOutputStream data = new DataOutputStream(new GZIPOutputStream(byteArray));
		 data.writeInt(ordinal);
	     for (Object par5 : object)
	     {
	       if(par5 instanceof Integer)
	       {
	        data.writeInt(((Integer)par5).intValue());
	       }
	       else if(par5 instanceof Float)
	       {
	        data.writeFloat(((Float)par5).floatValue());
	       }
	       else if(par5 instanceof Double)
	       {
	        data.writeDouble(((Double)par5).doubleValue());
	       }
	       else if(par5 instanceof String)
	       {
	        data.writeUTF((String)par5);
	       }
	       else if(par5 instanceof Boolean)
	       {
	        data.writeBoolean(((Boolean)par5).booleanValue());
	       }
	       else if(par5 instanceof Long)
	       {
	        data.writeLong(((Long)par5).longValue());
	       }
	     }
	     data.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		return byteArray.toByteArray();
	}
}
