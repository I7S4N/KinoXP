package project.kinoxpx.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbService {

    private final String API_KEY = "b25ef649";
    private final RestTemplate restTemplate = new RestTemplate();

    public OmdbMovieDTO fetchMovieByTitle(String title) {
        String url = UriComponentsBuilder
                .fromUriString("https://www.omdbapi.com/")
                .queryParam("apikey", API_KEY)
                .queryParam("t", title)
                .toUriString();

        return restTemplate.getForObject(url, OmdbMovieDTO.class);
    }

    public OmdbSearchResponseDTO searchMovies(String title) {
        String url = UriComponentsBuilder
                .fromUriString("https://www.omdbapi.com/")
                .queryParam("apikey", API_KEY)
                .queryParam("s", title)
                .toUriString();

        return restTemplate.getForObject(url, OmdbSearchResponseDTO.class);
    }

    public OmdbMovieDTO fetchMovieByImdbId(String imdbId) {
        String url = UriComponentsBuilder
                .fromUriString("https://www.omdbapi.com/")
                .queryParam("apikey", API_KEY)
                .queryParam("i", imdbId)
                .toUriString();

        return restTemplate.getForObject(url, OmdbMovieDTO.class);
    }
}