package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.ChiTietPhieuDatHang;

public interface ChiTietDatHangDAO extends Remote{
	public List<ChiTietPhieuDatHang> getAllChiTietPhieuDatHang(String maPDH) throws RemoteException;
	public boolean create(ChiTietPhieuDatHang ctpdh) throws RemoteException;
	
}
