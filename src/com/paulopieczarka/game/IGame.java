package com.paulopieczarka.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGame extends Remote
{
	/* Player defines. */
	
	public Player connect(String name) throws RemoteException;
	
	public void disconnect(int playerKey) throws RemoteException;
	
	public boolean playerAction(int playerKey, EPlayerAction action) throws RemoteException;
	
	/* Game defines. */
	
	public World getWorld() throws RemoteException;
	
	public void chat(String text) throws RemoteException;
}
