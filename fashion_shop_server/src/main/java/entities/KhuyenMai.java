package entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "KhuyenMai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhuyenMai implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3667750929455224717L;
	@Id
	@Column(columnDefinition = "varchar(6)",nullable = false)
	private String maKhuyenMai;
	@Column(columnDefinition = "nvarchar(255)",nullable = false)
	private String tenKhuyenMai;
	@Column(nullable = false)
	private LocalDate ngayBatDau;
	@Column(nullable = false)
	private LocalDate ngayKetThuc;
}
