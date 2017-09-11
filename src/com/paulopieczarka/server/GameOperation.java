package com.paulopieczarka.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.paulopieczarka.game.EnumAction;
import com.paulopieczarka.game.IGame;
import com.paulopieczarka.game.Player;
import com.paulopieczarka.game.World;

public class GameOperation extends UnicastRemoteObject implements IGame
{
	private static final long serialVersionUID = 1L;
	
	private World remoteWorld;
	
	protected GameOperation() throws RemoteException 
	{
		super();
		
		remoteWorld = new World("Akiles");
	}

	@Override
	public Player connect(String name) throws RemoteException 
	{
		System.out.println("[SERVER] Player "+name+" connected.");
		return remoteWorld.addPlayer(name);
	}

	@Override
	public boolean playerAction(int playerKey, EnumAction action) throws RemoteException 
	{
		Player remotePlayer = remoteWorld.getPlayer(playerKey);
		
		if(action.equals(EnumAction.MOVE_UP)) {
			remotePlayer.addPosY(-1);
			remotePlayer.setRotation(0);
		}
		else if(action.equals(EnumAction.MOVE_DOWN)) {
			remotePlayer.addPosY(1);
			remotePlayer.setRotation(180);
		}
		else if(action.equals(EnumAction.MOVE_LEFT)) {
			remotePlayer.addPosX(-1);
			remotePlayer.setRotation(-90);
		}
		else if(action.equals(EnumAction.MOVE_RIGHT)) {
			remotePlayer.addPosX(1);
			remotePlayer.setRotation(90);
		}
		
		return false;
	}

	@Override
	public World getWorld() throws RemoteException 
	{
		return remoteWorld;
	}

	@Override
	public void disconnect(int playerKey) throws RemoteException 
	{
		Player who = remoteWorld.getPlayer(playerKey);
		remoteWorld.removePlayer(playerKey);
		System.out.println("[SERVER] Player "+who.getName()+" is dead.");
	}

	@Override
	public void chat(String text) throws RemoteException {
		remoteWorld.addChat(text);
	}

	@Override
	public PacketWorld updateWorld(int playerKey) throws RemoteException 
	{
		return remoteWorld.mountPacket(playerKey);
	}

}
