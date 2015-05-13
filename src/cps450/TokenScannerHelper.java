package cps450;
import cps450.oodle.analysis.*;
import cps450.oodle.lexer.*;
import cps450.oodle.node.*;

/* Class Helper: contains helper methods for other classes like OodleLexer
 * Methods: - public static String getTokenType(Token token){}
 * 			- 
 * 
 * 
 * 
 * 
 */
public final class TokenScannerHelper {

	
	// private constructor
	private TokenScannerHelper () { 
        return;
    }
	
	
    /** 
     * RECEIVES: a token
     * FUCNTION: Gets the token's class type, ie. type of "and", "while" and "for" is "keywords".
     * RETURNS: a string that identifies the class of the token
     * 			This string returned will be one of the following according to its type:
     * 			
     * 			 Token Type						Tokens
     * 			==========					   =========
     * 			- whitespace					Twhitespace
     * 			- newline						TCr		
     * 			- keyword						TBoolean, TBegin, TClasskey, TElse, TEnd, TFalse,
     * 											TFrom, TIf, TInherits, TInt, TIs, TMe, TNew,
     * 											TNull, TStringClass, TThen, TTrue, TWhile, TLoop 
     * 			- keyword op					TAnd, TOr, TNot
     * 			- operator						TAndOp, TPlus, TMinus, TMultiplication, 
     * 											TDivides, TGreaterThan, TLessThan, 
     * 											TGreaterThanEqual, TEqualOp 
     * 			- miscellaneous					TEquals, TLeftParen, TRightParen, TLeftBracket, 
     * 											TRightBracket, TComma, TSemicolon, TColon, TDot
     * 			- identifier					TIdentifier
     * 			- integer lit					TIntegerLiteral
     * 			- string lit 					TStringLiteral
     * 			- comment						TComment
     * 			- unterminated string			TUnterminatedString
     * 			- illegal string				TIllegalString
     * 			- unrecognized character		TUnrecognizedChar
     * 			- consume cr lf					TConsumeCrLf
     * 			- Type not found				If no tokens get matched, return "Type not found".
     */
    public static String getTokenType(Token token) {
    			// keywords
    			if (token instanceof TBlanks )
    				return "whitespace";
    			
    			else if (token instanceof TEmptyLine)
    				return "newline";
    			
    			else if (token instanceof TBoolean)
    				return "keyword";
    			
    			else if (token instanceof TBegin)
    				return "keyword";
    			
    			else if (token instanceof TClassId)
    				return "keyword";
    			
    			else if (token instanceof TElse)
    				return "keyword";
    			
    			else if (token instanceof TEnd)
    				return "keyword";
    			
    			else if (token instanceof TFalse)
    				return "keyword";
    			
    			else if (token instanceof TFrom)
    				return "keyword";
    			
    			else if (token instanceof TIf)
    				return "keyword";
    			
    			else if (token instanceof TInherits)
    				return "keyword";
    			
    			else if (token instanceof TInt)
    				return "keyword";
    			
    			else if (token instanceof TIs)
    				return "keyword";
    			
    			else if (token instanceof TLoop)
    				return "keyword";
    			
    			else if (token instanceof TMe)
    				return "keyword";
    			
    			else if (token instanceof TNew)
    				return "keyword";
    			
    			else if (token instanceof TNull)
    				return "keyword";
    			
    			else if (token instanceof TString)
    				return "keyword";
    			
    			else if (token instanceof TThen)
    				return "keyword";
    			
    			else if (token instanceof TTrue)
    				return "keyword";
    			
    			else if (token instanceof TWhile)
    				return "keyword";
    		
    			else if (token instanceof TLoop)
    				return "keyword";
    			
    			// keyword logical operators
    			else if (token instanceof TAnd)
    				return "keyword op";
    			
    			else if (token instanceof TOr)
    				return "keyword op";
    			
    			else if (token instanceof TNot)
    				return "keyword op";
    			
    			// predefined operators
    			else if (token instanceof TOpCat)
    				return "operator";
    			
    			else if (token instanceof TOpPlus)
    				return "operator";
    			
    			else if (token instanceof TOpMinus)
    				return "operator";
    			
    			else if (token instanceof TOpMult)
    				return "operator";
    			
    			else if (token instanceof TOpDiv)
    				return "operator";
    			
    			else if (token instanceof TOpGt)
    				return "operator";
    			
    			else if (token instanceof TOpGteq)
    				return "operator";
    			
    			else if (token instanceof TOpEq)
    				return "operator";
    			
    			// Miscellaneous
    			else if (token instanceof TOpAssign)
    				return "miscellaneous";
    			
    			else if (token instanceof TLPar)
    				return "miscellaneous";
    			
    			else if (token instanceof TRPar)
    				return "miscellaneous";
    			
    			else if (token instanceof TLBrkt)
    				return "miscellaneous";
    			
    			else if (token instanceof TRBrkt)
    				return "miscellaneous";
    			
    			else if (token instanceof TComma)
    				return "miscellaneous";
    			
    			else if (token instanceof TSemicolon)
    				return "miscellaneous";
    			
    			else if (token instanceof TColon)
    				return "miscellaneous";
    			
    			else if (token instanceof TPeriod)
    				return "miscellaneous";

    			// Others
    			else if (token instanceof TId)
    				return "identifier";
    			
    			else if (token instanceof TIntLit)
    				return "integer lit";
    			
    			else if (token instanceof TStrLit)
    				return "string lit";
    			
    			else if (token instanceof TComment)
    				return "comment";

    			// lexical errors
    			else if (token instanceof TUntermStrLit)
    				return "unterminated string";
    			
    			else if (token instanceof TIllegalStrLit)
    				return "illegal string";
    			
    			else if (token instanceof TIllegalChar)
    				return "unrecognized character";
    			
    			else if (token instanceof TCont)
    				return "consume cr lf";


    				
    			return "ERROR: Oodle.getType(): Type not found";
    }
	
	
}
