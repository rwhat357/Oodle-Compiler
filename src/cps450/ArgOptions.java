package cps450;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/* class ArgOptions: this class is a helper class to Oodle.java to parse arguments and to hold the values of the flags ds, S, g, help
 * methods:
 *		public static void printHelpMessage(Options options)
 *		public static CommandLine parseAguments(String[] arguments)
 *		getters and setters for all the flags
 */
public final class ArgOptions {
	
	private static boolean dsOption = false; // Print all tokens in source file 
	private static boolean SOption = false;  // Only make .s file and not invoke GCC
	private static boolean gOption = false;  // Print debug comments in assembly
	private static boolean helpOption = false;  // Print help message
	
	private static Options options ;  // created options to parse
	
	// private constructor
	private ArgOptions () { 
        return;
    }
	
	
	/* RECEIVES: an Options object that contains the list of command line arguments
	 * FUNCTION: prints the usage from the Options object on the console
	 */
	public static void printHelpMessage(){
		
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp( "Oodle", options );	
	
	}
	
	/* RECEIVES: the arguments given to run the program
	 * FUNCTION: parses the arguments 
	 * RETURNS:  parsed a CommandLine object, exits the program if not able to parse, or if a file was not given, prints a message if it exits
	 */
	@SuppressWarnings("static-access")
	public static CommandLine parseAguments(String[] arguments){
		
		// parsed arguments
		CommandLine cmd = null;
		
		// create the Options
    	options = new Options();
    	
    	// add  options
    	options.addOption( OptionBuilder.withLongOpt( "ds" )
                .withDescription( "Display list of tokens, one per line, to standard output. \nTry: oodle.jar -ds <filename>" )
                .hasArg()
                .withValueSeparator(' ')
                .create() );
    	options.addOption("S", false, "Generate an assembly (.s) file in current directory from oodle files. \nTry: oodle.jar -S <filename>");
    	options.addOption("g", false, "Include debug information in assembly file. \nTry: oodle.jar -g <filename>");
    	options.addOption("help", false, "Print this help message.");
    	
    	// create the parser
        CommandLineParser parser = new GnuParser();
        
        // exit the program if no files were given
    	if(arguments.length < 1)
        {
            System.out.println("usage:");
            System.out.println("  Try: java Oodle.jar <filename>");
            System.out.println("  For Help: java Oodle.jar -help");
            System.exit(1);
        }
        
        // parse arguments
        try {
            // parse the command line arguments
        	 cmd = parser.parse( options, arguments );
        	 
        	 // set up options given
        	 ArgOptions.dsOption = cmd.hasOption("ds");
        	 ArgOptions.SOption = cmd.hasOption("S");
        	 ArgOptions.gOption = cmd.hasOption("g");
        	 ArgOptions.helpOption = cmd.hasOption("help");
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            printHelpMessage();
            System.exit(1);
        }
		
		return cmd;
	}
	// RECEIVES: command line arguments and an array list to place the filenames from the command line arguments
	// FUCNTION: gets all the files names given to flag "-ds" and places them into the <fileNames> array list
	//			 sets up the filenames in Globals class for <exeFilename>, <filename>,and <sourceFileName>
	// RETURNS: a array list of all the file names 
	// ASSUMPTIONS: assumes that there were files given in the arguments
	public static ArrayList<String> getAllFileNamesGivenInCmdArgs(CommandLine cmdArgs, ArrayList<String> fileNames, Boolean hasDsOption ) {
		

		
		// there is no -ds option, so return an array list of filenames
		if(hasDsOption == false){
			String[] files = cmdArgs.getArgs();
			for (int i = 0; i < files.length ; i++){
				
				fileNames.add(files[i]);
				
				// set filenames  if more than one files given
				Globals.SetExeFilename(files[i]);
				Globals.setFilename(files[i]);
				Globals.setSourceFilename(files[i]);
				
			}
			return fileNames;
		}
		
		// there is a -ds option, add all array list and return an array list of filenames
		// add first file name that follows -ds option
		String first_filename = cmdArgs.getOptionValue("ds");
		
		// set filenames if only one file given
		fileNames.add(first_filename);
		Globals.SetExeFilename(first_filename);
		Globals.setFilename(first_filename);
		Globals.setSourceFilename(first_filename);
		
		// if given more than one file, get the rest and append them to the main list
		String[] rest_filenames = cmdArgs.getArgs();
		if (rest_filenames.length > 0)
			for (int i = 0; i < rest_filenames.length; i++){
				fileNames.add(rest_filenames[i]);
				
				// set filenames  if more than one file given
				Globals.SetExeFilename(rest_filenames[i]);
				Globals.setFilename(rest_filenames[i]);
				Globals.setSourceFilename(rest_filenames[i]);
			}
		
		// return the list of names
		return fileNames;
	}


	public static ArrayList<String> addBuiltinOodleClasses(
			ArrayList<String> filenamesArray) {
		
		ArrayList<String> files = new ArrayList<String>();
		
		String writer = "Writer.ood";
		String reader = "Reader.ood";
		
		// add built ins
		files.add(writer);
		files.add(reader);
		
		// add given files
		for(String name : filenamesArray){
			files.add(name);
		}
		
		
		return files;
	}
	
	public static String printCmdLineOptions(String letter){
		if(letter == "ds"){
			return " :Print all tokens in source file";
		}
		if(letter =="S"){
			return " :Only make .s file and don't invoke GCC";
		}
		if(letter == "G"){
			return " :Print debug comments in .s file";
		}
		return null;

	}
 
	public static void setDSOption(boolean paramDsOption) {
		dsOption = paramDsOption;
	}
	
	public static void setSOption(boolean paramSOption) {
		SOption = paramSOption;
	}
	
	public static void setGOption(boolean paramGOption) {
		gOption = paramGOption;
	}
	
	public static void setHelpOption(boolean paramHelpOption) {
		helpOption = paramHelpOption;
	}
	
	public static boolean getHelpOption() {
		return helpOption;
	}
	
	public static boolean getDSOption() {
		return dsOption;
	}
	
	public static boolean getSOption() {
		return SOption;
	}
	
	public static boolean getGOption() {
		return gOption;
	}
}