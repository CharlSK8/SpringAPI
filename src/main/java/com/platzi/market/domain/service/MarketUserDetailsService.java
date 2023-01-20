package com.platzi.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * It returns a user with the username "charlie" and the password
 * "RSASSAPKCS1v15RSASSAPKCS1v15RSASSAPKCS1v15RSASSAPKCS1v15"
 */
@Service
public class MarketUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("charlie", "{noop}RSASSAPKCS1v15RSASSAPKCS1v15RSASSAPKCS1v15RSASSAPKCS1v15", new ArrayList<>());
    }
}
