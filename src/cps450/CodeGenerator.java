
package cps450;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Declarations.VarDec;
import cps450.oodle.analysis.DepthFirstAdapter;
import cps450.oodle.node.*;
import cps450.Globals;
import cps450.MethodParser;


// generate and print assembly code from Oodle source file
public class CodeGenerator extends DepthFirstAdapter {
	
	private MethodParser methodTracker = Globals.methodTracker; 
	PrintWriter prWr;
	final String startComment = "# ", oneSpace = " ", positionSeparator = " > ", newline = "\n";
	
	boolean notIsPresent; // is the keyword not present on this instruction
	boolean needToEmitDataDirective; // do we need to produce data directives
	boolean isStart; // are we working with the main method
	int indentCount = 0; // keep the number of indentation we need to write before writing the code
	int ifLabelCounter = 0, whileLabelCounter = 0; // labels for if and while jumps. i.e. L1, L2
	
	public CodeGenerator() {
		try {
			prWr = new PrintWriter(new FileWriter(Globals.executableFilename));
		} catch (IOException e) {
			System.out.println("Error generating executble");
		}
	}
	
	// override methods from DepthFirstAdapter

	@Override
	public void inAClassDef(AClassDef node) {
		
		String comment;
		String position = node.getClassId().getLine() + ", " + node.getClassId().getPos();
		
		comment = startComment + position + positionSeparator + node.getClassId() + node.getStid() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		emitCode(".data" + newline);
	}
	
	@Override
	public void inAMethodDecl(AMethodDecl node) {
		
		String comment;
		String position = node.getStid().getLine() + ", " + node.getStid().getPos(),
			strtId = node.getStid().getText() + " ",
			lPar = node.getLPar().getText(),
			argDecls,
			rPar = node.getRPar().getText() + " ",
			opt_type,
			isKword = node.getIs().getText();
		
		if(node.getArgumentDeclList() != null) {
			argDecls = node.getArgumentDeclList().toString();
		} else {
			argDecls = "";
		}
		
		if(node.getPartType() != null) {
			opt_type = node.getPartType().toString() + " ";
		} else {
			opt_type = "";
		}
				
		comment = startComment + position + positionSeparator + strtId + lPar + argDecls + rPar + opt_type + isKword  + "(" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
		emitComment(comment);
		}

		++indentCount;		
	}	
	
	@Override
	public void caseAIfStmt(AIfStmt node) {
		
		String comment, code;
		String position = node.getI1().getLine() + ", " + node.getI1().getPos();
		++ifLabelCounter; 
		int localIfLabel = ifLabelCounter; 
		if(ArgOptions.getGOption()){
		comment = startComment + position + positionSeparator + node.getI1() + node.getExpr().toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
		emitComment(comment);}
		}
        inAIfStmt(node);
        if(node.getI1() != null)
        {
            node.getI1().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        
		code = "popl %eax";
		emitCode(code);
        
		if(notIsPresent) {
			code = "cmpl $0, %eax";
			emitCode(code);
			notIsPresent = false;
		} else {
			code = "cmpl $1, %eax";
			emitCode(code);
		}
		
		code = "je trueLabel" + localIfLabel;
		emitCode(code);
		
		code = "jmp falseLabel" + localIfLabel;
		emitCode(code);
		
		code = "trueLabel" + localIfLabel + ":";
		emitCode(code);
		
		++indentCount; 
        
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getN1() != null)
        {
            node.getN1().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        
		code = "jmp endIf" + localIfLabel;
		emitCode(code);
		
		--indentCount; 
        
		code = "falseLabel" + localIfLabel + ":";
		emitCode(code);
		
		++indentCount; 
        
        if(node.getPartElse() != null)
        {
            node.getPartElse().apply(this);
        }
        
		code = "jmp endIf" + localIfLabel;
		emitCode(code);
		
		--indentCount; 
        
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        if(node.getI2() != null)
        {
            node.getI2().apply(this);
        }
        if(node.getN2() != null)
        {
            node.getN2().apply(this);
        }
        outAIfStmt(node);
        
		code = "endIf" + localIfLabel + ":";
		emitCode(code);
	}
	
	@Override
	public void caseALoopStmt(ALoopStmt node) {
		
		String comment, code;
		String position = node.getL1().getLine() + ", " + node.getL1().getPos();
		++whileLabelCounter;
		int localWhileLabel = whileLabelCounter;
		
		comment = startComment + position + positionSeparator + node.getL1() + node.getWhile() + node.getExpr().toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
        inALoopStmt(node);
        if(node.getL1() != null)
        {
            node.getL1().apply(this);
        }
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        
		code = "loopWhile" + localWhileLabel + ":";
		emitCode(code);
		
		++indentCount;
        
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        
		comment = startComment + position + positionSeparator + node.getL1() + node.getWhile() + node.getExpr().toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %eax";
		emitCode(code);
		
		if(notIsPresent) {
			code = "cmpl $1, %eax";
			emitCode(code);
			notIsPresent = false;
		} else {
			code = "cmpl $0, %eax";
			emitCode(code);
		}
		
		code = "jne startLoopWhile" + localWhileLabel;
		emitCode(code);
		
		code = "jmp endLoopWhile" + localWhileLabel;
		emitCode(code);
		
		--indentCount;
		
		code = "startLoopWhile" + localWhileLabel + ":";
		emitCode(code);
		
		++indentCount;
        
        if(node.getN1() != null)
        {
            node.getN1().apply(this);
        }
        {
            List<PStmt> copy = new ArrayList<PStmt>(node.getStmt());
            for(PStmt e : copy)
            {
                e.apply(this);
            }
        }
        
		comment = startComment + position + positionSeparator + node.getL1() + node.getWhile() + node.getExpr().toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
		emitComment(comment);
		}
		code = "jmp loopWhile" + localWhileLabel;
		emitCode(code);
		
		--indentCount;
		
		code = "endLoopWhile" + localWhileLabel + ":";
		emitCode(code);
        
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        if(node.getL2() != null)
        {
            node.getL2().apply(this);
        }
        if(node.getN2() != null)
        {
            node.getN2().apply(this);
        }
        outALoopStmt(node);
	}
	
	@Override
	public void caseAMethodDecl(AMethodDecl node) {
		
		String code;		
		
		// check if we are in the main method
		if(node.getStid().getText().trim().equals("start")) {
			isStart = true;
		} else {
			isStart = false;
		}

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
            node.getArgumentDeclList().apply(this);
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
            
                        
            if (!copy.isEmpty()) {
            	needToEmitDataDirective = true;
            }      
            
            for(PVarDecl e : copy)
            {
            	if(needToEmitDataDirective) {
	    			code = ".data" + newline;
	    			emitCode(code);
	    			needToEmitDataDirective = false;
            	}
                e.apply(this);
            }
        }
       
		if(isStart) {
			code = ".text\n.global main\nmain:" + newline;
			emitCode(code);
		} else {
			int paramSpace = (methodTracker.getNumParamsForMethod(node.getStid().getText()) * 4) + 4 + 4; 
			code = ".text";
			emitCode(code);
			code = node.getStid().getText().trim() + ":";
			emitCode(code);
			code = "pushl %ebp"; 
			emitCode(code);
			code = "movl %esp, %ebp";
			emitCode(code);
			code = "subl $" + paramSpace + ", %esp" + newline; 
			emitCode(code);
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
        
        //If we are not in the main function
        if(!isStart) {
			code = "movl -4(%ebp), %eax\t#put return value in EAX"; 
			emitCode(code);
			code = "movl %ebp, %esp\t# clear off local variables"; 
			emitCode(code);
			code = "pop %ebp\t# restore old BP";
			emitCode(code);
			code = "ret\t# return from method" + newline;
			emitCode(code);
        }
        
        --indentCount;
	}

	@Override
	public void caseAArgumentDeclList(AArgumentDeclList node) {
		
        inAArgumentDeclList(node);
        {
            List<PArgDeclTail> copy = new ArrayList<PArgDeclTail>(node.getArgDeclTail());
            

            if(!copy.isEmpty()) {
	            for (int i = copy.size() - 1; i >= 0; --i) {
	            	PArgDeclTail e = copy.get(i);
	                e.apply(this);
	            }
            }
        }
        if(node.getArgDecl() != null)
        {
            node.getArgDecl().apply(this);
        }
        outAArgumentDeclList(node);
	}
		
	@Override
	public void caseAExprList(AExprList node) {
        inAExprList(node);
        {
            List<PExprListTail> copy = new ArrayList<PExprListTail>(node.getExprListTail());

            if(!copy.isEmpty()) {
	            for (int i = copy.size() - 1; i >= 0; --i) {
	            	PExprListTail e = copy.get(i);
	                e.apply(this);
	            }
            }
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAExprList(node);
	}

	@Override
	public void outAAddExpr4(AAddExpr4 node) {
		
		
		String comment, code;
		String position = node.getOpPlus().getLine() + ", " + node.getOpPlus().getPos();	
		
		comment = startComment + position + positionSeparator + node.toString().trim() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx";
		emitCode(code);
		
		code = "popl %eax";
		emitCode(code);
		
		code = "addl %ebx, %eax";
		emitCode(code);
		
		code = "pushl %eax" + newline;
		emitCode(code);
	}

	@Override
	public void outAAndExpr1(AAndExpr1 node) {
		
		String comment, code;
		String position = node.getAnd().getLine() + ", " + node.getAnd().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx";
		emitCode(code);
		
		code = "popl %eax";
		emitCode(code);
		
		code = "andl %eax, %ebx";
		emitCode(code);
		
		code = "pushl %eax" + newline;
		emitCode(code);
	}

	@Override
	public void outAAssignStmt(AAssignStmt node) {
		
		String comment, code;
		String position = node.getId().getLine() + ", " + node.getId().getPos();
		boolean isNameOfMethod = methodTracker.isNameOfMethod(node.getId().getText());
		Symbol s = Globals.symbolMap.get(node.getId());
		
		comment = startComment + position + positionSeparator + node.toString().trim() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);		
		}
		if(isNameOfMethod) { 
			code = "popl %eax";
			emitCode(code);
			code = "movl %eax, -4(%ebp)\t#put return value on to the stack" + newline; 
			emitCode(code);
		} else if(s != null) { 
			if(!s.isSymbolGlobalVar() && !s.isSymbolClassVar() && !s.isSymbolLocalVar()) { 
				VarDec v = (VarDec) s.getDecl();
				code = "popl %eax";
				emitCode(code);
				code = "movl %eax, " + v.getOffset() + "(%ebp)" + newline;
				emitCode(code);
			} else { 
				code = "popl %eax";
				emitCode(code);
				code = "movl %eax, var" + node.getId().getText() + newline;
				emitCode(code);
			}
		}
	}

	@Override
	public void outACallExpr9(ACallExpr9 node) {
		
		String comment, code;
		String position = node.getId().getLine() + ", " + node.getId().getPos();
		int numParamsToPop = methodTracker.getNumParamsForMethod(node.getId().getText()) * 4;
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "call " + node.getId().getText();
		emitCode(code);
		
		code = "addl $" + numParamsToPop + ", %esp"; 
		emitCode(code);
		
		code = "pushl %eax" + newline; 
		emitCode(code);
	}

	@Override
	public void outACallStmt(ACallStmt node) {
		
		String comment, code;
		String position = node.getId().getLine() + ", " + node.getId().getPos();
		int numParamsToPop = methodTracker.getNumParamsForMethod(node.getId().getText()) * 4;
		
		comment = startComment + position + positionSeparator + node.toString().trim() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "call " + node.getId().getText();
		emitCode(code);
		
		code = "addl $" + numParamsToPop + ", %esp" + newline; 
		emitCode(code);
	}

	@Override
	public void outADivExpr5(ADivExpr5 node) {
	
		String comment, code;
		String position = node.getOpDiv().getLine() + ", " + node.getOpDiv().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx"; 
		emitCode(code);
		
		code = "movl $0, %edx"; 
		emitCode(code);
		
		code = "popl %eax";
		emitCode(code);
		
		code = "idivl %ebx, %eax";
		emitCode(code);
		
		code = "addl %eax, %edx"; 
		emitCode(code);
		
		code = "pushl %eax" + newline; 
		emitCode(code);
	}

	@Override
	public void outAEqExpr2(AEqExpr2 node) {
		
		String comment, code;
		String position = node.getOpEq().getLine() + ", " + node.getOpEq().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx";				
		emitCode(code);
		
		code = "popl %eax";				
		emitCode(code);
		
		code = "cmpl %eax, %ebx";				
		emitCode(code);
		
		code = "seteb %al";				
		emitCode(code);
		
		code = "movzbl %al, %eax";				
		emitCode(code);
		
		code = "pushl %eax" + newline;				
		emitCode(code);
	}

	@Override
	public void outAFalseLit(AFalseLit node) {
		
		String comment, code;
		String position = node.getE().getLine() + ", " + node.getE().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + "(" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "pushl $0" + newline;
		emitCode(code);
	}

	@Override
	public void outAGtEqExpr2(AGtEqExpr2 node) {
		
		String comment, code;
		String position = node.getOpGteq().getLine() + ", " + node.getOpGteq().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx";				
		emitCode(code);
		
		code = "popl %eax";				
		emitCode(code);
		
		code = "cmpl %ebx, %eax";				
		emitCode(code);
		
		code = "setgeb %al";				
		emitCode(code);
		
		code = "movzbl %al, %eax";				
		emitCode(code);
		
		code = "pushl %eax";				
		emitCode(code);
	}

	@Override
	public void outAGtExpr2(AGtExpr2 node) {
		
		String comment, code;
		String position = node.getOpGt().getLine() + ", " + node.getOpGt().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx";				
		emitCode(code);
		
		code = "popl %eax";				
		emitCode(code);
		
		code = "cmpl %ebx, %eax";				
		emitCode(code);
		
		code = "setgb %al";				
		emitCode(code);
		
		code = "movzbl %al, %eax";				
		emitCode(code);
		
		code = "pushl %eax";				
		emitCode(code);
	}

	@Override
	public void outAIdExpr9(AIdExpr9 node) {
		
		String comment, code;
		String position = node.getE().getLine() + ", " + node.getE().getPos();		
		
		if(node.toString().trim().equals("out") || node.toString().trim().equals("in")) {
			return;
		} else {
			Symbol s = Globals.symbolMap.get(node);
			VarDec v = (VarDec) s.getDecl();
			
			comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
			if(ArgOptions.getGOption()){
				emitComment(comment);
			}
			if(!s.isSymbolGlobalVar() && !s.isSymbolClassVar() && !s.isSymbolLocalVar()) {
				code = "movl " + v.getOffset() + "(%ebp), %eax";
				emitCode(code);
				code = "pushl %eax" + newline;
				emitCode(code);
			} else {
				code = "pushl var" + node.getE().getText() + newline;				
				emitCode(code);
			}
		}
	}

	@Override
	public void outAIntLit(AIntLit node) {
		
		String comment, code;
		String position = node.getE().getLine() + ", " + node.getE().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "pushl $" + node.getE().getText() + newline;
		emitCode(code);
	}

	@Override
	public void outAMultExpr5(AMultExpr5 node) {
		
		String comment, code;
		String position = node.getOpMult().getLine() + ", " + node.getOpMult().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %eax";
		emitCode(code);
		
		code = "popl %ebx";
		emitCode(code);
		
		code = "imull %ebx";
		emitCode(code);
		
		code = "pushl %eax" + newline; 
		emitCode(code);
	}

	@Override
	public void outANegExpr6(ANegExpr6 node) {
	
		String comment, code;
		String position = node.getOpMinus().getLine() + ", " + node.getOpMinus().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %eax";
		emitCode(code);
		
		code = "negl %eax";
		emitCode(code);
		
		code = "pushl %eax" + newline;
		emitCode(code);
	}

	@Override
	public void outANotExpr6(ANotExpr6 node) {
		notIsPresent = true; 
	}

	@Override
	public void outAOrExpr(AOrExpr node) {
		
		String comment, code;
		String position = node.getOr().getLine() + ", " + node.getOr().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "popl %ebx";
		emitCode(code);
		
		code = "popl %eax";
		emitCode(code);
		
		code = "orl %eax, %ebx";
		emitCode(code);
		
		code = "pushl %eax" + newline;
		emitCode(code);
	}

	@Override
	public void outAStart(AStart node) {
		
		AClassDef cd = (AClassDef) node.getClassDef();
		String comment, code;
		String position = cd.getEndid().getLine() + ", " + cd.getEndid().getPos() ;
		
		comment = startComment + position + positionSeparator + "(" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		
		code = "pushl $0";
		emitCode(code);
		
		code = "call exit" + newline;
		emitCode(code);
	}

	@Override
	public void outASubExpr4(ASubExpr4 node) {
		
		String comment, code;
		String position = node.getOpMinus().getLine() + ", " + node.getOpMinus().getPos();	
		
		comment = startComment + position + positionSeparator + node.toString().trim() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		
		code = "popl %ebx";
		emitCode(code);
		
		code = "popl %eax";
		emitCode(code);
		
		code = "subl %ebx, %eax";
		emitCode(code);
		
		code = "pushl %eax" + newline;
		emitCode(code);
	}

	@Override
	public void outATrueLit(ATrueLit node) {
		
		String comment, code;
		String position = node.getE().getLine() + ", " + node.getE().getPos();
		
		comment = startComment + position + positionSeparator + node.toString() + "(" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = "pushl $1" + newline;
		emitCode(code);
	}

	@Override
	public void outAVarDecl(AVarDecl node) {
		
		String comment, code;
		String position = node.getId().getLine() + ", " + node.getId().getPos();
	
		comment = startComment + position + positionSeparator + node.toString().trim() + " (" + node.getClass() + ")";
		if(ArgOptions.getGOption()){
			emitComment(comment);
		}
		code = ".comm  var" + node.getId().getText() + ", 4, 4" + newline;
		emitCode(code);
	}

	@Override
	public void outStart(Start node) {
		prWr.close();
	}
	
	// Helper methods
	// write code  to file that we've gathered so far
	public void emitCode (String instr) {
				
		int counter = 0;
		
		while(counter < indentCount) {
			instr = "\t" + instr;
			++counter;
		}
		prWr.println(instr);	prWr.flush();	  
	}	
	
	// write comment only
	public void emitComment (String instr) {

		int counter = 0;
		
		while(counter < indentCount) {
			instr = "\t" + instr;
			++counter;
		}

		prWr.println(instr);	prWr.flush();	  
	}
	
}
