
package cps450;

import java.util.*;

import Declarations.Declaration;
import Declarations.VarDec;


public class SymbolTable {
	
	
	private Deque<Symbol> symbolStack = new LinkedList<Symbol>(); 	
	private int currScope; 
	
	
	public SymbolTable() {
		
	
		currScope = Globals.globalS;
	}	 

	
	public Symbol push(String name, Declaration decl) {
		Symbol sym = new Symbol(name, decl, currScope);
		symbolStack.push(sym);
		return sym;
	}
		
	
	public Symbol lookup(String  name) {
		Iterator<Symbol> itr = symbolStack.iterator();
		Symbol s;
		
		while(itr.hasNext()) {
			s = (Symbol)itr.next();
			if(s.getName().equals(name)) {
				return s;
			}
		}
		
		
		s = null; 
		return s;
	}
	
	
	public void beginScope() {
		++currScope;
	}
	
	
	public void endScope()  {
		
		int numPops = 0;
		Iterator<Symbol> entries = symbolStack.iterator();
		
	    while (entries.hasNext()) {
	        
	        Symbol S = entries.next();
	        	        
	        if(S.getDecl() instanceof VarDec && S.getScope() == Globals.classS && currScope == Globals.localS) {  
	        	
	        } else if(S.getScope() == currScope) {	        
	        	++numPops; 	        	
	        }
	    }
	    
	    for(int i = 0; i < numPops; ++i) {
	    	symbolStack.pop(); 
	    }
		
	
		if((currScope - 1) < 0) {
			
		} else {
			--currScope;
		}
	}
	

	public int getScope() {
		return currScope;
	}
}
