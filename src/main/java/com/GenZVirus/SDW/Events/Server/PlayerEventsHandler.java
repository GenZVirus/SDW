package com.GenZVirus.SDW.Events.Server;

import java.util.List;
import java.util.UUID;

import com.GenZVirus.SDW.SDW;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = SDW.MOD_ID, bus = Bus.FORGE)
public class PlayerEventsHandler {
	
	// Creating a player list for use outside the class
	
	public static List<PlayerEntity> players = Lists.newArrayList();

	public static List<UUID> uuids = Lists.newArrayList();
	
	public static List<Hand> hands = Lists.newArrayList();
	
	@SubscribeEvent
	public static void onPlayerDeath(PlayerEvent.Clone e) {
		removePlayer(e.getOriginal());
		addPlayer(e.getPlayer());
	}
	
	// When a player leaves the server all lists clear his data from them
	
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		removePlayer(e.getPlayer());
	}
	
	// When a player joins the server all lists add his data to them

	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {		
		addPlayer(e.getPlayer());
	}
	
	
	private static void removePlayer(PlayerEntity player) {
		hands.remove(players.indexOf(player));
		players.remove(player);
		uuids.remove(player.getUniqueID());
	}
	
	private static void addPlayer(PlayerEntity player) {
		players.add(player);
		uuids.add(player.getUniqueID());
		hands.add(Hand.MAIN_HAND);
	}

}
