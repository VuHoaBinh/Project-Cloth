package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.NhanVienDAO;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class NhanVienService extends UnicastRemoteObject implements NhanVienDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5757753457739597621L;
	private EntityManager entityManager;

	public NhanVienService(EntityManager entityManager) throws RemoteException {
		this.entityManager = entityManager;
	}
	@Override
	public List<NhanVien> getAllNhanVien() throws RemoteException {
		return entityManager.createQuery("select nv FROM NhanVien nv", NhanVien.class).getResultList();
	}

	@Override
	public List<NhanVien> getAllNhanVienQuanLy() throws RemoteException {
		return entityManager.createQuery("select nv from NhanVien nv where nv.loaiNhanVien.maLoaiNhanVien = :maLoaiNhanVien", NhanVien.class)
				.setParameter("maLoaiNhanVien", "LNV001").getResultList();
	}

	@Override
	public boolean create(NhanVien nv) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(nv);
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
	public boolean Delete(String maNV) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			NhanVien nv = entityManager.find(NhanVien.class, maNV);
			entityManager.remove(nv);
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
	public boolean Update(NhanVien nv) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(nv);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> Find(String sql, ArrayList<String> token) throws RemoteException {
		Query query = entityManager.createNativeQuery(sql, NhanVien.class);
		for (int i = 0; i < token.size(); i++) {
			query.setParameter("a"+i, token.get(i));
		}
		return query.getResultList();
	}

	@Override
	public boolean UpdateTTCN(String maNV, String ten, String diaChi, String soDienThoai, LocalDate ngaySinh,
			boolean gioiTinh) throws RemoteException {
		try {
			entityManager.getTransaction().begin();
			NhanVien nv = entityManager.find(NhanVien.class, maNV);
			nv.setTenNhanVien(ten);
			nv.setDiaChi(diaChi);
			nv.setSoDienThoai(soDienThoai);
			nv.setNgaySinh(ngaySinh);
			nv.setGioiTinh(gioiTinh);
			entityManager.merge(nv);
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
	public NhanVien getNhanVien(String maNV) throws RemoteException {
		return entityManager.find(NhanVien.class, maNV);
	}
	@Override
	public Object[] getThongTinCaNhans(String maTK) throws RemoteException {
		String sql = "select nv.maNhanVien, nv.diaChi, nv.tenNhanVien, nv.soDienThoai, nv.ngaySinh, tk.email, nv.gioiTinh, tk.tenTaiKhoan,\r\n"
				+ "nv.quanLy, tk.matKhau, lnv.tenLoaiNhanVien, cl.buoi\r\n"
				+ "from NhanVien nv join TaiKhoan tk on nv.maNhanVien = tk.maNhanVien join LoaiNhanVien lnv on nv.maLoaiNhanVien = lnv.maLoaiNhanVien\r\n"
				+ "join CaLam cl on cl.maCaLam = nv.caLam where maTaiKhoan = :maTaiKhoan";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("maTaiKhoan", maTK);
		return (Object[]) query.getSingleResult();
	}

}
