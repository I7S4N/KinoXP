package project.kinoxpx.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OmdbSearchItemDTO {

    @JsonProperty("Title")
    public String title;

    @JsonProperty("Year")
    public String year;

    @JsonProperty("imdbID")
    public String imdbId;

    @JsonProperty("Type")
    public String type;
}
