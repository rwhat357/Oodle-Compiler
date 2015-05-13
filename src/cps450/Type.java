package cps450;


public class Type {
	  public static final Type 
  	ARRAY = new Type("array"),
  	BOOLEAN = new Type("boolean"),
  	ERROR = new Type("<error>"),
  	INT = new Type("int"),
  	STRING = new Type("string");
  
  protected String name; 
  
 
  public Type(String name) {
      this.name = name;
  }
  
 
  public String getType() {
  	return name;
  }
  
  
	@Override
	public String toString() { 
		return name;
	}


}
