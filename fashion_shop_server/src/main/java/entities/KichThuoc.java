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
@Table(name = "KichThuoc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KichThuoc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6355078299198208480L;
	@Id
	@Column(columnDefinition = "varchar(4)", nullable = false)
	private String maKichThuoc;
	@Column(columnDefinition = "varchar(4)", nullable = false)
	private String tenKichThuoc;
	@OneToMany(mappedBy = "kichThuoc")
	@ToString.Exclude
	private List<SanPham> dsSanPham;
}
