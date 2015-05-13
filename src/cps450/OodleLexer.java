//LEXER
package cps450;

import java.io.*;

import cps450.oodle.lexer.Lexer;
import cps450.oodle.lexer.LexerException;
import cps450.oodle.node.*;


// Lexes the specified Oodle file
public class OodleLexer extends Lexer {
	
	private String filename;
	
	
	public OodleLexer(String filename) throws FileNotFoundException {
		super(new PushbackReader(new BufferedReader(new FileReader(filename)), 1024));
		this.filename = filename;
	}
	
	
	protected void filter() {

		String message = "";
		
		//Unrecognized character
		if (this.token instanceof TIllegalChar) {		
			message = filename + ":" + token.getLine() + "," + token.getPos() + ":" + "Unrecognized character:" + token.getText();
			try {
				throw new Exception(message);
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		//Unterminated String
		} else if(this.token instanceof TUntermStrLit) {	
			message = filename + ":" + token.getLine() + "," + token.getPos() + ":" + "Unterminated string:" + token.getText();
			System.err.println(message);
			
		//Illegal String
		} else if(this.token instanceof TIllegalStrLit) {	
			message = filename + ":" + token.getLine() + "," + token.getPos() + ":" + "Illegal string:" + token.getText();
			System.err.println(message);
		// Valid 
		} else if (token != null && ArgOptions.getDSOption() && 
				(this.token instanceof TComment == false) && 
				(this.token instanceof TBlanks == false) && 
				(this.token instanceof EOF == false)) { 
			
			//EOL
			if(this.token instanceof TEmptyLine) {
				message = "cr";				
				
			//ID	
			} else if(this.token instanceof TId) {
				message = "identifier:" + token.getText();				
				
			//keyword	
			} else if(this.token instanceof TAnd || this.token instanceof TBegin || 
					this.token instanceof TBoolean || this.token instanceof TClassId || 
					this.token instanceof TElse || this.token instanceof TEnd || 
					this.token instanceof TFalse || this.token instanceof TFrom || 		
					this.token instanceof TIf || 	this.token instanceof TInherits || 		
					this.token instanceof TInt || this.token instanceof TIs || 
					this.token instanceof TLoop || this.token instanceof TMe || 
					this.token instanceof TNew || this.token instanceof TNot ||
					this.token instanceof TNull || this.token instanceof TOr || 
					this.token instanceof TString || this.token instanceof TThen || 
					this.token instanceof TTrue || 	this.token instanceof TWhile) {
				message = "keyword:" + token.getText();				
				
			// others randoms
			} else if(this.token instanceof TLPar  || 
					this.token instanceof TRPar  || this.token instanceof TRBrkt  || 
					this.token instanceof TLBrkt || this.token instanceof TComma || 
					this.token instanceof TSemicolon || this.token instanceof TColon || 
					this.token instanceof TPeriod) {
				message = "\'" + token.getText() + "\'";				
				
			// operators
			}else if(this.token instanceof TOpCat || this.token instanceof TOpPlus || 
					this.token instanceof TOpMinus || this.token instanceof TOpMult || 
					this.token instanceof TOpDiv || this.token instanceof TOpGt || 
					this.token instanceof TOpGteq || this.token instanceof TOpEq || 
					this.token instanceof TOpAssign) {
				message = "operator:" + token.getText();				
			
			// string literals
			} else if(this.token instanceof TStrLit) {
				message = "string literal:" + token.getText();				
			
			// others
			} else {
				message = token.getClass() + " " + token.getText();				
			}
			
			// print output
			System.out.println(filename + ":" + token.getLine() + "," + token.getPos() + ":" + message);
		}
	}
}
