package Controllers;

import Models.Fire;

import java.util.ArrayList;

public class FireLocations {
    private final ArrayList<Fire> fires;
    private static final FireLocations instance = new FireLocations();
    public static FireLocations getInstance(){
        return instance;
    }
    private FireLocations() {
        fires = new ArrayList<>();

        fires.add(new Fire(1, 45, 34, 1));
        fires.add(new Fire(2, 105, 53, 4));
        fires.add(new Fire(3, 45, 27, 4));
        fires.add(new Fire(4, 66, 11, 2));
        fires.add(new Fire(5, 23, 12, 1));
    }

    public boolean inList(int x, int y){
        for (Fire f : fires){
            if (f.getXCoordinate() == x && f.getYCoordinate() == y){
                return true;
            }
        }
        return false;
    }

    public Fire getFire(int x, int y){
        for (Fire f : fires){
            if (f.getXCoordinate() == x && f.getYCoordinate() == y){
                return f;
            }
        }
        return null;
    }
}
