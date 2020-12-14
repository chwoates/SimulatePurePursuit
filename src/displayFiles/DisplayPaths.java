package displayFiles;

import displayFiles.DisplayConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static displayFiles.DisplayConstants.*;

public class DisplayPaths {
    private int xCoord = 20, yCoord = 50;
    private Path pathPoints = new Path();
    private MakePaths makePaths = new MakePaths();

    public DisplayPaths() {
    }

    public void runDisplay() {

        JFrame frame = new JFrame();
        JButton button = new JButton("Eat me");
        MyDrawPanel drawPanel = new MyDrawPanel();
        frame.getContentPane().add(drawPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.setVisible(true);
        pathPoints = makePaths.getPath();

        for(Point pts : pathPoints.getPath()){
            xCoord = (int) (pts.getX());
            yCoord = (int) (pts.getY());
            drawPanel.repaint();
            try{
                Thread.sleep(100);
            } catch(Exception ex){}
        }
    }

    public class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.black);
            g.drawLine(98,820,1500,820);
            g.setColor(Color.red);
            g.fillOval(xCoord,  yCoord, 10, 10);
        }
    }
}
