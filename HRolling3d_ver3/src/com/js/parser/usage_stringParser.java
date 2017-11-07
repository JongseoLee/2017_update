package com.js.parser;

import java.io.File;

import com.js.io.Reader;
import com.js.io.Writer;

public class usage_stringParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inDataPath = System.getProperty("user.dir")
				+File.separator+"src"
				+File.separator+"com"
				+File.separator+"js"
				+File.separator+"io"
				+File.separator+"Datas"
				+File.separator+"model.bdf";
		
		String outDataPath = System.getProperty("user.dir")
				+File.separator+"src"
				+File.separator+"com"
				+File.separator+"js"
				+File.separator+"io"
				+File.separator+"Datas"
				+File.separator+"out_model.bdf";
		/* */
		//////////////////////////////////////////////////////////////////////
		// Using String
		Reader reader = new Reader(inDataPath);
		reader.running();
		System.out.println("================================================");
		
		//System.out.println("size : "+reader.getFileDataList().size());
		
		Writer writer = new Writer(outDataPath);
		writer.running(reader.getFileDataList());
		System.out.println("================================================");
		
		System.out.println("* START - ParserString");
		long start = System.currentTimeMillis();
		//--------------------------------------
		ParserDefault parser = new ParserDefault();
		parser.setFileDataList(reader.getFileDataList());
		for (int i=0;i<reader.getFileDataList().size();i++){
			//System.out.println(parser.splitBlockData(0, 8, i, "+"));
			parser.splitBlockData(0, 8, i, "+");
		}
		//--------------------------------------		
		long end =System.currentTimeMillis();
		System.out.println("* End - ParserString("+ (end-start)/1000.0 +"sec)");
		//
		//////////////////////////////////////////////////////////////////////
		
		//*/
		
		System.out.println("\n\n\n-------------------------------\n\n\n");
		
		
		
		
		/* 
		//////////////////////////////////////////////////////////////////////
		// Using StringBuilder
		ReaderStringBuilder reader_StringBuilder = new ReaderStringBuilder(inDataPath);
		reader_StringBuilder.running();
		System.out.println("================================================");
		
		//System.out.println("size : "+reader_StringBuilder.getFileDataList_StringBuilder().size());
		
		WriterStringBuilder writer_StringBuilder = new WriterStringBuilder(outDataPath,reader_StringBuilder.getFileDataList_StringBuilder());
		writer_StringBuilder.running();
		System.out.println("================================================");
		
		System.out.println("* START - ParserString");
		long start2 = System.currentTimeMillis();
		//--------------------------------------
		ParserStringBuilder parser_StringBuilder = new ParserStringBuilder();
		parser_StringBuilder.setFileDataList(reader_StringBuilder.getFileDataList_StringBuilder());
		for (int i=0;i<reader_StringBuilder.getFileDataList_StringBuilder().size();i++){
			//System.out.println(parser_StringBuilder.splitBlockData(0, 8, i, "+"));
			parser_StringBuilder.splitBlockData(0, 8, i, "+");
		}
		//--------------------------------------		
		long end2 =System.currentTimeMillis();
		System.out.println("* End - ParserString("+ (end2-start2)/1000.0 +"sec)");
		//
		//////////////////////////////////////////////////////////////////////
		
		// */
	}

}
