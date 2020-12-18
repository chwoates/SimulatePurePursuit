package displayFiles;

import displayFiles.DisplayConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static displayFiles.DisplayConstants.*;

public class DisplayPaths {

    private double xCoord = 20, yCoord = 50;
    private Path pathPoints = new Path();
    private Path userPoints = new Path();
    private Path filledPoints = new Path();
    private Path smoothedPoints = new Path();
    private MakePaths makePaths = new MakePaths();
    private boolean filled = false;
    private boolean erase = false;
    private int totalPoints = 0;
    JFrame frame = new JFrame();
    JButton button = new JButton("Eat me");
    MyDrawPanel drawPanel = new MyDrawPanel();

    public DisplayPaths() {
    }

    public void runDisplay() {

        frame.getContentPane().add(drawPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.setVisible(true);

        displayUserPoints();
        displayFilledPoints();
        delay(5000);
        smooth();
        delay(15000);
        erase = true;
        drawPanel.repaint();
        delay(2000);
        erase = false;

        for (Point pts : pathPoints.getPath()) {
            xCoord = (pts.getX());
            yCoord = (pts.getY());
            drawPanel.repaint();
            delay(50);
        }
    }

    public class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            if(erase){
                g.setColor(Color.white);
                g.fillRect(0,0,2000,1000);
            }
            else {
                g.setColor(Color.red);
                if (filled) g.setColor(Color.blue);
                g.fillOval((int) (xCoord * 15) + XOFFSET, (int) (YOFFSET - yCoord * 15), 15, 15);
            }
            }
    }

    public void displayUserPoints() {

        userPoints.addPathPoint(new Point(0, 0));
        userPoints.addPathPoint(new Point(30, 0));
        userPoints.addPathPoint(new Point(40, 43));
        userPoints.addPathPoint(new Point(70, 40));

        displayPoint(userPoints.getPath().get(0).getX(),userPoints.getPathPoint(0).getY());
        displayPoint(userPoints.getPath().get(3).getX(),userPoints.getPathPoint(3).getY());
        delay(1000);
        displayPoint(userPoints.getPath().get(1).getX(),userPoints.getPathPoint(1).getY());
        displayPoint(userPoints.getPath().get(2).getX(),userPoints.getPathPoint(2).getY());
        delay(1000);
    }

    public void displayFilledPoints() {

        int numPoints;
        filled = true;
        for (int j = 0; j < userPoints.getPath().size() - 1; ++j) {
                    Vector displacement = new Vector(userPoints.getPathPoint(j).getX(),
                    userPoints.getPathPoint(j).getY(),
                    userPoints.getPathPoint(j + 1).getX(),
                    userPoints.getPathPoint(j + 1).getY());
                numPoints = (int) (displacement.magnitude() / POINTSPACING);
                totalPoints = totalPoints + numPoints;
                System.out.println(numPoints);
                Point increment = new Point(displacement.normalize().getX() * POINTSPACING,
                    displacement.normalize().getY() * POINTSPACING);
                for (int i = 0; i < numPoints + 1; ++i) {
                    filledPoints.addPathPoint(new Point(userPoints.getPathPoint(j).getX() + i * increment.getX(),
                        userPoints.getPathPoint(j).getY() + i * increment.getY()));
            }
        }
        totalPoints = totalPoints + 3;
        System.out.println(totalPoints);
        filled = true;
        displayPath(filledPoints);
        pathPoints = filledPoints;
    }

    public void displayPoint(double xLoc, double yLoc){
        xCoord = xLoc;
        yCoord = yLoc;
        drawPanel.repaint();
        delay(100);
    }

    public void displayPath(Path path){
        for (Point pts : path.getPath()) {
            xCoord = (pts.getX());
            yCoord = (pts.getY());
            drawPanel.repaint();
            delay(50);
        }

    }

    public void smooth(){
        Vector middlePoint;
        Vector diffVec;
        Vector diffPts;
        Vector smoothedVec;
        double sumChanges = 0.0;
        double avgChange = 10.0;
        int k = 0;

        while (avgChange > 0.01) {
            ++k;
            sumChanges = 0.0;
            smoothedPoints = new Path();
            smoothedPoints.addPathPoint(new Point(pathPoints.getPathPoint(0).getX(), pathPoints.getPathPoint(0).getY()));
            for (int i = 1; i < (totalPoints - 1); ++i) {
                diffVec = new Vector(pathPoints.getPathPoint(i - 1), pathPoints.getPathPoint(i + 1));
                middlePoint = new Vector(pathPoints.getPathPoint(i - 1)).add(diffVec.scale(0.5));
                diffPts = new Vector(pathPoints.getPathPoint(i), middlePoint);
                smoothedVec = new Vector(pathPoints.getPathPoint(i)).add(diffPts.scale(SMOOTHING));
                smoothedPoints.addPathPoint(new Point(smoothedVec.getX(), smoothedVec.getY()));
                sumChanges = sumChanges + diffPts.scale(SMOOTHING).magnitude();
            }
            avgChange = sumChanges/(totalPoints-2);
            System.out.println(avgChange);
            smoothedPoints.addPathPoint(pathPoints.getPathPoint(totalPoints - 1));
            pathPoints = smoothedPoints;
            if((k % 40)== 0 ) {
                filled = false;
                if(k%80 == 0){filled=true;}
                displayPath(pathPoints);
            }
        }
        System.out.println("number of iterations: " + k);
    }

    public void createPath(){
        int k;

    }

    public void delay(int delayTime){
        try {
            Thread.sleep(delayTime);
        } catch (Exception ex) {
        }
    }
}
