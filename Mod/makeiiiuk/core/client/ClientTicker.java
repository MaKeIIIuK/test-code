package makeiiiuk.core.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.EnumSet;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTicker implements IScheduledTickHandler {

	private ClientProxy proxy;
	
    ClientTicker(ClientProxy proxy){
    	this.proxy = proxy;
    }
    
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		if(proxy.mc.theWorld != null && (proxy.csocket == null || proxy.csocket != null && proxy.csocket.isClosed())){
		    try { 
		    	proxy.csocket = new Socket("localhost", 4004);
		    	proxy.in = new DataInputStream(proxy.csocket.getInputStream());
			    System.out.println("Connected to server.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(proxy.mc.theWorld == null && proxy.csocket != null && !proxy.csocket.isClosed()){
			try {
				proxy.in.close();
				proxy.csocket.close();
				System.out.println("Connection closed.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(proxy.mc.theWorld != null && proxy.csocket != null && proxy.csocket.isConnected() && proxy.in != null){
			try {
				if(proxy.in.available() > 0){
				String serverWord = proxy.in.readUTF();
				proxy.mc.thePlayer.addChatMessage(serverWord);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	public String getLabel() {
		return "MakeiiiukClientTicker";
	}

	public int nextTickSpacing() {
		return 20;
	}

}
