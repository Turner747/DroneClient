package Models;

public class DroneMessage {
    private Drone drone;
    private Fire fire;
    private String message;

    public DroneMessage() {}

    public DroneMessage(Drone drone, Fire fire, String message) {
        this.drone = drone;
        this.fire = fire;
        this.message = message;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
