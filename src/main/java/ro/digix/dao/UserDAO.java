package ro.digix.dao;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.User;

public interface UserDAO extends AbstractDAO {
	
	public User create(User user);
	
	public User getUserById(long id);
	
	public void delete(User user);
	
	public void update(User user);
	
}
