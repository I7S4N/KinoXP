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

        // document.getElementById("movie-title").textContent = movie.title;
        // document.getElementById("movie-year").textContent = movie.year ?? "Ukendt";
        // document.getElementById("movie-duration").textContent = movie.durationMin ? `${movie.durationMin} min` : "Ukendt";
        // document.getElementById("movie-rated").textContent = movie.rated ?? "Ukendt";
        // document.getElementById("movie-category").textContent = movie.category ?? "Ukendt";
        // document.getElementById("movie-is3d").textContent = movie.is3d ? "Ja" : "Nej";

        document.getElementById("movie-title").textContent = movie.title;

        if (movie.year != null) {
            document.getElementById("movie-year").textContent = movie.movieYear;
        } else {
            document.getElementById("movie-year").parentElement.style.display = "none";
        }

        if (movie.durationMin != null) {
            document.getElementById("movie-duration").textContent = movie.durationMin + " min";
        } else {
            document.getElementById("movie-duration").parentElement.style.display = "none";
        }

        if (movie.rated && movie.rated !== "N/A") {
            document.getElementById("movie-rated").textContent = movie.rated;
        } else {
            document.getElementById("movie-rated").parentElement.style.display = "none";
        }

        if (movie.category && movie.category !== "N/A") {
            document.getElementById("movie-category").textContent = movie.category;
        } else {
            document.getElementById("movie-category").parentElement.style.display = "none";
        }

        document.getElementById("movie-is3d").textContent = movie.is3d ? "Ja" : "Nej";

    } catch (err) {
        errorMsg.textContent = err.message;
    }
}

loadMovieDetails();