package com.GenZVirus.SDW;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.GenZVirus.SDW.Network.PacketHandlerCommon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("simpledualwielding")
public class SDW {

	// Directly reference a log4j logger.
	private static final Logger LOGGER = LogManager.getLogger();

	/*
	 * Mod id reference
	 */
	public static final String MOD_ID = "simpledualwielding";

	/*
	 * Instance of Broken Events
	 */
	public static SDW instance;

	public SDW() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);

		instance = this;
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		
		  // Initializing the PacketHandler
    	
    	PacketHandlerCommon.init();
    	LOGGER.info("Packets loaded successfully");
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}
}
