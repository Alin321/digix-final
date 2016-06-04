package ro.digix.dao;

import java.util.List;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.User;

public interface UserDAO extends AbstractDAO {
	
	public User create(User user);
	
	public User getUserById(long id);
	
	public void delete(User user);
	
	public void update(User user);
	
	public List<User> getAll ();

	long getNextId();

	boolean authenticate(String email, String password);
}
