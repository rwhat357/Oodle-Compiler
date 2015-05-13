package cps450;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import cps450.oodle.lexer.Lexer;
import cps450.oodle.lexer.LexerException;
import cps450.oodle.node.Start;
import cps450.oodle.node.TEmptyLine;
import cps450.oodle.parser.Parser;

public class OodleParser extends Parser {
	Lexer lexer;
	
	public OodleParser(Lexer lexer) {
		super(lexer);
		this.lexer = lexer;

	}

	@Override
	public Start parse() throws LexerException, IOException {
		Start s =null;
		try{
			s = super.parse();
			
			System.out.println("Parsed all source files with 0 errors.");
		}
		catch (Exception e){
			
			int lineNum = this.lexer.peek().getLine();
			
			// If carriage return is the current bad token, change its text so 
			// the error message doesn't look uggly whne printer carriage return
			String tokenText = "";
			// Note: maybe add consume cr lf here to later
			if (this.lexer.peek() instanceof TEmptyLine){
				tokenText	= "<carriage return>";
			} else {
				tokenText = this.lexer.peek().getText();
			}
			
			// printing helpful error messages
			System.out.println(FilesInfo.getFileName(lineNum) + ":" + 
							   FilesInfo.getFileLineNumber(lineNum) + "," + 
							   this.lexer.peek().getPos() + ":syntax error:" + 
							   '"' + tokenText + '"');
			
			System.out.println("\tThis token doesn't belong at this position: " + '"' + tokenText + '"');
			System.out.println("\tToken complete class name: " + this.lexer.peek().getClass().getName());
			System.out.println("\tToken class name: " + this.lexer.peek().getClass().getSimpleName());
			System.out.println("\tToken type name: " + TokenScannerHelper.getTokenType(this.lexer.peek()) );
			System.out.println("\tPossible fix: " + FilesInfo.getFileName(lineNum)	+ " at " + e.getMessage());
							   
			
			// print stack trace
			System.out.println("Printing stack trace below...");
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String str = errors.toString();
			System.out.println("\t" + str);
		}
		return s;
	}

}
