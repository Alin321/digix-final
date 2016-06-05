package ro.digix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.digix.dao.FileTagDAO;

@Service("familyService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class FamilyServiceImpl implements FamilyService {

	@Autowired
	private FileTagDAO fileTagDAO;
	
}
