package dabral.rajdeep.SpringSecurityService;


import dabral.rajdeep.SpringSecurityService.entities.Role;
import dabral.rajdeep.SpringSecurityService.entities.User;
import dabral.rajdeep.SpringSecurityService.utility.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AppUser implements UserDetails {

    private String username;
    private String password;
    private Boolean isActive;
    private Boolean isDeleted;
    List<GrantAuthorityImpl> grantAuthorities;

    public AppUser(String username, String password, Boolean isActive, Boolean isDeleted, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.grantAuthorities = roleList.stream().map(role -> new GrantAuthorityImpl(role.getAuthority()))
                .collect(Collectors.toList());
    }

    public AppUser(UserDTO user){
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.isActive = user.getActive();
        this.isDeleted = user.getDeleted();
        this.grantAuthorities = user.getRoleList().stream()
                .map(role -> new GrantAuthorityImpl(role.getAuthority()))
                .collect(Collectors.toList());
    }
    public AppUser(){
        this.username=null;
        this.password=null;
        this.isDeleted=null;
        this.isActive=null;
        this.grantAuthorities=null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isDeleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
