package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.ChiTietKhuyenMai;


public interface ChiTietKhuyenMaiDAO extends Remote{
	public List<ChiTietKhuyenMai> getAllChiTietKhuyenMai(String maKhuyenMai) throws RemoteException;
	public List<ChiTietKhuyenMai> getAllChiTietKhuyenMai() throws RemoteException;
	public boolean create(ChiTietKhuyenMai ctkm) throws RemoteException;
	public boolean Update(ChiTietKhuyenMai ctkm) throws RemoteException;
	public boolean Delete(String maKM, String maSP) throws RemoteException;
	public boolean Delete(String maKM) throws RemoteException;
}
