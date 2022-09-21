package makeiiiuk.core;

import static makeiiiuk.core.Core.MODID;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import makeiiiuk.core.client.ClientProxy;
import makeiiiuk.core.packets.PacketHandler;
import makeiiiuk.core.proxy.IProxy;
import makeiiiuk.core.proxy.IServerProxy;
import makeiiiuk.core.proxy.ProxyInstance;
import makeiiiuk.core.server.ServerProxy;

@Mod(modid=MODID, name=MODID, version="Beta")
@NetworkMod(clientSideRequired=true, serverSideRequired=true, channels={PacketHandler.channelClient,PacketHandler.channelServer}, packetHandler=PacketHandler.class)
public class Core
{
  static final String MODID = "MaKeIIIuK_Core";
  
  @Mod.Instance(MODID)
  public static Core INSTANCE;
  private static final IProxy proxy = new ProxyInstance(ClientProxy.class.getName(), ServerProxy.class.getName()).getProxy();
  private static IServerProxy proxySinglePlayer;
  public static final boolean SINGLEPLAYER = true;
  public static final boolean LOCALHOST = false;
  @EventHandler
  public void preInit(FMLPreInitializationEvent par1)
  {
	   if(SINGLEPLAYER)
	   {
		    try
		    {
			    proxySinglePlayer = ServerProxy.class.newInstance();
				proxySinglePlayer.preInit(par1);
			}
		    catch (InstantiationException e)
		    {
				e.printStackTrace();
			}
		    catch (IllegalAccessException e)
		    {
				e.printStackTrace();
			}
	   }

	   proxy.preInit(par1);
  }

  @EventHandler
  public void init(FMLInitializationEvent event)
  {
	  long time = System.currentTimeMillis();

	  if(SINGLEPLAYER)
	  {
	      proxySinglePlayer.init(event);
	  }

	  proxy.init(event);
  }

  public void postInit(FMLPostInitializationEvent event) {
	  if(SINGLEPLAYER)
	  {
	     proxySinglePlayer.postInit(event);
	  }
	  
	  proxy.postInit(event);
	  }

  @EventHandler
  public void serverStart(FMLServerStartingEvent event) throws Exception {
	  
	  if(SINGLEPLAYER)
	  {
	     proxySinglePlayer.serverStart(event);
	  }
	  
	  if(!SINGLEPLAYER)
	  {
		 if(FMLCommonHandler.instance().getSide().isClient())
		 {
		    throw new Exception("Single mod is not supported!");
		 }
	  }

	  if(proxy instanceof IServerProxy)
	  {
         ((IServerProxy)proxy).serverStart(event);
	  }
//	  throw new Exception("Play on original servers!");
  }
}
