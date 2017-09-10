package com.paulopieczarka.app;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import com.paulopieczarka.game.Canvas;
import com.paulopieczarka.game.IGame;
import com.paulopieczarka.game.Player;

public class Application extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	
	private IGame game;
	private Player player;
	
	public Application() 
	{
		super("Dungeons Of Time");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		
		addWindowListener(this);
	}
	
	public void show(IGame game, Player player) throws RemoteException
	{
		Canvas canvas = new Canvas();
		setContentPane(canvas);
		
		addKeyListener(canvas);
		
		canvas.init(game, player);
		setVisible(true);
		
		this.game = game;
		this.player = player;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		try 
		{
			game.disconnect(player);
		} 
		catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
