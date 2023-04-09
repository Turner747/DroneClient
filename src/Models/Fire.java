package Models;

public class Fire {
    private int id;
    private int xCoordinate;
    private int yCoordinate;
    private int severity;

    public Fire() {}

    public Fire(int id, int xCoordinate, int yCoordinate, int severity) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.severity = severity;
    }

    public Fire(Fire fire){
        this.id = fire.id;
        this.xCoordinate = fire.xCoordinate;
        this.yCoordinate = fire.yCoordinate;
        this.severity = fire.severity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "Fire{" +
                "id=" + id +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", severity=" + severity +
                '}';
    }
}
