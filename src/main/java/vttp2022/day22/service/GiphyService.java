package vttp2022.day22.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {
    private final static String URL = "https://api.giphy.com/v1/gifs";

    //set GIPHY_API_KEY=
    @Value("${giphy.api.key}")
    private String giphyKey;

    public List<String> getGiphs(String q, String rating, Integer limit){
        List<String> results = new LinkedList<>();
        String searchUrl = UriComponentsBuilder.fromUriString(URL)
        .path("/search")
            .queryParam("api_key",giphyKey)
            .queryParam("q",q)
            .queryParam("limit",limit)
            .queryParam("rating",rating)
            .toUriString();
            System.out.println(">>>>>>>>>SearchURL: "+ searchUrl);
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
            .get(searchUrl)
            .accept(MediaType.APPLICATION_JSON)
            .build();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
            // resp = restTemplate.getForEntity(searchUrl, String.class);
            
            // catch (Exception ex) {
            //     System.err.printf(">>>> Error: %s\n", ex.getMessage());
            //     ex.printStackTrace();
            //     return results;
            // }

            // InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        JsonReader r = Json.createReader(is);
        JsonObject o = r.readObject();
        JsonArray a = o.getJsonArray("data");
            for(int i = 0; i <a.size(); i++){
                    JsonObject jsonO = a.getJsonObject(i);
                    String result = jsonO.getJsonObject("images").getJsonObject("fixed_width").getString("url");
                    results.add(result);
                }
        return results;
            
    }

    public List<String> getGiphs(String q, String rating){
        return getGiphs(q, rating, 10);
    }

    public List<String> getGiphs(String q, Integer limit){
        return getGiphs(q, "pg", limit);
    }

    public List<String> getGiphs(String q){
        return getGiphs(q, "pg", 10);
    }


}
