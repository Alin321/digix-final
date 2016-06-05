package ro.digix.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.entities.User;
import ro.digix.entities.UserFile;
import ro.digix.services.UserFileService;
import ro.digix.services.UserService;

@Component("userMB")
@Scope("session")
public class UserMB {

	@Autowired
	UserService userService;

	@Autowired
	UserFileService userFileService;

	private long id;
	private Date birthDate;
	private String email;
	private String firstName;
	private String lastName;
	private String avatar;

	private List<UserFile> searchResult;

	@PostConstruct
	public void init() {
		searchResult = new ArrayList<>();
	}

	public List<UserFile> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<UserFile> searchResult) {
		this.searchResult = searchResult;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getAllUsers() {
		return userService.getAll();
	}

	public List<UserFile> getAllUserFiles(long auxId) {
		return userService.getAllFiles(auxId);
	}

	public List<UserFile> getAllUserFriendFiles() {
		return userService.getAllFriendFiles(id);
	}

	public User getUserById(long id) {
		return userService.getUserById(id);
	}

	public List<String> getAllCategories() {
		return userFileService.getAllCategories();
	}

	public String goToCategory(String s) {
		return null;
	}

	public List<User> getAllMyFriends() {
		return userService.getAllMyFriends(id);
	}

	public void goToProfile(long id) throws IOException {
		String uri = "userProfile.xhtml?id=" + id;
		FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
	}
}
