package Models;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Drone implements Serializable {
    private int id;
    private String name;
    private int xCoordinate;
    private int yCoordinate;
    private String socketId;

    public Drone() {}

    public Drone(int id, String name, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Drone(int id, String name, int xCoordinate, int yCoordinate, String socketId) {
        this.id = id;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.socketId = socketId;
    }

    public Drone(Drone drone) {
        this.id = drone.id;
        this.name = drone.name;
        this.xCoordinate = drone.xCoordinate;
        this.yCoordinate = drone.yCoordinate;
        this.socketId = drone.socketId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", socketId=" + socketId +
                '}';
    }

    public String toCSV(){
        return id + "," + name + "," + xCoordinate + "," + yCoordinate + "\n";
    }

    public static Drone createDrone(){
        Drone d = new Drone();
        Scanner in = new Scanner(System.in);

        System.out.println("Drone Creation:");

        System.out.print("Enter drone id: ");
        d.setId(in.nextInt());

        in.nextLine();
        System.out.print("Enter drone name: ");
        d.setName(in.nextLine());

        d.setXCoordinate(0);
        d.setYCoordinate(0);

        return d;
    }

    public Socket sendUpdate(Fire fire, boolean newDrone, Socket s){
        try{
            final int SERVER_PORT = 8888;
            if (s == null)
                s = new Socket("localhost", SERVER_PORT);

            ObjectInputStream in = null;
            ObjectOutputStream out = null;
            out =new ObjectOutputStream( s.getOutputStream());
            in = new ObjectInputStream( s.getInputStream());

            DroneMessage message = null;
            if (newDrone){
                message = new DroneMessage(DroneStatus.NEW,this, fire, "");
            }else{
                message = new DroneMessage(DroneStatus.UPDATE,this, fire, "");
            }
            out.writeObject(message);

            ServerResponse response = (ServerResponse) in.readObject();

            System.out.println(response.getMessage());

        }catch (UnknownHostException e){
            System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){
            System.out.println("readline:"+e.getMessage());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return s;
    }

    public void goToLocation(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
}
