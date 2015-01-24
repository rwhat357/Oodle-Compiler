package cps450;
import cps450.oodle.analysis.*;
import cps450.oodle.lexer.*;
import cps450.oodle.node.*;

public final class Helper {

	
	// private constructor
	private Helper () { 
        return;
    }
	
	
    /* RECEIVES: a token
     * FUCNTION: Gets the token's class type, ie. type of "and", "while" and "for" is "keywords".
     * RETURNS: a string that identifies the class of the token
     * 			This string will be one of the following:
     * 			- blank
     * 			- newline
     * 			- keyword
     * 			- miscellaneous
     * 			- unknown
     * 			- not found
     * 
     */
    public static String getTokenType(Token token) {
    			// keywords
    			if (token instanceof TWhitespace )
    				return "whitespace";
    			
    			else if (token instanceof TNewline)
    				return "newline";
    			
    			else if (token instanceof TBoolean)
    				return "keyword";
    			
    			else if (token instanceof TBegin)
    				return "keyword";
    			
    			else if (token instanceof TClass)
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
    			else if (token instanceof TAndOp)
    				return "operator";
    			
    			else if (token instanceof TAddOp)
    				return "operator";
    			
    			else if (token instanceof TSubOp)
    				return "operator";
    			
    			else if (token instanceof TMulOp)
    				return "operator";
    			
    			else if (token instanceof TDivOp)
    				return "operator";
    			
    			else if (token instanceof TGtOp)
    				return "operator";
    			
    			else if (token instanceof TGteqOp)
    				return "operator";
    			
    			else if (token instanceof TEqOp)
    				return "operator";
    			
    			// Miscellaneous
    			else if (token instanceof TAssign)
    				return "miscellaneous";
    			
    			else if (token instanceof TLPar)
    				return "miscellaneous";
    			
    			else if (token instanceof TRPar)
    				return "miscellaneous";
    			
    			else if (token instanceof TLBracket)
    				return "miscellaneous";
    			
    			else if (token instanceof TRBracket)
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
    			else if (token instanceof TIdentifier)
    				return "identifier";
    			
    			else if (token instanceof TIntLit)
    				return "integer lit";
    			
    			else if (token instanceof TStrLit)
    				return "string lit";
    			
    			else if (token instanceof TComment)
    				return "comment";

    			// lexical errors
    			else if (token instanceof TUnterminatedString)
    				return "unterminated string";
    			
    			else if (token instanceof TIllegalString)
    				return "illegal string";
    			
    			else if (token instanceof TUnrecognizedChar)
    				return "unrecognized character";
    			
    			else if (token instanceof TConsumeCrLf)
    				return "consume cr lf";


    				
    			return "ERROR: Oodle.getType(): Type not found";
    }
	
	
}
