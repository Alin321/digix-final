package ro.digix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.UserDAO;
import ro.digix.dao.UserDAOImpl;
import ro.digix.entities.User;

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
	public long getNextId() {
		return userDAO.getNextId();
	}

	@Override
	public boolean authenticate(String email, String password) {
		return userDAO.authenticate(email, password);
	}


}
