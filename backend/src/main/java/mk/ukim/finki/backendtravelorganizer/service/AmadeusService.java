package mk.ukim.finki.backendtravelorganizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AmadeusService {

    @Value("${amadeus.client_id}")
    private String clientId;

    @Value("${amadeus.client_secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String accessToken;
    private long tokenExpiry = 0;

    public String getAccessToken() {
        if (accessToken != null && System.currentTimeMillis() < tokenExpiry) {
            return accessToken;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://test.api.amadeus.com/v1/security/oauth2/token", request, Map.class);

        Map body = response.getBody();
        accessToken = (String) body.get("access_token");
        Integer expiresIn = (Integer) body.get("expires_in");
        tokenExpiry = System.currentTimeMillis() + expiresIn * 1000;

        return accessToken;
    }

    public String getRandomHotels() {
        String token = getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://test.api.amadeus.com/v1/reference-data/locations/hotels/by-geocode" +
                "?latitude=48.8566&longitude=2.3522&radius=20&radiusUnit=KM";

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String responseBody = response.getBody();

            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode data = root.get("data");

            // Only take first 5
            ArrayNode limited = objectMapper.createArrayNode();
            for (int i = 0; i < Math.min(5, data.size()); i++) {
                limited.add(data.get(i));
            }

            ObjectNode result = objectMapper.createObjectNode();
            result.set("data", limited);

            return objectMapper.writeValueAsString(result);
        } catch (HttpStatusCodeException e) {
            System.err.println("Status code: " + e.getStatusCode());
            System.err.println("Response: " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse hotel data");
        }
    }
}
