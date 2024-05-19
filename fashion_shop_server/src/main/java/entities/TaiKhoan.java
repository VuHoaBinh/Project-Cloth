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
@Table(name = "TaiKhoan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaiKhoan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3966096387653616864L;
	@Id
	@Column(columnDefinition = "varchar(6)",nullable = false)
	private String maTaiKhoan;
	@Column(columnDefinition = "varchar(20)",nullable = false)
	private String tenTaiKhoan;
	@Column(columnDefinition = "varchar(20)",nullable = false)
	private String matKhau;
	@Column(columnDefinition = "varchar(50)",nullable = true)
	private String email;
	@Column(columnDefinition = "varchar(10)",nullable = false)
	private String quyenHan;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maNhanVien", columnDefinition = "varchar(6)", nullable = false)
	private NhanVien nhanVien;
}
