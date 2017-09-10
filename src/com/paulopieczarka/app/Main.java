package com.paulopieczarka.app;

import com.paulopieczarka.client.Client;

public class Main 
{
	public static final String SERVER_NAME = "ServerOfLove";
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 2020;
	
	public static void main(String[] args)
	{
		Client client = new Client();
		client.start();
	}
	
	/**
	 * @return address:port/name.
	 **/
	public static String getServerAddress()
	{
		return "//"+SERVER_IP+":"+SERVER_PORT+"/"+SERVER_NAME;
	}
}
