package entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "ChiTietPhieuDatHang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChiTietPhieuDatHang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507279260568793761L;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maPhieuDatHang", columnDefinition = "varchar(6)", nullable = false)
	private PhieuDatHang phieuDatHang;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maSanPham", columnDefinition = "varchar(6)", nullable = false)
	private SanPham sanPham;
	@Column(columnDefinition = "int", nullable = false)
	private int soLuong;
	@Column(columnDefinition = "float", nullable = false)
	private double donGia;
}
