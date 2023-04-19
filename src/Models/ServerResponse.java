package Models;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private DroneStatus status;
    private String message;

    public ServerResponse() {
    }

    public ServerResponse(DroneStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServerResponse (ServerResponse response) {
        this.status = response.status;
        this.message = response.message;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
