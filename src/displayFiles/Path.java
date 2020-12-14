package displayFiles;

import java.util.ArrayList;

public class Path {

    ArrayList<Point> pathPoints = new ArrayList<Point>();

    public Path(){}

    public void addPathPoint(Point newPoint){
        pathPoints.add(newPoint);
    }

    public ArrayList<Point> getPath(){
        return pathPoints;
    }
}
