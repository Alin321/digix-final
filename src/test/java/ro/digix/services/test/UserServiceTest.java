package ro.digix.services.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.digix.entities.User;
import ro.digix.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void userServiceCreateTest() {
		//Creare user nou
		User user = new User();
		user.setId(2000L);
		user.setEmail("alincuzuccc@yahoo.com");
		user.setFirstName("Alin");
		
		//Inserare user in baza de date si verificare 
		userService.create(user);		
		User found = new User();		
		found = userService.getUserById(2000L);		
		assertNotNull(found);
		
		//Update nume user si verificare
		user.setLastName("TEST");
		userService.update(user);		
		found = userService.getUserById(2000L);
		assertEquals(found.getLastName(),"TEST");
		
		//Delete user si verificare
		userService.delete(user);
		found = userService.getUserById(2000L);
		assertNull(found);
		
	}
}
