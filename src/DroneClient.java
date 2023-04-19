import Controllers.FireLocations;
import Models.Drone;
import Models.Fire;
import Models.ServerResponse;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class DroneClient {
    public static void main (String args[]) {

        FireLocations fl = FireLocations.getInstance();
        Drone drone = Drone.createDrone();
        drone.sendUpdate(null, true);

        final int MAX_X = 800;
        final int MAX_Y = 500;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                drone.sendUpdate(null, false);
            }
        }, 10000, 10000);

        while(true){
            if (drone.getXCoordinate() == 0 && drone.getYCoordinate() == 0){
                for (int i = 0; i < MAX_Y; i++) {
                    drone.setYCoordinate(i);
                    for (int j = 0; j < MAX_X; j++) {
                        drone.setXCoordinate(j);

                        if(fl.inList(drone.getXCoordinate(), drone.getYCoordinate())){
                            Fire fire = new Fire(fl.getFire(drone.getXCoordinate(), drone.getYCoordinate()));
                            drone.sendUpdate(fire, false);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } else {
                for (int i = drone.getYCoordinate(); i > 0; i--) {
                    drone.setYCoordinate(i);
                    for (int j = drone.getXCoordinate(); j > 0; j--) {
                        drone.setXCoordinate(j);

                        if(fl.inList(drone.getXCoordinate(), drone.getYCoordinate())){
                            Fire fire = new Fire(fl.getFire(drone.getXCoordinate(), drone.getYCoordinate()));
                            drone.sendUpdate(fire, false);
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }

        }

    }
}
