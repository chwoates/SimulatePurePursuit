package displayFiles;

import displayFiles.DisplayConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static displayFiles.DisplayConstants.*;

public class DisplayPaths {

    private int xCoord = 20, yCoord = 50;
    private Path pathPoints = new Path();
    private Path userPoints = new Path();
    private Path filledPoints = new Path();
    private MakePaths makePaths = new MakePaths();
    private boolean filled = false;
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

        pathPoints = makePaths.getPath();

        for(Point pts : pathPoints.getPath()){
           // xCoord = (int) (pts.getX());
           // yCoord = (int) (pts.getY());
            //drawPanel.repaint();
            try{
                Thread.sleep(100);
            } catch(Exception ex){}
        }
    }

    public class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.drawLine(98,800,1600,800);
            g.setColor(Color.red);
            if(filled)  g.setColor(Color.blue);
            g.fillOval(xCoord*25 + XOFFSET,  YOFFSET-yCoord*15, 15, 15);
        }
    }

    public void displayUserPoints(){

        userPoints.addPathPoint(new Point(0,0));
        userPoints.addPathPoint(new Point(50,40));
        userPoints.addPathPoint(new Point(20,0));
        userPoints.addPathPoint(new Point(30,40));

        for(Point pts : userPoints.getPath()) {
            xCoord = (int) (pts.getX());
            yCoord = (int) (pts.getY());
            drawPanel.repaint();
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
        }
    }

    public void displayFilledPoints() {

        int distance = (int) (userPoints.getPath().get(2).getX() - userPoints.getPath().get(0).getX());
        int numPoints = distance / POINTSPACING;
        for (int i = 0; i < numPoints; ++i) {
            filledPoints.addPathPoint(new Point(userPoints.getPath().get(0).getX() + i*POINTSPACING,
                    userPoints.getPath().get(0).getY()));
        }
        filled = true;
        int j = 0;
        for (Point pts : filledPoints.getPath()) {
            xCoord = (int) (pts.getX());
            yCoord = (int) (pts.getY());
            if (j > 0) drawPanel.repaint();
            ++j;
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
            }
        }
        filled = false;
    }
}
