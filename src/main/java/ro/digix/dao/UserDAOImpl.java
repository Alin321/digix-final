package ro.digix.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import ro.digix.entities.UserFriend;

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
		String hql = "FROM User U";
		Query query = getCurrentSession().createQuery(hql);
		List<User> results = (List<User>) query.list();
		return results;
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

		if (results.size() > 0) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFile> getAllFiles(long id) {
		String hql = "FROM UserFile uf where uf.user.id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<UserFile> results = (List<UserFile>) query.list();

		Collections.sort(results, new Comparator<UserFile>() {
			@Override
			public int compare(UserFile o1, UserFile o2) {
				if (o2.getDateAdded().compareTo(o1.getDateAdded()) == 0) {
					if (o2.getId() > o1.getId()) {
						return 1;
					} else
						return -1;
				}
				return o2.getDateAdded().compareTo(o1.getDateAdded());
			}
		});
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
		if (results.size() > 0) {
			u = results.get(0);
		}

		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFile> getAllFriendFiles(long id) {
		List<UserFile> listToReturn = new ArrayList<>();

		String hql = "FROM UserFriend UF where UF.user.id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<UserFriend> usersFriends = (List<UserFriend>) query.list();
		for (UserFriend uf : usersFriends) {
			hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac";
			Query query2 = getCurrentSession().createQuery(hql);
			query2.setParameter("id", uf.getFriendId());
			query2.setParameter("ac", "P");
			List<UserFile> results = (List<UserFile>) query2.list();

			for (UserFile usf : results) {
				listToReturn.add(usf);
			}
		}

		String hqlA = "FROM UserFriend UF where UF.friendId = :id";
		Query queryA = getCurrentSession().createQuery(hqlA);
		queryA.setParameter("id", id);
		List<UserFriend> usersFriends2 = (List<UserFriend>) queryA.list();
		for (UserFriend uf : usersFriends2) {
			hqlA = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac";
			Query query2A = getCurrentSession().createQuery(hqlA);
			query2A.setParameter("id", uf.getUser().getId());
			query2A.setParameter("ac", "P");
			List<UserFile> results = (List<UserFile>) query2A.list();

			for (UserFile usf : results) {
				listToReturn.add(usf);
			}
		}

		listToReturn.addAll(getAllFiles(id));

		Collections.sort(listToReturn, new Comparator<UserFile>() {
			@Override
			public int compare(UserFile o1, UserFile o2) {
				if (o2.getDateAdded().compareTo(o1.getDateAdded()) == 0) {
					if (o2.getId() > o1.getId()) {
						return 1;
					} else
						return -1;
				}
				return o2.getDateAdded().compareTo(o1.getDateAdded());
			}
		});

		return listToReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllMyFriends(long id) {
		List<User> listToReturn = new ArrayList<>();

		String hql = "FROM UserFriend UF where UF.user.id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<UserFriend> usersFriends = (List<UserFriend>) query.list();
		for (UserFriend uf : usersFriends) {
			listToReturn.add(getUserById(uf.getFriendId()));
		}

		String hqlA = "FROM UserFriend UF where UF.friendId = :id";
		Query queryA = getCurrentSession().createQuery(hqlA);
		queryA.setParameter("id", id);
		List<UserFriend> usersFriends2 = (List<UserFriend>) queryA.list();
		for (UserFriend uf : usersFriends2) {
			listToReturn.add(getUserById(uf.getUser().getId()));
		}

		Collections.sort(listToReturn, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			}
		});
		return listToReturn;
	}

	@Override
	public List<UserFile> getAllMineAndFriendsFiles(long id) {
		List<UserFile> listToReturn = new ArrayList<>();
		listToReturn.addAll(getAllFriendFiles(id));
		listToReturn.addAll(getAllFiles(id));
		return listToReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFile> getAllMineAndFriendsFiles(long id, boolean b, boolean c, boolean d) {
		List<UserFile> listToReturn = new ArrayList<>();

		String hql = "FROM UserFriend UF where UF.user.id = :id";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<UserFriend> usersFriends = (List<UserFriend>) query.list();
		for (UserFriend uf : usersFriends) {
			Query query2 = null;
			if (!b && !c && d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and uf.type=:type";
				query2 = getCurrentSession().createQuery(hql);
				query2.setParameter("type", "act");
			}

			if (!b && c && !d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and uf.type=:type";
				query2 = getCurrentSession().createQuery(hql);
				query2.setParameter("type", "video");
			}

			if (b && !c && !d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and uf.type=:type";
				query2 = getCurrentSession().createQuery(hql);
				query2.setParameter("type", "poza");
			}

			if (b && c && !d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and (uf.type=:type1 or uf.type=:type2)";
				query2 = getCurrentSession().createQuery(hql);
				query2.setParameter("type1", "poza");
				query2.setParameter("type2", "video");
			}

			query2.setParameter("id", uf.getFriendId());
			query2.setParameter("ac", "P");
			List<UserFile> results = (List<UserFile>) query2.list();

			for (UserFile usf : results) {
				listToReturn.add(usf);
			}
		}

		String hqlA = "FROM UserFriend UF where UF.friendId = :id";
		Query queryA = getCurrentSession().createQuery(hqlA);
		queryA.setParameter("id", id);
		List<UserFriend> usersFriends2 = (List<UserFriend>) queryA.list();
		for (UserFriend uf : usersFriends2) {
			Query query2A = null;
			if (!b && !c && d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and uf.type=:type";
				query2A = getCurrentSession().createQuery(hql);
				query2A.setParameter("type", "act");
			}

			if (!b && c && !d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and uf.type=:type";
				query2A = getCurrentSession().createQuery(hql);
				query2A.setParameter("type", "video");
			}

			if (b && !c && !d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and uf.type=:type";
				query2A = getCurrentSession().createQuery(hql);
				query2A.setParameter("type", "poza");
			}

			if (b && c && !d) {
				hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac and (uf.type=:type1 or uf.type=:type2)";
				query2A = getCurrentSession().createQuery(hql);
				query2A.setParameter("type1", "poza");
				query2A.setParameter("type2", "video");
			}
			query2A.setParameter("id", uf.getUser().getId());
			query2A.setParameter("ac", "P");
			List<UserFile> results = (List<UserFile>) query2A.list();

			for (UserFile usf : results) {
				listToReturn.add(usf);
			}
		}

		Query query3 = null;
		if (!b && !c && d) {
			hql = "FROM UserFile uf where uf.user.id = :id and uf.type=:type";
			query3 = getCurrentSession().createQuery(hql);
			query3.setParameter("type", "act");
		}

		if (!b && c && !d) {
			hql = "FROM UserFile uf where uf.user.id = :id and uf.type=:type";
			query3 = getCurrentSession().createQuery(hql);
			query3.setParameter("type", "video");
		}

		if (b && !c && !d) {
			hql = "FROM UserFile uf where uf.user.id = :id and uf.type=:type";
			query3 = getCurrentSession().createQuery(hql);
			query3.setParameter("type", "poza");
		}

		if (b && c && !d) {
			hql = "FROM UserFile uf where uf.user.id = :id and (uf.type=:type1 or uf.type=:type2)";
			query3 = getCurrentSession().createQuery(hql);
			query3.setParameter("type1", "poza");
			query3.setParameter("type2", "video");
		}
		query3.setParameter("id", id);
		List<UserFile> results = (List<UserFile>) query3.list();

		for (UserFile usf : results) {
			listToReturn.add(usf);
		}

		Collections.sort(listToReturn, new Comparator<UserFile>() {
			@Override
			public int compare(UserFile o1, UserFile o2) {
				if (o2.getDateAdded().compareTo(o1.getDateAdded()) == 0) {
					if (o2.getId() > o1.getId()) {
						return 1;
					} else
						return -1;
				}
				return o2.getDateAdded().compareTo(o1.getDateAdded());
			}
		});

		return listToReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserFile> getAllFriendFilesForFriendPage(long id) {
		String hql = "FROM UserFile uf where uf.user.id = :id and uf.accessType=:ac";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("ac", "P");
		List<UserFile> results = (List<UserFile>) query.list();

		Collections.sort(results, new Comparator<UserFile>() {
			@Override
			public int compare(UserFile o1, UserFile o2) {
				if (o2.getDateAdded().compareTo(o1.getDateAdded()) == 0) {
					if (o2.getId() > o1.getId()) {
						return 1;
					} else
						return -1;
				}
				return o2.getDateAdded().compareTo(o1.getDateAdded());
			}
		});
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean areFriends(long id, long id2) {
		String hql = "From UserFriend uf where (uf.user.id=:id1 and uf.friendId=:id2) or (uf.user.id=:id3 and uf.friendId=:id4)";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("id1", id);
		query.setParameter("id2", id2);
		query.setParameter("id3", id2);
		query.setParameter("id4", id);
		List<User> results =(List<User>) query.list();
		
		return !results.isEmpty();
	}
}
