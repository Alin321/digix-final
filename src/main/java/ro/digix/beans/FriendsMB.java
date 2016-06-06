package ro.digix.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ro.digix.entities.User;
import ro.digix.entities.UserFriend;
import ro.digix.services.UserFriendService;
import ro.digix.services.UserService;

@Component("friendsMB")
@Scope("request")
public class FriendsMB {

	@Autowired
	private UserMB userMB;

	@Autowired
	private UserService userService;

	@Autowired
	private UserFriendService userFriendService;

	private String searchString = "";
	private String liveString;

	private boolean clearedOnce = false;

	public String getSearchString() {
		return searchString;
	}

	public void setUserFriendService(UserFriendService userFriendService) {
		this.userFriendService = userFriendService;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getLiveString() {
		return liveString;
	}

	// public void setLiveString(String liveString) {
	// this.liveString = liveString;
	// }

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();
		allUsers = userService.getAll();
		for (Iterator<User> it = allUsers.iterator(); it.hasNext();) {
			if (it.next().getId() == userMB.getId()) {
				it.remove();
			}
		}
		Collections.sort(allUsers, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				if (userService.areFriends(o1.getId(), userMB.getId())) {
					return -1;
				}
				return 1;
			}

		});
		return allUsers;
	}

	public boolean areFriends(long id) {
		boolean b = userService.areFriends(id, userMB.getId());
		return b;
		
	}

	public void addToFriendList(long id) throws IOException {
		UserFriend uf = new UserFriend();
		User u = new User();
		u.setId(userMB.getId());

		uf.setFriendId(id);
		uf.setUser(u);
		uf.setId(userFriendService.getNextId());

		userFriendService.create(uf);

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
}
