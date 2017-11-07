package com.js.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class myUtil {
	/*
	private boolean isPattern(String fileName){
		String partName = this.fsObj.getPartName().toLowerCase();
		Pattern p = Pattern.compile("(^"+partName+"_[0-9][0-9][0-9][0-9].dac$)");
		Matcher m = p.matcher(fileName);

		if(m.find()){
			return true;
		}else{
			return false;
		}    
	}
	 */
	
	/* - �ð� ����
	long start = System.currentTimeMillis();
	long end = System.currentTimeMillis();
	System.out.println( "���� �ð� : " + ( end - start )/1000.0 );
	// */
	// Check FileName
	public static void changeDirectory(String path){
		System.out.println("dir : " + System.getProperty("user.dir"));
		System.setProperty("user.dir", path);
		System.out.println("change dir : "+System.getProperty("user.dir"));
		
	}
	
	public static String getFileName(String path){
		try{
			File f = new File(path);
			String file = f.getName();
			String [] tokens = null;
			tokens = file.split("\\.");
			ArrayList<String> result = new ArrayList<String>();
			for(String str : tokens){
				result.add(str);
			}
			return result.get(0);
		}catch(Exception e){
			return null;
		}
	}
	
	public static String getFileNameIncludeExtension(String path){
		try{
			File f = new File(path);
			return f.getName();
		}catch(Exception e){
			return null;
		}
	}
	// Check parentPath
	public static String getParentPath(String path){
		File f = new File(path);
		return f.getParent();
	}
	
	// Check extensions
	public static String getExtensions(String path){
		try{
			File f = new File(path);
			String file = f.getName();
			String [] tokens = null;
			tokens = file.split("\\.");
			ArrayList<String> result = new ArrayList<String>();
			for(String str : tokens){
				result.add(str);
			}
			return result.get(1);	
		}catch(Exception e){
			return null;
		}
				
	}
	// Get current Time 
	public static String getCurrentTime(){
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd_HHmmss" );
		Date currentTime = new Date ( );
		String dTime = formatter.format (currentTime);
		return dTime;
	}
	
	
	//소수점 자리수 표현 1.-4 => 0.0004
	public static String getSmallNumber(String s){
		String str = s;
		ArrayList<String> tempList = new ArrayList<String>();
		String[] arr = str.split("\\.");
		for(int i=0; i<arr.length;i++){
			if(arr[i].length() !=0){
				tempList.add(arr[i]);
			}
		}
		String last = tempList.get(1);
		int cnt_0 = 0;
		int pointNum = 0;
		boolean going = true;
		boolean oneDigit = true;
		String value = "";
		for(int i=0;i<last.length();i++){
			if(going){
				if(last.charAt(i) == '0'){
					cnt_0++;
				}else{
					going = false;
					value += last.charAt(i);
				}
			}else{
				pointNum++;
				if(pointNum == 1){
					oneDigit = false;
					value =value + "."+ last.charAt(i);	
					break;
				}
			}
		}
		if(oneDigit){
			value = value +".";
		}
		
		return  value+"-"+(cnt_0+1);
	}
	
	// Check value
	public static boolean CheckDoubleValue(String inputData){
		boolean result = false;
		try{
			if (!inputData.isEmpty()){
				double value = Double.parseDouble(inputData);
				result = true;
				/*
				if (value > 0){
					result = true;	
				}else {
					result = false;
					JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
				}
				// */
			}
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, "Input Data is not String", "Input Data error", JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
			result = false;
		}
		return result;
	}
	
	public static boolean CheckFloatValue(String inputData){
		boolean result = false;
		try{
			if (!inputData.isEmpty()){
				float value = Float.parseFloat(inputData);
				result = true;
				/*
				if (value >= 0){
					result = true;	
				}else {
					result = false;
					//JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
				}
				//*/
			}
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, "Input Data is not String", "Input Data error", JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
			result = false;
		}
		return result;
	}

	
	public static boolean CheckIntegerValue(String inputData){
		boolean result = false;
		try{
			if (!inputData.isEmpty()){
				int value = Integer.parseInt(inputData);
				result = true;
				/*
				if (value >= 0){
					result = true;	
				}else {
					result = false;
					//JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
				}
				// */
				
			}
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, "Input Data is not String", "Input Data error", JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog(null, "Input Data must be greater than '0'. ", "Input Data error", JOptionPane.ERROR_MESSAGE);
			result =false;
		}
		return result;
	}
	// Check OS
	public static String checkOS(){
		String os = null;
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().contains("win")){
			os = "window";
		}else {
			os = "other";
		}
		return os;
	}

	public static ArrayList<String> splitData(String line,String token){
		
		ArrayList<String> result = new ArrayList<String>();

		String[] arr = line.split(token);

		for(int i=0; i<arr.length;i++){
			if(arr[i].length() !=0){
				result.add(arr[i]);
			}
		}

		return result;
	}
	
	public static ArrayList<String> splitData_csv(String line,String token){
		
		ArrayList<String> result = new ArrayList<String>();

		String[] arr = line.trim().split(token);

		for(int i=0; i<arr.length;i++){
			if(arr[i].length() !=0){
				result.add(arr[i].trim());
			}
		}

		return result;
	}
	
	public static ArrayList<String> splitData_shortFormat(String line){
		ArrayList<String> resultList = new ArrayList<String>();
		String tempStr = null;
		
		int startPoint = 0;
		int endPoint = line.length();
		int addPoint = 8;
		
		for (int i = startPoint ; i< endPoint ; i = i+addPoint){
			int nextPoint = i+addPoint;
			if(nextPoint < endPoint){
				tempStr = line.substring(i, nextPoint).trim();
				if(tempStr.length() != 0){
					resultList.add(tempStr);
				}else{
					resultList.add(" ");
				}
				
			}else {
				tempStr = line.substring(i, endPoint).trim();
				if(tempStr.length() != 0){
					resultList.add(tempStr);
				}else{
					resultList.add(" ");
				}
			}
			tempStr = null;
		}
		return resultList;
	}
	
	public static ArrayList<String> splitData_longFormat(String line){
		ArrayList<String> resultList = new ArrayList<String>();
		String tempStr = null;
		// cardName column
		resultList.add(line.substring(0,8).trim());
		
		int startPoint = 8;
		int endPoint = line.length();
		int addPoint = 16;
		
		for (int i = startPoint ; i< endPoint ; i = i+addPoint){
			int nextPoint = i+addPoint;
			if(nextPoint < endPoint){
				tempStr = line.substring(i, nextPoint).trim();
				if(tempStr.length() != 0){
					resultList.add(tempStr);
				}else{
					resultList.add(" ");
				}
				
			}else {
				tempStr = line.substring(i, endPoint).trim();
				if(tempStr.length() != 0){
					resultList.add(tempStr);
				}else{
					resultList.add(" ");
				}
			}
			tempStr = null;
		}
		return resultList;
	}


	// Change char to Sting 
	public static String change_charToString(char _c){
		char ch = _c;
		Character cr = new Character(ch);
		String str = cr.toString();
		return str;
	}

	// ArrayList print data 
	public static void printArrData(ArrayList<String> ArrData){
		for (int i = 0; i<ArrData.size();i++){
			System.out.println("index = "+i+" || value = "+ArrData.get(i));
		}
	}
	// Map Data prit data
	public static void printMapData(Map <String,String> mapData){
		System.out.println("----------------------------------------------------------------");
		Iterator<String> iterator = mapData.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			System.out.print("Key = "+key);
			System.out.println(" || Value = "+ mapData.get(key));

		}
	}
	// Check float value
	public static boolean isFloatValue(String value){
		try{
			Float.parseFloat(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	// Check double Value
	public static boolean isDouble(String inputData){
		boolean result = false;
		try{
			if (!inputData.isEmpty()){
				double value = Double.parseDouble(inputData);
				result = true;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Input Data is not String", "Input Data error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	// check path
	public static boolean checkPath(String path){
		File f =new File(path);
		return f.exists();
	}

	// set Path
	public static String setPath(String currentPath, String FolderName){
		return currentPath+File.separator+FolderName;
	}	

	// make folder 
	public static Boolean makeDir(String folderName){
		boolean result = false;
		File folder = new File(folderName);
		if(!folder.exists()){
			folder.mkdirs();
			//System.out.println("Success - mkdir");
			result = true; 
		}
		return result;
	}
	// file list from dirPath
	public static List<File> getDirFileList(String dirPath){
		List<File> dirFileList = null;

		File dir = new File(dirPath);

		if (dir.exists()){
			File[] files = dir.listFiles();
			dirFileList = Arrays.asList(files);
		}
		return dirFileList;
	}

	public static ArrayList<File> findFiles(String dirPath, String token){
		ArrayList<File> fileList = new ArrayList<File>();
		List<File> dirFileList = null;
		File dir = new File(dirPath);

		if(dir.exists()){
			File [] files = dir.listFiles();
			dirFileList = Arrays.asList(files);

			for(int i=0;i<dirFileList.size();i++){
				if(dirFileList.get(i).isFile()){
					if(dirFileList.get(i).getName().contains(".userMenu")){
						//System.out.println(dirFileList.get(i).getName());
						fileList.add(dirFileList.get(i));
					}
				}
			}
		}
		return fileList;
	}


	public static Boolean fileIsLive(String isLivefile) {
		File f1 = new File(isLivefile);
		if(f1.exists()) return true;
		else return false;		  
	}

	public static void fileMake(String makeFileName) {
		File f1 = new File(makeFileName);
		try {
			f1.createNewFile();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fileDelete(String deleteFileName) {
		File I = new File(deleteFileName);
		I.delete();
	}

	public static void fileCopy(String inFileName, String outFileName) {
		try{
			FileInputStream inputStream = new FileInputStream(inFileName);         
			FileOutputStream outputStream = new FileOutputStream(outFileName);
			  
			FileChannel fcin =  inputStream.getChannel();
			FileChannel fcout = outputStream.getChannel();
			  
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
			  
			fcout.close();
			fcin.close();
			  
			outputStream.close();
			inputStream.close();
		}catch(Exception e){
			
		}
		
		/* 
		 * old version
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File fisf = new File(inFileName);
		File fosf = new File(outFileName);
		try {
			fis = new FileInputStream(fisf);
			fos = new FileOutputStream(fosf);

			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				fis.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			try{
				fos.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			fosf.setExecutable(true);
		}
		//*/
	}

	public static void fileMove(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			//fileDelete(inFileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void makeDirectory(String path){
        File dir = new File(path);
 
        if (!dir.exists()) { //���� ������ ���� ����
            dir.mkdirs();
        }
	}

	public static boolean deleteDirectory(File targetPath){
		//boolean result = false;
		if(!targetPath.exists()){
			return false;
		}
		File[] files = targetPath.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteDirectory(file);
			} else {
				file.delete();
			}
		}

		return targetPath.delete();
	}
	
	public static void copyDirectory(File sourcelocation , File targetdirectory){ 
		try{
			//���丮�� ��� 
			if (sourcelocation.isDirectory()) {                
				//����� Directory�� ������ ����ϴ�. 
				if (!targetdirectory.exists()) { 
					targetdirectory.mkdir(); 
				} 

				String[] children = sourcelocation.list(); 
				for (int i=0; i<children.length; i++) { 
					copyDirectory(new File(sourcelocation, children[i]), 
							new File(targetdirectory, children[i])); 
				} 
			} else { 
				//������ ��� 
				InputStream in = new FileInputStream(sourcelocation);                 
				OutputStream out = new FileOutputStream(targetdirectory); 

				// Copy the bits from instream to outstream 
				byte[] buf = new byte[1024]; 
				int len; 
				while ((len = in.read(buf)) > 0) { 
					out.write(buf, 0, len); 
				} 
				in.close(); 
				out.close(); 
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	public static void CleareObj (Object obj){
		//System.out.println("Clear : "+obj.getClass().toString());
		obj = null;
		System.gc();
	}
	
	public static String getIPAddress(){
		InetAddress ip = null;
		String IPAddress = "";
		// ���� IP���
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPAddress = ip.getHostAddress();
		return IPAddress;
	}
	
	public static String getMacAddress(){
		InetAddress ip = null;
		NetworkInterface netif = null;
		String MacAddress = "";
		
		// ���� IP���
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��Ʈ��ũ �������̽� ���
		try {
			netif = NetworkInterface.getByInetAddress(ip);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��Ʈ��ũ �������̽��� NULL�� �ƴϸ�
		if (netif != null) {
			// ��Ʈ��ũ �������̽� ǥ�ø� ���
			//System.out.print(netif.getDisplayName() + " : ");

			// �ƾ�巹�� ���
			byte[] mac = null;
			try {
				mac = netif.getHardwareAddress();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// �ƾ�巹�� ���
			for (byte b : mac) {
				MacAddress = MacAddress + String.format("%02X", b);
			}
		}
		return MacAddress;
	}
	
	public static String getCurrentDate(){
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		//System.out.println ( mTime );
		return mTime;
	}
}
