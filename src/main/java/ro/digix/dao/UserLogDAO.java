package ro.digix.dao;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.UserLog;

public interface UserLogDAO extends AbstractDAO {
	
	public UserLog create(UserLog log);
	
	public UserLog getUserLogById(long id);
	
	public void delete(UserLog log);
	
	public void update(UserLog log);
}
