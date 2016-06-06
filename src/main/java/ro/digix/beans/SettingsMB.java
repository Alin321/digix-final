package ro.digix.beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.entities.User;
import ro.digix.services.UserService;

@Component("settingsMB")
@Scope("request")
public class SettingsMB extends BaseMB {

	@Autowired
	private UserMB userMB;

	@Autowired
	private UserService userService;

	private String oldPassword;
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	public void changePassword(){
		User u = userService.getUserById(userMB.getId());
		
		if(!u.getPassword().equals(Crypter.encryptSHA256(oldPassword))){
			addMessageToComponent("oldPassword", "Password is incorrect!");
			return;
		}
		
		u.setPassword(Crypter.encryptSHA256(newPassword));
		userService.update(u);
		addMessageToComponent("oldPassword", "Succesfully changed!");
		
	}
	
	public String deleteAccount() throws IOException{
		User u = userService.getUserById(userMB.getId());
		userService.delete(u);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.invalidateSession();
	    ec.redirect(ec.getRequestContextPath() + "/index.html");
	    return null;
		
	}
	
	public void changeAvatar(){
		
	}
}
