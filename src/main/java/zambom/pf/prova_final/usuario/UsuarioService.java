package zambom.pf.prova_final.usuario;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {
    public ResponseEntity<ReturnUsuarioDTO> validateUser(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://184.72.80.215/usuario/validate", HttpMethod.GET, entity, ReturnUsuarioDTO.class);
    }
    
}