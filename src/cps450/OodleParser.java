package cps450;

import java.io.IOException;
import cps450.oodle.lexer.Lexer;
import cps450.oodle.lexer.LexerException;
import cps450.oodle.node.Start;
import cps450.oodle.parser.Parser;
import cps450.oodle.parser.ParserException;

public class OodleParser extends Parser {

	Lexer lexer;
	
	public OodleParser(Lexer lexer) {
		super(lexer);
		this.lexer = lexer;
	}
	
	public OodleParser(OodleLexer oodleLexer) {
		super(oodleLexer);
		this.lexer = oodleLexer;
	}

	@Override
	public Start parse() throws ParserException, LexerException, IOException {
		try {
			return super.parse();
		} catch (ParserException e) {
			
			String syntaxtError = "syntactical error";
			
			System.out.println("<filenameParse>:" + 
							  this.lexer.peek().getLine() + ":" +
							  this.lexer.peek().getPos() + ":" +
							  syntaxtError + ":MORE INFO:"+
							  this.lexer.peek().getClass().toString() + " " + 
							  this.lexer.peek().getText()); 
			throw e;
		}
	}
	
	
}
