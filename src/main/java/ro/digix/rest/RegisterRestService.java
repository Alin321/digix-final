package ro.digix.rest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

	@Path("create2")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User create2(User user) {
		return userService.create(user);
	}

	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") long id) {
		User user = userService.getUserById(id);
		System.out.println(user.getEmail());
		return user;
	}

	@Path("get_all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@Path("/create")
	@POST
	// @Consumes(MediaType.APPLICATION_JSON)
	public Response create(@FormParam("lastName") String lastName, @FormParam("firstName") String firstName,
			@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("birthday") String bAux) {

		String[] data = bAux.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(data[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(data[1]));
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));

		Date date = cal.getTime();

		User u = new User();
		u.setLastName(lastName);
		u.setFirstName(firstName);
		u.setEmail(email);
		u.setPassword(password);
		u.setId(userService.getNextId());
		u.setBirthDate(date);
		userService.create(u);
		
		java.nio.file.Path path = Paths.get("D://workspace/digix_github/digix-final/src/main/webapp/upload" + File.separator + email);

		try {
			Files.createDirectories(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		java.net.URI location = null;
		try {
			location = new java.net.URI("../log-in.xhtml");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.temporaryRedirect(location).build();
	}

}
