package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entities.KhachHang;

public interface KhachHangDAO extends Remote{
	public List<KhachHang> getAllKhachHang() throws RemoteException;
	public boolean create(KhachHang kh) throws RemoteException;
	public boolean update(KhachHang kh) throws RemoteException;
	public boolean delete(String maKhachHang) throws RemoteException;
	public List<KhachHang> Find(String sql, ArrayList<String> token) throws RemoteException;
	public KhachHang getKhachHang(String maKhachHang) throws RemoteException;
	public List<Object[]> loadDuLieuKhachHang() throws RemoteException;
}
