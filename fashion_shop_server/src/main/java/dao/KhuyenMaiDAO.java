package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.KhuyenMai;

public interface KhuyenMaiDAO extends Remote{
	public List<KhuyenMai> getAllKhuyenMai() throws RemoteException;
	public boolean create(KhuyenMai km) throws RemoteException;
	public boolean Delete(String maKM) throws RemoteException;
	public boolean Update(KhuyenMai km) throws RemoteException;
	
}
