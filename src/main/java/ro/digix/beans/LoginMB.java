package ro.digix.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.entities.User;
import ro.digix.services.UserService;

@Component("loginMB")
@Scope("request")
public class LoginMB extends BaseMB {
	@Autowired
	private UserService userService;

	@Autowired
	private UserMB userMB;

	private String email;
	private String password;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public String authenticate() {
		if (email == null || password == null) {
			return null;
		}

		if (userService.authenticate(email, password)) {
			User u = userService.getUserByEmail(email);
			userMB.setEmail(u.getEmail());
			userMB.setBirthDate(u.getBirthDate());
			userMB.setFirstName(u.getFirstName());
			userMB.setLastName(u.getLastName());
			userMB.setId(u.getId());
			userMB.setAvatar(u.getAvatarLocation());
			return getRedirectedURL("afterLogIn.xhtml");

		} else {
			addMessageToComponent("", "User name or password is invalid");
			return null;
		}
	}

	public boolean checkIfEmailExists(String email) {
		return userService.checkIfEmailExists(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
