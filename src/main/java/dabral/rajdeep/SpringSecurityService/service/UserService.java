package dabral.rajdeep.SpringSecurityService.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUserName(String username);
}
