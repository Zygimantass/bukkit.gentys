package me.mchackerslt.gentys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Failai {
	File log;
	public static void writeFile(String failas, String text)
	{
	    File log = new File(failas);
	    try{
	    	PrintWriter out = new PrintWriter(new FileWriter(log, true));
	    	out.append(text);
	    	out.close();
	    }catch(IOException e){
	        e.printStackTrace();
	    }
	    log = null;
	}
}
