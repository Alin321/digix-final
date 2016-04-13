package ro.digix.rest;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	public Response  create(
			@FormParam("lastName") String lastName,
			@FormParam("firstName") String firstName,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("birthday") String bAux){
		
		String [] data = bAux.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.parseInt(data[0]));
		cal.set(Calendar.MONTH,Integer.parseInt(data[1]));
		cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(data[2]));
		
		Date date = cal.getTime();
		long range = 500L;
		Random r = new Random();
		long number = (long)(r.nextDouble()*range);
		User u = new User();
		u.setLastName(lastName);
		u.setFirstName(firstName);
		u.setEmail(email);
		u.setPassword(password);
		u.setId(number);
		u.setBirthDate(date);
		userService.create(u);
		java.net.URI location = null;
		try {
			location = new java.net.URI("../index.jsp?msg="+u.getFirstName()+"-Added");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return Response.temporaryRedirect(location).build();
	}
	
}
