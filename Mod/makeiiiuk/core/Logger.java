package makeiiiuk.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class Logger
{
  private static final boolean debug = true;
  
  public static void console(Object message)
  {
    String toConsole = "[MAKEIIIUK]";
    
    Side side = FMLCommonHandler.instance().getEffectiveSide();
    if (side == Side.CLIENT)
    {
      toConsole = toConsole + " CLIENT SIDE: ";
    }
    else
    {
      toConsole = toConsole + " SERVER SIDE: ";
    }
    
    System.out.println(toConsole + message);
  }
}
