
package cps450;
import java.util.*;

import Declarations.ArgDec;
import Declarations.MethDec;
import Declarations.VarDec;
import cps450.Globals;
import cps450.oodle.analysis.DepthFirstAdapter;
import cps450.oodle.node.*;

/* check the semantics of the Oodle source code */
public class SemanticChecker extends DepthFirstAdapter {

	private HashMap<Node, Type> attributeGrammarMap = new HashMap<Node, Type>(); 
	private SymbolTable symbolTable = Globals.symbolTable; 
	private MethodParser methodTracker = Globals.methodTracker; 
	
	private int localVariableOffset = 0; 
	private int parameterOffset = 0; 
	private int numClasses = 0; 
	private boolean weAreInAnArgDecList = false; 
	
	public SemanticChecker() {
		readOodleStandardLibrary();
	}
	
	/* overriding methods on DepthFistAdapter */
	
	@Override
	public void inAClassDef(AClassDef node) {
		
		++numClasses;
		
		if(numClasses > 1) {
			reportError(node.getStid().getLine(), node.getStid().getPos(), "To many class declarations: unsupported");
		}
		symbolTable.beginScope();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void inAMethodDecl(AMethodDecl node) {

		localVariableOffset = -4; 
		
		LinkedList tailList, typeList = new LinkedList();
		String id = node.getStid().getText();
		Type t;
		MethDec theMethod;

		if (node.getArgumentDeclList() != null) {
			AArgumentDeclList declList = (AArgumentDeclList) node.getArgumentDeclList();

			if (declList.getArgDecl() != null) {
				AArgDecl theDecl = (AArgDecl) declList.getArgDecl();
				tailList = declList.getArgDeclTail();

				typeList.add(typeFromNode(theDecl.getType()));

				for (Iterator it = tailList.iterator(); it.hasNext();) {
					AArgDeclTail tail = (AArgDeclTail) it.next();
					PType theType = ((AArgDecl) tail.getArgDecl()).getType();
					typeList.add(typeFromNode(theType));
				}
			}
		}

		if (node.getPartType() == null) {
			t = new Type("null");
		} else {
			t = typeFromNode(node.getPartType());
		}

		theMethod = new MethDec(id, t, typeList, typeList.size());

		symbolTable.push(id, theMethod);
		methodTracker.addMethod(theMethod);
		
		symbolTable.beginScope();
	}	
	
	@Override
	public void caseAMethodDecl(AMethodDecl node) {
		
        parameterOffset = 8; 
		
	      inAMethodDecl(node);
	        if(node.getStid() != null)
	        {
	            node.getStid().apply(this);
	        }
	        if(node.getLPar() != null)
	        {
	            node.getLPar().apply(this);
	        }
	        if(node.getArgumentDeclList() != null)
	        {
	        	weAreInAnArgDecList = true;
	            node.getArgumentDeclList().apply(this);
	            weAreInAnArgDecList = false;
	        }
	        
	        if(node.getRPar() != null)
	        {
	            node.getRPar().apply(this);
	        }
	        if(node.getPartType() != null)
	        {
	            node.getPartType().apply(this);
	        }
	        if(node.getIs() != null)
	        {
	            node.getIs().apply(this);
	        }
	        if(node.getN1() != null)
	        {
	            node.getN1().apply(this);
	        }
	        {
	            List<PVarDecl> copy = new ArrayList<PVarDecl>(node.getVarDecl());
	            for(PVarDecl e : copy)
	            {
	                e.apply(this);
	            }
	        }
	        if(node.getBegin() != null)
	        {
	            node.getBegin().apply(this);
	        }
	        if(node.getN2() != null)
	        {
	            node.getN2().apply(this);
	        }
	        {
	            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
	            for(PStmt e : copy)
	            {
	                e.apply(this);
	            }
	        }
	        if(node.getEnd() != null)
	        {
	            node.getEnd().apply(this);
	        }
	        if(node.getEndid() != null)
	        {
	            node.getEndid().apply(this);
	        }
	        if(node.getN3() != null)
	        {
	            node.getN3().apply(this);
	        }
	        outAMethodDecl(node);
	}
	
	@Override
	public void outAAddExpr4(AAddExpr4 node) {
	
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.INT && t2 == Type.INT) {
			result = Type.INT;
		} else {
			reportError(node.getOpPlus().getLine(), node.getOpPlus().getPos(), "Operand of type: " + t1 + " doesn't match operand of type: " + t2);
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAAndExpr1(AAndExpr1 node) {
		
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.BOOLEAN && t2 == Type.BOOLEAN) {
			result = Type.BOOLEAN;
		} else {
			reportError(node.getAnd().getLine(), node.getAnd().getPos(), "Operand of type: " + t1 + " doesn't match second type " + t2);
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAArgDecl(AArgDecl node) {		
	
		String id = node.getId().getText();
		Type t = typeFromNode(node.getType());
		ArgDec theArgDecl = new ArgDec(t, parameterOffset);
		parameterOffset += 4; 

		Symbol s = symbolTable.lookup(id);

		if (s != null) {
			if (s.getScope() == symbolTable.getScope()) {
				reportError(node.getId().getLine(), node.getId().getPos(), "Use of redeclared variable: " + id);
			} else {
				symbolTable.push(id, theArgDecl);
			}

		} else {
			symbolTable.push(id, theArgDecl);
		}
	}

	@Override
	public void outAArrInxExpr9(AArrInxExpr9 node) {
		
		attributeGrammarMap.put(node, Type.ERROR);
		reportError(node.getQuart().getLine(), node.getQuart().getPos(), "Arrays are not supported.");
	}

	@Override
	public void outAAssignStmt(AAssignStmt node) {
		
		String id = node.getId().getText();
		Symbol sym = symbolTable.lookup(id);

		if (sym == null) {
			reportError(node.getId().getLine(), node.getId().getPos(), "Undeclared variable: " + id);
			return;
		}

		Type lhsType = sym.getDecl().type;
		Type rhsType = attributeGrammarMap.get(node.getExpr());

		if (lhsType != rhsType) {
			reportError(node.getId().getLine(), node.getId().getPos(), "Operand of type: " + lhsType + " is different from " + rhsType);
		}		
		
		if(node.getPartArrInx().size() != 0) {
			reportError(node.getId().getLine(), node.getId().getPos(), "Unsupported array stuff used");
		}
		
		Globals.symbolMap.put(node.getId(), symbolTable.lookup(node.getId().getText()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void outACallExpr9(ACallExpr9 node) {
		
		LinkedList argsList = new LinkedList(), typeList = new LinkedList();
		Type theExprType;
		String id = node.getId().toString();
		Symbol sym = symbolTable.lookup(id);
		MethDec methDcl;
		Type t1, t2;

		if (sym == null) {
			
			id = node.getId().toString().trim();
			sym = symbolTable.lookup(id);
			
			if(sym == null) {
				reportError(node.getId().getLine(), node.getId().getPos(), "Undefined function called: " + id);
				return;
			}
		}
		
		if(node.getExprList() == null) { 
			methDcl = (MethDec) sym.getDecl();
			argsList = methDcl.getArgsList();
			
			if(argsList != null) {
				if(argsList.size() != 0) {
					reportError(node.getId().getLine(), node.getId().getPos(), "Incorrect number of arguments supplied to: " + id);
				}
			}
		} else {		
				
			argsList = ((AExprList)node.getExprList()).getExprListTail();
			theExprType = attributeGrammarMap.get(((AExprList)node.getExprList()).getExpr());
			
			typeList.add(theExprType);
					
			for (Iterator it = argsList.iterator(); it.hasNext(); ) {
				AExprListTail exprTail = (AExprListTail) it.next();				
				typeList.add(attributeGrammarMap.get(exprTail.getExpr()));
			}			 

			methDcl = (MethDec) sym.getDecl();
			argsList = methDcl.getArgsList();
			
			if(argsList == null) {
				reportError(node.getId().getLine(), node.getId().getPos(), "Incorrect number of arguments supplied to: " + id);
			} else {
			 
				Iterator i2 = typeList.iterator();
					 
		
				for(Iterator it = argsList.iterator(); it.hasNext(); ) {
					t1 = (Type) it.next();
					 
					if(!i2.hasNext()) {
						reportError(node.getId().getLine(), node.getId().getPos(), "Incorrect number of arguments supplied to: " + id);
						return;
					} else {
						t2 = (Type)i2.next();
					}
					 
					if(t1 != t2) {
						reportError(node.getId().getLine(), node.getId().getPos(), "Incorrect argument(s) supplied to: " + id);
						return;
					}
				}	
			}
		}		
		
		attributeGrammarMap.put(node, sym.getType());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void outACallStmt(ACallStmt node) {
		
		LinkedList argsList = new LinkedList(), typeList = new LinkedList();
		Type theExprType;
		String id = node.getId().toString();
		Symbol sym = symbolTable.lookup(id);
		MethDec methDcl;
		Type t1, t2;

		if (sym == null) {
			
			id = node.getId().toString().trim();
			sym = symbolTable.lookup(id);
			
			if(sym == null) {
				reportError(node.getId().getLine(), node.getId().getPos(), "Undefined function called: " + id);
				return;
			}
		}

				
		if(node.getExprList() == null) { 
			methDcl = (MethDec) sym.getDecl();
			argsList = methDcl.getArgsList();
			
			if(argsList != null) {
				if(argsList.size() != 0) {
					reportError(node.getId().getLine(), node.getId().getPos(), "To many/to few arguements for: " + id);
				}
			}
		} else {		
				
			argsList = ((AExprList)node.getExprList()).getExprListTail();
			theExprType = attributeGrammarMap.get(((AExprList)node.getExprList()).getExpr());
			
			typeList.add(theExprType);
					
			for (Iterator it = argsList.iterator(); it.hasNext(); ) {
				AExprListTail exprTail = (AExprListTail) it.next();				
				typeList.add(attributeGrammarMap.get(exprTail.getExpr()));
			}			 

			methDcl = (MethDec) sym.getDecl();
			argsList = methDcl.getArgsList();
			
			if(argsList == null) {
				reportError(node.getId().getLine(), node.getId().getPos(), "To many/to few arguements given to: " + id);
			} else {
			 
				Iterator i2 = typeList.iterator();
					 
				
				for(Iterator it = argsList.iterator(); it.hasNext(); ) {
					t1 = (Type) it.next();
					 
					if(!i2.hasNext()) {
						reportError(node.getId().getLine(), node.getId().getPos(), "To many/to few arguements given to: " + id);
						return;
					} else {
						t2 = (Type)i2.next();
					}
					 
					if(t1 != t2) {
						reportError(node.getId().getLine(), node.getId().getPos(), "Wrong arguement given to " + id + "; need:" + t1 + "; received:" + t2);
						return;
					}
				}	
			}
		}
		
		attributeGrammarMap.put(node, sym.getType());
	}

	@Override
	public void outACatExpr3(ACatExpr3 node) {
		
		
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.STRING && t2 == Type.STRING) {
			result = Type.STRING;
		} else {
			reportError(node.getOpCat().getLine(), node.getOpCat().getPos(), "Type: " + t1 + " doesn't work with type: " + t2 );
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
		
		
		reportError(node.getOpCat().getLine(), node.getOpCat().getPos(), "Strings are not supported.");
	}

	@Override
	public void outADivExpr5(ADivExpr5 node) {
		
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.INT && t2 == Type.INT) {
			result = Type.INT;
		} else {
			reportError(node.getOpDiv().getLine(), node.getOpDiv().getPos(), "The type of the first operand (" + t1 + ") is not compatible with the type of the second operand (" + t2 + ")");
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAEqExpr2(AEqExpr2 node) {
		
		Type result;
		Type e1Type = attributeGrammarMap.get(node.getStarts());
		Type e2Type = attributeGrammarMap.get(node.getEnds());

		if (e1Type == Type.INT && e2Type == Type.INT) {
			result = Type.BOOLEAN;
		} else if (e1Type == Type.STRING && e2Type == Type.STRING) {
			result = Type.BOOLEAN;
		} else if (e1Type == e2Type) {
			result = Type.ERROR;
			reportError(node.getOpEq().getLine(), node.getOpEq().getPos(),
					"Operator > not compatible with type: " + e1Type.toString());
		} else {
			result = Type.ERROR;
			reportError(node.getOpEq().getLine(), node.getOpEq().getPos(), "The type of the first operand (" + e1Type + ") is not compatible with the type of the second operand (" + e2Type + ").");
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAFalseLit(AFalseLit node) {
		attributeGrammarMap.put(node, Type.BOOLEAN);
	}

	@Override
	public void outAGtEqExpr2(AGtEqExpr2 node) {
		
		Type result;
		Type e1Type = attributeGrammarMap.get(node.getStarts());
		Type e2Type = attributeGrammarMap.get(node.getEnds());

		if (e1Type == Type.INT && e2Type == Type.INT) {
			result = Type.BOOLEAN;
		} else if (e1Type == Type.STRING && e2Type == Type.STRING) {
			result = Type.BOOLEAN;
		} else if (e1Type == e2Type) {
			result = Type.ERROR;
			reportError(node.getOpGteq().getLine(), node.getOpGteq().getPos(),
					"Operator > not compatible with type: " + e1Type.toString());
		} else {
			result = Type.ERROR;
			reportError(node.getOpGteq().getLine(), node.getOpGteq().getPos(), "The type of the first operand (" + e1Type + ") is not compatible with the type of the second operand (" + e2Type + ").");
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAGtExpr2(AGtExpr2 node) {
		
		Type result;
		Type e1Type = attributeGrammarMap.get(node.getStars());
		Type e2Type = attributeGrammarMap.get(node.getEnds());

		if (e1Type == Type.INT && e2Type == Type.INT) {
			result = Type.BOOLEAN;
		} else if (e1Type == Type.STRING && e2Type == Type.STRING) {
			result = Type.BOOLEAN;
		} else if (e1Type == e2Type) {
			result = Type.ERROR;
			reportError(node.getOpGt().getLine(), node.getOpGt().getPos(),
					"Operator > not compatible with type: " + e1Type.toString());
		} else {
			result = Type.ERROR;
			reportError(node.getOpGt().getLine(), node.getOpGt().getPos(), "The type of the first operand (" + e1Type + ") is not compatible with the type of the second operand (" + e2Type + ").");
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAIdExpr9(AIdExpr9 node) {
		
		Type result = Type.ERROR;
		String id = node.getE().getText();
		Symbol s = symbolTable.lookup(id);
		
		if (s == null) {
			reportError(node.getE().getLine(), node.getE().getPos(), "Undefined variable: " + id);
		} else {
			result = s.getDecl().type;
		}

		attributeGrammarMap.put(node, result);
		Globals.symbolMap.put(node, s);
	}

	@Override
	public void outAIfStmt(AIfStmt node) {
		Type whileCond = attributeGrammarMap.get((node.getExpr()));

		if (whileCond != Type.BOOLEAN) {
			reportError(node.getI1().getLine(), node.getI1().getPos(), "Non-bool item found:  " + whileCond);
		}
	}

	@Override
	public void outAInheritsFrom(AInheritsFrom node) {
		
		// Inheritance is an unsupported feature
		reportError(node.getId().getLine(), node.getId().getPos(), "Unsuporrted: Inheritance");
	}

	@Override
	public void outAIntLit(AIntLit node) {
		attributeGrammarMap.put(node, Type.INT);
	}

	@Override
	public void outALitExpr9(ALitExpr9 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outALoopStmt(ALoopStmt node) {
		Type whileCond = attributeGrammarMap.get((node.getExpr()));

		if (whileCond != Type.BOOLEAN) {
			reportError(node.getWhile().getLine(), node.getWhile().getPos(), "Non-boolean item:  " + whileCond);
		}
	}

	@Override
	public void outAMeExpr9(AMeExpr9 node) {
		
		// Objects are an unsupported feature
		attributeGrammarMap.put(node, Type.ERROR);
		reportError(node.getMe().getLine(), node.getMe().getPos(), "Unsupported item: me");
	}

	@Override
	public void outAMethodDecl(AMethodDecl node) {
		
		try {
			symbolTable.endScope();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	@Override
	public void outAMultExpr5(AMultExpr5 node) {
		
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.INT && t2 == Type.INT) {
			result = Type.INT;
		} else {
			reportError(node.getOpMult().getLine(), node.getOpMult().getPos(), "First operand type: " + t1 + " doesn't match second type: " + t2);
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outANegExpr6(ANegExpr6 node) {
		
		Type t = attributeGrammarMap.get(node.getE());
		
		if(t == null || t != Type.INT) {
			attributeGrammarMap.put(node, Type.ERROR);
		} else {		
			attributeGrammarMap.put(node, Type.INT);
		}
	}

	@Override
	public void outANewExpr9(ANewExpr9 node) {
		
		attributeGrammarMap.put(node, Type.ERROR);
		reportError(node.getNew().getLine(), node.getNew().getPos(), "Unsupported feature: new");
	}

	@Override
	public void outANotExpr6(ANotExpr6 node) {
		
		Type t = attributeGrammarMap.get(node.getE());
		
		if(t == null || t != Type.BOOLEAN) {
			attributeGrammarMap.put(node, Type.ERROR);
		} else {		
			attributeGrammarMap.put(node, Type.BOOLEAN);
		}
	}

	@Override
	public void outANullLitLit(ANullLitLit node) {

		attributeGrammarMap.put(node, Type.ERROR);
		reportError(node.getNull().getLine(), node.getNull().getPos(), "Unsupported feature: null");
	}

	@Override
	public void outAPartArrInx(APartArrInx node) {
		
		attributeGrammarMap.put(node, Type.ERROR);
		reportError(node.getLBrkt().getLine(), node.getLBrkt().getPos(), "Unsupported feature: array");
	}

	@Override
	public void outAPartExp(APartExp node) {
		if(node.getExpr() != null) {
			reportError(node.getOpAssign().getLine(), node.getOpAssign().getPos(), "Unsupported feature: variable declaration style");
		}
	}

	@Override
	public void outAPartType(APartType node) {
		Type t = attributeGrammarMap.get(node.getType());
		
		if(t == null) {
			reportError(node.getColon().getLine(), node.getColon().getPos(), "No type given for this class variable declaraiton");
			attributeGrammarMap.put(node, Type.ERROR);
		} else {
			attributeGrammarMap.put(node, t);
		}		
	}

	@Override
	public void outAOrExpr(AOrExpr node) {
		
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.BOOLEAN && t2 == Type.BOOLEAN) {
			result = Type.BOOLEAN;
		} else {
			reportError(node.getOr().getLine(), node.getOr().getPos(), "First operand of type: " + t1 + " doesnt match second operand of type: " + t2);
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAOtherExpr(AOtherExpr node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAOtherExpr1(AOtherExpr1 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAOtherExpr2(AOtherExpr2 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAOtherExpr3(AOtherExpr3 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAOtherExpr4(AOtherExpr4 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAOtherExpr5(AOtherExpr5 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAOtherExpr6(AOtherExpr6 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}

	@Override
	public void outAParExpr9(AParExpr9 node) {
		attributeGrammarMap.put(node, attributeGrammarMap.get(node.getE()));
	}
	
	@Override
	public void outAPosExpr6(APosExpr6 node) {
		
		Type t = attributeGrammarMap.get(node.getE());
		
		if(t == null || t != Type.INT) {
			attributeGrammarMap.put(node, Type.ERROR);
		} else {		
			attributeGrammarMap.put(node, Type.INT);
		}
	}

	@Override
	public void outAStrLit(AStrLit node) {
				
		reportError(node.getE().getLine(), node.getE().getPos(),"Strings are not supported.");
		attributeGrammarMap.put(node, Type.STRING);
	}

	@Override
	public void outASubExpr4(ASubExpr4 node) {
	
		Type t1 = attributeGrammarMap.get(node.getStarts());
		Type t2 = attributeGrammarMap.get(node.getEnds());

		assert t1 != null && t2 != null;

		Type result;

		if (t1 == Type.INT && t2 == Type.INT) {
			result = Type.INT;
		} else {
			reportError(
					node.getOpMinus().getLine(),
					node.getOpMinus().getPos(),
					"First operand: "
							+ t1
							+ " isn't the same as second operand type: "
							+ t2);
			result = Type.ERROR;
		}

		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outATrueLit(ATrueLit node) {
		Type result = Type.BOOLEAN;
		attributeGrammarMap.put(node, result);
	}

	@Override
	public void outAVarDecl(AVarDecl node) {
		String id = node.getId().getText();
		Type t = Type.ERROR;
		VarDec theVarDecl;
						
		
		if(node.getPartType() != null) {
			t = attributeGrammarMap.get(((APartType) node.getPartType()).getType());
		}
		
		if(symbolTable.getScope() == Globals.localS) {
			if(weAreInAnArgDecList) {
				localVariableOffset -= 4;
				theVarDecl = new VarDec(t, localVariableOffset , "outAVarDec");
			} else {
				parameterOffset += 4;
				theVarDecl = new VarDec(t, parameterOffset , "outAVarDec");
			}
		} else {
			theVarDecl = new VarDec(t, Globals.noOffset , "outAVarDec");
		}

		Symbol s = symbolTable.lookup(id);

		
		if (s != null) {						
			
			if (s.getScope() == symbolTable.getScope()) {
				reportError(node.getId().getLine(), node.getId().getPos(), "Use of redeclared variable: " + id);
				return; 
			}		
			
			id = (id + "_" + symbolTable.getScope()).toString();
			TId convertID = new TId(id);
			node.setId(convertID);
			symbolTable.push(id, theVarDecl);
			Globals.symbolMap.put(node, s);
			
		} else {
			symbolTable.push(id, theVarDecl);
			Globals.symbolMap.put(node, s);
		}
	}

	@Override
	public void outAArrType(AArrType node) {
		attributeGrammarMap.put(node, Type.ERROR);
		reportError(node.getLBrkt().getLine(), node.getLBrkt().getPos(), "The array is not supported.");
	}

	@Override
	public void outABooleanType(ABooleanType node) {
		attributeGrammarMap.put(node, Type.BOOLEAN);
	}

	@Override
	public void outAIdType(AIdType node) {
		attributeGrammarMap.put(node, new Type(node.getId().getText()));
	}

	@Override
	public void outAIntType(AIntType node) {
		attributeGrammarMap.put(node, Type.INT);
	}

	@Override
	public void outAStringType(AStringType node) {
		attributeGrammarMap.put(node, Type.STRING);
	}
	
	/* Helper methods */
	private void reportError(int line, int col, String msg) {
		System.out.println(Globals.filename + ":" + line + "," + col + ":" + msg);
		Globals.incSemanticErrors();
	}

	public Type typeFromNode(PPartType node) {

		PType p = ((APartType) node).getType();
		return typeFromNode(p); 
	}

	public Type typeFromNode(PType p) {

		if (p instanceof AArrType) {
			return Type.ARRAY;
		} else if (p instanceof ABooleanType) {
			return Type.BOOLEAN;
		} else if (p instanceof AIdType) {
			AIdType aType = (AIdType) p.clone();
			return new Type(aType.getId().getText());
		} else if (p instanceof AIntType) {
			return Type.INT;
		} else if (p instanceof AStringType) {
			return Type.STRING;
		} else {
			return Type.ERROR;
		}
	}
	
	/* add builtin reader and writer objects */
	@SuppressWarnings("unchecked")
	public void readOodleStandardLibrary() {		
		
		String readIntString = "readint";
		MethDec readMethod = new MethDec(readIntString, Type.INT, null, 0);
		symbolTable.push(readIntString, readMethod);
		methodTracker.addMethod(readMethod);
		
		String inReader = "in";
		VarDec inVar = new VarDec(new Type("inreader"), Globals.noOffset,"readOodStdLib");
		symbolTable.push(inReader, inVar);
		
		String writeIdString = "writeint";
		LinkedList writeParams = new LinkedList();
		writeParams.add(Type.INT);		
		MethDec writeMethod = new MethDec(writeIdString, null, writeParams, 1);					
		symbolTable.push(writeIdString, writeMethod);
		methodTracker.addMethod(writeMethod);
		
		String outWriter = "out";
		VarDec outVar = new VarDec(new Type("outwriter"), Globals.noOffset, "outwriter");
		symbolTable.push(outWriter, outVar);
	}
}
