package com.paulopieczarka.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.paulopieczarka.game.Player;

public class PacketWorld implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public HashMap<Integer, Player> playerList;
	public ArrayList<String> chatLines;
	
	public int getSize() 
	{
		try 
		{
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream;
		
			objectOutputStream = new ObjectOutputStream(byteOutputStream);
			objectOutputStream.writeObject((Object)this);
        	objectOutputStream.flush();
        	objectOutputStream.close();
        
        	return byteOutputStream.toByteArray().length;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

        return 0;
	}
}
