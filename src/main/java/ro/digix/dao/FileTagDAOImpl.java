package ro.digix.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.entities.FileTag;

@Repository
@Transactional
public class FileTagDAOImpl implements FileTagDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public FileTag create(FileTag tag) {
		getCurrentSession().save(tag);
		getCurrentSession().flush();
		return tag;
	}

	@Override
	public FileTag getFileTadById(long id) {
		return getCurrentSession().get(FileTag.class, id);
	}

	@Override
	public void delete(FileTag tag) {
		getCurrentSession().delete(tag);
		getCurrentSession().flush();

	}

	@Override
	public void update(FileTag tag) {
		getCurrentSession().update(tag);
		getCurrentSession().flush();

	}

}
