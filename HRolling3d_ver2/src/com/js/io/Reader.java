package com.js.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;


public class Reader {
	
	private Logger log = Logger.getLogger(Reader.class);
	
	private String filePath; 
	private ArrayList<String> fileDataList;
	private BufferedReader reader;
	
	public Reader(String filePath) {
		// TODO Auto-generated constructor stub
		this.filePath = filePath;
		this.fileDataList = new ArrayList<String>();
	}
	
	public void running(){
		//log.info("* START - Read file");
		//log.info("* File Path : " + this.inFilePath);
		long start = System.currentTimeMillis();
		initReader();
		readFile();
		long end =System.currentTimeMillis();
		//log.info("* Read file path("+ (end-start)/1000.0 +"sec) : "+ this.inFilePath);
	}
	
	/*
	public void running_utf8(){
		this.initReader_UTF8();
		this.readFile();
	}
	//*/
	
	private void initReader(){
		File fObj = new File(this.filePath);
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fObj),"UTF8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	
	
	public void readFile(){
		String line = null;
		try {
			while((line = reader.readLine())!=null){
				if(line.length() !=0 ){
					fileDataList.add(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			try {
				this.reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error(e.getMessage());
			}
		}
	}
	
	////////////////////////////////////////////////////////
	// get - set method
	public ArrayList<String> getFileDataList() {
		return this.fileDataList;
	}
	//
	////////////////////////////////////////////////////////
}
