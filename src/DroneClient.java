import Models.Drone;
import Models.ServerResponse;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DroneClient {
    public static void main (String args[]) {

        Socket s = null;

        Drone drone = Drone.createDrone();

        // todo: move to method
        try{

            int serverPort = 8888;
            s = new Socket("localhost", serverPort);

            ObjectInputStream in = null;
            ObjectOutputStream out =null;
            out =new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream( s.getInputStream());

            out.writeObject(drone);

            ServerResponse response = (ServerResponse) in.readObject();

            System.out.println(response.message);

        }catch (UnknownHostException e){
            System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){
            System.out.println("readline:"+e.getMessage());
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if(s!=null) {
                try {
                    s.close();
                }catch (IOException e){
                    System.out.println("close:"+e.getMessage());
                }
            }
        }
    }
}
