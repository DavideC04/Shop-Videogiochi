package org.exercise.ShopVideogiochi.security;

import org.exercise.ShopVideogiochi.model.User;
import org.exercise.ShopVideogiochi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DatabaseUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userResult = userRepository.findByEmail(username);
        
        if (userResult.isPresent()) {
            User user = userResult.get();
            return new DatabaseUserDetails(user);
        } else {
            throw new UsernameNotFoundException("Email " + username + " not found");
        }
    }
}
