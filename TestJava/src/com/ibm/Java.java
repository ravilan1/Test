package com.ibm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Date;

public class Java {
	String directory = null;
	String configPath = null;
	BufferedReader br = null;
	
	public void gettingConfigDetails() throws FileNotFoundException{
		directory = System.getProperty("user.dir");
		System.out.println(directory);
		configPath = directory + "\\" + "config.txt";
		System.out.println(configPath);
		br = new BufferedReader(new InputStreamReader(new FileInputStream(configPath)));
		Date d = new Date();
		System.out.println("Todays Date::::"+d);
	}
	
	
	public static void main(String args[]) throws FileNotFoundException{
		Java ja = new Java();
		ja.gettingConfigDetails();
	}

}
