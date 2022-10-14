import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) throws JsonProcessingException {
        String sensorName = "Client's sensor";

        registerSensor(sensorName);

        make1000Measurements(sensorName);
    }

    private static void registerSensor(String sensorName) {
        String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);

        makePostRequest(url, jsonData);
    }

    private static void make1000Measurements(String sensorName) {
        Random random = new Random();
        String url = "http://localhost:8080/measurements/add";

        for (int i = 1; i < 1001; i++) {
            Double value = random.nextBoolean() ? random.nextDouble() * 45 : random.nextDouble() * -45;
            Boolean raining = value > 0 && random.nextBoolean();

            Map<String, Object> jsonData = new HashMap<>();
            jsonData.put("value", value);
            jsonData.put("raining", raining);
            jsonData.put("sensor", Map.of("name", sensorName));

            System.out.println("Measurement № " + i);
            makePostRequest(url, jsonData);
        }
    }

    private static void makePostRequest(String url, Map<String, Object> jsonData) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(jsonData, headers);

        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println(response);
    }
}
