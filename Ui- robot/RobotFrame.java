import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RobotFrame {

	private JFrame robotFrame;
	private JLabel labRobotPicStatus;
	private ImageIcon robotImageOn;
	private RobotContext robot;
	private int startRIghtPoint = 146, startLeftPoint = 235, startForwardPoint = 249, startBackdPoint = 249;
	private final int MAX_RIGHT = 637, MAX_LEFT = 0, MAX_UP = 50, MAX_DOWN = 0;
	private JProgressBar progressBa;
	private int batteryVal = 100, batteryOnCharge = 0;
	private JLabel labInfoo;

	public void showRobot() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RobotFrame window = new RobotFrame();
					window.robotFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RobotFrame() {
		initialize();
	}

	private void initialize() {
		robotFrame = new JFrame();
		progressBa = new JProgressBar();
		progressBa.setStringPainted(true);
		robot = new RobotContext();
		robot.changeState(new OnState());
		robotFrame.getContentPane().setBackground(Color.WHITE);
		robotFrame.getContentPane().setLayout(null);
		makeAction();

		progressBa.setBackground(Color.WHITE);
		progressBa.setForeground(Color.BLUE);
		progressBa.setBounds(10, 11, 172, 25);
		robotFrame.getContentPane().add(progressBa);

		labRobotPicStatus = new JLabel("");
		labRobotPicStatus.setHorizontalAlignment(SwingConstants.CENTER);
		labRobotPicStatus.setIcon(createNewImageIcon("C:\\Users\\john\\workspace\\RobotState\\images\\RobotFront.jpg"));
		labRobotPicStatus.setBounds(235, 89, 146, 249);
		robotFrame.getContentPane().add(labRobotPicStatus);

		labInfoo = new JLabel("");
		labInfoo.setFont(new Font("Times New Roman", Font.BOLD, 17));
		labInfoo.setBounds(235, 7, 183, 31);
		robotFrame.getContentPane().add(labInfoo);

		robotFrame.setTitle("Mr. Robot Sam");
		robotFrame.setResizable(false);
		robotFrame.setBounds(100, 100, 637, 467);
		robotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private ImageIcon createNewImageIcon(String path) {
		robotImageOn = new ImageIcon(path);
		Image image = robotImageOn.getImage(); // transform it
		Image newimg = image.getScaledInstance(200, 250, java.awt.Image.SCALE_FAST);
		robotImageOn = new ImageIcon(newimg); // transform it back
		return robotImageOn;
	}

	private void batteryCare() {
		if (progressBa.getValue() < 20) {
			progressBa.setForeground(Color.RED);
		}
		if (progressBa.getValue() >= 20) {
			progressBa.setForeground(Color.ORANGE);
		}
		if (progressBa.getValue() >= 60) {
			progressBa.setForeground(Color.BLUE);
		}
		batteryVal -= 10;
		progressBa.setValue(batteryVal);
	}

	private void batteryChargeMode() {
		if (robot.getTheState().equals(new ChargeState().toString())) {
			Thread t = new Thread() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException InterruptedException) {
						}
						if (progressBa.getValue() < 20) {
							progressBa.setForeground(Color.RED);
						}
						if (progressBa.getValue() >= 20) {
							progressBa.setForeground(Color.ORANGE);
						}
						if (progressBa.getValue() >= 60) {
							progressBa.setForeground(Color.BLUE);
						}
						batteryOnCharge += 10;
						progressBa.setValue(batteryOnCharge);
					}
				}
			};
			t.start();
		}
	}

	private void makeAction() {
		robotFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (robot.getTheState().equals(new OnState().toString())) {
					labRobotPicStatus.setIcon(
							createNewImageIcon("C:\\Users\\john\\workspace\\RobotState\\images\\RobotFront.jpg"));
					labInfoo.setText("John");
					labRobotPicStatus.setBounds(235, 89, 146, 249);
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						batteryCare();
						if (startRIghtPoint < MAX_RIGHT) {
							startRIghtPoint += robot.goEast();
							labRobotPicStatus.setBounds(startLeftPoint, 89, startRIghtPoint, startForwardPoint);
						} else {
							System.err.println("This is the Limit");
							startRIghtPoint = 146;
							startLeftPoint = 235;
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						batteryCare();
						if (startLeftPoint > MAX_LEFT) {
							startLeftPoint -= robot.goEast();
							labRobotPicStatus.setBounds(startLeftPoint, 89, startRIghtPoint, startForwardPoint);
						} else {
							System.err.println("This is the Limit");
							startRIghtPoint = 146;
							startLeftPoint = 235;
						}
					}

					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						batteryCare();
						if (startForwardPoint > MAX_DOWN) {
							startForwardPoint += robot.goEast();
							labRobotPicStatus.setBounds(startLeftPoint, 89, startRIghtPoint, startForwardPoint);
						} else {
							System.err.println("This is the Limit");
							startRIghtPoint = 146;
							startLeftPoint = 235;
							startForwardPoint = 249;
						}
					}
					if (e.getKeyCode() == KeyEvent.VK_O) {
						robot.changeState(new OffState());
						;
						labInfoo.setText("OFF State Mode");
					}
					if (e.getKeyCode() == KeyEvent.VK_E) {
						robot.changeState(new OnState());
						labInfoo.setText("ON State Mode");
					}

					if (progressBa.getValue() == 0) {
						robot.changeState(new ChargeState());
						labRobotPicStatus.setIcon(
								createNewImageIcon("C:\\Users\\john\\workspace\\RobotState\\images\\RobotCharged.jpg"));
						labRobotPicStatus.setBounds(235, 89, 146, 249);
						labInfoo.setText("The battery is Loading...");
						batteryChargeMode();
					}
				}
			}
		});
	}
}
