package com.js.parser;

import java.util.ArrayList;

public class ParserDefault {
	
	private ArrayList<String> fileDataList;
	private int startPoint;
	private int addPoint;
	private int endPoint;
	private String continuedLineDelimiter;
	
	public ParserDefault(){
		this.fileDataList = new ArrayList<String>();
	}
	
	public static ArrayList<String> splitLineData(String line, String delimiter){
		ArrayList<String> resultLineDataList = new ArrayList<String>();
		String [] tokens = null;
		if(delimiter.equals(" ")){
			tokens = line.split(" ");
			for(String token : tokens){
				if(token.length() != 0){
					resultLineDataList.add(token);	
				}else{
					//resultLineDataList.add("null");
				}
			}
		}else{
			tokens = line.split(delimiter);
			for(String token : tokens){
				resultLineDataList.add(token);
			}
		}
		
		
		return resultLineDataList;
	}
	public static ArrayList<String> splitLineData_table3(String line, String delimiter){
		ArrayList<String> resultLineDataList = new ArrayList<String>();
		String [] tokens = null;
		if(delimiter.equals(" ")){
			tokens = line.split(" ");
			for(String token : tokens){
				if(token.length() != 0){
					resultLineDataList.add(token);	
				}else{
					//resultLineDataList.add("null");
				}
			}
		}else{
			tokens = line.split(delimiter);
			for(String token : tokens){
				resultLineDataList.add(token);
			}
		}
		
		if(resultLineDataList.size() == 1){
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
		}else if(resultLineDataList.size() == 2){
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
		}else if(resultLineDataList.size() == 3){
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
		}else if(resultLineDataList.size() == 4){
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
		}else if(resultLineDataList.size() == 5){
			resultLineDataList.add("");
			resultLineDataList.add("");
			resultLineDataList.add("");
		}else if(resultLineDataList.size() == 6){
			resultLineDataList.add("");
			resultLineDataList.add("");
		}else if(resultLineDataList.size() == 7){
			resultLineDataList.add("");
		}
		
		return resultLineDataList;
	}
	
	public ArrayList<String> splitLineData(int fileDataLineNum, String delimiter){
		ArrayList<String> resultLineDataList = new ArrayList<String>();
		String line = fileDataList.get(fileDataLineNum);
		String [] tokens = null;
		
		tokens = line.split(delimiter);
		for(String token : tokens){
			resultLineDataList.add(token);
		}
		
		return resultLineDataList;
	}
	
	public ArrayList<String> splitLineData(int startP, int addP, int fileDataLineNum){
		ArrayList<String> resultLineDataList = new ArrayList<String>();
		String line = fileDataList.get(fileDataLineNum);
		String token = null;
		startPoint = startP;
		addPoint = addP;
		endPoint = line.length();
		
		for(int i = startPoint ; i < endPoint ; i=i+addPoint){
			int nextPoint = i+addPoint;
			
			if(nextPoint < endPoint){
				token = line.substring(i, nextPoint).trim();
				if(token.length()!=0){
					resultLineDataList.add(token);
				}else if(token.length() == 0){
					resultLineDataList.add("BLANK");
				}
			}else{
				token = line.substring(i,endPoint).trim();
				if(token.length()!=0){
					resultLineDataList.add(token);
				}else if(token.length() == 0){
					resultLineDataList.add("BLANK");
				}
			}
			token = null;
		}
		return resultLineDataList;
	}
	
	public ArrayList<String> splitBlockData(int startP, int addP, int fileDataLineNum, String delimiter){
		ArrayList<String> resultBlockDataList = new ArrayList<String>();
		ArrayList<String> resultSplitLineData = new ArrayList<String>();
		
		startPoint = startP;
		addPoint = addP;
		continuedLineDelimiter = delimiter;
		
		resultSplitLineData = splitLineData(startPoint,addPoint,fileDataLineNum);
		
		if(isEndLine(resultSplitLineData)){
			// End block data
			for(String resultToken : resultSplitLineData){
				resultBlockDataList.add(resultToken);
			}
			return resultBlockDataList;
		}else {
			// continue block data
			int lineNum = fileDataLineNum;
			while(true){
				for(String resultToken : resultSplitLineData){
					if(!resultToken.equals(continuedLineDelimiter)){
						resultBlockDataList.add(resultToken);
					}
				}
				
				if(isEndLine(resultSplitLineData)){
					break;
				}else {
					lineNum++;
					try{
						resultSplitLineData = splitLineData(startPoint,addPoint,lineNum);
					}catch (Exception e){
						e.printStackTrace();
						break;
					}
				}
			}
			return resultBlockDataList;
		}
	}
	
	public boolean isEndLine(ArrayList<String> fileLineDataList){
		if(fileLineDataList.get(fileLineDataList.size()-1).equals(continuedLineDelimiter)){
			return false;
		}else{ 
			return true;
		}
	}
	
	
	public void setFileDataList(ArrayList<String> fileDataList) {
		this.fileDataList = fileDataList;
	}
	
	
}
