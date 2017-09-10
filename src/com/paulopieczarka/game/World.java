package com.paulopieczarka.game;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class World implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private String name;
	
	private ArrayList<Player> playerList;
	
	public World(String name)
	{
		this.name = name;
		this.playerList = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Player addPlayer(String name)
	{
		Random rand = new Random(name.hashCode());
		Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		
		Player newPlayer = new Player(name, color);
		newPlayer.setLocation(rand.nextInt(WIDTH)/32, rand.nextInt(HEIGHT)/32);
		
		playerList.add(newPlayer);
		return newPlayer;
	}
	
	public Player getPlayer(int index) {
		return this.playerList.get(index);
	}
	
	public Player getPlayer(Player player)
	{
		for(int i=0; i < playerList.size(); i++)
		{
			if(playerList.get(i).getName().equalsIgnoreCase(player.getName())) {
				return playerList.get(i);
			}
		}
		
		return null;
	}
	
	public ArrayList<Player> getList() {
		return this.playerList;
	}
	
	public boolean removePlayer(Player player)
	{
		for(int i=0; i < playerList.size(); i++)
		{
			if(playerList.get(i).getName().equalsIgnoreCase(player.getName())) {
				playerList.remove(i);
				return true;
			}
		}
		
		return false;
	}
}
