
package cps450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Declarations.MethDec;


public class MethodParser {
	
	
	private HashMap<String, MethDec> methList = new HashMap<String, MethDec>();
	
	public MethodParser() {
		
	}

	public List<MethDec> getParams(){ //get my parameters
		List<MethDec> paramsList = new ArrayList<MethDec>();
		for(int i = 0; i < methList.size(); ++i) {
			Iterator<Entry<String, MethDec>> it = methList.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, MethDec> pair = (Map.Entry<String, MethDec>)it.next();
				if(String.valueOf(pair.getValue()) == String.valueOf(i)) {
					paramsList.add(pair.getValue());
				}
			}
		}
		return paramsList;
	}
	public void addMethod(MethDec newMeth) {
		methList.put(newMeth.getName(), newMeth);
	}
	

	public int getNumParamsForMethod(String id) {
		return methList.get(id).getNumParams();
	}
	 

	public boolean isNameOfMethod (String id) {
		MethDec theDecl = methList.get(id);
		
		if(theDecl != null) {
			return true;
		} else {
			return false;
		}
	}
}
