package com.mickaelbrenoit.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mickaelbrenoit.business.model.User;
import com.mickaelbrenoit.business.model.Role;
import com.mickaelbrenoit.business.service.UserService;

//TODO enable Users in DB
@Service("securityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {
    
	@Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User loggedInUser = userService.findByLogin(login);
        if (loggedInUser == null) {
            throw new UsernameNotFoundException(login);
        }
        
        List<GrantedAuthority> authorities = getAuthorities(loggedInUser);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new CustomUserDetails(
        		loggedInUser.getLogin(),
        		loggedInUser.getPassword(),
        		loggedInUser.getFirstName(),
        		loggedInUser.getLastName(),
        		loggedInUser.getEmail(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
    }
    
    private static List<GrantedAuthority> getAuthorities(User user) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        final Role role = user.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

}
