package ro.digix.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "familie")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "familie")
public class Family implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3833524672672864546L;

	@Id
	private long id;

	@Column(name = "parinte_id")
	private long idParinte;

	@Column(name = "copil_id")
	private long idCopil;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdParinte() {
		return idParinte;
	}

	public void setIdParinte(long idParinte) {
		this.idParinte = idParinte;
	}

	public long getIdCopil() {
		return idCopil;
	}

	public void setIdCopil(long idCopil) {
		this.idCopil = idCopil;
	}

}
