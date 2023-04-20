import Controllers.FireLocations;
import Models.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class DroneClient {
    public static void main (String args[]) {

        FireLocations fl = FireLocations.getInstance();
        Drone drone = Drone.createDrone();
        Socket s = drone.sendUpdate(null, true, null);

        final int MAX_X = 800;
        final int MAX_Y = 500;

        Thread listener = new Thread(() -> {
            //while(true){
                try {
                    ObjectInputStream in = new ObjectInputStream( s.getInputStream() );

                    DroneMessage inMessage = (DroneMessage) in.readObject();

                    Drone inDrone = inMessage.getDrone();

                    if (inMessage.getStatus() == DroneStatus.DELETE) {
                        System.out.println("Drone shut down from server");
                        System.exit(0);
                    }

                    if (inMessage.getStatus() == DroneStatus.UPDATE){
                        drone.goToLocation(inDrone.getXCoordinate(), inDrone.getYCoordinate());
                    }

                    //try {
                        ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream() );
                        DroneMessage outMessage = new DroneMessage();
                        outMessage.setStatus(DroneStatus.SUCCESS);
                        outMessage.setMessage("Drone update successful");
                        out.writeObject(outMessage);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }

//                    if (inMessage != null){
//                        Thread messageHandler = new Thread(() -> {
//
//                        });
//                        messageHandler.start();
//                    }

                } catch (IOException e) {
                    // Handle any exceptions
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            //}

        });
        listener.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                drone.sendUpdate(null, false, s);
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
                            drone.sendUpdate(fire, false, s);
                        }

                        try {
                            Thread.sleep(10);
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
                            drone.sendUpdate(fire, false, s);
                        }

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }

        }

    }
}
