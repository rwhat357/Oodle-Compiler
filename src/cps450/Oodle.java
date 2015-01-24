// Oodle.java

package cps450;

import cps450.oodle.analysis.*;
import cps450.oodle.lexer.*;
import cps450.oodle.node.*;

import java.lang.String;
import java.io.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class Oodle
{
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
	
	
	
    public static void main(String[] arguments) throws IOException {

    	// parse arguments, print a help message if given incorrect or insufficient arguments
    	CommandLine cmdArgs = parseAguments(arguments);
    	
    	// -ds present, then print token list from all the files given
    	if(cmdArgs.hasOption("ds")) {
    		
    		// print first argument
    		String first_filename = cmdArgs.getOptionValue("ds");
    		printTokensList(first_filename);
    		
    		// if given more than one argument, print the rest
    		String[] rest_filenames = cmdArgs.getArgs();
    		if (rest_filenames.length > 0)
    			for (int i = 0; i < rest_filenames.length; i++)
    				printTokensList(rest_filenames[i] );
    		
    		
    	// no -ds present, then print lexical errors from all the files given
    	} else { 
    		
    		for (int i = 0; i < arguments.length; i++)
    			printLexicalErrors(arguments[i]);
    	}

    	return;
    }
    
	/* RECEIVES: a string containing all the files to scan
     * FUNCITON: does a lexical scan for all the tokens and prints only lexical errors
     * 			 Lexical Errors:
     * 				- An unrecognized character is any character of the input file that is not a part of one of the lexical components listed below.
	 *				- An unterminated string is a string whose closing quote is not found by the end of the current input line.
	 *				- An illegal string is a string which contains illegal escape sequences.
     * EXCEPTIONS: prints any exceptions to the console
     */
    private static void printLexicalErrors(String filename) throws IOException {
    	 //String[] files = fileNames.split(" ");
   	 
    	// load lexer 
        Lexer lexer= new Lexer(
			    new PushbackReader(
			    new BufferedReader( 
			    new FileReader(filename)), 1024));
	
        
        // NOTE: possible bug for lexicalError if the error is the first character
        // process tokens from lexer
        Token t = getNextToken(lexer);
        String fileName = filename;
        char div = ':';
        char co = ',';
        String lexicalError = "";
        
        int lineNum = t.getLine();
        int linePos = t.getPos();
        String text = t.getText();
        
        // print each token
        while ( ! (t instanceof EOF)  ) {
        	
        	lexicalError = Helper.getTokenType(t);
        	
        	lineNum = t.getLine();
        	linePos = t.getPos();
        	text	= t.getText();
        	
        	if ( lexicalError.equals("unrecognized character") || 
        			lexicalError.equals("unterminated string") || 
        			(lexicalError.equals("illegal string") ))
        	{
        		System.out.println(fileName + div + lineNum + co + linePos + div + lexicalError + div + text);
    		} 

        	t = getNextToken(lexer);
        	
        }
		
		return;
	}
    
	/* RECEIVES: a string containing all the files to scan
     * FUNCITON: does a lexical scan for all the tokens and prints all the tokens and its details
     * EXCEPTIONS: prints any exceptions to the console
     */
	private static void printTokensList(String filename) throws IOException {
    	 
    	// load lexer 
		Lexer lexer;
		try {
			 lexer= new Lexer(
				    new PushbackReader(
				    new BufferedReader( 
				    new FileReader(filename)), 1024));
		
		} catch (java.io.FileNotFoundException e){
			 System.out.println( "Oodle could not find file <" + filename + "> in the specified directory.");
			 return;
		}

        // process tokens from lexer
        Token t = getNextToken(lexer);
        String fileName = filename;
        char div = ':';
        char co = ',';
        String tokenType = "";
        
        int lineNum = t.getLine();
        int linePos = t.getPos();
        String text = t.getText();
        
        // print each token
        while ( ! (t instanceof EOF)  ) {
        	
        	tokenType = Helper.getTokenType(t);
        	
        	// If next token is a space, don't print it. Get the next token.
        	if ( tokenType.equals("whitespace") || 
        			tokenType.equals("comment") ||
        			tokenType.equals("consume cr lf")){
        		t = getNextToken(lexer);
        		continue;
        	}
        	
        	lineNum = t.getLine();
        	linePos = t.getPos();
        	text	= t.getText();
        	
        	// if newline encountered, then print "cr" instead of "\n"
        	if ( tokenType.equals("newline") )
        	{
        		text = "cr";
        		tokenType = "";
        	}
        	
        	// if miscellaneous (':', ';', etc) encountered, then print add quotes to the token text
        	if ( tokenType.equals("miscellaneous") )
        	{
        		text = "'" + text + "'";
        		tokenType = "";
        	}

        	System.out.println(fileName + div + lineNum + co + linePos + div + tokenType + div + text);
            t = getNextToken(lexer);
        }
		
	}
    
    
	/* RECEIVES: a Lexer object to get the next token from
     * FUNCITON: gets the next token from Lexer object 
     * RETURNS: the next token
     * EXCEPTIONS: prints any exceptions to the console
     */
    private static Token getNextToken(Lexer l) throws IOException {
	      Token t = null;
	      try {
	        t = l.next(); // read next token
	      } catch (LexerException e) {
	            System.out.println(e);
	
	      }
	      return t;
    }

   
}

