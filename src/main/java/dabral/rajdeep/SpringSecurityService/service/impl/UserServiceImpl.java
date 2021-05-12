package dabral.rajdeep.SpringSecurityService.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dabral.rajdeep.SpringSecurityService.AppUser;
import dabral.rajdeep.SpringSecurityService.service.UserService;
import dabral.rajdeep.SpringSecurityService.utility.RestUtil;
import dabral.rajdeep.SpringSecurityService.utility.UserDTO;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RestUtil restUtil;

    @Value( "${coreLogicService.getUserDetail}" )
    String getUserDetailsUrl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUserName(String username) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl( getUserDetailsUrl )
            .queryParam( "email", username );

        // TODO: 12/05/21 pass body to post call or use get call
        UserDTO userDTO = restUtil.post( new TypeReference<UserDTO>() {
        }, new Object(), uriBuilder.toUriString(), new HashMap<>() );

        if( Objects.isNull( userDTO ) ) {
            throw new UsernameNotFoundException( "User not found." );
        }

        // TODO: 12/05/21 remove password field from here as password should never be returned to user in any form
        return new AppUser( userDTO.getEmail(), passwordEncoder.encode( userDTO.getPassword() ), userDTO.getActive(),
            userDTO.getDeleted(), userDTO.getRoleList() );
    }
}

