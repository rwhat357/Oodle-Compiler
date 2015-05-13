
package cps450;

import cps450.oodle.node.*;
import cps450.Globals;
import cps450.oodle.parser.*;
import cps450.oodle.lexer.*;

import java.io.*;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;


public class Oodle {	
	
	
	public static void main(String[] args) throws ParserException, IOException, LexerException {
    	
		int errCount = 0;
		
		// parse arguments, print a help message if given incorrect or insufficient arguments
    	CommandLine cmdArgs = ArgOptions.parseAguments(args);
		if(ArgOptions.getHelpOption()){ // return help message if specified
			ArgOptions.printHelpMessage();
			return;
		}
			
		// place all filenames in the list
		// access by classes: OodleLexer, OodleParser, and Semantic Checker objects only
		ArrayList<String> filenamesArray = new ArrayList<String>(); // holds all the files given
		filenamesArray = ArgOptions.getAllFileNamesGivenInCmdArgs(cmdArgs, filenamesArray, ArgOptions.getDSOption());
		
		// initialize singleton FilesInfo 
		FilesInfo.instantiateFilesInfo(filenamesArray);

		OodleLexer lexer = new OodleLexer(Globals.filename);
		
		System.out.println("Scanning and parsing source files...");
		Parser parser = new Parser(lexer);
		
		try {
			Start node = parser.parse();
			
			
			SemanticChecker checker = new SemanticChecker();
			node.apply(checker);  
			
			
			if(Globals.getNumSemanticErrors() == 0) {
				CodeGenerator codeGenerator = new CodeGenerator();
				node.apply(codeGenerator);
				
				
				if(!ArgOptions.getSOption()) {
					GenerateExecutable.createGCCCode();
				}
			}			
		} catch(ParserException P) { 
			++errCount;
			System.out.println(Globals.filename + ":" + P.getToken().getLine() + "," + P.getToken().getPos() + ":" + " parser error: at " + P.getMessage());
			
		} catch(LexerException L) { 
			++errCount;
			System.out.println(L.getMessage());
		} finally {
			
			
			if(ArgOptions.getSOption() && errCount== 0 && Globals.getNumSemanticErrors() == 0) {
				System.out.println("Assembly code was successfully generated.");
			}
			
			if(errCount!= 0 && Globals.getNumSemanticErrors() != 0){
				System.out.println("There are <" + errCount+ "> errors that need to be fixed; ");
				System.out.println( Globals.getNumSemanticErrors() + " are semantical errors.");	
			}
		}	
	}
}
