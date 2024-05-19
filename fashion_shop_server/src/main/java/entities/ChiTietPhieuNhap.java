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
@Table(name = "ChiTietPhieuNhap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChiTietPhieuNhap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8633587180827427222L;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maPhieuNhap", columnDefinition = "varchar(6)", nullable = false)
	private PhieuNhap phieuNhap;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maSanPham", columnDefinition = "varchar(6)", nullable = false)
	private SanPham sanPham;
	@Column(nullable = false)
	private int soLuong;
}
