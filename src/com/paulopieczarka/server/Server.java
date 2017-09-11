package com.paulopieczarka.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteObject;

import com.paulopieczarka.app.Main;

public class Server 
{
	public Server() 
	{
		System.setProperty("java.rmi.server.hostname", Main.SERVER_IP);
		
		try 
		{
			LocateRegistry.createRegistry(Main.SERVER_PORT);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		try
		{
			Naming.rebind(Main.getServerAddress(), new GameOperation());
			System.out.println("[SERVER] Server is ready.");
		}
		catch (Exception e) {
			System.err.println("[SERVER.ER] "+e.getMessage());
			e.printStackTrace();
		}
	}
}
