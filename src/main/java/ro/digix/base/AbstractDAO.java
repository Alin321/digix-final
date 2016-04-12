package ro.digix.base;

import org.hibernate.Session;

public interface AbstractDAO {

	public Session getCurrentSession();
}