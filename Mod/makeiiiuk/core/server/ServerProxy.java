package makeiiiuk.core.server;

import java.io.File;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import makeiiiuk.core.packets.PacketHandler;
import makeiiiuk.core.proxy.IServerProxy;
import makeiiiuk.core.server.packets.ServerPackets;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy implements IServerProxy
{	
	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.packetsRegistry(PacketHandler.channelServer, ServerPackets.values());
	}

	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ServerEvent());
	}

	public void postInit(FMLPostInitializationEvent event) {}

	public void serverStart(FMLServerStartingEvent event) {
		/*try {											//XXX мб будем грузить приложение если оно закрыто при старте сервера
			Runtime.getRuntime().exec("ServerCore.java");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public File getMinecraftDir() {
		return new File(".");
	}
	
	public boolean isRemote() {
		return false;
	}
}
