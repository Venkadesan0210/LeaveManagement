package com.example.leavemanagement.service;
import com.example.leavemanagement.repo.UserRepo;
import com.example.leavemanagement.entity.JwtRequest;
import com.example.leavemanagement.entity.JwtResponse;
import com.example.leavemanagement.entity.User;
import com.example.leavemanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
@Component
public class JwtService implements UserDetailsService {
    private JwtUtil jwtUtil;
    private UserRepo userDao;
    private AuthenticationManager authenticationManager;
    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Autowired
    public void setUserDao(UserRepo userDao) {
        this.userDao = userDao;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        Optional<User> optionalUser = userDao.findById(userName);
        if (optionalUser.isPresent()) {
            UserDetails userDetails = loadUserByUsername(userName);
            String newGeneratedToken = jwtUtil.generateToken(userDetails);

            User user = optionalUser.get();
            return new JwtResponse(user, newGeneratedToken);
        } else {
            // Throwing a more specific exception when the user is not found
            throw new UserNotFoundException("User not found with username: " + userName);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username");
        }
    }
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
        );
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws UserDisabledException, InvalidCredentialsException {
        try {
            // Uncovered code
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new UserDisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("INVALID_CREDENTIALS", e);
        }
    }


    public class UserDisabledException extends Exception {
        public UserDisabledException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class InvalidCredentialsException extends Exception {
        public InvalidCredentialsException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
