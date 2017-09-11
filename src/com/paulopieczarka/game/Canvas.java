package com.paulopieczarka.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.paulopieczarka.server.PacketWorld;

public class Canvas extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	
	public volatile boolean running = true;
	
	protected IGame game;
	protected World  world;
	protected Player player;
	
	// System performance.
	private long lastupdate;
	private int downBytes;
	private int lastDownBytes;
	private int responseTime;
	
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
		ArrayList<Player> remotePlayers = world.getList();
		for(int i=0; i < remotePlayers.size(); i++)
		{
			Player player = remotePlayers.get(i);
			
			float x = player.getX()*32 + 16;
			float y = player.getY()*32 + 16;
			g.translate(x, y);
			g.rotate(Math.toRadians(player.getRotation()));
			
			g.setColor(player.getColor());
			g.fillRect(-16, -16, 32, 32);
			
			g.setColor(player.getHairColor());
			g.fillRect(-16, -16, 32, 8);
			
			g.rotate(-Math.toRadians(player.getRotation()));
			
			g.setColor(Color.WHITE);
			g.drawString(player.getName(), -g.getFontMetrics().stringWidth(player.getName())/2, -28);
			
			g.translate(-x, -y);
		}
		
		for(int i=0; i < world.getChat().size(); i++)
		{
			g.setColor(Color.WHITE);
			String line = world.getChat().get(i);
			
			if(line.startsWith("Player ")) {
				g.setColor(Color.GRAY);
			}
			
			g.drawString(line, 10, getHeight()-(18*(world.getChat().size()-(i+1))+10));
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Down: "+lastDownBytes+"bytes/s ("+responseTime+"ms)", 10, 20);
		g.drawString("Players: "+world.getList().size(), 10, 40);
	}
	
	public void update() throws RemoteException
	{
		long st = System.currentTimeMillis();
		
		PacketWorld worldUpdates = game.updateWorld(player.hashCode());
		world.unmountPacket(worldUpdates);
		downBytes += worldUpdates.getSize();
		if(lastupdate+1000 <= System.currentTimeMillis()) 
		{
			lastDownBytes = downBytes/60;
			downBytes = 0;
			lastupdate = System.currentTimeMillis();
			responseTime = (int)(System.currentTimeMillis()-st);
		}
	}
	
	@Override
	public void paint(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Consolas", Font.BOLD, 14));
		
		g2d.setColor(Color.decode("#7FA34B"));
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
				game.playerAction(player.hashCode(), EnumAction.MOVE_LEFT);
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.playerAction(player.hashCode(), EnumAction.MOVE_RIGHT);
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				game.playerAction(player.hashCode(), EnumAction.MOVE_UP);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.playerAction(player.hashCode(), EnumAction.MOVE_DOWN);
			}
			else if(e.getKeyCode() == KeyEvent.VK_T) {
				game.chat(player.getName()+": "+JOptionPane.showInputDialog("Type a message:"));
			}
		} 
		catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
}
