package makeiiiuk.core.client;

import java.io.DataInputStream;
import java.io.File;
import java.net.Socket;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import makeiiiuk.core.client.packets.ClientPackets;
import makeiiiuk.core.packets.PacketHandler;
import makeiiiuk.core.proxy.IClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy
  implements IClientProxy
{
  Minecraft mc;
  static Socket csocket;
  static DataInputStream in;
  
  public void preInit(FMLPreInitializationEvent event){
	  PacketHandler.packetsRegistry(PacketHandler.channelClient, ClientPackets.values());
  }
  
  public void init(FMLInitializationEvent event){
	  this.mc = Minecraft.getMinecraft();
	  TickRegistry.registerScheduledTickHandler(new ClientTicker(this), Side.CLIENT);
  }

  public void postInit(FMLPostInitializationEvent event){}
 
  public void registerRenderers() {}

  public File getMinecraftDir() {
	  return new File(".");
  }
  
  public boolean isRemote() {
	  return true;
  }
}
