package com.paulopieczarka.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.paulopieczarka.app.IHandShake;

public class ServerOperation extends UnicastRemoteObject implements IHandShake
{
	private static final long serialVersionUID = 1L;
	
	protected ServerOperation() throws RemoteException {
		super();
	}

	@Override
	public String handShake(String who) throws RemoteException 
	{
		System.out.println("[SERVER] "+who+" is trying to contact.");
		return "Server says hello to "+who+".";
	}
}
