
package cps450;

import java.util.HashMap;

import cps450.oodle.node.Node;

public class Globals {
	
	public static SymbolTable symbolTable = new SymbolTable(); 
	public static HashMap<Node, Symbol>symbolMap = new HashMap<Node, Symbol>();
	public static MethodParser methodTracker = new MethodParser(); //Load with semantics unload with Code Generator

	public static String filename; 
	public static String sourceFilename; 
	public static String executableFilename; 
	private static int SemanticErrors;
	public static final int globalS = 0;
	public static final int classS = 1;
	public static final int localS = 2;
	public static final int noOffset = 0;
	
	
	public static void setFilename(String filename) {
		Globals.filename = filename;
	}
	
	public static void setSourceFilename(String sourceFilename) {
		Globals.sourceFilename = sourceFilename;
	}
	
	public static void SetExeFilename(String exeFilename) {		
		Globals.executableFilename = exeFilename.replaceAll(".ood", ".s");
	}
	
	public static void incSemanticErrors() {
		++SemanticErrors;
	}
	
	public static int getNumSemanticErrors() {
		return SemanticErrors;
	}
}
