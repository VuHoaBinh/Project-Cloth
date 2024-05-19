package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.LoaiSanPham;

public interface LoaiSanPhamDAO extends Remote{
	public List<LoaiSanPham> getAllLoaiSanPham() throws RemoteException;
	public boolean create(LoaiSanPham lsp) throws RemoteException;
	public LoaiSanPham getLoaiSanPhamById(String id) throws RemoteException;
}
