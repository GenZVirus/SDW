package com.GenZVirus.SDW.Network;

import java.util.UUID;
import java.util.function.Supplier;

import com.GenZVirus.SDW.Events.Server.PlayerEventsHandler;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendPlayerHandPacket {

	public UUID uuid;
	public int hand;
	
	public SendPlayerHandPacket(UUID uuid, int hand) {
		this.uuid = uuid;
		this.hand = hand;
	}
	
	public static void encode(SendPlayerHandPacket pkt, PacketBuffer buf) {
		buf.writeUniqueId(pkt.uuid);
		buf.writeInt(pkt.hand);
	}
	
	public static SendPlayerHandPacket decode(PacketBuffer buf) {
		return new SendPlayerHandPacket(buf.readUniqueId(), buf.readInt());
	}
	
	public static void handle(SendPlayerHandPacket pkt, Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() ->{
			if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
				if(pkt.hand == 0) {
					PlayerEventsHandler.hands.set(PlayerEventsHandler.uuids.indexOf(pkt.uuid), Hand.MAIN_HAND);
				} else {
					PlayerEventsHandler.hands.set(PlayerEventsHandler.uuids.indexOf(pkt.uuid), Hand.OFF_HAND);
				}
			}
		});
		
		ctx.get().setPacketHandled(true);
	}
	
}
