package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.CaLam;


public interface CaLamDAO extends Remote{
	public List<entities.CaLam> getAllCaLam() throws RemoteException;
	public CaLam getCaLam(String id) throws RemoteException;
}
