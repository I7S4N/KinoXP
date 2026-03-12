package project.kinoxpx.dto;

public record CreateMovieRequestDTO(String title,
                                    int year,
                                    int durationMin,
                                    int ageLimit,
                                    String category,
                                    boolean is3d) {
}
