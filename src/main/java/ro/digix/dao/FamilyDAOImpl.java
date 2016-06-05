package ro.digix.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FamilyDAOImpl implements FamilyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
