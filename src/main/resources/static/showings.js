//
// let movies = [
//     { id: 1, title: "Dune" },
//     { id: 2, title: "Batman" }
// ]
//
// let theaters = [
//     { id: 1, name: "Sal 1" },
//     { id: 2, name: "Sal 2" },
// ]
//
// let showings = [
//     { movieId: 1, theaterId: 2, time: "18:00" },
//     { movieId: 2, theaterId: 1, time: "20:30" }
// ]
//
// function loadShowings() {
//
//     let list = document.getElementById("showingsList")
//     list.innerHTML = ""
//
//     showings.forEach(show => {
//
//         let movie = movies.find(m => m.id == show.movieId)
//         let theater = theaters.find(t => t.id == show.theaterId)
//
//         let li = document.createElement("li")
//
//         li.textContent = movie.title + " - " + theater.name + " - " + show.time
//
//         list.appendChild(li)
//
//     })
//
// }
//
// function createMovie() {
//
//     let title = document.getElementById("movieTitle").value
//
//     if (title === "") return
//
//     let newMovie = {
//         id: movies.length + 1,
//         title: title
//     }
//
//     movies.push(newMovie)
//
//     document.getElementById("movieTitle").value = ""
//
//     loadMoviesDropdown()
//
// }
//
// function createShowing() {
//
//     if (!movieId || !theaterId || !time) return;
//
//     let movieId = document.getElementById("movieDropdown").value
//     let theaterId = document.getElementById("theaterDropdown").value
//     let time = document.getElementById("showingTime").value
//
//     let newShowing = {
//         movieId: movieId,
//         theaterId: theaterId,
//         time: time
//     }
//
//     showings.push(newShowing)
//
//     loadShowings()
//
// }
//
// function loadMoviesDropdown() {
//
//     let dropdown = document.getElementById("movieDropdown")
//     dropdown.innerHTML = ""
//
//     movies.forEach(movie => {
//
//         let option = document.createElement("option")
//
//         option.value = movie.id
//         option.textContent = movie.title
//
//         dropdown.appendChild(option)
//
//     })
//
// }
//
// function loadTheatersDropdown() {
//
//     let dropdown = document.getElementById("theaterDropdown")
//     dropdown.innerHTML = ""
//
//     theaters.forEach(theater => {
//
//         let option = document.createElement("option")
//
//         option.value = theater.id
//         option.textContent = theater.name
//
//         dropdown.appendChild(option)
//
//     })
//
// }
//
// loadMoviesDropdown()
// loadTheatersDropdown()
// loadShowings()

const API_BASE = "http://localhost:8080";

async function loadMoviesDropdown() {
    const dropdown = document.getElementById("movieDropdown");
    dropdown.innerHTML = "";

    try {
        const res = await fetch("http://localhost:8080/api/movies");

        if (!res.ok) {
            throw new Error("Kunne ikke hente film");
        }

        const movies = await res.json();

        dropdown.innerHTML = `<option value="">Vælg film</option>`;

        movies.forEach(movie => {
            const option = document.createElement("option");
            option.value = movie.id;
            option.textContent = movie.title;
            dropdown.appendChild(option);
        });

    } catch (err) {
        dropdown.innerHTML = `<option value="">Fejl ved hentning af film</option>`;
    }
}

async function loadTheatersDropdown() {
    const dropdown = document.getElementById("theaterDropdown");
    dropdown.innerHTML = "";

    try {
        const res = await fetch("http://localhost:8080/api/theaters");

        if (!res.ok) {
            throw new Error("Kunne ikke hente sale");
        }

        const theaters = await res.json();

        dropdown.innerHTML = `<option value="">Vælg sal</option>`;

        theaters.forEach(theater => {
            const option = document.createElement("option");
            option.value = theater.id;

            option.textContent =
                theater.type?.displayName ||
                theater.type ||
                `Sal ${theater.id}`;

            dropdown.appendChild(option);
        });

    } catch (err) {
        dropdown.innerHTML = `<option value="">Fejl ved hentning af sale</option>`;
    }
}

async function createShowing() {
    const movieId = document.getElementById("movieDropdown").value;
    const theaterId = document.getElementById("theaterDropdown").value;
    const startTime = document.getElementById("showingTime").value;
    const message = document.getElementById("message");

    message.textContent = "";

    if (!movieId) {
        message.textContent = "Vælg en film.";
        return;
    }

    if (!theaterId) {
        message.textContent = "Vælg en sal.";
        return;
    }

    if (!startTime) {
        message.textContent = "Vælg dato og tidspunkt.";
        return;
    }

    const body = {
        movieId: Number(movieId),
        theaterId: Number(theaterId),
        startTime: startTime
    };

    try {
        const res = await fetch(`${API_BASE}/api/showings`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        });

        if (!res.ok) {
            const errorText = await res.text();
            message.textContent = "Kunne ikke oprette showing: " + errorText;
            return;
        }

        message.textContent = "Showing oprettet.";
        document.getElementById("showingTime").value = "";

        await loadShowings();

    } catch (err) {
        console.error(err);
        message.textContent = "Serverfejl. Prøv igen.";
    }
}

async function loadShowings() {
    const list = document.getElementById("showingsList");
    list.innerHTML = "";

    try {
        const res = await fetch(`${API_BASE}/api/showings`);

        if (!res.ok) {
            throw new Error("Kunne ikke hente showings");
        }

        const showings = await res.json();

        if (showings.length === 0) {
            list.innerHTML = "<li>Ingen showings fundet.</li>";
            return;
        }

        showings.forEach(showing => {
            const li = document.createElement("li");

            const title = showing.movieTitle ?? showing.title ?? "Ukendt film";
            const theater = showing.theaterType ?? showing.type ?? "Ukendt sal";
            const time = showing.startTime ? formatDateTime(showing.startTime) : "Ukendt tidspunkt";

            li.textContent = `${title} - ${theater} - ${time}`;
            list.appendChild(li);
        });

    } catch (err) {
        console.error(err);
        list.innerHTML = `<li>Fejl ved hentning af showings: ${err.message}</li>`;
    }
}

function formatDateTime(dateStr) {
    return new Date(dateStr).toLocaleString("da-DK", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit"
    });
}

async function init() {
    await loadMoviesDropdown();
    await loadTheatersDropdown();
    await loadShowings();
}

init();