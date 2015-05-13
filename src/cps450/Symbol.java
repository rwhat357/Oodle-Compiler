
package cps450;

import java.util.ArrayList;

import Declarations.ArgDec;
import Declarations.Declaration;
import Declarations.VarDec;
import cps450.oodle.node.Node;


public class Symbol {	
	
	
	private final String name;
	private final Declaration decl; 
	private ArrayList attributes;
	private int scope;	
	private boolean isGlobalVar = false;
	private boolean isClassVar = false;
	private boolean isLocalVar = false;
	
	
	public Symbol(String name, Declaration decl, int scope) {
		this.name = name;
		this.decl = decl;
		this.scope = scope;
		
		if(this.decl instanceof ArgDec) {
			return;
		} 
		
		if(this.decl instanceof VarDec && this.scope == Globals.globalS) {
			isGlobalVar = true;
		} else {
			isGlobalVar = false;
		}
		
		if(this.decl instanceof VarDec && this.scope == Globals.classS) {
			isClassVar = true;
		} else {
			isClassVar = false;
		}
		
		if(this.decl instanceof VarDec && this.scope == Globals.localS) {
			isLocalVar = true;
		} else {
			isLocalVar = false;
		}
	}
	
	
	public void setAttribute(Object O) {
		attributes.add(O);
	}
	
	
	public int getScope() {
		return scope;
	}
		
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return decl.getType();
	}
	
	public Declaration getDecl() {
		return decl;
	}
	
	public boolean isSymbolGlobalVar() {
		return isGlobalVar;
	}
	
	public boolean isSymbolClassVar() {
		return isClassVar;
	}
	
	public boolean isSymbolLocalVar() {
		return isLocalVar;
	}
}
