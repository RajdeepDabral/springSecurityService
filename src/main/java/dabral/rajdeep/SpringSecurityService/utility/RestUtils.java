package dabral.rajdeep.SpringSecurityService.utility;

import dabral.rajdeep.SpringSecurityService.AppUser;
import dabral.rajdeep.SpringSecurityService.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestUtils {
    @Autowired
    RestTemplate restTemplate;

    public <K,V,T> ResponseEntity<T> getExchange(String url, HttpEntity requestEntity, Class<T> responseType, Map<K,V> uriVariables) {
        return restTemplate.exchange(url,HttpMethod.POST,requestEntity,responseType, uriVariables);
    }
    public <K,V,T> ResponseEntity<T> getExchange(String url, HttpEntity requestEntity, Class<T> responseType) {
        return restTemplate.exchange(url,HttpMethod.POST,requestEntity,responseType);
    }
    public <T,K,V> ResponseEntity<T> postRequest(String url ,Class<T> responseType ,Map<K,V> uriVariables){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(requestHeaders);

        this.getExchange(url,requestEntity,responseType);
        if(genericResponse.getBody().getStatus()){
            LinkedHashMap<String,Object> hashMap = (LinkedHashMap<String, Object>) genericResponse.getBody().getData();

            hashMap.forEach((k,v)-> System.out.println( k +" : "+ v));

            return new AppUser((String)hashMap.get("email"),passwordEncoder.encode((String)hashMap.get("password")),
                    (Boolean)hashMap.get("isActive"),(Boolean)hashMap.get("isDeleted"),(List<Role>)hashMap.get("roleList"));
        }
    }

}
