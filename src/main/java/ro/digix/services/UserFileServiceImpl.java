package ro.digix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.UserFileDAO;
import ro.digix.entities.UserFile;

@Service("userFileService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserFileServiceImpl implements UserFileService {

	@Autowired
	UserFileDAO userFileDao;
	
	@Override
	public UserFile create(UserFile user) {
		return userFileDao.create(user);
	}

	@Override
	public UserFile getUserFileById(long id) {
		return userFileDao.getUserFileById(id);
	}

	@Override
	public void delete(UserFile user) {
		userFileDao.delete(user);
		
	}

	@Override
	public void update(UserFile user) {
		userFileDao.update(user);
		
	}

	@Override
	public List<String> getAllCategories() {
		return userFileDao.getAllCategories();
	}

	@Override
	public long getNewId() {
		return userFileDao.getNewId();
	}

}
