package com.paulopieczarka.app;

import javax.swing.JOptionPane;

import com.paulopieczarka.client.Client;

public class Main 
{
	public static final String SERVER_NAME = "ServerOfLove";
	public static final int SERVER_PORT = 2020;
	public static String SERVER_IP = "localhost";
	
	public static void main(String[] args)
	{
		SERVER_IP = JOptionPane.showInputDialog("Server address", SERVER_IP);
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
