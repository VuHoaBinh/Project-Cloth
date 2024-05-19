package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entities.NhanVien;

public interface NhanVienDAO extends Remote{
	public List<NhanVien> getAllNhanVien() throws RemoteException;
	public List<NhanVien> getAllNhanVienQuanLy() throws RemoteException;
	public boolean create(NhanVien nv) throws RemoteException;
	public boolean Delete(String maNV) throws RemoteException;
	public boolean Update(NhanVien nv) throws RemoteException;
	public List<NhanVien> Find(String sql, ArrayList<String> token) throws RemoteException;
	public boolean UpdateTTCN(String maNV,String ten, String diaChi, String soDienThoai, LocalDate ngaySinh, boolean gioiTinh) throws RemoteException;
	public NhanVien getNhanVien(String maNV) throws RemoteException;
	public Object[] getThongTinCaNhans(String maTK) throws RemoteException;
}
