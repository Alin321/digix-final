package ro.digix.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.entities.UserFile;

@Repository
@Transactional
public class UserFileDAOImpl implements UserFileDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserFile create(UserFile user) {
		getCurrentSession().save(user);
		getCurrentSession().flush();
		return user;
	}

	@Override
	public UserFile getUserFileById(long id) {
		return getCurrentSession().get(UserFile.class, id);
	}

	@Override
	public void delete(UserFile user) {
		getCurrentSession().delete(user);
		getCurrentSession().flush();
		
	}

	@Override
	public void update(UserFile user) {
		getCurrentSession().update(user);
		getCurrentSession().flush();
		
	}
}
