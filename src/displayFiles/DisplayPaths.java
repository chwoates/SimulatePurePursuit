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

        for (Point pts : pathPoints.getPath()) {
            // xCoord = (int) (pts.getX());
            // yCoord = (int) (pts.getY());
            //drawPanel.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
            }
        }
    }

    public class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.drawLine(XOFFSET, YOFFSET, 1200, YOFFSET);
            g.setColor(Color.red);
            if (filled) g.setColor(Color.blue);
            g.fillOval((int) (xCoord * 15) + XOFFSET, (int) (YOFFSET - yCoord * 15), 15, 15);
        }
    }

    public void displayUserPoints() {

        userPoints.addPathPoint(new Point(0, 0));
        userPoints.addPathPoint(new Point(30, 0));
        userPoints.addPathPoint(new Point(40, 40));
        userPoints.addPathPoint(new Point(70, 40));

        //xCoord = (userPoints.getPath().get(0).getX());
        //yCoord = (userPoints.getPath().get(0).getY());
        //drawPanel.repaint();
        displayPoint(userPoints.getPath().get(0).getX(),userPoints.getPath().get(0).getY());
        displayPoint(userPoints.getPath().get(3).getX(),userPoints.getPath().get(3).getY());
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
        displayPoint(userPoints.getPath().get(1).getX(),userPoints.getPath().get(1).getY());
        displayPoint(userPoints.getPath().get(2).getX(),userPoints.getPath().get(2).getY());

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
    }

    public void displayFilledPoints() {

        int numPoints;
        filled = true;
        for (int j = 0; j < userPoints.getPath().size() - 1; ++j) {
            Vector displacement = new Vector(userPoints.getPath().get(j).getX(),
                    userPoints.getPath().get(j).getY(),
                    userPoints.getPath().get(j + 1).getX(),
                    userPoints.getPath().get(j + 1).getY());
            numPoints = (int) (displacement.magnitude() / POINTSPACING);
            System.out.println(numPoints);
            Point increment = new Point(displacement.normalize().getX() * POINTSPACING,
                    displacement.normalize().getY() * POINTSPACING);
            for (int i = 0; i < numPoints + 1; ++i) {
                filledPoints.addPathPoint(new Point(userPoints.getPath().get(j).getX() + i * increment.getX(),
                        userPoints.getPath().get(j).getY() + i * increment.getY()));
            }
        }

        filled = true;
        for (Point pts : filledPoints.getPath()) {
            xCoord = (pts.getX());
            yCoord = (pts.getY());
            drawPanel.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
            }
        }
    }

    public void displayPoint(double xLoc, double yLoc){
        xCoord = xLoc;
        yCoord = yLoc;
        drawPanel.repaint();
        try {
            Thread.sleep(100);
        } catch (Exception ex) {
        }

    }
}
