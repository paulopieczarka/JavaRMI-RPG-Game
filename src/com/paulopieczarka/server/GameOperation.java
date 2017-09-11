package com.paulopieczarka.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.paulopieczarka.game.EPlayerAction;
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
	public boolean playerAction(int playerKey, EPlayerAction action) throws RemoteException 
	{
		Player remotePlayer = remoteWorld.getPlayer(playerKey);
		
		if(action.equals(EPlayerAction.MOVE_UP)) {
			remotePlayer.addPosY(-1);
		}
		else if(action.equals(EPlayerAction.MOVE_DOWN)) {
			remotePlayer.addPosY(1);
		}
		else if(action.equals(EPlayerAction.MOVE_LEFT)) {
			remotePlayer.addPosX(-1);
		}
		else if(action.equals(EPlayerAction.MOVE_RIGHT)) {
			remotePlayer.addPosX(1);
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

}
