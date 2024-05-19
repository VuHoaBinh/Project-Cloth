package entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "KhachHang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2521082880762617270L;
	@Id
	@Column(columnDefinition = "varchar(6)", nullable = false)
    private String maKhachHang;
	@Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String tenKhachHang;
	@Column(columnDefinition = "varchar(6)", nullable = false)
    private boolean gioiTinh;
	@Column(columnDefinition = "nvarchar(255)", nullable = true)
    private String diaChi;
	@Column(columnDefinition = "varchar(10)", nullable = false)
    private String soDienThoai;
	@Column(columnDefinition = "varchar(50)", nullable = true)
    private String email;
	@OneToMany(mappedBy = "khachHang")
	@ToString.Exclude
	private List<PhieuDatHang> dsPhieuDatHang;
	@OneToMany(mappedBy = "khachHang")
	@ToString.Exclude
	private List<HoaDon> dsHoaDon;
}
