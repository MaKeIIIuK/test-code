package makeiiiuk.core.proxy;

import java.io.File;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxy
{
	void preInit(FMLPreInitializationEvent par1);

	void init(FMLInitializationEvent par1);

	void postInit(FMLPostInitializationEvent par1);
}
