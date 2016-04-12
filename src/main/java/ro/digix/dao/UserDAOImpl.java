package ro.digix.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.entities.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User create(User user) {
		
		getCurrentSession().save(user);
		getCurrentSession().flush();
			
		return user;
	}

	@Override
	public User getUserById(long id) {
		return getCurrentSession().get(User.class, id);
	}

	@Override
	public void delete(User user) {
		getCurrentSession().delete(user);
		getCurrentSession().flush();
		
	}

	@Override
	public void update(User user) {
		getCurrentSession().saveOrUpdate(user);
		getCurrentSession().flush();
		
	}

}
