package Declarations;

import cps450.Type;

public class VarDec extends Declaration {
	private int offset;	


	public VarDec(Type t, int offset, String note) {
		super(t,note);
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

}
