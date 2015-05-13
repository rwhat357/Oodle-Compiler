package Declarations;

import cps450.Type;

public class Declaration {
	
	public final Type type;
	final protected String info;
	
	public Declaration(Type t , String notes) {
		this.type = t;
		this.info = notes;
	}

	public Type getType() {
		return type;
	}
	
	public String getStringInfo(){
		return this.info;
	}
}
