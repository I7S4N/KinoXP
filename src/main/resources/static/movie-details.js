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
            throw new Error("Kunne ikke hente filmdetaljer.");
        }

        const movie = await res.json();

        document.getElementById("movie-title").textContent = movie.title ?? "Ukendt titel";

        if (movie.movieYear != null && movie.movieYear > 0) {
            document.getElementById("movie-year").textContent = movie.movieYear;
        } else {
            document.getElementById("movie-year").textContent = "Ikke oplyst";
        }

        if (movie.durationMin != null && movie.durationMin > 0) {
            document.getElementById("movie-duration").textContent = `${movie.durationMin} min`;
        } else {
            document.getElementById("movie-duration").textContent = "Ikke oplyst";
        }

        if (movie.rated && movie.rated !== "N/A" && /\d/.test(movie.rated)) {
            document.getElementById("movie-rated").textContent = movie.rated;
        } else {
            document.getElementById("movie-rated").textContent = "Ikke oplyst";
        }

        if (movie.category && movie.category !== "N/A") {
            document.getElementById("movie-category").textContent = movie.category;
        } else {
            document.getElementById("movie-category").textContent = "Ikke oplyst";
        }

        document.getElementById("movie-is3d").textContent = movie.is3d ? "Ja" : "Nej";

    } catch (err) {
        errorMsg.textContent = err.message;
    }
}

loadMovieDetails();