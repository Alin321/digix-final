package ro.digix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.UserFriendDAO;
import ro.digix.entities.UserFriend;

@Service("userFriendService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserFriendServiceImpl implements UserFriendService {

	@Autowired
	UserFriendDAO userFriendDAO;
	@Override
	public UserFriend create(UserFriend user) {
		return userFriendDAO.create(user);
	}

	@Override
	public UserFriend getUserFriendById(long id) {
		return userFriendDAO.getUserFriendById(id);
	}

	@Override
	public void delete(UserFriend user) {
		userFriendDAO.delete(user);

	}

	@Override
	public void update(UserFriend user) {
		userFriendDAO.update(user);

	}

	@Override
	public long getNextId() {
		return userFriendDAO.getNextId();
	}

}
