package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.TaiKhoan;

public interface TaiKhoanDAO extends Remote{
	public boolean isTaiKhoan(TaiKhoan taiKhoan) throws RemoteException;
	public List<TaiKhoan> getAllTaiKhoan() throws RemoteException;
	public boolean create(TaiKhoan tk) throws RemoteException;
	public boolean update(TaiKhoan tk) throws RemoteException;
	public boolean delete(String maTK) throws RemoteException;
	public boolean updateMatKhau(String maTK, String matKhauMoi) throws RemoteException;
	public boolean updateEmail(String maTK, String email) throws RemoteException;
	
}
