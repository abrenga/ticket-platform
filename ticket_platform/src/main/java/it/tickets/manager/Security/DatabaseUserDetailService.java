package it.tickets.manager.Security;

import it.tickets.manager.Model.UserModel;
import it.tickets.manager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user=userRepository.findByUsername(username);

        if(user.isPresent()){
            return new DatabaseUserDetails(user.get());
        }else
            throw new UsernameNotFoundException(username);
    }



}
