package com.tfworkers.PDSISystem.Security;


import com.tfworkers.PDSISystem.Repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      com.tfworkers.PDSISystem.Model.Entity.User username1 =  userRepository.findByUsername(username);
        if (username1 == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(username1.getUsername(), username1.getPassword(), emptyList());
    }
}