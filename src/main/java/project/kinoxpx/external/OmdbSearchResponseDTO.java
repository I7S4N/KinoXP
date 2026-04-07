package project.kinoxpx.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OmdbSearchResponseDTO {

    @JsonProperty("Search")
    public List<OmdbSearchItemDTO> search;

    @JsonProperty("Response")
    public String response;

    @JsonProperty("Error")
    public String error;
}
