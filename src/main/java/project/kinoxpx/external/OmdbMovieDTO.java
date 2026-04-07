package project.kinoxpx.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbMovieDTO {

    //Her converter vi java objekterne til JSON og omvendt.
    //Dette bruges til at kunne hente fra vores ekstern API
    @JsonProperty("Title")
    public String title;

    @JsonProperty("Year")
    public String year;

    @JsonProperty("Runtime")
    public String runtime;

    @JsonProperty("Genre")
    public String genre;

    @JsonProperty("Rated")
    public String rated;

    @JsonProperty("Error")
    public String error;

    @JsonProperty("Response")
    public String response;

    @JsonProperty("imdbID")
    public String imdbId;
}
