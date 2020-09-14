
package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.exception.UserAlreadyExistsException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.UserAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 * 
 * @CrossOrigin,@EnableFeignClients,@RibbonClient
 * 
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1/auth")
public class UserAuthenticationController {

	/*
	 * Autowiring should be implemented for the UserAuthenticationService. (Use Constructor-based
	 * autowiring) Please note that we should not create an object using the new
	 * keyword
	 */
	private UserAuthenticationService userAuthenticationService;

	@Autowired
	public UserAuthenticationController(UserAuthenticationService authicationService) {
		this.userAuthenticationService = authicationService;
	}
	private ResponseEntity responseEntity;
	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the user created successfully.
	 * 2. 409(CONFLICT) - If the userId conflicts with any existing user
	 *
	 * This handler method should map to the URL "/api/v1/auth/register" using HTTP POST method
	 */

	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody User user) {

		try {

			Boolean flag = userAuthenticationService.saveUser(user);
			responseEntity = new ResponseEntity(flag, HttpStatus.CREATED);


		} catch (UserAlreadyExistsException exception) {
			responseEntity = new ResponseEntity("error", HttpStatus.CONFLICT);
		}


		return responseEntity;

	}



	/* Define a handler method which will authenticate a user by reading the Serialized user
	 * object from request body containing the username and password. The username and password should be validated
	 * before proceeding ahead with JWT token generation. The user credentials will be validated against the database entries.
	 * The error should be return if validation is not successful. If credentials are validated successfully, then JWT
	 * token will be generated. The token should be returned back to the caller along with the API response.
	 * This handler method should return any one of the status messages basis on different
	 * situations:
	 * 1. 200(OK) - If login is successful
	 * 2. 401(UNAUTHORIZED) - If login is not successful
	 *
	 * This handler method should map to the URL "/api/v1/auth/login" using HTTP POST method
	 */

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		try
		{
			User newUser = this.userAuthenticationService.findByUserIdAndPassword(user.getUserId(), user.getUserPassword());
			if (newUser != null)
			{
				String token;
				try
				{
					token = this.getToken(user.getUserId(), user.getUserPassword());
					return new ResponseEntity(token, HttpStatus.OK);
				}
				catch (Exception e)
				{
					return new ResponseEntity(HttpStatus.UNAUTHORIZED);
				}

			}
			else
			{
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
		}
		catch (UserNotFoundException e)
		{
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}

	}

	// Generate JWT token
	public String getToken(String username, String password) throws Exception {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + 900000))
				.signWith(SignatureAlgorithm.HS256,"secretkey").compact();


	}


}
