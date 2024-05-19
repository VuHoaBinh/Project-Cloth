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
@Table(name = "LoaiNhanVien")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoaiNhanVien implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7824272047148016339L;
	@Id
	@Column(columnDefinition = "varchar(6)",nullable = false)
	private String maLoaiNhanVien;
	@Column(columnDefinition = "nvarchar(30)",nullable = false)
	private String tenLoaiNhanVien;
	@OneToMany(mappedBy = "loaiNhanVien")
	@ToString.Exclude
	private List<NhanVien> nhanViens;
}
