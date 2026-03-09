package project.kinoxpx.model;

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

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public String getType() {
        return type;
    }
}
