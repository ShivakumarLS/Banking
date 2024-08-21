package com.mini.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mini.banking.Service.CustomerService;
import com.mini.banking.models.Customer;
import com.mini.banking.models.LoginResponseDTO;
import com.mini.banking.utility.JwtUtility;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired 
    JwtUtility jwtUtility;

   
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    CustomerService customerService;
    
    @PostMapping("/login")
    public LoginResponseDTO login(String username,String password){
       try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			System.out.println(auth.toString());
      String token = jwtUtility.generateToken(auth);
			return new LoginResponseDTO(customerService.findByName(username), token);
		} catch (AuthenticationException e) {
			throw  new  ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials!");
		}
    }

}
