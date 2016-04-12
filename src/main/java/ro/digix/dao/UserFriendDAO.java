package ro.digix.dao;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.UserFriend;

public interface UserFriendDAO extends AbstractDAO {

	public UserFriend create(UserFriend user);
	
	public UserFriend getUserFriendById(long id);
	
	public void delete(UserFriend user);
	
	public void update(UserFriend user);
}
