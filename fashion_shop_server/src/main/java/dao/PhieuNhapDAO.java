package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.PhieuNhap;

public interface PhieuNhapDAO extends Remote{
	public List<PhieuNhap> getAllPhieuNhap() throws RemoteException;
	public boolean create(PhieuNhap pn) throws RemoteException;
	public PhieuNhap getPhieuNhapById(String id) throws RemoteException;
}
