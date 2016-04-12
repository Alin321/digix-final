package ro.digix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.UserLogDAO;
import ro.digix.entities.UserLog;

@Service("userLogService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserLogServiceImpl implements UserLogService {

	@Autowired
	private UserLogDAO userLogDAO;
	
	@Override
	public UserLog create(UserLog log) {
		return userLogDAO.create(log);
	}

	@Override
	public UserLog getUserLogById(long id) {
		return userLogDAO.getUserLogById(id);
	}

	@Override
	public void delete(UserLog log) {
		userLogDAO.delete(log);

	}

	@Override
	public void update(UserLog log) {
		userLogDAO.update(log);

	}

}
