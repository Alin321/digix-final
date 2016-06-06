package ro.digix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.FileTagDAO;
import ro.digix.entities.FileTag;

@Service("fileTagService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class FileTagServiceImpl implements FileTagService {

	@Autowired
	private FileTagDAO fileTagDAO;
	
	@Override
	public FileTag create(FileTag tag) {
		return fileTagDAO.create(tag);
	}

	@Override
	public FileTag getFileTadById(long id) {
		return fileTagDAO.getFileTadById(id);
	}

	@Override
	public void delete(FileTag tag) {
		fileTagDAO.delete(tag);

	}

	@Override
	public void update(FileTag tag) {
		fileTagDAO.update(tag);

	}

	@Override
	public long getNextId() {
		return fileTagDAO.getNextId();
	}

}
