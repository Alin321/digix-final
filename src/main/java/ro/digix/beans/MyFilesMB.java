package ro.digix.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.entities.UserFile;
import ro.digix.services.UserFileService;
import ro.digix.services.UserService;

@Component("myFilesMB")
@Scope("request")
public class MyFilesMB {

	@Autowired
	private UserMB userMB;

	@Autowired
	private UserFileService userFileService;

	@Autowired
	private UserService userService;

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public void setUserFileService(UserFileService userFileService) {
		this.userFileService = userFileService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<UserFile> getAllMyFiles() {
		return userService.getAllFiles(userMB.getId());
	}

	public boolean isFilePublic(long id){
		return userFileService.isFilePublic(id);
	}
	
	public String changeAccessType(long id) throws IOException {
		UserFile uf = userFileService.getUserFileById(id);
		if(uf.getAccessType().equalsIgnoreCase("p")){
			uf.setAccessType("S");
		} else {
			uf.setAccessType("P");
		}
		
		userFileService.update(uf);
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	    
	    return null;
	}
}
