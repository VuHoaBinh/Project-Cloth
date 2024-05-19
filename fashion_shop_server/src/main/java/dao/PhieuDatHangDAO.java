package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entities.PhieuDatHang;

public interface PhieuDatHangDAO extends Remote{
	public List<PhieuDatHang> getAllPhieuDatHang() throws RemoteException;
	public boolean create(PhieuDatHang pdh) throws RemoteException;
	public boolean Delete(String maPDH) throws RemoteException;
	public PhieuDatHang getPhieuDatHang(String maPDH) throws RemoteException;
	public List<Object[]> findPhieuDatHang(String sql, ArrayList<String> token) throws RemoteException;
	public List<Object[]> loadDuLieuPhieuDatHang() throws RemoteException;
}
