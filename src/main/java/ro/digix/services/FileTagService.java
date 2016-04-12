package ro.digix.services;

import ro.digix.entities.FileTag;

public interface FileTagService {

	public FileTag create(FileTag tag);
	
	public FileTag getFileTadById(long id);
	
	public void delete(FileTag tag);
	
	public void update(FileTag tag);
}
