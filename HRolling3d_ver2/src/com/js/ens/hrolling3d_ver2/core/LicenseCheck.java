package com.js.ens.hrolling3d_ver2.core;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import com.js.ens.hrolling3d_ver2.FolderTree;
//import com.js.ens.transformation.core.AES256;
import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class LicenseCheck {
	private ArrayList<String> keyList_16 = new ArrayList<String>();
	private String privateKey = "";
	
	private String userLicensePeriod = "";
	private String startDate = "";
	private String endDate = "";
	private String userLicenseMacAddress = "";
	private String encPeriod = "";
	private String encMac = "";
	
	private InetAddress ip = null;
	private NetworkInterface netif = null;
	private String MacAddress = "";
	
	//private String LicenseLogPath = "";
	//private ArrayList<String> logDataList = new ArrayList<String>();
	
	
	public LicenseCheck() {
		// TODO Auto-generated constructor stub
		// configuration -> Key.ens
		// license       -> License.ens
		/*
		String licenseFolder = myUtil.setPath(System.getProperty("user.dir"),"license");
		String licenseLogFolder = myUtil.setPath(licenseFolder, "log");
		this.LicenseLogPath = myUtil.setPath(licenseLogFolder,"licenseLog_"+myUtil.getCurrentDate()+".log");
		//*/
	}
	
	public boolean running(){
		boolean result = false;
		checkPrivateKey();
		trans_Key();
		dec_runnging(this.privateKey);
		if(compareMacAddress()){
			if(comparePeriod()){
				result = true;
			}else{
				result = false;
			}
			
		}else{
			result = false;
		}
		return result;
	}
	
	public void checkPrivateKey(){
		ArrayList<String> fileDataList = new ArrayList<String>();
		//String progPath = System.getProperty("user.dir");
		//String licenseKeyPath = myUtil.setPath(myUtil.setPath(progPath, "configuration"),"Key.ens");
		//String licenseKeyPath = FolderTree.filePath_License;
		Reader reader = new Reader(FolderTree.filePath_License);
		reader.running();
		fileDataList = reader.getFileDataList();
		this.splitPrivateKey(fileDataList);
	}
	
	public void splitPrivateKey(ArrayList<String> fileDataList){
		ParserDefault obj = new ParserDefault();
		obj.setFileDataList(fileDataList);
		keyList_16 = obj.splitLineData(0, 4, 0);
	}
	
	public void trans_Key(){
		privateKey = "";
		for(int i = 0;i<keyList_16.size();i++){
			int intVal = Integer.parseInt(keyList_16.get(i), 16);
			char charVal = (char) intVal;
			privateKey += charVal;
		}
		//System.out.println("#=> Key : "+privateKey);
	}
	
	public void dec_runnging(String key){
		this.readLicense();
		AES256 aes256 = null;
		try {
			aes256 = new AES256(key);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.userLicensePeriod = aes256.aesDecode(this.encPeriod);
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.userLicenseMacAddress = aes256.aesDecode(this.encMac);
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("#=> Period : "+ this.userLicensePeriod);
		//System.out.println("#=> Mac Address : "+ this.userLicenseMacAddress);
	}
	
	public void readLicense(){
		String progPath = System.getProperty("user.dir");
		String licenseFilePath = myUtil.setPath(myUtil.setPath(progPath,"license"), "Licenes.ens");
		Reader reader = new Reader(licenseFilePath);
		reader.running();
		encPeriod = reader.getFileDataList().get(0);
		encMac = reader.getFileDataList().get(1);
	}
	
	public boolean compareMacAddress(){
		this.MacAddress = myUtil.getMacAddress();
		if(MacAddress.equals(this.userLicenseMacAddress)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean comparePeriod(){
		this.parsingDate(this.userLicensePeriod);
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date START = null;
		Date END = null;
		Date currentDate = null;
		try {
			START = mSimpleDateFormat.parse(this.startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			END = mSimpleDateFormat.parse( this.endDate );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			currentDate = mSimpleDateFormat.parse(myUtil.getCurrentDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int compare_start = currentDate.compareTo(START);
		int compare_end = currentDate.compareTo(END);
		if(compare_start >= 0){
			if(compare_end <=0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
		/*
		int compare = currentDate.compareTo(END);
		if(compare >0){
			//�񱳳�¥ < ���� ��¥
		}else if(compare <0) {
			//�񱳳�¥ > ���� ��¥
		}else {
			// �񱳳�¥ = ���� ��¥
		}
		*/
		//return true;
	}
	
	public void parsingDate(String period){
		ArrayList<String> tokens = new ArrayList<String>();
		tokens = ParserDefault.splitLineData(period, "_");
		this.startDate = tokens.get(0);
		this.endDate = tokens.get(1);
		//System.out.println(this.startDate);
		//System.out.println(this.endDate);
	}
	
}
