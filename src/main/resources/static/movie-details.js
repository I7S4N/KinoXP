const API_BASE = "http://localhost:8080";

const params = new URLSearchParams(window.location.search);
const movieId = params.get("id");

async function loadMovieDetails() {
    const errorMsg = document.getElementById("error-msg");

    if (!movieId) {
        errorMsg.textContent = "Ingen film-id angivet.";
        return;
    }

    try {
        const res = await fetch(`${API_BASE}/api/movies/${movieId}`);

        if (!res.ok) {
            throw new Error("Kunne ikke hente film detaljer");
        }

        const movie = await res.json();

        document.getElementById("movie-title").textContent = movie.title ?? "Ukendt titel";
        document.getElementById("movie-year").textContent = movie.movieYear ?? "Ukendt";
        document.getElementById("movie-duration").textContent = movie.durationMin ? `${movie.durationMin} min` : "Ukendt";
        document.getElementById("movie-rated").textContent = movie.rated && movie.rated !== "N/A" ? movie.rated : "Ikke oplyst";
        document.getElementById("movie-category").textContent = movie.category && movie.category !== "N/A" ? movie.category : "Ikke oplyst";
        document.getElementById("movie-is3d").textContent = movie.is3d ? "Ja" : "Nej";

    } catch (err) {
        errorMsg.textContent = err.message;
    }
}

loadMovieDetails();