package Models;

import java.io.Serializable;
import java.util.Scanner;

public class Drone implements Serializable {
    private int id;
    private String name;
    private int xCoordinate;
    private int yCoordinate;
    private Fire fire;

    public Drone() {}

    public Drone(int id, String name, int xCoordinate, int yCoordinate, Fire fire) {
        this.id = id;
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.fire = fire;
    }

    public Drone(Drone drone) {
        this.id = drone.id;
        this.name = drone.name;
        this.xCoordinate = drone.xCoordinate;
        this.yCoordinate = drone.yCoordinate;
        this.fire = drone.fire;
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

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    public boolean fireSpotted() {
        return fire != null;
    }

    @Override
    public String toString() {
        return "Models.Drone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", fire=" + fire +
                '}';
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
}
