package dabral.rajdeep.SpringSecurityService.service.impl;

import dabral.rajdeep.SpringSecurityService.AppUser;
import dabral.rajdeep.SpringSecurityService.entities.Role;
import dabral.rajdeep.SpringSecurityService.entities.User;
import dabral.rajdeep.SpringSecurityService.service.PasswordEncryptAndDecrypt;
import dabral.rajdeep.SpringSecurityService.service.UserService;
import dabral.rajdeep.SpringSecurityService.utility.GenericResponse;
import dabral.rajdeep.SpringSecurityService.utility.RestUtils;
import dabral.rajdeep.SpringSecurityService.utility.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RestUtils restUtils;

    @Value("${coreLogic.service}")
    String coreLogicService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUserName(String username) {
        ResponseEntity<UserDTO> genericResponse;
        Map<String,String> map = new HashMap<>();
        UserDTO user=  new UserDTO();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(coreLogicService + "getUserDetail")
                .queryParam("email", username);

        genericResponse = restUtils.postRequest(uriBuilder.toUriString(), UserDTO.class,map);

        if(genericResponse.getBody().getStatus()){
            LinkedHashMap<String,Object> hashMap = (LinkedHashMap<String, Object>) genericResponse.getBody().getData();

            hashMap.forEach((k,v)-> System.out.println( k +" : "+ v));

            return new AppUser((String)hashMap.get("email"),passwordEncoder.encode((String)hashMap.get("password")),
                    (Boolean)hashMap.get("isActive"),(Boolean)hashMap.get("isDeleted"),(List<Role>)hashMap.get("roleList"));
        }
        throw new UsernameNotFoundException("user not found.");
    }
}

