package com.paulopieczarka.server;

import java.util.HashMap;

import com.paulopieczarka.game.World;

public class PacketManager 
{
	private static HashMap<Integer, World> playerWorlds = new HashMap<>();
	
	public static void add(int playerKey, World world)
	{
		playerWorlds.put(playerKey, world);
	}
	
	public static World getLast(int playerKey)
	{
		return playerWorlds.get(playerKey);
	}
}
