package cps450;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

import cps450.oodle.lexer.*;
import cps450.oodle.node.*;

import java.io.*;


public class OodleLexer extends Lexer {
	
	Boolean hasDsOption;

	
	public OodleLexer(PushbackReader in, Boolean hasDs) {
		super(in);
		hasDsOption = hasDs;
	}
	
	
	public OodleLexer(String filename, Boolean hasDs) throws FileNotFoundException {
		super(new PushbackReader(new BufferedReader(new FileReader(filename)),
				1024));
		hasDsOption = hasDs;
	}
	

	// Override filter() defined in Lexer to prevent illegal characters from
	// being
	// reported to the parser
    @Override
	protected void filter() throws LexerException, IOException {
		super.filter();
    	
		/*
		System.out.println(token.getClass() +
                   ", state : " + state.id() +
                   ", text : [" + token.getText() + "]");
		   
		if (this.token instanceof EOF) {
			System.out.println("found eof");
		}
		*/
		
    	// print an error if the token given is not a valid lexeme
    	String errMsg = null;
		if (this.token instanceof TUnrecognizedChar) {
			errMsg = "unrecognized char:" + this.token.getText();
		}
		
		else if (this.token instanceof TIllegalString) {
			errMsg = "illegal string:" + this.token.getText();
		}
		
		else if (this.token instanceof TUnterminatedString) {
			errMsg = "unterminated string:" + this.token.getText();
		}
		
		if (errMsg != null) {
			System.out.println("<filename>" + ":" + this.token.getLine() + "," + this.token.getPos() + ":" + errMsg);
			
			//System.err.println("Error [" + this.token.getLine()
			//		+ this.token.getPos() + "]: " + errMsg);
			this.token = null; // prevent Lexer from returning this token
		}
		
		// prevent lexer from returnning Whitespaces, ConsumeCrLf and Comments
		if (this.token instanceof TWhitespace  ||
			this.token instanceof TConsumeCrLf ||
			this.token instanceof TComment      )
			this.token = null; // prevent Lexer from returning this token
		
		// print tokens if -ds was given, and if the token is a valid lexeme, and if token is not end of file
		if ((token != null) && hasDsOption && (!(token instanceof EOF))) {
			
			System.out.println("<filename>" + ":" + 
							token.getLine() + "," + 
							token.getPos()     + ":" + 
							getMyTokenText(token));
		}
	}
    
    
	/* RECEIVES: a token
     * FUNCITON: gets the text of the given token
     * RETURNS: the text of the token
     */
    private  String getMyTokenText(Token t){
    	
    	String myTokenText = t.getText();
    	String tokenType = Helper.getTokenType(t);
    	String tokenTypeStr = "";


    	// if newline encountered, then print "cr" instead of "\n"
    	if ( tokenType.equals("newline") )
    		myTokenText = "cr";
    	
    	// if miscellaneous (':', ';', etc) encountered, then print add quotes to the token text
    	else if ( tokenType.equals("miscellaneous") )
    		myTokenText = "'" + myTokenText + "'";
    	
    	else if  ( tokenType.equals("keyword") )
    		myTokenText = "keyword:" + myTokenText;
    	
    	else if  ( tokenType.equals("identifier") )
    		myTokenText = "identifier:" + myTokenText;
    	
    	else if  ( tokenType.equals("operator") )
    		myTokenText = "operator:'" + myTokenText + "'";
    		
    	else if  ( tokenType.equals("string lit") )
    		myTokenText = "string lit:" + myTokenText;
    		
    	else if  ( tokenType.equals("keyword op") )
    		myTokenText = "keyword op:" + myTokenText;
    	
    	else if  ( tokenType.equals("integer lit") )
    		myTokenText = "integer lit:" + myTokenText;
    	
    	else 
    		myTokenText = "ERROR: OodleLexer.getMyTokenText():not found tokentype";
    	
    
    	return myTokenText;
    }
    

}
