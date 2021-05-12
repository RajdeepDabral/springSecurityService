package dabral.rajdeep.SpringSecurityService.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestUtil {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public <T> T get(TypeReference<?> toValueTypeRef, String fullPath, Map<String, String> headersConfig,
        Object... uriVariables) {
        HttpEntity httpEntity = null;
        if( !CollectionUtils.isEmpty( headersConfig ) ) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType( MediaType.APPLICATION_JSON );
            headersConfig.forEach( headers::set );
            httpEntity = new HttpEntity( headers );
        }

        ResponseEntity<Object> responseEntity = this.restTemplate.exchange( fullPath, HttpMethod.GET, httpEntity,
            Object.class, uriVariables );
        return this.extractResult( toValueTypeRef, responseEntity );
    }

    public <T, R> T post(TypeReference<?> toValueTypeRef, R body, String path, Map<String, String> headersConfig,
        Object... uriVariables) {

        HttpHeaders headers = new HttpHeaders();
        if( !CollectionUtils.isEmpty( headersConfig ) ) {
            headersConfig.forEach( headers::set );
        }

        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<R> requestEntity = new HttpEntity<>( body, headers );
        ResponseEntity<Object> responseEntity = this.restTemplate.exchange( path, HttpMethod.POST, requestEntity,
            Object.class, uriVariables );
        return this.extractResult( toValueTypeRef, responseEntity );
    }

    protected <T> T extractResult(TypeReference<?> toValueTypeRef, ResponseEntity<Object> responseEntity) {
        T result = null;
        if( responseEntity.getStatusCode().is2xxSuccessful() ) {
            ResponseDTO<T> responseDTO = (ResponseDTO<T>) this.objectMapper.convertValue( responseEntity.getBody(),
                new TypeReference<ResponseDTO<T>>() {
                } );
            if( responseDTO.getStatus() ) {
                result = (T) this.objectMapper.convertValue( responseDTO.getData(), toValueTypeRef );
            }
        }

        return result;
    }
}
