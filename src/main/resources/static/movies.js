const API_BASE = "http://localhost:8080";

async function searchMovies() {
    const titleInput = document.getElementById("movieTitle");
    const message = document.getElementById("message");
    const searchResults = document.getElementById("searchResults");

    const title = titleInput.value.trim();

    message.textContent = "";
    searchResults.innerHTML = "";

    if (!title) {
        message.textContent = "Indtast en filmtitel.";
        message.style.color = "red";
        return;
    }

    try {
        const res = await fetch(`${API_BASE}/api/movies/search?title=${encodeURIComponent(title)}`);

        if (!res.ok) {
            const errorText = await res.text();
            message.textContent = "Kunne ikke finde film: " + errorText;
            message.style.color = "red";
            return;
        }

        const movies = await res.json();

        if (movies.length === 0) {
            message.textContent = "Ingen film fundet.";
            message.style.color = "red";
            return;
        }

        searchResults.innerHTML = movies.map(movie => `
            <div class="movie-card">
                <h3>${movie.title}</h3>
                <p>År: ${movie.year ?? "Ikke oplyst"}</p>
                <p>Type: ${movie.type ?? "Ukendt"}</p>
                <button onclick="selectMovie('${movie.imdbId}')">Tilføj film</button>
            </div>
        `).join("");

    } catch (err) {
        console.error(err);
        message.textContent = "Serverfejl. Prøv igen.";
        message.style.color = "red";
    }
}

async function selectMovie(imdbId) {
    const is3dInput = document.getElementById("is3d");
    const message = document.getElementById("message");
    const searchResults = document.getElementById("searchResults");
    const titleInput = document.getElementById("movieTitle");

    const body = {
        imdbId: imdbId,
        is3d: is3dInput.checked
    };

    try {
        const res = await fetch(`${API_BASE}/api/movies/from-imdb`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        });

        if (!res.ok) {
            const errorText = await res.text();
            message.textContent = "Kunne ikke oprette film: " + errorText;
            message.style.color = "red";
            return;
        }

        const movie = await res.json();

        message.textContent = `Film oprettet: ${movie.title}`;
        message.style.color = "lightgreen";

        searchResults.innerHTML = "";
        titleInput.value = "";
        is3dInput.checked = false;

        await loadMovies();

    } catch (err) {
        console.error(err);
        message.textContent = "Serverfejl. Prøv igen.";
        message.style.color = "red";
    }
}

async function loadMovies() {
    const movieList = document.getElementById("movieList");
    movieList.innerHTML = "";

    try {
        const res = await fetch(`${API_BASE}/api/movies`);

        if (!res.ok) {
            movieList.innerHTML = "<li>Kunne ikke hente film.</li>";
            return;
        }

        const movies = await res.json();

        if (movies.length === 0) {
            movieList.innerHTML = "<li>Ingen film oprettet endnu.</li>";
            return;
        }

        movies.forEach(movie => {
            const li = document.createElement("li");
            li.innerHTML = `
                <strong>${movie.title}</strong>
                ${movie.is3d ? "(3D)" : ""}
                <button onclick="showMovieDetails(${movie.id})">Vis detaljer</button>
            `;
            movieList.appendChild(li);
        });

    } catch (err) {
        console.error(err);
        movieList.innerHTML = "<li>Fejl ved hentning af film.</li>";
    }
}

function showMovieDetails(movieId) {
    window.location.href = `/movie-details.html?id=${movieId}`;
}

loadMovies();