import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {

    int sizeX, sizeY;
    JFrame frame;
    Boolean[][] valueArray;


    Window(int x, int y, Color background, String title, Boolean[][] array){
        sizeX = x;
        sizeY = y;

        valueArray = array;
        this.setSize(x,y);
        this.setBackground(background);
        frame = new JFrame(title);
        frame.setBackground(background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(x,y);
        frame.add(this);

    }


    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                     if(valueArray[i][j]) g2d.drawLine(i,j,i,j);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

}
