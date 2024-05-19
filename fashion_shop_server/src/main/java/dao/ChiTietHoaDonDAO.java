package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.ChiTietHoaDon;


public interface ChiTietHoaDonDAO extends Remote{
	public List<ChiTietHoaDon> getAllChiTietHoaDon(String maHoaDon) throws RemoteException;
	public List<ChiTietHoaDon> getAllChiTietHoaDon() throws RemoteException;
	public boolean create(ChiTietHoaDon cthd) throws RemoteException;
	
}
