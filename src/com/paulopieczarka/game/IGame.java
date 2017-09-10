package com.paulopieczarka.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGame extends Remote
{
	/* Player defines. */
	
	public Player connect(String name) throws RemoteException;
	
	public void disconnect(Player player) throws RemoteException;
	
	public boolean playerAction(Player player, EPlayerAction action) throws RemoteException;
	
	/* Game defines. */
	
	public World getWorld() throws RemoteException;
}
