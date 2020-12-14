package displayFiles;

import displayFiles.DisplayConstants;

import javax.swing.*;
import java.awt.*;

import static displayFiles.DisplayConstants.*;

public class DisplayPaths {
    private int xCoord = 20, yCoord = 50;
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

        for(int i=0; i<10; i++){
            xCoord = xCoord + i;
            yCoord = yCoord + i;
            drawPanel.repaint();
            try{
                Thread.sleep(100);
            } catch(Exception ex){}
        }
    }

    public class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(Color.orange);
            g.fillRect(xCoord, yCoord, 100, 100);
        }
    }
}
