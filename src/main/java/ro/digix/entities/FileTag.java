package ro.digix.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the FILE_TAGS database table.
 * 
 */
@Entity
@Table(name="FILE_TAGS")
@NamedQuery(name="FileTag.findAll", query="SELECT f FROM FileTag f")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "file_tags")
public class FileTag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="tag")
	private String tag;

	//bi-directional many-to-one association to UserFile
	@ManyToOne
	@JoinColumn(name="FILE_ID")
	private UserFile userFile;

	public FileTag() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public UserFile getUserFile() {
		return this.userFile;
	}

	public void setUserFile(UserFile userFile) {
		this.userFile = userFile;
	}

}