package it.epicode.beservice.service;

import java.util.Optional;

/*
 * il service definisce un metodo che verrà usato in fase di auenticazione che verifica tramite il repository se
 * esiste lo user con quel username nel database
 */

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.User;
import it.epicode.beservice.model.UserDetailsImpl;
import it.epicode.beservice.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return UserDetailsImpl.build(user.get());
        } else {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }

}
