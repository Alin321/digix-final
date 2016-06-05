package ro.digix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.UserDAO;
import ro.digix.entities.User;
import ro.digix.entities.UserFile;

@Service("userService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Override
	public User create(User user) {
		return userDAO.create(user);
	}

	@Override
	public User getUserById(long id) {
		return userDAO.getUserById(id);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
		
	}
	@Override
	public void update(User user) {
		userDAO.update(user);
		
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public boolean authenticate(String email, String password) {
		return userDAO.authenticate(email, password);
	}

	@Override
	public long getNextId() {
		return userDAO.getNextId();
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		return userDAO.checkIfEmailExists(email);
	}

	@Override
	public List<UserFile> getAllFiles(long id) {
		return userDAO.getAllFiles(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

	@Override
	public List<UserFile> getAllFriendFiles(long id) {
		return userDAO.getAllFriendFiles(id);
	}

	@Override
	public List<User> getAllMyFriends(long id) {
		return userDAO.getAllMyFriends(id);
	}

	@Override
	public List<UserFile> getAllMineAndFriendsFiles(long id) {
		return userDAO.getAllMineAndFriendsFiles(id);
	}

	@Override
	public List<UserFile> getAllMineAndFriendsFiles(long id, boolean b, boolean c, boolean d) {
		return userDAO.getAllMineAndFriendsFiles(id, b, c, d);
	}


}
