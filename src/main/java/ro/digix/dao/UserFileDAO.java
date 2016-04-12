package ro.digix.dao;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.UserFile;

public interface UserFileDAO extends AbstractDAO {

	public UserFile create(UserFile user);
	
	public UserFile getUserFileById(long id);
	
	public void delete(UserFile user);
	
	public void update(UserFile user);
}
