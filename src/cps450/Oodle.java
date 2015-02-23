package cps450;

import cps450.oodle.lexer.LexerException;
import cps450.oodle.lexer.Lexer;
import cps450.oodle.node.EOF;
import cps450.oodle.node.Token;
import cps450.oodle.parser.Parser;
import cps450.oodle.parser.ParserException;

import java.lang.String;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import org.apache.commons.cli.CommandLine;

/**
 * @author user
 *
 */
public class Oodle {
	
    /**
     * @param args
     * @throws IOException
     * @throws LexerException
     */
    public static void main(String[] args) throws IOException, LexerException  {

    	// parse arguments, print a help message if given incorrect or insufficient arguments
    	CommandLine cmdArgs = MyOptions.parseAguments(args);
    	Boolean hasDs = cmdArgs.hasOption("ds");
		ArrayList<String> filenamesArray = new ArrayList<String>(); // holds all the files given
		
		
		// place all filenames in the list
		filenamesArray = MyOptions.getAllFileNamesGivenInCmdArgs(cmdArgs, filenamesArray, hasDs );
		
		// read all files iDnto <source>
		String source = ""; 
		boolean first = true;
		int filesCount = filenamesArray.size();
		
		// read one file at a time and add 
		// TODO: get file # of lines in an vector to show filenames when lexing
		for(int i = 0; i < filesCount; i++) {
			BufferedReader reader = new BufferedReader(new FileReader(filenamesArray.get(i)));
			String line = null;
			while((line = reader.readLine()) != null) { // read a line of a file
				source += ((first ? "" : "\n") + line);
				first = false;
			}
			
			reader.close();
		}
		
		try {

	        // Create a lexer instance.
	        OodleLexer oodleLexer = new OodleLexer
	        				 ( new PushbackReader
	        				 ( new StringReader(source)), hasDs);
			
	        // Create a parser instance
	        OodleParser oodleParser = new OodleParser(oodleLexer);
			
	        // Parse the input.
	        // Note: As you parse, the lexer prints the tokens if -ds is provided.
	        // Otherwise, only the lexical errors are printed. The parser stops if it encounters syntax error.
			oodleParser.parse();
			
			System.out.println("success!");
			
		} catch (LexerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} catch (ParserException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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

