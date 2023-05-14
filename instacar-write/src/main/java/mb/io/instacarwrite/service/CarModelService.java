package mb.io.instacarwrite.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class CarModelService {

    @Value("${mb-api.url}")
    protected String apiUrl;

    public String getLinkFromApi(String model){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
       try{
           ResponseEntity<String> response = restTemplate.exchange(apiUrl+"/"+model.substring(1), HttpMethod.GET,entity,String.class);
           JSONObject payload = new JSONObject(response.getBody());
           return payload.optString("url");
       }catch (Exception e){
            log.info("Error while retrieving model"+ e.getMessage());
        }
        return "";
    }
}
