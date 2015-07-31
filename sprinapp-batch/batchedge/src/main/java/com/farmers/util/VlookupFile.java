package com.farmers.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class VlookupFile {

	public static HashSet<String> buildSet(String filepath) throws IOException
	{
		HashSet<String> dataSet = new HashSet<String>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(filepath);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        dataSet.add(line.trim());
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		
		
		return dataSet;
	}
	
	
	// returns elements of data not in source
	public static HashSet<String> vlookup(HashSet<String> source,HashSet<String> data) throws IOException
	{
		HashSet<String> exclusionSet = new HashSet<String>();
		for(String item:data)
		{
			if(!source.contains(item))
			{
				exclusionSet.add(item);
			}
		}
		
		
		return exclusionSet;
		// return exclusion set
	}
	
	public static void writeSet2File(Set<String> obj, String path) throws IOException
	{		
		    PrintWriter pw = null;
		    try {
		        pw = new PrintWriter(
		            new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
		        for (String s : obj) {
		            pw.println(s);
		        }
		        pw.flush();
		    } finally {
		        pw.close();
		    }
	}
	
	
	
	public static void main(String[] args)
	{
		String lookupdatafile= null;
		String datafile = null;
		String outputfolder = "c:/temp";
	
		if(args!=null && args.length >0)			
		{
			for(int i=0;i<args.length;i++)
			{
				if(args[i].indexOf("--lookupfile=") > -1)
				{
					lookupdatafile= args[i].substring("--lookupfile=".length());
				}
				else if(args[i].indexOf("--datafile=") > -1)
				{
					datafile = args[i].substring("--datafile=".length());
				}
				else if(args[i].indexOf("--resultFolder=") > -1)
				{
					outputfolder = args[i].substring("--resultFolder=".length());
				}
				
			}
	  	 
		}
		
		System.out.println("lookupfile is "+lookupdatafile);
		System.out.println("datafile is "+datafile);
		System.out.println("output folder is "+ outputfolder);
		try {
			if(datafile !=null  && lookupdatafile == null)
			{
				System.out.println("Removing duplicates lines from the datafile :: "+datafile);
				HashSet<String> data = buildSet(datafile);
				System.out.println("data has "+data.size() + " distinct elements");
				if(outputfolder!=null)
					writeSet2File(data, outputfolder+"/setofdata.txt");
				else
					System.out.println("Provide a valid arg for --resultFolder= ");
					
			}
			
			if(datafile == null && lookupdatafile == null)
			{
				System.out.println("Provide valid input args --lookupfile and --datafile ");
				System.exit(0);
			}
			else
			{
			
				System.out.println("Vlook up :: Start processing ");			
				
					HashSet<String> lookupsource = buildSet(lookupdatafile);
					HashSet<String> data = buildSet(datafile);
					HashSet<String> exclusion = vlookup(lookupsource,data);
					System.out.println("source has "+lookupsource.size() + " distinct elements");
					System.out.println("data has "+data.size() + " distinct elements");
					System.out.println("data  exclusive of source  "+exclusion.size() + " elements");
					writeSet2File(lookupsource, outputfolder+"/setofsource.txt");
					writeSet2File(data, outputfolder+"/setofdata.txt");
					writeSet2File(exclusion, outputfolder+"/datanotinsource.txt");
					
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("Vlook up :: Ends ");
		}
		
	}
	
	@Test
	public void hugeFileLookup()
	{
		String startfile = "C:/Users/uswbdnu/Documents/Farmers/silanis/Data Migration/june25/slave-log-bin.000351.decoded.sql";
		String lastfile = "C:/Users/uswbdnu/Documents/Farmers/silanis/Data Migration/june25/slave-log-bin.000361.decoded.sql";
		
		
	}
	
	
}
