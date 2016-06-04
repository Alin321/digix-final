package ro.digix.dao;

import java.util.List;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.User;
import ro.digix.entities.UserFile;

public interface UserDAO extends AbstractDAO {

	public User create(User user);

	public User getUserById(long id);
	
	public User getUserByEmail(String email);

	public void delete(User user);

	public void update(User user);

	public List<User> getAll();

	public List<UserFile> getAllFiles();

	long getNextId();

	boolean authenticate(String email, String password);

	public boolean checkIfEmailExists(String email);
}
