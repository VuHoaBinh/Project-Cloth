package entities;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "HoaDon")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1299267359876653671L;
	@Id
	@Column(columnDefinition = "varchar(6)", nullable = false)
	private String maHoaDon;
	@Column(columnDefinition = "date", nullable = false)
	private LocalDate ngayLap;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nguoiLap", columnDefinition = "varchar(6)", nullable = false)
	private NhanVien nhanVien;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "khachHang", columnDefinition = "varchar(6)", nullable = false)
	private KhachHang khachHang;
}
