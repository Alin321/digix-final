package ro.digix.services;

import ro.digix.entities.UserFile;

public interface UserFileService {
	
	public UserFile create(UserFile user);
	
	public UserFile getUserFileById(long id);
	
	public void delete(UserFile user);
	
	public void update(UserFile user);
}
