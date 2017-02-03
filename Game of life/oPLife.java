package lifeGame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class oPLife {
	private boolean thisGame[][];
	private boolean newLife[][];
	private final int ZERO = 0;
	private int getMuch;
	private boolean generation;
	public oPLife(int x) { 
		/** Contractor check if no zero **/
		if (x > ZERO) {
			setMuch(x);
			thisGame = new boolean[getMuch][getMuch];
			getRan();
		}
	}

	public void setMuch(int lo) {
		/** Set the lo in index **/
		if (lo > ZERO)
			this.getMuch = lo;
	}

	public int getMuch() {
		return this.getMuch;
	}

	public void getRan() { /** Make randomize **/
		Random rmd = new Random();
		for (int x = 0; x < getMuch; x++)
			for (int y = 0; y < getMuch; y++)
				thisGame[x][y] = rmd.nextBoolean();
	}

	private void clear() { /** Clear the table **/
		for (int x = 0; x < getMuch; x++)
			for (int y = 0; y < getMuch; y++)
				this.thisGame = new boolean[x][y];
	}

	private boolean checkStatus(int x, int y) {
		if (x > 0 && y > 0)
			return true;
		return false;
	}
	/** Check neighbor Life **/
	public int neighbours(int x, int y) {
		int neighbours = 0;

		if (x >= 1 && y >= 1 && x < getMuch() - 1 && y < getMuch() - 1) {
			if (thisGame[x][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x][y - 1] == true) {
				neighbours++;
				
			}
			if (thisGame[x + 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y + 1] == true) {
				neighbours++;
			}
		} else if (x == 0 && y == 0) {
			if (thisGame[x][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y + 1] == true) {
				neighbours++;
			}
		} else if (x == 0 && y >= 1 && y < getMuch() - 1 && x < getMuch() - 1) {
			if (thisGame[x][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y - 1] == true) {
				neighbours++;
			}
		} else if (x >= 1 && x < getMuch() - 1 && y == 0 && y < getMuch() - 1) {
			if (thisGame[x][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y + 1] == true) {
				neighbours++;
			}
		} else if (x == getMuch() && y >= 1 && y < getMuch() - 1) {
			if (thisGame[x][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y + 1] == true) {
				neighbours++;
			}
		} else if (x >= 1 && x < getMuch() - 1 && y == getMuch()) {
			if (thisGame[x][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y - 1] == true) {
				neighbours++;
			}
		} else if (x == 0 && y == getMuch()) {
			if (thisGame[x][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x + 1][y - 1] == true) {
				neighbours++;
			}
		} else if (x == getMuch() && y == 0) {
			if (thisGame[x - 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x][y + 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y + 1] == true) {
				neighbours++;
			}
		} else if (x == getMuch() && y == getMuch()) {
			if (thisGame[x][y - 1] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y] == true) {
				neighbours++;
			}
			if (thisGame[x - 1][y - 1] == true) {
				neighbours++;
			}
		}
		return neighbours;
	}

	public void lifeGenerator() {
		
		boolean[][] old, New;
		int n;
		for (int cols = 0; cols < getMuch() ; cols++) {
			for (int rows = 0; rows < getMuch(); rows++) {
				n = neighbours(cols, rows);
				old = thisGame;
				if (thisGame[cols][rows] == true) {
					if (n < 2) {
						generation = false;
					} else if (n > 3) {
						generation = false;
					} else if (n == 2) {
						generation = false;
					}
				} else {
					if (n == 3) {
						generation = true;
					} else {
						generation = false;
					}
				}
				if (generation == true) {
					New = old;
					New[cols][rows] = true;
					newLife = New;
				} else {
					New = old;
					New[cols][rows] = false;
					newLife = New;
				}
			}
		}

	}

	public int DailogConfirm() {
		int j;
		int n = JOptionPane.showConfirmDialog(null, "Do you like to begin?", "@Yes/ no situation",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (n == JOptionPane.NO_OPTION) {
			j = 1;
			System.exit(0);
		} else if (n == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "The game is beginning!");
			lifeGenerator();
			//printNewLife();
			JFrame application = new JFrame();
			JLabel southLabel = new JLabel( );
			southLabel.setText( "On run!" );
			paintGame paintSQ = new paintGame(getMuch);
			application.setVisible(true);
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			application.add(paintSQ);
			application.add( southLabel, BorderLayout.SOUTH );
			application.setSize(500, 500);
			j = 2;
		} else {
			j = 3;
			JOptionPane.showMessageDialog(null, "GOODBYE");
			System.exit(0);
		}
		return j;
	}

	public void printOldLife() {
		// String s = "";
		for (int i = 0; i < getMuch; i++) {
			for (int j = 0; j < getMuch; j++) {
				if (newLife[i][j])
					System.out.printf(" * ", thisGame[i][j]);
				else
					System.out.printf("  ", thisGame[i][j]);

			}
			System.out.printf("\n");
		}

	}

	public void printNewLife() {
		// String s = "";
		for (int i = 0; i < newLife.length; i++) {
			for (int j = 0; j < newLife[i].length; j++) {
				if (newLife[i][j])
					System.out.printf(" + ", newLife[i][j]);
				else
					System.out.printf(" - "
							+ " ", newLife[i][j]);
			}
			System.out.printf("\n");
		}
	}

}
