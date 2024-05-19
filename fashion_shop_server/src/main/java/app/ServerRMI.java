package app;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dao.CaLamDAO;
import dao.ChiTietDatHangDAO;
import dao.ChiTietHoaDonDAO;
import dao.ChiTietKhuyenMaiDAO;
import dao.ChiTietPhieuNhapDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.KichThuocDAO;
import dao.LoaiNhanVienDAO;
import dao.LoaiSanPhamDAO;
import dao.MauSacDAO;
import dao.NhanVienDAO;
import dao.PhieuDatHangDAO;
import dao.PhieuNhapDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;
import jakarta.persistence.EntityManager;
import services.CaLamService;
import services.ChiTietHoaDonService;
import services.ChiTietKhuyenMaiService;
import services.ChiTietPhieuDatHangService;
import services.ChiTietPhieuNhapService;
import services.EntityManagerFactoryUtils;
import services.HoaDonService;
import services.KhachHangService;
import services.KhuyenMaiService;
import services.KichThuocService;
import services.LoaiNhanVienService;
import services.LoaiSanPhamService;
import services.MauSacService;
import services.NhanVienService;
import services.PhieuDatHangService;
import services.PhieuNhapService;
import services.SanPhamService;
import services.TaiKhoanService;

public class ServerRMI {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry registry = LocateRegistry.createRegistry(2008);
		EntityManagerFactoryUtils entityManagerFactoryUtils = new EntityManagerFactoryUtils();
		EntityManager entityManager = entityManagerFactoryUtils.getEntityManager();
		
		CaLamDAO caLamDao = new CaLamService(entityManager);
		ChiTietDatHangDAO chiTietDatHangDao = new ChiTietPhieuDatHangService(entityManager);
		ChiTietHoaDonDAO chiTietHoaDonDao = new ChiTietHoaDonService(entityManager);
		ChiTietKhuyenMaiDAO chiTietKhuyenMaiDao = new ChiTietKhuyenMaiService(entityManager);
		ChiTietPhieuNhapDAO chiTietPhieuNhapDao = new ChiTietPhieuNhapService(entityManager);
		HoaDonDAO hoaDonDao = new HoaDonService(entityManager);
		KhachHangDAO khachHangDao = new KhachHangService(entityManager);
		KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiService(entityManager);
		KichThuocDAO kichThuocDao = new KichThuocService(entityManager);
		LoaiNhanVienDAO loaiNhanVienDao = new LoaiNhanVienService(entityManager);
		LoaiSanPhamDAO loaiSanPhamDao = new LoaiSanPhamService(entityManager);
		MauSacDAO mauSacDao = new MauSacService(entityManager);
		NhanVienDAO nhanVienDao = new NhanVienService(entityManager);
		PhieuDatHangDAO phieuDatHangDao = new PhieuDatHangService(entityManager);
		PhieuNhapDAO phieuNhapDao = new PhieuNhapService(entityManager);
		SanPhamDAO sanPhamDao = new SanPhamService(entityManager);
		TaiKhoanDAO taiKhoanDao = new TaiKhoanService(entityManager);
		
		registry.bind("caLamDao", caLamDao);
		registry.bind("chiTietDatHangDao", chiTietDatHangDao);
		registry.bind("chiTietHoaDonDao", chiTietHoaDonDao);
		registry.bind("chiTietKhuyenMaiDao", chiTietKhuyenMaiDao);
		registry.bind("chiTietPhieuNhapDao", chiTietPhieuNhapDao);
		registry.bind("hoaDonDao", hoaDonDao);
		registry.bind("khachHangDao", khachHangDao);
		registry.bind("khuyenMaiDao", khuyenMaiDao);
		registry.bind("kichThuocDao", kichThuocDao);
		registry.bind("loaiNhanVienDao", loaiNhanVienDao);
		registry.bind("loaiSanPhamDao", loaiSanPhamDao);
		registry.bind("mauSacDao", mauSacDao);
		registry.bind("nhanVienDao", nhanVienDao);
		registry.bind("phieuDatHangDao", phieuDatHangDao);
		registry.bind("phieuNhapDao", phieuNhapDao);
		registry.bind("sanPhamDao", sanPhamDao);
		registry.bind("taiKhoanDao", taiKhoanDao);
		System.out.println("Server is running...");
	}
}
