package cps450;

public class ErrorCounter {
	private int parseErr =0;
	private int lexErr =0;
	private int semErr =0;
	private boolean unsupportedFeature = false;
	
	public ErrorCounter(){
		
	}
	
	public void setUnsupportedFeatures() {
		this.unsupportedFeature = true;
	}
	
	public boolean getUnsupportedFeatures() {
		return this.unsupportedFeature;
	}
	
	public void addLexErr(){
		this.lexErr +=1;
	}
	
	public void addParseErr(){
		this.parseErr +=1;
	}
	
	public void addSemErr(){
		this.semErr +=1;
	}
	
	public int getLexErr(){
		return this.lexErr;
	}
	
	public int getParserErr(){
		return this.parseErr;
	}
	
	public int getSemErr(){
		return this.semErr;
	}
	
	public int totalErrs(){
		return this.lexErr + this.parseErr + this.semErr;
	}

}

