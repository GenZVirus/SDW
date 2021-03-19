package com.GenZVirus.SDW.Network;

import com.GenZVirus.SDW.SDW;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandlerCommon {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(SDW.MOD_ID, "spell"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	public static void init() {
		int id = 0;
		
		INSTANCE.messageBuilder(SendPlayerHandPacket.class, id++)
		.encoder(SendPlayerHandPacket::encode)
		.decoder(SendPlayerHandPacket::decode)
		.consumer(SendPlayerHandPacket::handle)
		.add();	

	}
}
