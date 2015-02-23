package cps450;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/* class MyOptions: this class is a helper class to Oodle.java to parse arguments
 * methods:
 *		public static void printHelpMessage(Options options)
 *		public static CommandLine parseAguments(String[] arguments)
 */
public final class MyOptions {
	
	
	// private constructor
	private MyOptions () { 
        return;
    }
	
	
	/* RECEIVES: an Options object that contains the list of command line arguments
	 * FUNCTION: prints the usage from the Options object on the console
	 */
	public static void printHelpMessage(Options options){
		
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
    	Options options = new Options();
    	
    	// add  options
    	options.addOption( OptionBuilder.withLongOpt( "ds" )
                .withDescription( "display list of tokens, one per line, to standard output. Try command -ds <filename>" )
                .hasArg()
                .withValueSeparator(' ')
                .create() );
    	//options.addOption("ds", true, "display list of tokens, one per line, to standard output. Try command -ds <filename>");
    	options.addOption("help", false, "print this message");
    	
    	// create the parser
        CommandLineParser parser = new GnuParser();
        
        // exit the program if no files were given
    	if(arguments.length < 1)
        {
            System.out.println("usage:");
            System.out.println("  java Oodle <filename>");
            System.exit(1);
        }
        
        // parse arguments
        try {
            // parse the command line arguments
        	 cmd = parser.parse( options, arguments );
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            printHelpMessage(options);
            System.exit(1);
        }
		
		return cmd;
	}
	// RECEIVES: command line arguments and an array list to place the filenames from the command line arguments
	// FUCNTION: gets all the files names given to flag "-ds" and places them into the <fileNames> array list
	// RETURNS: a array list of all the file names 
	// ASSUMPTIONS: assumes that there were files given in the arguments
	public static ArrayList<String> getAllFileNamesGivenInCmdArgs(CommandLine cmdArgs, ArrayList<String> fileNames, Boolean hasDsOption ) {
		
		// there is no -ds option, so return an array list of filenames
		if(hasDsOption == false){
			String[] files = cmdArgs.getArgs();
			for (int i = 0; i < files.length; i++)
				fileNames.add(files[i]);
			return fileNames;
		}
		
		// there is a -ds option, add all array list and return an array list of filenames
		// add first file name that follows -ds option
		String first_filename = cmdArgs.getOptionValue("ds");
		fileNames.add(first_filename);
		
		// if given more than one file, get the rest and append them to the main list
		String[] rest_filenames = cmdArgs.getArgs();
		if (rest_filenames.length > 0)
			for (int i = 0; i < rest_filenames.length; i++)
				fileNames.add(rest_filenames[i]);
		
		// return the list of names
		return fileNames;
	}
	

}
