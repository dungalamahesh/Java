import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class FileOptionUse {
	// Array of files
	private final String ROOT_BACKUP_PATH = "pages"; // The Default backup files
	private File f = new File(ROOT_BACKUP_PATH);
	private File[] allFiles = f.listFiles();
	private DefaultListModel pageList;

	public FileOptionUse() {
		pageList = new DefaultListModel();
	}

	public DefaultListModel createList() { // Creating the DefaultListModel of pages
		f = new File(ROOT_BACKUP_PATH); 
		allFiles = f.listFiles();
		pageList.removeAllElements(); //First 'clean' all Elements
		for (int i = 0; i < allFiles.length; i++){
			pageList.addElement(allFiles[i].getName()); //And add only Names of Files from the List	
		}
		return pageList;
	}

	/*public DefaultListModel returnRefreshList() { // Creating the list of pages
		return pageList;
	}*/
	
	/**Check if this file is exist before**/
	public boolean checkFile(File fileForCheck) {
		for (int i = 0; i < allFiles.length; i++) {
			if (fileForCheck.equals(allFiles[i]))
				return true;
		}
		return false;
	}
	/**Back File by find the string and the path**/
	public void backupFile(String nameToAdd, String diractionOfOldFile) {
		String diractionPlusName = ROOT_BACKUP_PATH + "/" + nameToAdd+ ".BACK";//Add to the end of file '.BACK'
		String oldDiractionPlusName = diractionOfOldFile + "/" + nameToAdd;
		File oldFileToBackup = new File(oldDiractionPlusName); //Find the string by path (We saved before) 
		File thisFileToBackup = new File(diractionPlusName); //Find the string by path (We saved before) 
		if (checkFile(thisFileToBackup) == false) { 
			try {
				//diractionPlusName = ROOT_BACKUP_PATH + "/" + nameToAdd+ ".BACK";//Add to the end of file '.BACK'
				//thisFileToBackup = new File(diractionPlusName);
				Files.copy(oldFileToBackup.toPath(), thisFileToBackup.toPath());
				thisFileToBackup.createNewFile(); //And create the file
				JOptionPane.showMessageDialog(null, "This file is backuped!!");
			} catch (IOException ioException) {
				JOptionPane.showMessageDialog(null, "This file isn't exist\n" +ioException.getMessage());
			}
			catch (NullPointerException nullPointerException) {
				JOptionPane.showMessageDialog(null, "This file isn't exist\n" +nullPointerException.getMessage());
			}
		}
		if (checkFile(thisFileToBackup) == true) { //If this file is exist, ask the user if want to replace, By Yes/ No Option
			int n = JOptionPane.showConfirmDialog(null, "This File is exist\nDo you sure you want create", "Warning!!",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "This file isn't exist");
			} if (n == JOptionPane.YES_OPTION) {
				if ( checkFile(thisFileToBackup) == true){
					String deleteThisFileBefore = nameToAdd+ ".BACK";
					deleteElement(deleteThisFileBefore);
					try {
						diractionPlusName = ROOT_BACKUP_PATH + "/" + nameToAdd+ ".BACK";//Add to the end of file '.BACK'
						thisFileToBackup = new File(diractionPlusName);
						Files.copy(oldFileToBackup.toPath(), thisFileToBackup.toPath());
						thisFileToBackup.createNewFile();
					} catch (IOException ioException) {
						JOptionPane.showMessageDialog(null, "Some problem with the restore\n" +ioException.getMessage());
					}
				}
				try {
					thisFileToBackup.createNewFile();
				} catch (IOException ioException) {
					JOptionPane.showMessageDialog(null, "This file isn't exist\n" +ioException.getMessage());
				}
				catch (NullPointerException nullPointerException) {
					JOptionPane.showMessageDialog(null, "This file isn't exist\n" +nullPointerException.getMessage());
				}
			}
		}
	}
	/**Just Delete element**/
	public void deleteElement(String thisFileForDelete){
		String diractionPlusName = ROOT_BACKUP_PATH + "/" + thisFileForDelete;
		File thisFileToDelete = new File(diractionPlusName);
		if ( checkFile(thisFileToDelete)== true ){//If this file is available
			thisFileToDelete.delete();
		}
		if ( checkFile(thisFileToDelete)== false ){//If this file isn't available
			JOptionPane.showMessageDialog(null, "This file isn't deleted\n");
		}
	}

}
