package ro.digix.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.entities.UserLog;

@Repository
@Transactional
public class UserLogDAOImpl implements UserLogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserLog create(UserLog log) {
		getCurrentSession().save(log);
		getCurrentSession().flush();
		return log;
	}

	@Override
	public UserLog getUserLogById(long id) {
		return getCurrentSession().get(UserLog.class, id);
	}

	@Override
	public void delete(UserLog log) {
		getCurrentSession().delete(log);
		getCurrentSession().flush();

	}

	@Override
	public void update(UserLog log) {
		getCurrentSession().update(log);
		getCurrentSession().flush();

	}

}
