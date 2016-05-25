package ro.digix.services;

import java.util.List;

import ro.digix.entities.User;

public interface UserService {
	public User create(User user);

	public User getUserById(long id);

	public void delete(User user);

	public void update(User user);

	public List<User> getAll ();
}
