package ro.digix.services;

import java.util.List;

import ro.digix.entities.User;
import ro.digix.entities.UserFile;

public interface UserService {
	public User create(User user);

	public User getUserById(long id);
	
	public User getUserByEmail(String email);

	public void delete(User user);

	public void update(User user);

	public List<User> getAll ();

	public List<UserFile> getAllFiles(long id);
	
	long getNextId();
	
	public boolean authenticate(String email, String password);

	public boolean checkIfEmailExists(String email);

	public List<UserFile> getAllFriendFiles(long id);

	public List<User> getAllMyFriends(long id);

	public List<UserFile> getAllMineAndFriendsFiles(long id);

	public List<UserFile> getAllMineAndFriendsFiles(long id, boolean b, boolean c, boolean d);	
}
