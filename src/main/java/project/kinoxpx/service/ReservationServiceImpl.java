package project.kinoxpx.service;

import org.springframework.stereotype.Service;
import project.kinoxpx.dto.ReservationRequestDTO;
import project.kinoxpx.dto.ReservationResponseDTO;
import project.kinoxpx.dto.SeatDTO;
import project.kinoxpx.dto.SeatStatusResponseDTO;
import project.kinoxpx.exception.InvalidRequestException;
import project.kinoxpx.exception.ResourceNotFoundException;
import project.kinoxpx.exception.SeatAlreadyReservedException;
import project.kinoxpx.model.Reservation;
import project.kinoxpx.model.ReservedSeats;
import project.kinoxpx.model.Showing;
import project.kinoxpx.model.Theater;
import project.kinoxpx.repository.ReservationRepository;
import project.kinoxpx.repository.ReservedSeatRepository;
import project.kinoxpx.repository.ShowingRepository;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ShowingRepository showingRepository;
    private final ReservedSeatRepository reservedSeatRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(
            ShowingRepository showingRepository,
            ReservedSeatRepository reservedSeatRepository,
            ReservationRepository reservationRepository) {

        this.showingRepository = showingRepository;
        this.reservedSeatRepository = reservedSeatRepository;
        this.reservationRepository = reservationRepository;


        //Returnerer hvilke sæder der allerede er reserveret til en showing


    }

    @Override
    public SeatStatusResponseDTO getSeatStatusReponse(Long showingId){


        // ---------- 1. Find showing ----------
        // Hvis showingen ikke findes kaster vi en fejl
        Showing showing = showingRepository.findById(showingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Showing not found"));


        // ---------- 2. Hent theater ----------
        // Vi skal bruge antal rækker og sæder pr række
        Theater theater = showing.getTheater();


        // ---------- 3. Hent reserverede sæder ----------
        // Finder alle ReservedSeat rows der tilhører denne showing
        List<ReservedSeats> reservedSeats =
                reservedSeatRepository.findByShowingId(showingId);


        // ---------- 4. Mapper til SeatDto ----------
        // Vi sender ikke entity til klienten
        // Derfor konverterer vi til DTO
        List<SeatDTO> reservedSeatDtos = reservedSeats.stream()
                .map(seat -> new SeatDTO(
                        seat.getSeatRowNumber(),
                        seat.getSeatNumber()
                ))
                .toList();


        // ---------- 5. Returner seat status ----------
        return new SeatStatusResponseDTO(
                theater.getNumberOfRows(),
                theater.getSeatsPerRow(),
                reservedSeatDtos
        );
    }


    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO req) {

        // ---------- 1. Input validering ----------

        // Der skal vælges mindst ét sæde
        if (req.seats() == null || req.seats().isEmpty()) {
            throw new InvalidRequestException("At least one seat must be selected");
        }

        // Kunde navn må ikke være tomt
        if (req.customerName() == null || req.customerName().isBlank()) {
            throw new InvalidRequestException("Customer name is required");
        }

        // Kontakt information må ikke være tom
        if (req.contactInfo() == null || req.contactInfo().isBlank()) {
            throw new InvalidRequestException("Contact info is required");
        }


        // ---------- 2. Find showing ----------
        Showing showing = showingRepository.findById(req.showingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Showing not found"));


        // ---------- 3. Hent allerede reserverede sæder ----------
        // Vi bruger disse til at checke dobbeltbooking
        List<ReservedSeats> reservedSeats =
                reservedSeatRepository.findByShowingId(req.showingId());


        // ---------- 4. Dobbeltbooking check ----------
        // Vi sammenligner requested seats med seats der allerede er reserveret
        for (SeatDTO requestedSeat : req.seats()) {

            for (ReservedSeats reservedSeat : reservedSeats) {

                // Hvis række og sæde matcher betyder det
                // at sædet allerede er reserveret
                if (reservedSeat.getSeatRowNumber() == requestedSeat.rowNumber()
                        && reservedSeat.getSeatNumber() == requestedSeat.seatNumber()) {

                    throw new SeatAlreadyReservedException(
                            "Seat already reserved: row "
                                    + requestedSeat.rowNumber()
                                    + " seat "
                                    + requestedSeat.seatNumber()
                    );
                }
            }
        }


        // ---------- 5. Beregn pris ----------
        // For simpelt system bruger vi fast pris
        int pricePerSeat = 100;

        int totalPrice = req.seats().size() * pricePerSeat;


        // ---------- 6. Opret reservation ----------
        Reservation reservation = new Reservation();

        reservation.setShowing(showing);
        reservation.setCustomerName(req.customerName());
        reservation.setContactInfo(req.contactInfo());
        reservation.setTotalPrice(totalPrice);

        // Gem reservation i databasen
        reservation = reservationRepository.save(reservation);


        // ---------- 7. Gem reserverede sæder ----------
        // For hvert seat i request opretter vi en ReservedSeat række
        for (SeatDTO seat : req.seats()) {

            ReservedSeats reservedSeat = new ReservedSeats();

            reservedSeat.setReservation(reservation);
            reservedSeat.setShowing(showing);
            reservedSeat.setSeatRowNumber(seat.rowNumber());
            reservedSeat.setSeatNumber(seat.seatNumber());

            reservedSeatRepository.save(reservedSeat);
        }


        // ---------- 8. Returner response ----------
        return new ReservationResponseDTO(
                reservation.getId(),
                totalPrice,
                "Reservation created successfully"
        );
    }
}


