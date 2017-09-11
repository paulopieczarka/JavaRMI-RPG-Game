package com.paulopieczarka.game;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class World implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private String name;
	
	private HashMap<Integer, Player> playerList;
	private ArrayList<String> chatLines;
	
	public World(String name)
	{
		this.name = name;
		this.playerList = new HashMap<>();
		this.chatLines = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Player addPlayer(String name)
	{
		Random rand = new Random(name.hashCode());
		Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		Color hair = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
		
		Player newPlayer = new Player(name, color);
		newPlayer.setLocation(rand.nextInt(WIDTH)/32, rand.nextInt(HEIGHT)/32);
		newPlayer.setHairColor(hair);
		
		addChat("Player "+name+" is online.");
		playerList.put(newPlayer.hashCode(), newPlayer);
		return newPlayer;
	}
	
	public Player getPlayer(int playerKey)
	{
		return playerList.get(playerKey);
	}
	
	public ArrayList<Player> getList() 
	{
		ArrayList<Player> players = new ArrayList<>();
		players.addAll(playerList.values());
		return players;
	}
	
	public boolean removePlayer(int playerKey)
	{
		Player who = getPlayer(playerKey);
		addChat("Player "+who.getName()+" left.");
		return playerList.remove(playerKey) != null;
	}
	
	public void addChat(String text)
	{
		this.chatLines.add(text);
		if(chatLines.size() > 6) {
			chatLines.remove(0);
		}
	}
	
	public ArrayList<String> getChat() {
		return this.chatLines;
	}
}
