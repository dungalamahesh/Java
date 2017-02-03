import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class buildFileDiraction {
	private final String ROOT_FILE_PATH = "C:\\";
	private String nowDiraction;
	private final String ROOT_BACKUP_PATH = "pages"; // The Default backup files
	private File f = new File(ROOT_FILE_PATH);
	private File[] allFiles = f.listFiles();
	private File[] newFiles;
	private DefaultListModel pageList;
	
	public buildFileDiraction() {
		allFiles = f.listFiles();
		pageList = new DefaultListModel();
	}

	public DefaultListModel starterDiraction(){
		try{
			File newDiraction = new File(ROOT_FILE_PATH);
			newFiles=newDiraction.listFiles();
			pageList.removeAllElements(); //Clean the last List 
			for ( int i = 0 ; i < newFiles.length ; i++ )
				pageList.addElement(newFiles[i].getName()); //Add elements into the DefaultListModel
		}catch(NullPointerException thisIsAnNullPoint){
			JOptionPane.showMessageDialog(null, "No this Diraction\nTry again");
		}
		return pageList;
	}

	public DefaultListModel changeDiraction(String diraction){
		this.nowDiraction = diraction; //Save the new Direction 
		try{
			File newDiraction = new File(diraction);
			newFiles=newDiraction.listFiles();
			pageList.removeAllElements(); //Clean the last List 
			for ( int i = 0 ; i < newFiles.length ; i++ )
				pageList.addElement(newFiles[i].getName()); //Add elements into the DefaultListModel
		}catch(NullPointerException thisIsAnNullPoint){
			JOptionPane.showMessageDialog(null, "No this Diraction\nTry again");
			starterDiraction();
		}
		return pageList;
	}
	private boolean checkFile(File fileForCheck){
		for ( int i = 0 ; i < newFiles.length ; i++ ){
			if ( fileForCheck.equals(newFiles[i]))
				return true;
		}
		return false;
	}
	/**Restore File from bakList Files**/
	public void restore(String thisName){
		String fromDiractionPlusName = ROOT_BACKUP_PATH + "/" + thisName;
		String diractionPlusName = nowDiraction + "/" + thisName;
		diractionPlusName = diractionPlusName.replace(".BACK", "");
		File oldFileToRestore = new File(fromDiractionPlusName); //Get Info from backup File
		File thisFileToRestore = new File(diractionPlusName); //Push the direction and name
		if (checkFile(thisFileToRestore) == false) {
			try { //If this file isn't exit remove '.BACK' from the file name
				Files.copy(oldFileToRestore.toPath(), thisFileToRestore.toPath());
				thisFileToRestore.createNewFile(); //And create new file by the 'new name'
				JOptionPane.showMessageDialog(null, "This file is Restored!!");
			} catch (IOException ioException) {
				JOptionPane.showMessageDialog(null, "This file isn'ts Restored\n" +ioException.getMessage());
			}
			catch (NullPointerException nullPointerException) {
				JOptionPane.showMessageDialog(null, "This file isn't Restored\n" +nullPointerException.getMessage());
			}
		}
		if (checkFile(thisFileToRestore) == true) { //If the file is exist, ask the user if delete the old and create new one
			int n = JOptionPane.showConfirmDialog(null, "This File is Available\nDo you sure you want replace?", "Warning!!",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "This files isn't Restored");
			}if (n == JOptionPane.YES_OPTION) {
				deleteElementByDiraction(diractionPlusName);
					try {
						diractionPlusName = diractionPlusName.replace(".BACK", "");
						thisFileToRestore = new File(diractionPlusName);
						Files.copy(oldFileToRestore.toPath(), thisFileToRestore.toPath());
						thisFileToRestore.createNewFile();
						JOptionPane.showMessageDialog(null, "This files is Restored");
					} catch (IOException ioException) {
						JOptionPane.showMessageDialog(null, "Some problem with the restore\n" +ioException.getMessage());
					}
				}
			}
	}
	/**Just Delete element+ Get file width the direction**/
	public void deleteElementByDiraction(String thisFileForDeletePlusDiraction){
		File thisFileToDelete = new File(thisFileForDeletePlusDiraction); //Get the direction with the String
		if ( checkFile(thisFileToDelete)== true ){//If this file is available
			thisFileToDelete.delete();
		}
		if ( checkFile(thisFileToDelete)== false ){//If this file isn't available
			JOptionPane.showMessageDialog(null, "This file isn't deleted\n");
		}
	}
	/**Just Delete element**/
	public void deleteElement(String thisFileForDeletePlusDiraction){
		String diractionPlusName = nowDiraction + "/" + thisFileForDeletePlusDiraction;
		File thisFileToDelete = new File(diractionPlusName);
		if ( checkFile(thisFileToDelete)== true ){//If this file is available
			int n = JOptionPane.showConfirmDialog(null, "Do you sure that you want\nto delete this file" +thisFileForDeletePlusDiraction, "Warning!!",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				thisFileToDelete.delete();
			}
			if (n == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "This file isn't deleted");
			}
		}
		if ( checkFile(thisFileToDelete)== false ){//If this file isn't available
			JOptionPane.showMessageDialog(null, "This file isn't deleted\n");
		}
	}
	/**Add new file from other Path/ Direction**/
	public void addChoosedFile(File addThisFile){
		String diractionPlusName = nowDiraction + "/" + addThisFile.getName();
		File thisFileToADD = new File(diractionPlusName);
		if ( checkFile(thisFileToADD)== true ){//If this file is available
			JOptionPane.showMessageDialog(null, "Soory this file is alreay exist");
		}
		if ( checkFile(thisFileToADD)== false ){//If this file isn't available
			try {
				Files.copy(addThisFile.toPath(), thisFileToADD.toPath());
				thisFileToADD.createNewFile(); //Creating the file
				JOptionPane.showMessageDialog(null, "This File is Copied into Source Diraction");
			} catch (IOException iOException) {
				JOptionPane.showMessageDialog(null, "Soory this file is alreay exist" +iOException.getMessage());
			} catch (NullPointerException nullPointerException) {
				JOptionPane.showMessageDialog(null, "Soory this file is alreay exist" +nullPointerException.getMessage());
			}
		}
	}
	/**Rename the File that send (string name) by the choose option**/
	public void renameThisFile(String renameThisFileName){ 
		String diractionPlusName = nowDiraction + "/" + renameThisFileName;
		File thisFileToRename = new File(diractionPlusName); //Push now path+ name we got
		if ( checkFile(thisFileToRename)== true ){  //if the file is exist making the change
			String newName = JOptionPane.showInputDialog(null, "Enter new name:", "new name",
					JOptionPane.QUESTION_MESSAGE);
			String newDiractionPlusName = nowDiraction + "/" + newName;
			File newFileToRename = new File(newDiractionPlusName);
			thisFileToRename.renameTo(newFileToRename);
			JOptionPane.showMessageDialog(null, "This File name is change to\n'" +newName+ "'");
		}
		if ( checkFile(thisFileToRename)== false ){
			JOptionPane.showMessageDialog(null, "This file isn't available");
		}
	}
	/**Write into existent file (only)
	 * @throws IOException **/
	public void writeIntoFile(String writeToThisFile ) throws IOException{
		String diractionPlusName = nowDiraction + "/" + writeToThisFile;
		File writeForThisFile = new File(diractionPlusName);
		if ( checkFile(writeForThisFile)== true ){
			String getTextForThisFile = JOptionPane.showInputDialog(null, "Enter your text\nattention! This action Will erase the other text", "Text Value",
					JOptionPane.PLAIN_MESSAGE);
			FileWriter filterWriterTheText = null;
			try {
				filterWriterTheText = new FileWriter(writeForThisFile.getAbsoluteFile(), true); //Get the file Absolute
			} catch (IOException iOException) {
				JOptionPane.showMessageDialog(null, "Some problem with this file" +iOException.getMessage());
			} 
			BufferedWriter bufferForWrite = new BufferedWriter(filterWriterTheText); //Buffer for Writer to this file
			try {
				java.util.Date editTime = new java.util.Date(); //Get current time
				bufferForWrite.write(getTextForThisFile+ " (Edit time: " +editTime+ ")");
				bufferForWrite.newLine();
			} catch (IOException iOException) {
				JOptionPane.showMessageDialog(null, "You can't write to this file" +iOException.getMessage());
			}
			bufferForWrite.close(); //@Close the file always in the end
		}
		if ( checkFile(writeForThisFile)== false ){
			JOptionPane.showMessageDialog(null, "This file isn't available");
		}
	}
	/**Print the Text from File**/
	public void printFile(String readFormThisFile){
		BufferedReader rederBuffer = null; //Buffer for reader 
		String diractionPlusName = nowDiraction + "/" + readFormThisFile; //File path
		
		try {
			String messages; //Check if end text
			String accumulator = ""; 
			rederBuffer = new BufferedReader(new FileReader(diractionPlusName));
			while ((messages = rederBuffer.readLine()) != null) {
				accumulator += messages+ "\n"; //accumulator the text while have more text
			}
			JOptionPane.showMessageDialog(null, "" +accumulator); //print the text
		} catch (IOException iOException) { 
			JOptionPane.showMessageDialog(null, "Some Problem with the read\n" +iOException.getMessage()); //print the text
		} finally {
			try {
				if (rederBuffer != null) rederBuffer.close(); //Close the buffer reader
			} catch (IOException iOException) {
				JOptionPane.showMessageDialog(null, "Some Problem with Close the reader\n" +iOException.getMessage()); //print the text
			}
		}
	}
	
}
