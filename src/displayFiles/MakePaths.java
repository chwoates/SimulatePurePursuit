package displayFiles;

import java.util.ArrayList;

public class MakePaths {

    Path pathPoints = new Path();

    public MakePaths(){
        for(int i=0; i<75; ++i){
            pathPoints.addPathPoint(new Point(100+20*i,800-10*i));
        }
    }

    public void fillPoints(ArrayList<Point> userPoints){

    }

    public Path getPath(){
        return pathPoints;
    }
}
