package ro.digix.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the USER_FILES database table.
 * 
 */
@Entity
@Table(name="USER_FILES")
@NamedQuery(name="UserFile.findAll", query="SELECT u FROM UserFile u")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "user_file")
public class UserFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACCESS_TYPE")
	private String accessType;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ADDED")
	private Date dateAdded;

	@Column(name="location")
	private String location;

	@Column(name="type")
	private String type;

	//bi-directional many-to-one association to FileTag
	@OneToMany(mappedBy="userFile")
	private List<FileTag> fileTags;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public UserFile() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessType() {
		return this.accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Date getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FileTag> getFileTags() {
		return this.fileTags;
	}

	public void setFileTags(List<FileTag> fileTags) {
		this.fileTags = fileTags;
	}

	public FileTag addFileTag(FileTag fileTag) {
		getFileTags().add(fileTag);
		fileTag.setUserFile(this);

		return fileTag;
	}

	public FileTag removeFileTag(FileTag fileTag) {
		getFileTags().remove(fileTag);
		fileTag.setUserFile(null);

		return fileTag;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}