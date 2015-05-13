package cps450;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cps450.oodle.node.Node;
import cps450.oodle.node.Token;

/**
 * @author user
 *  class holds the properties of all files given to the Oodle
 * parser in the command line arguments.
 */
public class FilesInfo {
	
	private static ArrayList<String> filenamesArray; // reference to an array list of file names created in Oodle.java
	private static ArrayList<Integer> fileTotalLines; // holds the total lines for each file in the same order as filenames
	private static int filesCount;
	private static String sourceCode;
	private static int totalLines;
	
	public FilesInfo (){
		// singleton
		System.out.println("Error: FilesInfo class cannot be instantiated.");
	}
	
	public static void instantiateFilesInfo(ArrayList<String> _filenamesArray) throws IOException {
		
		// initializers
		filenamesArray = _filenamesArray; 
		fileTotalLines = new ArrayList<Integer>();
		sourceCode = ""; // initialize <sourceCode> field
		filesCount = _filenamesArray.size(); // get files count
		totalLines = 0;
		

		// read all files' source code into <sourceCode> field
		boolean isFirstLine = true;
		

		// read one file at a time and add 
		for(int i = 0; i < filesCount; i++) {
			
			BufferedReader reader = new BufferedReader(new FileReader(_filenamesArray.get(i)));
			
			int numLines = 0;
			String line = null;
			while((line = reader.readLine()) != null) { // read a line of a file
				
				numLines++; // increment the number of lines for the file
				sourceCode += ((isFirstLine ? "" : "\n") + line);
				isFirstLine = false;
			}
	
			reader.close();
			fileTotalLines.add(numLines); // add number of lines to the list for read file
			totalLines += numLines; // add the total number of lines to <totalLines> field
		
		}

	}
	
	public static int getFilesCount(){
		return filesCount;
	}

	public static String getSourceCode() {
		return sourceCode;
	}

	public static int getTotalLines() {
		return totalLines;
	}

	/* RECEIVES: an Int, line number from the source code
	 * FUNCTON: gets the line number and figures out to which filename the line number belongs to
	 * RETURNS: returns a String, the filename
	 * ASSUMPTIONS: assumes that lineNumber > 0
	 */
	public static String getFileName(int lineNumber) {
		
		String filename = "";
		int currLineCount = 0;
		
		for (int i = 0; i <  filesCount; i++){
			currLineCount =  fileTotalLines.get(i);
			
			if (currLineCount >= lineNumber){
				
				filename =  filenamesArray.get(i);
				break;
			} 
			
			lineNumber -= currLineCount;
		}
		
		
		return filename;
	}
	
	/* RECEIVES: an Int, line number from the source code
	 * FUNCTON: gets the line number and figures out to which filename the line number belongs to
	 * RETURNS: returns an Int, the line number of that filename
	 * ASSUMPTIONS: assumes that lineNumber > 0
	 */
	public static int getFileLineNumber(int lineNumber) {
		
		int fileLineNumberToReturn = 0;
		
		int currLineCount = 0;
		
		for (int i = 0; i <  filesCount; i++){
			currLineCount =  fileTotalLines.get(i);
			
			if (currLineCount >= lineNumber){
				
				fileLineNumberToReturn = lineNumber;
				break;
			} 
			
			lineNumber -= currLineCount;
		}
		
		
		return fileLineNumberToReturn;
	}
	
	/* RECEIVES: 
	 * FUNCTON: 
	 * RETURNS: 
	 * ASSUMPTIONS: 
	 */
	public static String getFileLinePosAndErrorMessage(int lineNumber, int positionInTheLine){
		String errorString = "";
		String phaseErrorMessage = "semantical error";
		
		errorString = getFileName(lineNumber) + ":" + getFileLineNumber(lineNumber) + "," +  positionInTheLine  + ":" +phaseErrorMessage;
		
		return errorString;
	}

}
