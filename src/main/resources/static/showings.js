
let movies = [
    { id: 1, title: "Dune" },
    { id: 2, title: "Batman" }
]

let theaters = [
    { id: 1, name: "Sal 1" },
    { id: 2, name: "Sal 2" },
]

let showings = [
    { movieId: 1, theaterId: 2, time: "18:00" },
    { movieId: 2, theaterId: 1, time: "20:30" }
]

function loadShowings() {

    let list = document.getElementById("showingsList")
    list.innerHTML = ""

    showings.forEach(show => {

        let movie = movies.find(m => m.id == show.movieId)
        let theater = theaters.find(t => t.id == show.theaterId)

        let li = document.createElement("li")

        li.textContent = movie.title + " - " + theater.name + " - " + show.time

        list.appendChild(li)

    })

}

function createMovie() {

    let title = document.getElementById("movieTitle").value

    if (title === "") return

    let newMovie = {
        id: movies.length + 1,
        title: title
    }

    movies.push(newMovie)

    document.getElementById("movieTitle").value = ""

    loadMoviesDropdown()

}

function createShowing() {

    let movieId = document.getElementById("movieDropdown").value
    let theaterId = document.getElementById("theaterDropdown").value
    let time = document.getElementById("showingTime").value

    let newShowing = {
        movieId: movieId,
        theaterId: theaterId,
        time: time
    }

    showings.push(newShowing)

    loadShowings()

}

function loadMoviesDropdown() {

    let dropdown = document.getElementById("movieDropdown")
    dropdown.innerHTML = ""

    movies.forEach(movie => {

        let option = document.createElement("option")

        option.value = movie.id
        option.textContent = movie.title

        dropdown.appendChild(option)

    })

}

function loadTheatersDropdown() {

    let dropdown = document.getElementById("theaterDropdown")
    dropdown.innerHTML = ""

    theaters.forEach(theater => {

        let option = document.createElement("option")

        option.value = theater.id
        option.textContent = theater.name

        dropdown.appendChild(option)

    })

}

loadMoviesDropdown()
loadTheatersDropdown()
loadShowings()
