package com.mycompany.service;

import com.mycompany.dao.inter.UserRepository;
import com.mycompany.entity.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserTable userTable =  userRepository.findByEmail(email);

        if (userTable != null) {
            User.UserBuilder builder = User.withUsername(email);

            builder.disabled(false);
            builder.password(userTable.getPassword());
            //we can get user groups from the DB assign it to array like so here
            //String[] authArr = new String[]{userTable.getGroup().toArray()}
            String[] authoritiesArr = new String[]{ "ADMIN", "USER"/*, "ROLE_USER"*/};
            builder.authorities(authoritiesArr);

            return builder.build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
