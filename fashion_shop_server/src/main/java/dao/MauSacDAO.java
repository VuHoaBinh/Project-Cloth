package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.MauSac;

public interface MauSacDAO extends Remote{
	public List<MauSac> getAllMauSac() throws RemoteException;
	public boolean create(MauSac ms) throws RemoteException;
	public MauSac getMauSacById(String id) throws RemoteException;
}
