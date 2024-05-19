package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entities.SanPham;

public interface SanPhamDAO extends Remote{
	public List<SanPham> getAllSanPham() throws RemoteException;
	public List<SanPham> getAllSanPhamTheoNhaCungCap(String nhaCungCap) throws RemoteException;
	public List<SanPham> Find(String sql, ArrayList<String> token) throws RemoteException;
	public boolean UpdateSoLuong(String masp, int soluong) throws RemoteException;
	public boolean UpdateNhaCungCap(String masp, String nhaCungCap) throws RemoteException;
	public boolean create(SanPham sp) throws RemoteException;
	public boolean Delete(String maSP) throws RemoteException;
	public boolean Update(SanPham sp) throws RemoteException;
	public SanPham getSanPham(String maSP) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoNgay(int ngay, int thang, int nam, String loaiSP) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoThang(int thang, int nam, String loaiSP) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoNam(int nam, String loaiSP) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoDTLSPTheoNgay(int ngay, int thang, int nam) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoDTLSPTheoThang(int thang, int nam) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoDTLSPTheoNam(int nam) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoSLLSPTheoNgay(int ngay, int thang, int nam) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoSLLSPTheoThang(int thang, int nam) throws RemoteException;
	public List<Object[]> thongKeSanPhamTheoSLLSPTheoNam(int nam) throws RemoteException;
}
