package ro.digix.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.digix.entities.User;
import ro.digix.services.UserService;

@Service
@Path("/register")
public class RegisterRestService {

	@Autowired
	private UserService userService;
	
	@Path("/create")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User create(User u){
		return userService.create(u);
	}
	
	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		
		return userService.getUserById(50L);
	}
	
}
