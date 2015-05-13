package cps450;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.io.File;

/* loads standard library (stdlib.o) from the current directory 
 * The standard library contains c code for writeint() and readint()
 * */
public class StandadLibraryLoad {
	protected String path;
	
	public StandadLibraryLoad(){
		this.path = null;
	}
	public void getJAR(){
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("stdlib.o");
		System.out.println(is);
	}
	public void t2() throws IOException, InterruptedException{ //Tyvm StackOverflow
		  JarFile jar = new JarFile("oodle.jar");
	      ZipEntry entry = jar.getEntry("stdlib.o");
	      String findSTD = System.getProperty("user.dir");
	      File efile = new File(findSTD, entry.getName());

	      InputStream in = 
	         new BufferedInputStream(jar.getInputStream(entry));
	      OutputStream out = 
	         new BufferedOutputStream(new FileOutputStream(efile));
	      byte[] buffer = new byte[2048];
	      for (;;)  {
	        int nBytes = in.read(buffer);
	        if (nBytes <= 0) break;
	        out.write(buffer, 0, nBytes);
	      }
	      out.flush();
	      out.close();
	      in.close(); 
		
		}


	  
	
}
