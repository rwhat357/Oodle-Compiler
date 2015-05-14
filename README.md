# Oodle-Compiler
Author: Fredy Whatley
Date: 04/30/2015


Table of Contents

Introduction	1
Usage	1
Features	2
Technical Notes	3
Tools and Versions	3
Compiler Organization	3
I/O	4
Special Techniques and Features	4
Testing and Bug Report	5
Appendices	5

	 
Introduction
This manual’s purpose is to explain the Oodle compiler’s (oodle.jar) capabilities and underlying implementation. It also explains how to use the compiler to produce executables from Oodle source files. The last section includes technical notes about Oodle’s documentation and features.
Usage
Run the Oodle compiler by running “oodle.jar” program at the command line with the following syntax:

java –jar oodle.jar [options] [files]

Where [files] is a list of one or more Oodle source files and [options] is any of the following:

Option	Function
-ds	Display a list of tokens from the listed files
-S	Output an assembly file with the same name as the last source file listed, but do not produce an executable. When the –S option is not present, Oodle produces an executable in the current working directory.
--help	Display usage



Oodle.jar expects to find the file “stdlib.o” in the current working directory. This file contains the input and output methods for the compiler which are linked with the Oodle source file(s) to produce and executable at compile time. 
If the “-S” compiler option is present at the command line, the compiler will produce an assembly file from Oodle source files. If the user wants to produce an executable from the assembly file, he must run the following gcc command to produce an executable.

gcc –o [executable_name] [assembled filename].s stdlib.c

This command will link the runtime C library with the generated assembly code to create an executable.
Features

C Features		Implemented?
Instance variable declarations (types int and boolean only) without initializer expressions
	 
Expressions: literals, id's, parenthesized expressions
	 
Assignment statement
	 
If-then-else statement
	 
Call statement
	 
Output of integer data using out.writeint(x)
	 
Loop while statement
	 
Method declarations with arguments and an optional return type (no string support required)	 
Method calls	 
Recursion support (use of stack-based activation records)	 
Input of integer data using x := in.readint( )	 

There aren’t any bugs for all the features mentioned above.
Technical Notes
Tools and Versions
Oodle was written in Java on an Ubuntu 12.04 virtual machine using SableCC3, a Java compiler generator that generates lexer and parser classes from a specification file and also includes tree walker frameworks for performing semantic checking and code generation. The IDE used for development was Eclipse. Kdbg, an open-source Linux utility, was used for debugging generated assembly files.
Compiler Organization
Oodle is built in four major sections: a Lexer class, which converts an oodle source file into a stream of tokens, a Parser class, which converts the tokens into a parse tree, a Semantic Checker class, which traverses the tree to decorate it with type information, and a Code Generator class which traverses the decorated tree to produce AT&T assembly code. Both the Semantic Checker and the Code Generator tree walkers rely on a static instance of the SymbolTable class, which maps class, variable and method declarations in a three-level hierarchy. The declarations themselves are implemented as a series of classes inheriting from class Declaration. Each declaration is associated with an instance of the Type class, and can be primitive (integer, boolean) or a constructed type.

Within the Semantic Checker and Code Generator tree walker classes, there must be some intermediate representation of the decorated parse tree. Oodle uses an attribute grammar map for this purpose (a Java HashMap of parse tree nodes, each mapped to another HashMap that stores attributes of that node). In the Semantic Checker, the intermediate representation is used to hold information about a node’s type. In the Code Generator, the attribute grammar map also stores assembly code snippets for various nodes.
I/O
In order to handle input and output, Oodle relies on two classes of the Oodle Standard Library: “Reader” and “Writer”. These classes call “readint” and “writeint”, functions in the “stdlib.c” runtime library that in turn invoke C system calls to perform the actual I/O tasks. When generating assembly code, Oodle also initializes two global objects of the Reader and Writer classes, “in” and “out”, which the rest of the program uses to call I/O functions.
Special Techniques and Features
I learned two valuable techniques while developing this compiler. First, I learned how to use a singleton class that contains other singleton classes. I implemented this singleton of singletons in my Globals. Second, I also learned the versatility and power of hashmaps when implementing the SemanticChecker and CodeGenerator classes, Hashmaps were very helpful and ideal to use for the Oodle compiler because it works like a bag that can contain anything and that anything can be identified with key.
 
Testing and Bug Report (C level)

Test File	Input	Output	Pass?
cbasics.ood	N/A	0
10
20
0
10
-5
0
10
20
-5
25	 
cchange.ood	121	4
2
0
1	 
cfact.ood	5	5
120	 
cgcd.ood	50
100	50	 
citerfact.ood	5	0
120	 

There are no known bugs to report.
Appendices

(See attached source files for the SemanticCheck.java and CodeGenerator.java classes.)
