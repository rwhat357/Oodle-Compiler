
package Declarations;

import java.util.LinkedList;

import cps450.Type;
import cps450.oodle.node.PType;

public class MethDec extends Declaration {

	private LinkedList<PType> argsList; 
	private String name; 
	private int numParams;

	public MethDec(String name, Type returnType, LinkedList<PType> argsList, int numParms) {		
		super(returnType, name);
		this.name = name;
		this.argsList = argsList;
		this.numParams = numParms;
	}
	
	public void addToList(PType p){
		argsList.add(p);
	}
	
	public int getListSize(){
		return argsList.size();
	}
	
	public boolean doesMethListContain(PType p){
			for (PType e : argsList)
			   if (e == p)
			      return true;
			return false;
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public LinkedList<PType> getArgsList() {
		return argsList;
	}
	

	public int getNumParams() {
		return numParams;
	}
		
}
