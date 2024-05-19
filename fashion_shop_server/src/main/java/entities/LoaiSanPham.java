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
@Table(name = "LoaiSanPham")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoaiSanPham implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7474001314941822492L;
	@Id
	@Column(columnDefinition = "varchar(6)", nullable = false)
	private String maLoaiSanPham;
	@Column(columnDefinition = "nvarchar(70)",nullable = false)
	private String tenLoaiSanPham;
	@OneToMany(mappedBy = "loaiSanPham")
	@ToString.Exclude
	private List<SanPham> dsSanPham;
}
