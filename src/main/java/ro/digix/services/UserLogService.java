package ro.digix.services;

import ro.digix.entities.UserLog;

public interface UserLogService {
	public UserLog create(UserLog log);

	public UserLog getUserLogById(long id);

	public void delete(UserLog log);

	public void update(UserLog log);
}
