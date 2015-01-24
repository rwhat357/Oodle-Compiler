package cps450.test;

import org.junit.*;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PushbackReader;

import cps450.oodle.lexer.Lexer;
import cps450.oodle.lexer.LexerException;
import cps450.oodle.node.*;

import junit.framework.TestCase;


public class LexerTest extends TestCase {
	Lexer lex;

	public void testSuccessfulScan() throws IOException, LexerException {
		
		lex = new Lexer(new PushbackReader(new InputStreamReader(getClass().getResourceAsStream(
				"lexertest.jub"))));
		
		// testing sample test from file
		assertNextToken(TGtOp.class);
		assertNextToken(TLPar.class);
		assertNextToken(TRPar.class);
		assertNextToken(TComma.class);
		assertNextToken(TAddOp.class);
		assertNextToken(TSubOp.class);
		assertNextToken(TMulOp.class);
		assertNextToken(TDivOp.class);
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TStrLit.class);
		assertNextToken(TIntLit.class);
		assertNextToken(TIdentifier.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TNewline.class);
		
		assertNextToken(TComment.class);
		assertNextToken(TNewline.class);
		assertNextToken(TIdentifier.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TIdentifier.class);
		assertNextToken(TNewline.class);
		
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TNewline.class);
		
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TLPar.class);
		assertNextToken(TUnrecognizedChar.class);
		assertNextToken(TRPar.class);
		assertNextToken(TNewline.class);
		
		// testing keywords
		assertNextToken(TBoolean.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TBegin.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TClass.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TElse.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TEnd.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TFalse.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TFrom.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TInherits.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TInt.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TIs.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TLoop.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TMe.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TNew.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TNull.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TString.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TThen.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TTrue.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TWhile.class);
		assertNextToken(TNewline.class);

		// testing keyword logical operators 
		assertNextToken(TAnd.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TOr.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TNot.class);
		assertNextToken(TWhitespace.class);
		
		// testing predefined operator
		assertNextToken(TAndOp.class);
		assertNextToken(TAddOp.class);
		assertNextToken(TSubOp.class);
		assertNextToken(TDivOp.class);
		assertNextToken(TGtOp.class);
		assertNextToken(TGteqOp.class);
		assertNextToken(TEqOp.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TAssign.class);
		assertNextToken(TLPar.class);
		assertNextToken(TRPar.class);
		assertNextToken(TLBracket.class);
		assertNextToken(TRBracket.class);
		assertNextToken(TComma.class);
		assertNextToken(TSemicolon.class);
		assertNextToken(TColon.class);
		assertNextToken(TPeriod.class);
		assertNextToken(TConsumeCrLf.class);
		
		// testing comments
		assertNextToken(TComment.class);
		assertNextToken(TNewline.class);
		
		// testing identifiers
		assertNextToken(TIdentifier.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TIdentifier.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TIdentifier.class);
		assertNextToken(TNewline.class);
		
		// testing string literals
		assertNextToken(TStrLit.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TStrLit.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TStrLit.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TStrLit.class);
		assertNextToken(TNewline.class);
		
		// testing unterminated strings
		assertNextToken(TUnterminatedString.class);
		assertNextToken(TNewline.class);
		assertNextToken(TUnterminatedString.class);
		assertNextToken(TNewline.class);
		
		// testing illegal strings
		assertNextToken(TIllegalString.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TIllegalString.class);
		assertNextToken(TWhitespace.class);
		assertNextToken(TIllegalString.class);
		assertNextToken(TNewline.class);

		// testing strings
		assertNextToken(TStrLit.class, "\"Fredy Whatley Finished Phase 1\"");
		assertNextToken(TNewline.class);
		assertNextToken(TIdentifier.class, "Code_check_done");
		assertNextToken(EOF.class);
		
		return;

	}

	private void assertNextToken(Class type, String value) throws LexerException, IOException {
		Token tok = lex.next();
		System.err.println(tok);
		assertTrue(tok.getClass() == type);
		assertTrue(tok.getText().equals(value));
		
	}

	private void assertNextToken(Class type) throws LexerException, IOException {
		Token tok = lex.next();
		System.err.println(tok);
		assertTrue(tok.getClass() == type);	

	}
}
