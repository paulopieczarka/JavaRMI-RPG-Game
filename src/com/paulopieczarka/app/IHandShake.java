package com.paulopieczarka.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHandShake extends Remote
{
	public String handShake(String who) throws RemoteException;
}
