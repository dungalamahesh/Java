package lifeGame;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class oPTest {
	public static void main(String args[]) {

		
		String getM = JOptionPane.showInputDialog(null, "Enter Life Matrix (NxN):", "getMatrix values@",
				JOptionPane.QUESTION_MESSAGE);
		int mi2Add = Integer.parseInt(getM);
		oPLife gameO = new oPLife(mi2Add);
		JFrame application = new JFrame();
		paintGame paintSQ = new paintGame(mi2Add);

		do {
			gameO.DailogConfirm();
		} while (true);

	}

}
