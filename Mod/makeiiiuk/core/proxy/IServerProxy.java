package makeiiiuk.core.proxy;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface IServerProxy extends IProxy
{
   void serverStart(FMLServerStartingEvent par1);
}
