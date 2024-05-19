package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.LoaiNhanVienDAO;
import entities.LoaiNhanVien;
import jakarta.persistence.EntityManager;

public class LoaiNhanVienService extends UnicastRemoteObject implements LoaiNhanVienDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9188707858414983600L;
	private EntityManager entityManager;

	public LoaiNhanVienService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<LoaiNhanVien> getAllLoaiNhanVien() throws RemoteException {
		return entityManager.createQuery("select lnv from LoaiNhanVien lnv", LoaiNhanVien.class).getResultList();
	}
	@Override
	public LoaiNhanVien getLoaiNhanVienById(String id) throws RemoteException {
		return entityManager.find(LoaiNhanVien.class, id);
	}

}
