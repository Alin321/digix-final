package ro.digix.dao;

import ro.digix.base.AbstractDAO;
import ro.digix.entities.FileTag;

public interface FileTagDAO extends AbstractDAO {

	public FileTag create(FileTag tag);
	
	public FileTag getFileTadById(long id);
	
	public void delete(FileTag tag);
	
	public void update(FileTag tag);
	
	public long getNextId();
}
