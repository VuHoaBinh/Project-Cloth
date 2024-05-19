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
@Table(name = "ChiTietKhuyenMai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChiTietKhuyenMai implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6725774027162465172L;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maKhuyenMai", columnDefinition = "varchar(6)", nullable = false)
	private KhuyenMai khuyenMai;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maSanPham", columnDefinition = "varchar(6)", nullable = false)
	private SanPham sanPham;
	@Column(columnDefinition = "float", nullable = true)
    private double phanTramKhuyenMai;
}
