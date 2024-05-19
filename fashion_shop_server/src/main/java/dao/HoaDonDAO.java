package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entities.HoaDon;

public interface HoaDonDAO extends Remote{
	public List<HoaDon> getAllHoaDon() throws RemoteException;
	public boolean create(HoaDon hd) throws RemoteException;
	public HoaDon getHoaDonById(String id) throws RemoteException;
	public List<Object[]> findHoaDon(String sql, ArrayList<String> token) throws RemoteException;
	public List<Object[]> loadDuLieuHoaDon() throws RemoteException;
	public List<Object[]> thongKeDoanhThuTheoTuan(int tuan, int thang, int nam, String tenNV, String tenKH) throws RemoteException;
	public List<Object[]> thongKeDoanhThuTheoThang(int thang, int nam, String tenNV, String tenKH) throws RemoteException;
	public List<Object[]> thongKeDoanhThuTheoNam(int nam, String tenNV, String tenKH) throws RemoteException;
	public List<Object[]> thongKeDoanhThuTheoNgay(int ngay, int thang, int nam, String tenNV, String tenKH) throws RemoteException;
	public List<Object[]> thongKeDoanhThuTheoNamChart(int nam, String tenNV, String tenKH) throws RemoteException;

}
