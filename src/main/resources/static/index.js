
const API_BASE = "http://localhost:8080";



async function loadMovies() {
    const grid = document.getElementById("movie-grid");

    try {
        const res = await fetch(`${API_BASE}/api/movies`);

        if (!res.ok) {
            throw new Error("Kunne ikke hente film");
        }

        const movies = await res.json();

        if (movies.length === 0) {
            grid.innerHTML = `<p>Ingen film oprettet endnu.</p>`;
            return;
        }

        grid.innerHTML = movies.map(movie => `
            <div class="movie-card">
                <h3>${movie.title}</h3>
                <p>${movie.is3d ? "3D-film" : "2D-film"}</p>
                <button onclick="goToMovieDetails(${movie.id})">Se detaljer</button>
            </div>
        `).join("");

    } catch (err) {
        grid.innerHTML = `<p>Fejl: ${err.message}</p>`;
    }
}


async function loadTodayShowings() {
    const grid = document.getElementById("today-grid");

    try {
        const res = await fetch(`${API_BASE}/api/showings/today`);

        if (!res.ok) throw new Error("Kunne ikke hente dagens visninger");

        const showings = await res.json();

        if (showings.length === 0) {
            grid.innerHTML = `<p>Ingen visninger i dag</p>`;
            return;
        }

        grid.innerHTML = showings.map(showing => `
            <div class="showing-card">
                <h3>${showing.movieTitle}</h3>
                <p>${formatTime(showing.startTime)}</p>
                <p>${showing.theaterType}</p>
                <button onclick="reserve(${showing.id})">Reserver billetter</button>
            </div>
        `).join("");

    } catch (err) {
        grid.innerHTML = `<p>Fejl: ${err.message}</p>`;
    }
}


async function loadShowings() {
    const grid = document.getElementById("showings-grid");

    try {
        const res = await fetch(`${API_BASE}/api/showings/upcoming`);

        if (!res.ok) throw new Error("Kunne ikke hente visninger");

        const showings = await res.json();

        if (showings.length === 0) {
            grid.innerHTML = `<p>Ingen kommende visninger</p>`;
            return;
        }

        grid.innerHTML = showings.map(showing => {
            const title = showing.title ?? showing.movieTitle ?? "Ukendt film";
            const theater = showing.type ?? showing.theaterType ?? "Ukendt sal";

            return `
                <div class="showing-card">
                    <h3>${title}</h3>
                    <p>${formatDate(showing.startTime)}</p>
                    <p>${formatTime(showing.startTime)}</p>
                    <p>${theater}</p>
                    <button onclick="reserve(${showing.id})">Reserver billetter</button>
                </div>
            `;
        }).join("");

    } catch (err) {
        console.error(err);
        grid.innerHTML = `<p>Fejl: ${err.message}</p>`;
    }
}

function goToMovieDetails(movieId) {
    window.location.href = `movie-details.html?id=${movieId}`;
}

function reserve(showingId) {
    window.location.href = `reservations.html?showingId=${showingId}`;
}

function formatDate(dateStr) {
    return new Date(dateStr).toLocaleDateString("da-DK", {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric"
    });
}

function formatTime(dateStr) {
    return new Date(dateStr).toLocaleTimeString("da-DK", {
        hour: "2-digit",
        minute: "2-digit"
    });
}

loadMovies()
loadTodayShowings();
loadShowings();