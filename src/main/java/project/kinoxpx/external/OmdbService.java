package project.kinoxpx.external;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbService {

        //Vores adgangsnøgle til OMDb API.
        //Den eksterne API som vi vælger at hente fra
        private final String API_KEY = "YOUR_API_KEY";



        //Bruger vi til at sende HTTP forespørgelser til OMDb API
        private final RestTemplate restTemplate = new RestTemplate();



        //objektet her mapper JSON data
        public OmdbMovieDTO fetchMovieByTitle(String title){


            // her bygger vi URL til OMBd API
            // Vi starter med base URL, derefter tilføjes API næglen
            // Dernæst tilføjes søgeparameter
            String url = "http://www.omdbapi.com/?apikey="
                    + API_KEY
                    + "&t="
                    + title;

            // kalder API og mapper JSON til DTO
            // Vi kalder URL vi byggede også mapper JACKSON,
            // JSON svaret automatisk til OmdbMovieDTO
            return restTemplate.getForObject(url, OmdbMovieDTO.class);

        }

}
