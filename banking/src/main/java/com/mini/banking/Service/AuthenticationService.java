package com.mini.banking.Service;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.mini.banking.Repository.AccountRepository;
import com.mini.banking.Repository.CustomerRepository;
import com.mini.banking.models.Account;
import com.mini.banking.models.AccountType;
import com.mini.banking.models.Customer;
import com.mini.banking.models.LoginResponseDTO;
import com.mini.banking.utility.EncryptionUtility;

@Service
public class AuthenticationService {

    @Autowired 
    CustomerService customerService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


 
    public Customer registerUser(String username, String password,String accountType) throws Exception {
        
        String encodedPassword = passwordEncoder.encode(password);

        return customerService.createCustomer(username, encodedPassword, 0, AccountType.Savings);
    }

    public LoginResponseDTO loginUser(String username, String password) {

        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(customerService.findByName(username), token);

        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials!");
        }
    }
}
