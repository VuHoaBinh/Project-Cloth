package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.LoaiNhanVien;

public interface LoaiNhanVienDAO extends Remote{
	public List<LoaiNhanVien> getAllLoaiNhanVien()throws RemoteException;
	public LoaiNhanVien getLoaiNhanVienById(String id)throws RemoteException;
}
