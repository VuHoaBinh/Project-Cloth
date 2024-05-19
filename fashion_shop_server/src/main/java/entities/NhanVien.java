package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "NhanVien")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NhanVien implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204301626091721133L;
	@Id
	@Column(columnDefinition = "varchar(6)",nullable = false)
	private String maNhanVien;
	@Column(columnDefinition = "nvarchar(50)",nullable = false)
	private String tenNhanVien;
	@Column(columnDefinition = "varchar(6)",nullable = false)
	private boolean gioiTinh;
	@Column(nullable = true)
	private LocalDate ngaySinh;
	@Column(columnDefinition = "nvarchar(255)",nullable = true)
	private String diaChi;
	@Column(columnDefinition = "varchar(10)",nullable = false)
	private String soDienThoai;
	@Column(columnDefinition = "float",nullable = false)
	private double luong;
	@OneToMany(mappedBy = "nhanVien")
	@ToString.Exclude
	private List<TaiKhoan> taiKhoans;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caLam", columnDefinition = "varchar(4)", nullable = false)
	private CaLam caLam;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maLoaiNhanVien", columnDefinition = "varchar(6)", nullable = false)
	private LoaiNhanVien loaiNhanVien;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quanLy", columnDefinition = "varchar(6)", nullable = true)
	private NhanVien quanLy;
	@OneToMany(mappedBy = "quanLy")
	@ToString.Exclude
	private List<NhanVien> nhanViens;
	@OneToMany(mappedBy = "nhanVien")
	@ToString.Exclude
	private List<PhieuNhap> phieuNhaps;
	@OneToMany(mappedBy = "nhanVien")
	@ToString.Exclude
	private List<PhieuDatHang> dsPhieuDatHang;
	@OneToMany(mappedBy = "nhanVien")
	@ToString.Exclude
	private List<HoaDon> dsHoaDon;
	
	
	
}
