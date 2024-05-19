package entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SanPham")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SanPham implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8197056988393555751L;
	@Id
	@Column(columnDefinition = "varchar(6)", nullable = false)
	private String maSanPham;
	@Column(columnDefinition = "nvarchar(60)", nullable = false)
	private String tenSanPham;
	@Column(columnDefinition = "nvarchar(20)", nullable = false)
	private String donVi;
	@Column(columnDefinition = "nvarchar(60)", nullable = true)
	private String nhaCungCap;
	@Column(nullable = true)
	private String hinhAnh;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mauSac", nullable = false)
	private MauSac mauSac;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kichThuoc", nullable = false)
	private KichThuoc kichThuoc;
	@Column(nullable = true)
	private int soLuong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maLoaiSanPham", nullable = false)
	private LoaiSanPham loaiSanPham;
	@Column(nullable = true)
	private double donGia;
	
}
