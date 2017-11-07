package com.js.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Writer {
	private Logger log = Logger.getLogger(Writer.class);
	private String outputFilePath;
	private ArrayList<String> outputDataList;
	private BufferedWriter writer;
	
	public Writer(String outputFilePath) {
		// TODO Auto-generated constructor stub
		this.outputFilePath = outputFilePath;
		this.outputDataList = new ArrayList<String>();
	}
	
	public void running(ArrayList<String> outputDataList){
		this.outputDataList = outputDataList;
		long start = System.currentTimeMillis();
		this.initWriter();
		this.writeFile();
		long end =System.currentTimeMillis();
		//log.info("* End - Write file("+ (end-start)/1000.0 +"sec)");
		log.info("* Export File Path("+ (end-start)/1000.0 +"sec) : " + this.outputFilePath);
		//this.fileDataList.clear();
	}
	
	public void running_utf8(ArrayList<String> outputDataList){
		this.outputDataList = outputDataList;
		this.initWriter_UTF8();
		this.writeFile();
	}
	private void initWriter(){
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.outputFilePath),"MS949"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error - Encoding ");
			//e.printStackTrace();
			log.error(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error - File Not Found");
			//e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	private void initWriter_UTF8(){
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.outputFilePath),"UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//System.out.println("Error - Encoding ");
			//e.printStackTrace();
			log.error(e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	private void writeFile() {
		try {
			for(int i=0;i<this.outputDataList.size();i++){
				writer.write(this.outputDataList.get(i));
				writer.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error(e.getMessage());
			}
		}
	}
		
}
