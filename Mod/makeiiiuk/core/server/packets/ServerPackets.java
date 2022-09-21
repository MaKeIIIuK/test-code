package makeiiiuk.core.server.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import makeiiiuk.core.packets.IPacketData;
import makeiiiuk.core.packets.PacketHandler;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public enum ServerPackets implements IPacketData {
	NULL{//0
		@Override
		public void consume(DataInputStream data) throws Exception {}

		@Override
		public void execute(INetworkManager manager, EntityPlayer player) throws Exception {}
	};

 private static void sendPacketToTrackingPlayers(TileEntity par1, int par2, Object... par3)
 {
  Packet250CustomPayload par4 = new Packet250CustomPayload();
  par4.channel = PacketHandler.channelClient;
  par4.data = PacketHandler.generateDataPacket(par2, par3);
  par4.length = par4.data.length;
     Chunk chunk = par1.getWorldObj().getChunkFromBlockCoords(par1.xCoord, par1.zCoord);
     int x = MathHelper.floor_double(par1.xCoord / 16.0D);
     int z = MathHelper.floor_double(par1.zCoord / 16.0D);

     PlayerInstance pi = ((WorldServer)par1.getWorldObj()).getPlayerManager().getOrCreateChunkWatcher(x, z, false);
     if (pi != null)
     {
       pi.sendToAllPlayersWatchingChunk(par4);
     }
  }

 public static void sendPacketToPlayer(EntityPlayer par1, int par2, Object... par3)
 {
  Packet250CustomPayload par4 = new Packet250CustomPayload();
  par4.channel = PacketHandler.channelClient;
  par4.data = PacketHandler.generateDataPacket(par2, par3);
  par4.length = par4.data.length;
  ((EntityPlayerMP)par1).playerNetServerHandler.sendPacketToPlayer(par4);
 }

 public static void sendPacketToAllPlayer(int par2, Object... par3)
 {
  Packet250CustomPayload par4 = new Packet250CustomPayload();
  par4.channel = PacketHandler.channelClient;
  par4.data = PacketHandler.generateDataPacket(par2, par3);
  par4.length = par4.data.length;
  PacketDispatcher.sendPacketToAllPlayers(par4);
 }
}
