package com.paulopieczarka.game;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int posX;
	private int posY;
	
	private String name;
	private Color color;
	
	public Player(String name, Color color)
	{
		this.name = name;
		this.color = color;
		this.posX = 0;
		this.posY = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	public void setLocation(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	public void addPosX(int amount) {
		this.posX += amount;
	}
	
	public void addPosY(int amount) {
		this.posY += amount;
	}
	
	@Override
	public String toString() {
		return "Player "+name+" at "+posX+", "+posY+".";
	}
}
