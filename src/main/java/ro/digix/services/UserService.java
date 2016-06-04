package ro.digix.services;

import java.util.List;

import ro.digix.entities.User;
import ro.digix.entities.UserFile;

public interface UserService {
	public User create(User user);

	public User getUserById(long id);

	public void delete(User user);

	public void update(User user);

	public List<User> getAll ();

	public List<UserFile> getAllFiles();
	
	long getNextId();
	
	public boolean authenticate(String email, String password);

	public boolean checkIfEmailExists(String email);	
}
