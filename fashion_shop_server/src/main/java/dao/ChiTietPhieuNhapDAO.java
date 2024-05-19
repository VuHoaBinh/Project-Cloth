package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.ChiTietPhieuNhap;

public interface ChiTietPhieuNhapDAO extends Remote{
	public List<ChiTietPhieuNhap> getAllChiTietHoaDon(String maPhieuLap) throws RemoteException;
	public boolean create(ChiTietPhieuNhap ctpn) throws RemoteException;
}
