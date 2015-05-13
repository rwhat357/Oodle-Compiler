package cps450;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.print.DocFlavor.URL;

/* Generates executable with GCC */
public final class GenerateExecutable extends SecurityManager {
   
	public static void createGCCCode(){
		
		System.out.println("Generating executable file...");
		
		try {
			String stdLib = System.getProperty("user.dir") + "/stdlib.o", exeName = "-o" + Globals.executableFilename.replace(".s", " ").trim();
			
			String findSTD = System.getProperty("user.dir");
			System.out.println("Loading stdlib.o from "+findSTD + "...");
			StandadLibraryLoad t = new StandadLibraryLoad();
			try{t.t2();} catch (Exception e){}
			
			ProcessBuilder process = new ProcessBuilder("gcc", Globals.executableFilename, stdLib, exeName);	
			process.redirectErrorStream(true);
			
			Process proc = process.start();
			proc.waitFor();	
			
			InputStream shellIn = proc.getInputStream();
			int exitVal = proc.exitValue();
			
			if(exitVal == 0) {
				System.out.println("Compile successfully with 0 errors. The executable is in the current directory.");
			} else {
				System.out.println("There was a problem when compiling your program, error code: " + exitVal + ". Make sure you have the stdlib.o in the current directory.") ;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
		
	}
	
	
}
