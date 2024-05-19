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
@Table(name = "CaLam")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CaLam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5922927381348525581L;
	@Id
	@Column(columnDefinition = "varchar(4)",nullable = false)
	private String maCaLam;
	@Column(columnDefinition = "nvarchar(10)",nullable = false)
	private String buoi;
	@Column(columnDefinition = "nvarchar(15)",nullable = true)
	private String thoiGianBatDau;
	@Column(columnDefinition = "nvarchar(15)",nullable = true)
	private String thoiGianKetThuc;
	@OneToMany(mappedBy = "caLam")
	@ToString.Exclude
	private List<NhanVien> nhanViens;
	
	
}
