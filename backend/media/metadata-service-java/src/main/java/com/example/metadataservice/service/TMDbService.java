package com.example.metadataservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class TMDbService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchMovieMetadata(String title) {
        String url = "https://api.themoviedb.org/3/search/movie?query=" + title + "&api_key=" + tmdbApiKey;

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, null, String.class);

        return response.getBody();
    }
}

