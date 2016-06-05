package ro.digix.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "avatar_location")
	private String avatarLocation;

	// bi-directional many-to-one association to UserFile
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@JsonIgnore
	private List<UserFile> userFiles;

	// bi-directional many-to-one association to UserFriend
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserFriend> userFriends;

	// bi-directional many-to-one association to UserLog
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<UserLog> userLogs;

	public User() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public String getAvatarLocation() {
		return avatarLocation;
	}

	public void setAvatarLocation(String avatarLocation) {
		this.avatarLocation = avatarLocation;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserFile> getUserFiles() {
		return this.userFiles;
	}

	public void setUserFiles(List<UserFile> userFiles) {
		this.userFiles = userFiles;
	}

	public UserFile addUserFile(UserFile userFile) {
		getUserFiles().add(userFile);
		userFile.setUser(this);

		return userFile;
	}

	public UserFile removeUserFile(UserFile userFile) {
		getUserFiles().remove(userFile);
		userFile.setUser(null);

		return userFile;
	}

	public List<UserFriend> getUserFriends() {
		return this.userFriends;
	}

	public void setUserFriends(List<UserFriend> userFriends) {
		this.userFriends = userFriends;
	}

	public UserFriend addUserFriend(UserFriend userFriend) {
		getUserFriends().add(userFriend);
		userFriend.setUser(this);

		return userFriend;
	}

	public UserFriend removeUserFriend(UserFriend userFriend) {
		getUserFriends().remove(userFriend);
		userFriend.setUser(null);

		return userFriend;
	}

	public List<UserLog> getUserLogs() {
		return this.userLogs;
	}

	public void setUserLogs(List<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

	public UserLog addUserLog(UserLog userLog) {
		getUserLogs().add(userLog);
		userLog.setUser(this);

		return userLog;
	}

	public UserLog removeUserLog(UserLog userLog) {
		getUserLogs().remove(userLog);
		userLog.setUser(null);

		return userLog;
	}

}