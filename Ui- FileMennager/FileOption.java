import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class FileOption {

	private JFrame frmFilemennager;
	private JTextField txtChangeDiractionOf;
	private JTextField txtDefultbackupfiles;
	private FileOptionUse fileOption = new FileOptionUse();
	private buildFileDiraction buildDiraction = new buildFileDiraction();
	private File[] backupFilesShowNames;
	private File[] inUseFiles;
	private String changeDiraction;
	private JList Olist;
	private JList backupList;
	private JFileChooser fileChooser;
	private JPopupMenu popupMenu;
	
	/** Launch the application **/
	public void onTheFrame(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileOption window = new FileOption();
					window.frmFilemennager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**Create the application*/
	public FileOption() {
		initialize();
	}
	
	/**Initialize the contents of the frame**/
	private void initialize() { 
		frmFilemennager = new JFrame();
		frmFilemennager.setResizable(false);
		frmFilemennager.setType(Type.POPUP);
		frmFilemennager.setTitle("FileManager");
		frmFilemennager.getContentPane().setBackground(new Color(0, 153, 255));
		frmFilemennager.setBounds(100, 100, 519, 486);
		frmFilemennager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFilemennager.getContentPane().setLayout(null);
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					fileOption.backupFile(Olist.getSelectedValue().toString(), txtChangeDiractionOf.getText()); //backUp file
				}catch(NullPointerException nullPointerException){
					JOptionPane.showMessageDialog(null, "You can't choose from this List\n");
				}
				backupList.setModel(fileOption.createList());
			}
		});
		btnBackup.setForeground(new Color(255, 0, 0));
		btnBackup.setFont(new Font("DengXian", Font.BOLD, 12));
		btnBackup.setToolTipText("Backup your files");
		btnBackup.setBounds(401, 379, 83, 34);
		frmFilemennager.getContentPane().add(btnBackup);
		
		JButton btnRestore = new JButton("Restore");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					buildDiraction.restore(backupList.getSelectedValue().toString());
				}catch(NullPointerException nullPointerException){
					JOptionPane.showMessageDialog(null, "You can't choose from this List\n");
				}
				Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
			}
		});
		btnRestore.setForeground(new Color(255, 0, 0));
		btnRestore.setToolTipText("Restore from backup");
		btnRestore.setFont(new Font("DengXian", Font.BOLD, 12));
		btnRestore.setBounds(308, 379, 83, 34);
		frmFilemennager.getContentPane().add(btnRestore);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					buildDiraction.deleteElement(Olist.getSelectedValue().toString());
					JOptionPane.showMessageDialog(null, "This file is deleted\n");
				}catch(NullPointerException nullPointerException){
					JOptionPane.showMessageDialog(null, "You can't choose from this List\n");
				}
				Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setToolTipText("Delete File");
		btnDelete.setFont(new Font("DengXian", Font.BOLD, 12));
		btnDelete.setBounds(211, 379, 83, 34);
		frmFilemennager.getContentPane().add(btnDelete);
		
		JButton btnAddFile = new JButton("Add File");
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				File fileChoosed = null; //Local VAR that save the File chooses
				 int returnVal = fileChooser.showOpenDialog(null);
                 if(returnVal == JFileChooser.APPROVE_OPTION){
                	 fileChoosed = fileChooser.getSelectedFile();
                 }
                 try{
                	 buildDiraction.addChoosedFile(fileChoosed);
                	 Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
                 }catch(NullPointerException nullPointerException){
                	 JOptionPane.showMessageDialog(null, "You didn't choose any file"); 
                 }
			}
		});
		btnAddFile.setForeground(new Color(255, 0, 0));
		
		btnAddFile.setToolTipText("Add new File from diraction");
		btnAddFile.setFont(new Font("DengXian", Font.BOLD, 12));
		btnAddFile.setBounds(114, 379, 87, 34);
		frmFilemennager.getContentPane().add(btnAddFile);
		
		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					buildDiraction.renameThisFile(Olist.getSelectedValue().toString());
				}catch(NullPointerException nullPointerException){
					JOptionPane.showMessageDialog(null, "You can't rename Backup files name"); 
				}
				Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
			}
		});
		btnRename.setForeground(new Color(255, 0, 0));
		btnRename.setToolTipText("Rename from list of files");
		btnRename.setFont(new Font("DengXian", Font.BOLD, 11));
		btnRename.setBounds(21, 379, 83, 34);
		frmFilemennager.getContentPane().add(btnRename);
		
		txtChangeDiractionOf = new JTextField();
		txtChangeDiractionOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
			}
		});
		txtChangeDiractionOf.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtChangeDiractionOf.setText("Change Diraction of src");
		txtChangeDiractionOf.setBounds(21, 51, 213, 26);
		frmFilemennager.getContentPane().add(txtChangeDiractionOf);
		txtChangeDiractionOf.setColumns(10);
		
		txtDefultbackupfiles = new JTextField();
		txtDefultbackupfiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backupList.setModel(fileOption.createList());
			}
		});
		txtDefultbackupfiles.setForeground(Color.RED);
		txtDefultbackupfiles.setText("Defult://backupfiles");
		txtDefultbackupfiles.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		txtDefultbackupfiles.setColumns(10);
		txtDefultbackupfiles.setBounds(267, 51, 213, 26);
		frmFilemennager.getContentPane().add(txtDefultbackupfiles);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("File Manager");
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setForeground(new Color(128, 0, 0));
		lblNewJgoodiesTitle.setFont(new Font("Bodoni MT Condensed", Font.PLAIN, 33));
		lblNewJgoodiesTitle.setBounds(137, 5, 213, 40);
		frmFilemennager.getContentPane().add(lblNewJgoodiesTitle);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("By John#3 Yaghobieh");
		lblNewJgoodiesLabel.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 12));
		lblNewJgoodiesLabel.setForeground(new Color(128, 0, 0));
		lblNewJgoodiesLabel.setBounds(10, 432, 131, 14);
		frmFilemennager.getContentPane().add(lblNewJgoodiesLabel);
		
		JButton btnEdit = new JButton();
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					buildDiraction.writeIntoFile(Olist.getSelectedValue().toString());
				} catch (IOException iOException) {
					 JOptionPane.showMessageDialog(null, "Please try again\n" +iOException.getMessage());
				} catch (NullPointerException nullPointerException) {
					 JOptionPane.showMessageDialog(null, "You can edit to backup- list\n");
				}
			}
		});
		btnEdit.setIcon(new ImageIcon("bin\\btnEdit.png"));
		btnEdit.setToolTipText("Edit from Source list");
		btnEdit.setBounds(21, 5, 56, 40);
		frmFilemennager.getContentPane().add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 88, 213, 280);
		frmFilemennager.getContentPane().add(scrollPane);
		
		Olist = new JList();
		scrollPane.setViewportView(Olist);
		Olist.setModel(buildDiraction.starterDiraction());
		Olist.setVisibleRowCount(10);
		
		popupMenu = new JPopupMenu(); //Popup Menu 
		/**Add the values + Action to Menu with @JMenuItem**/
		JMenuItem deleteItem = new JMenuItem("Delete This item");
		deleteItem.setIcon(new ImageIcon("bin\\btnDeleteClickBar.png"));
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					buildDiraction.deleteElement(Olist.getSelectedValue().toString());
					JOptionPane.showMessageDialog(null, "This file is deleted\n"); 
				}catch(NullPointerException nullPointerException){
					JOptionPane.showMessageDialog(null, "You have to click on your choose file"); 
				}
				Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
			}
		});
		JMenuItem rename = new JMenuItem("Rename file name");
		rename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					buildDiraction.renameThisFile(Olist.getSelectedValue().toString());
				}catch(NullPointerException nullPointerException){
					JOptionPane.showMessageDialog(null, "You have to click on your choose file"); 
				}
				Olist.setModel(buildDiraction.changeDiraction(txtChangeDiractionOf.getText()));
			}
		});
		rename.setIcon(new ImageIcon("bin\\btnRenameClickBar.png"));
		popupMenu.add(deleteItem);
		popupMenu.addSeparator();
		popupMenu.add(rename);

		addPopup(Olist, popupMenu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(267, 88, 213, 280);
		frmFilemennager.getContentPane().add(scrollPane_1);
		
		backupList = new JList();
		scrollPane_1.setViewportView(backupList);
		backupList.setModel(fileOption.createList());
		backupList.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		backupList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JButton btnPrint = new JButton();
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					buildDiraction.printFile(Olist.getSelectedValue().toString());
				}catch(NullPointerException NullPointerException){
					JOptionPane.showMessageDialog(null, "You have to click on your choose file"); 
				}
			}
		});
		btnPrint.setIcon(new ImageIcon("bin\\btnPrint.png"));
		btnPrint.setToolTipText("Edit from Source list");
		btnPrint.setBounds(87, 5, 56, 40);
		frmFilemennager.getContentPane().add(btnPrint);
	}
	
	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
