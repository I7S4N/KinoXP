package model;

import jakarta.persistence.*;

@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numberOfRows;

    @Column(nullable = false)
    private int seatsPerRow;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TheaterType type;

}
