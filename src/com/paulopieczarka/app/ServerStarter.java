package com.paulopieczarka.app;

import com.paulopieczarka.server.Server;

public class ServerStarter 
{
	public static void main(String[] args)
	{
		Server server = new Server();
		server.start();
	}
}
