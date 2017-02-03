package lifeGame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class paintGame extends JPanel {
	oPLife grid;
	public paintGame(int getMuch) {
		grid = new oPLife(getMuch);
	}
	public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Color gColor = g.getColor();
    for (int i = 0; i < grid.getMuch() ; i++) {
        for (int j = 0; j < grid.getMuch(); j++) {
            if ( grid.neighbours(i, j) == 1) {
                g.setColor(Color.blue);
                g.fillRect(i*10 , j*10, getHeight(), getWidth());
            }
            else {
            	g.setColor(Color.black);
            g.fillRect(i*10 , j*10 , getHeight(), getWidth());
        }
        }
       g.setColor(gColor);
    }
	}
}
