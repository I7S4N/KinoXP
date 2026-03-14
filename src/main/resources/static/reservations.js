const API_BASE = "http://localhost:8090";
const PRICE_PER_SEAT = 95;

const params = new URLSearchParams(window.location.search);
const showingId = params.get("showingId");

let selectedSeats = [];
let totalRows = 0;
let seatsPerRow = 0;

// ────────────────────────────────
// loadSeatMap
// ────────────────────────────────
async function loadSeatMap(showingId) {
    try {
        const res = await fetch(`${API_BASE}/api/showings/${showingId}/seats`);
        if (!res.ok) throw new Error("Kunne ikke hente sæder");

        const data = await res.json();

        document.getElementById("showing-title")
            .textContent = `${data.title} — ${formatDateTime(data.startTime)}`;

        totalRows = data.numberOfRows;
        seatsPerRow = data.seatsPerRow;

        renderSeatMap(data.reservedSeats);

    } catch (err) {
        document.getElementById("seat-map").innerHTML = `<p>${err.message}</p>`;
    }
}

function renderSeatMap(reservedSeats) {
    const map = document.getElementById("seat-map");
    map.innerHTML = "";

    const reservedSet = new Set(
        reservedSeats.map(s => `${s.seatRowNumber}-${s.seatNumber}`)
    );

    for (let row = 1; row <= totalRows; row++) {
        const rowEl = document.createElement("div");

        const label = document.createElement("span");
        label.textContent = `Række ${row}: `;
        rowEl.appendChild(label);

        for (let seat = 1; seat <= seatsPerRow; seat++) {
            const btn = document.createElement("button");
            btn.textContent = seat;

            if (reservedSet.has(`${row}-${seat}`)) {
                btn.disabled = true;
            } else {
                btn.onclick = () => toggleSeat(row, seat, btn);
            }

            rowEl.appendChild(btn);
        }
        map.appendChild(rowEl);
    }
}

// ────────────────────────────────
// toggleSeat
// ────────────────────────────────
function toggleSeat(row, seat, btn) {
    const key = `${row}-${seat}`;
    const idx = selectedSeats.findIndex(s => s.key === key);

    if (idx === -1) {
        selectedSeats.push({ key, seatRowNumber: row, seatNumber: seat });
        btn.style.fontWeight = "bold";
    } else {
        selectedSeats.splice(idx, 1);
        btn.style.fontWeight = "normal";
    }

    updateSummary();
}

function updateSummary() {
    const count = selectedSeats.length;
    document.getElementById("selected-count").textContent = count;
    document.getElementById("total-price").textContent = count * PRICE_PER_SEAT;
    document.getElementById("submit-btn").disabled = count === 0;
}

// ────────────────────────────────
// submitReservation
// ────────────────────────────────
async function submitReservation() {
    const customerName = document.getElementById("customerName").value.trim();
    const customerEmail = document.getElementById("customerEmail").value.trim();
    const customerPhone = document.getElementById("customerPhone").value.trim();
    const message = document.getElementById("message");

    message.textContent = "";

    if (!customerName) { message.textContent = "Indtast venligst dit navn."; return; }
    if (!customerEmail) { message.textContent = "Indtast venligst din email."; return; }
    if (!customerPhone) { message.textContent = "Indtast venligst dit telefonnummer."; return; }
    if (selectedSeats.length === 0) { message.textContent = "Vælg mindst ét sæde."; return; }

    const body = {
        showingId: parseInt(showingId),
        customerInfo: customerName,
        contactInfo: customerEmail + " / " + customerPhone,
        seats: selectedSeats.map(s => ({
            seatRowNumber: s.seatRowNumber,
            seatNumber: s.seatNumber
        }))
    };

    try {
        const res = await fetch(`${API_BASE}/api/reservations`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
        });

        if (!res.ok) {
            message.textContent = await res.text();
            return;
        }

        const data = await res.json();
        showConfirmation(data, customerName);

    } catch (err) {
        message.textContent = "Noget gik galt. Prøv igen.";
    }
}

function showConfirmation(data, customerName) {
    document.getElementById("submit-btn").style.display = "none";
    document.getElementById("confirmation").style.display = "block";
    document.getElementById("confirm-name").textContent = `Navn: ${customerName}`;
    document.getElementById("confirm-seats").textContent =
        `Sæder: ${selectedSeats.map(s => `Række ${s.seatRowNumber}, Sæde ${s.seatNumber}`).join(" | ")}`;
    document.getElementById("confirm-total").textContent = `Total: ${data.totalPrice} kr.`;
}

function formatDateTime(dateStr) {
    return new Date(dateStr).toLocaleString("da-DK", {
        weekday: "long", month: "long", day: "numeric",
        hour: "2-digit", minute: "2-digit"
    });
}

if (showingId) {
    loadSeatMap(showingId);
} else {
    document.getElementById("showing-title").textContent = "Ingen visning valgt";
}