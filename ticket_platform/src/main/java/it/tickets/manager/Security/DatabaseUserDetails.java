/*package it.tickets.manager.Security;

import it.tickets.manager.Model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import it.tickets.manager.Model.Role;

public class DatabaseUserDetails implements UserDetails
{
    private final Integer id;
    private final String username;
    private final String password;
    private final Set< GrantedAuthority> authorities;

    public DatabaseUserDetails(UserModel user){
        this.id=user.getId();
        this.username=getUsername();
        this.password=getPassword();

        authorities=new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

    }


    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}*/
