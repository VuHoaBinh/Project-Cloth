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
@Table(name = "MauSac")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MauSac implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8602837208604922892L;
	@Id
	@Column(columnDefinition = "varchar(4)", nullable = false)
	private String maMauSac;
	@Column(columnDefinition = "nvarchar(20)", nullable = false)
	private String tenMauSac;
	@OneToMany(mappedBy = "mauSac")
	@ToString.Exclude
	private List<SanPham> dsSanPham;
}
