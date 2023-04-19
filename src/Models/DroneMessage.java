package Models;

import java.io.Serializable;

public class DroneMessage implements Serializable {

    private DroneStatus status;
    private Drone drone;
    private Fire fire;
    private String message;

    public DroneMessage() {}

    public DroneMessage(DroneStatus status, Drone drone, Fire fire, String message) {
        this.status = status;
        this.drone = drone;
        this.fire = fire;
        this.message = message;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
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
