package ro.digix.rest;

import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  create(User u){
		userService.create(u);
		java.net.URI location = null;
		try {
			location = new java.net.URI("http://localhost:8082/digix-final/index.jsp?msg=A_User_Added");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return Response.temporaryRedirect(location).build();
	}
	
}
