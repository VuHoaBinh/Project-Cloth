package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.LoaiSanPhamDAO;
import entities.LoaiSanPham;
import jakarta.persistence.EntityManager;

public class LoaiSanPhamService extends UnicastRemoteObject implements LoaiSanPhamDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7455558716593582981L;
	private EntityManager entityManager;

	public LoaiSanPhamService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}

	@Override
	public List<LoaiSanPham> getAllLoaiSanPham() throws RemoteException {
		return entityManager.createQuery("select lsp from LoaiSanPham lsp", LoaiSanPham.class).getResultList();
	}

	@Override
	public boolean create(LoaiSanPham lsp) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(lsp);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public LoaiSanPham getLoaiSanPhamById(String id) throws RemoteException {
		return entityManager.find(LoaiSanPham.class, id);
	}

}
