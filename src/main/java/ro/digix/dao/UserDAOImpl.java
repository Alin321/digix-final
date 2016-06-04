package ro.digix.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.entities.User;
import ro.digix.entities.UserFile;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		return (List<User>) getCurrentSession().createCriteria(User.class).list();
	}

	@Override
	public long getNextId() {
		Criteria criteria = getCurrentSession().createCriteria(User.class).setProjection(Projections.max("id"));
		long maxId = (long) criteria.uniqueResult();
		return maxId + 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean authenticate(String email, String password) {
		String hql = "FROM User U WHERE U.email = :email AND U.password = :password";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> results = (List<User>) query.list();
		if (results.size() == 0) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkIfEmailExists(String email) {
		String hql = "FROM User U WHERE U.email = :email";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		List<User> results = (List<User>) query.list();
		
		if(results.size() > 0) {
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFile> getAllFiles() {
		String hql = "FROM UserFile uf where uf.user.id = 100";
		Query query = getCurrentSession().createQuery(hql);
		List<UserFile> results = (List<UserFile>) query.list();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String email) {
		String hql = "FROM User U WHERE U.email = :email";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		List<User> results = (List<User>) query.list();
		
		User u = null;
		if(results.size() > 0) {
			u = results.get(0);
		}
		
		return u;
	}
}
