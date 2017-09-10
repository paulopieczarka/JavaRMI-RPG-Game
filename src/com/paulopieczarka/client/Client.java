package com.paulopieczarka.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

import javax.swing.JOptionPane;

import com.paulopieczarka.app.Application;
import com.paulopieczarka.app.Main;
import com.paulopieczarka.game.IGame;
import com.paulopieczarka.game.Player;

public class Client 
{
	public void start()
	{
		Random rand = new Random();
		String username = JOptionPane.showInputDialog("Player name", "Player"+rand.nextInt(99999));
		
		try 
		{
			IGame game = (IGame)Naming.lookup(Main.getServerAddress());
			Player player = game.connect(username);
			System.out.println(player.toString());
			
			Application app = new Application();
			app.show(game, player);
		}
		catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
