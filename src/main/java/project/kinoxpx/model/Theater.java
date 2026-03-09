package project.kinoxpx.model;

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

    public Theater(){}

    public Theater(Long id, int numberOfRows, int seatsPerRow, TheaterType type) {
        this.id = id;
        this.numberOfRows = numberOfRows;
public class Theater {

    private Long id;
    private String name;
    private int rows;
    private int seatsPerRow;
    private String type;

    public Theater(Long id, String name, int rows, int seatsPerRow, String type) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public TheaterType getType() {
        return type;
    }

    public void setType(TheaterType type) {
        this.type = type;
    }
}
