package ro.digix.services;

import ro.digix.entities.UserFriend;

public interface UserFriendService {

	public UserFriend create(UserFriend user);
	
	public UserFriend getUserFriendById(long id);
	
	public void delete(UserFriend user);
	
	public void update(UserFriend user);
}
