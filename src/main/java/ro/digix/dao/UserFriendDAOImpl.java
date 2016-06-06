package ro.digix.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.entities.User;
import ro.digix.entities.UserFriend;

@Repository
@Transactional
public class UserFriendDAOImpl implements UserFriendDAO {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserFriend create(UserFriend user) {
		getCurrentSession().save(user);
		getCurrentSession().flush();
		return user;
	}

	@Override
	public UserFriend getUserFriendById(long id) {
		return getCurrentSession().get(UserFriend.class, id);
	}

	@Override
	public void delete(UserFriend user) {
		getCurrentSession().delete(user);
		getCurrentSession().flush();

	}

	@Override
	public void update(UserFriend user) {
		getCurrentSession().update(user);
		getCurrentSession().flush();

	}

	@Override
	public long getNextId() {
		Criteria criteria = getCurrentSession().createCriteria(UserFriend.class).setProjection(Projections.max("id"));
		long maxId = (long) criteria.uniqueResult();
		return maxId + 1;
	}

}
