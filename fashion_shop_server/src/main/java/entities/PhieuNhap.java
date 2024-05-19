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
@Table(name = "PhieuNhap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PhieuNhap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3195941476301203251L;
	@Id
	@Column(columnDefinition = "varchar(6)", nullable = false)
	private String maPhieuNhap;
	@Column(nullable = false)
	private LocalDate ngayLap;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nguoiLap", columnDefinition = "varchar(6)", nullable = false)
	private NhanVien nhanVien;
	@Column(columnDefinition = "nvarchar(60)", nullable = true)
	private String nhaCungCap;
}
