package com.paulopieczarka.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	
	public volatile boolean running = true;
	
	protected IGame game;
	protected World  world;
	protected Player player;
	
	public void init(IGame game, Player player) throws RemoteException 
	{
		this.game = game;
		this.world = game.getWorld();
		this.player = player;
		
		Thread mainLoop = new Thread(this);
		mainLoop.start();
	}
	
	public void render(Graphics2D g)
	{
		for(int i=0; i < world.getList().size(); i++)
		{
			Player player = world.getPlayer(i);
			
			float x = player.getX()*32;
			float y = player.getY()*32;
			g.translate(x, y);
			
			g.setColor(player.getColor());
			g.fillRect(0, 0, 32, 32);
			
			g.setColor(Color.WHITE);
			g.drawString(player.getName(), 16-g.getFontMetrics().stringWidth(player.getName())/2, -5);
			
			g.translate(-x, -y);
		}
	}
	
	public void update() throws RemoteException
	{
		world = game.getWorld();
	}
	
	@Override
	public void paint(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.BLACK);
		g2d.clearRect(0, 0, getWidth(), getHeight());
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		render(g2d);
	}
	
	@Override
	public void run() 
	{
		while(running)
		{
			try 
			{
				update();
			} 
			catch (RemoteException e1) {
				e1.printStackTrace();
			}
			repaint();
			
			try { Thread.sleep(1000/60); } catch(Exception e) {}
		}
	}
	
	public void shutdown() {
		this.running = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		try 
		{
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.playerAction(player, EPlayerAction.MOVE_LEFT);
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.playerAction(player, EPlayerAction.MOVE_RIGHT);
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				game.playerAction(player, EPlayerAction.MOVE_UP);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.playerAction(player, EPlayerAction.MOVE_DOWN);
			}
		} 
		catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
}
