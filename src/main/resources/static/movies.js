const API_BASE = "http://localhost:8080";

async function createMovie() {
    const titleInput = document.getElementById("movieTitle");
    const is3dInput = document.getElementById("is3d");
    const message = document.getElementById("message");

    const title = titleInput.value.trim();
    const is3d = is3dInput.checked;

    message.textContent = "";

    if (!title) {
        message.textContent = "Indtast en filmtitel.";
        return;
    }

    const body = {
        title: title,
        is3d: is3d
    };

    try {
        const res = await fetch(`${API_BASE}/api/movies`, {
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
        titleInput.value = "";
        is3dInput.checked = false;

        await loadMovies();

    } catch (err) {
        console.error(err);
        message.textContent = "Serverfejl. Prøv igen.";
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