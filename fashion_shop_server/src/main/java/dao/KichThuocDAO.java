package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.KichThuoc;

public interface KichThuocDAO extends Remote{
	public List<KichThuoc> getAllKichThuoc() throws RemoteException;
	public boolean create(KichThuoc kt)	throws RemoteException;
	public KichThuoc getKichThuocById(String id) throws RemoteException;
}
