package com.js.chartViewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class PostPlotManager {
	/*
	public static void main(String[] args){
		String resultType_Path = "Path";
		String resultType_history = "History";
		
		ArrayList<String> resultFilesPathList = new ArrayList<String>();
		resultFilesPathList.add("G:\\06.ENS\\post 파일\\path_plot\\model1_Longi_1_inc_0.txt");
		resultFilesPathList.add("G:\\06.ENS\\post 파일\\path_plot\\model1_Longi_2_inc_0.txt");
		resultFilesPathList.add("G:\\06.ENS\\post 파일\\path_plot\\model1_Longi_3_inc_0.txt");
		
		
		try{
			PostPlotManager postMgr = new PostPlotManager(resultType_Path,resultFilesPathList);
			postMgr.running();
			
			//PostPlotManager postMgr2 = new PostPlotManager(resultType_Path,resultFilesPathList);
			//postMgr2.running();
		}catch (Exception e){
			
		}
		
	}
	*/
	
	private String resultType;
	private ArrayList<String> filePahtList;
	private ArrayList<ArrayList<Float>> xDataList;
	private ArrayList<ArrayList<Float>> yDataList;
	private String xaxisTitle = "";
	private String yaxisTitle = "";
	
	private ArrayList<String> legendList;
	
	
	public PostPlotManager(String type, ArrayList<String> pathList){
		this.resultType = type;
		this.filePahtList = pathList;
		legendList = new ArrayList<String>();
		for(String path : this.filePahtList){
			String fileName = myUtil.getFileName(path);
			legendList.add(ParserDefault.splitLineData(fileName,"\\.").get(0));
		}
	}
	
	public void running(){
		GenPlotData();
		ShowPlot(resultType+" Plot");
		
	}
	
	public void GenPlotData(){
		xDataList = new ArrayList<ArrayList<Float>>();
		yDataList = new ArrayList<ArrayList<Float>>();
		
		if(resultType.equals("Path")){
			PathPlot pathData = new PathPlot(this.filePahtList);
			pathData.running();
			xDataList = pathData.getxDataList();
			yDataList = pathData.getyDataList();
			xaxisTitle = pathData.getXaxisTitle();
			yaxisTitle = pathData.getYaxisTitle();
			
		}else if(resultType.equals("History")){
			HistoryPlot historyData = new HistoryPlot(this.filePahtList);
			historyData.running();
			xDataList = historyData.getxDataList();
			yDataList = historyData.getyDataList();
			xaxisTitle = historyData.getXaxisTitle();
			yaxisTitle = historyData.getYaxisTitle();
			
			//System.out.println("parsing end");
		}
	}
	
	public void ShowPlot(String title){
		//System.out.println("FRAME - "+title);
		ChartWrapper chartWrapper = new ChartWrapper(title,this.xaxisTitle,this.yaxisTitle);
		chartWrapper.setLegendList(this.legendList);
		chartWrapper.init(xDataList, yDataList);
		
		final JFrame f = new JFrame(resultType+ " plot");
		f.setSize(500, 500);
		f.getContentPane().add(chartWrapper.getChartPanel());
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				f.dispose();
			}
		});
		f.setVisible(true);
		
		//System.out.println("FRAME END - "+title);
	}
	
	
	//=====================================================================================
	//
	class PathPlot{
		
		private ArrayList<String> filePahtList;
		private ArrayList<ArrayList<Float>> xDataList;
		private ArrayList<ArrayList<Float>> yDataList;
		private String xaxisTitle = "";
		private String yaxisTitle = "";
		
		public PathPlot(ArrayList<String> pathList){
			this.filePahtList = pathList;
		}
		
		public void running(){
			xDataList = new ArrayList<ArrayList<Float>>();
			yDataList = new ArrayList<ArrayList<Float>>();
			
			for(String path : filePahtList){
				Parser p = new Parser(path);
				p.running("Path");
				xDataList.add(p.getxData());
				yDataList.add(p.getyData());
				xaxisTitle = p.getXaxisTitle();
				yaxisTitle = p.getYaxisTitle();
			}
		}
		
		public ArrayList<ArrayList<Float>> getxDataList() {
			return xDataList;
		}

		public ArrayList<ArrayList<Float>> getyDataList() {
			return yDataList;
		}
		
		public void setxDataList(ArrayList<ArrayList<Float>> xDataList) {
			this.xDataList = xDataList;
		}
		
		public void setyDataList(ArrayList<ArrayList<Float>> yDataList) {
			this.yDataList = yDataList;
		}
		
		public String getYaxisTitle() {
			return yaxisTitle;
		}

		public void setYaxisTitle(String yaxisTitle) {
			this.yaxisTitle = yaxisTitle;
		}

		public String getXaxisTitle() {
			return xaxisTitle;
		}

		public void setXaxisTitle(String xaxisTitle) {
			this.xaxisTitle = xaxisTitle;
		}
	}
	
	//=====================================================================================
	//	
	class HistoryPlot{
		
		private ArrayList<String> filePahtList;
		private ArrayList<ArrayList<Float>> xDataList;
		private ArrayList<ArrayList<Float>> yDataList;
		private String xaxisTitle = "";
		private String yaxisTitle = "";
		
		public HistoryPlot(ArrayList<String> pathList){
			this.filePahtList = pathList;
		}
		
		public void running(){
			xDataList = new ArrayList<ArrayList<Float>>();
			yDataList = new ArrayList<ArrayList<Float>>();
			
			for(String path : filePahtList){
				Parser p = new Parser(path);
				p.running("History");
				xDataList.add(p.getxData());
				yDataList.add(p.getyData());
				xaxisTitle = p.getXaxisTitle();
				yaxisTitle = p.getYaxisTitle();
			}
		}
		
		public ArrayList<ArrayList<Float>> getxDataList() {
			return xDataList;
		}

		public ArrayList<ArrayList<Float>> getyDataList() {
			return yDataList;
		}
		
		public void setxDataList(ArrayList<ArrayList<Float>> xDataList) {
			this.xDataList = xDataList;
		}
		
		public void setyDataList(ArrayList<ArrayList<Float>> yDataList) {
			this.yDataList = yDataList;
		}
		
		public String getYaxisTitle() {
			return yaxisTitle;
		}

		public void setYaxisTitle(String yaxisTitle) {
			this.yaxisTitle = yaxisTitle;
		}

		public String getXaxisTitle() {
			return xaxisTitle;
		}

		public void setXaxisTitle(String xaxisTitle) {
			this.xaxisTitle = xaxisTitle;
		}

	
	}
	
	//=====================================================================================
	//

	class Parser{
		private String filePath;
		private ArrayList<String> fileDataList;
		private ArrayList<Float> xData;
		private ArrayList<Float> yData;
		private String xaxisTitle = "";
		private String yaxisTitle = "";
		
		public Parser(String filePath){
			this.filePath = filePath;
		}
		
		public void running(String type){
			Reader reader = new Reader(filePath);
			reader.running();
			fileDataList = reader.getFileDataList();
			if(type.equals("Path")){
				parsing_path();	
			}else if(type.equals("History")){
				parsing_history();
			}
			
		}
		
		public void parsing_path(){
			//System.out.println("PATH");
			// 여기서 parsing
			ArrayList<String> splitDataTokens = new ArrayList<String>();
			
			xData = new ArrayList<Float>();
			yData = new ArrayList<Float>();
			
			for(String line : fileDataList){
				splitDataTokens = ParserDefault.splitLineData(line, " ");
				//System.out.println(splitDataTokens);
				if(splitDataTokens.size() != 0){
					String firstTokens = splitDataTokens.get(0);
					
					if(firstTokens.equals("X")){
						// X Title
						for(int i = 2;i<splitDataTokens.size();i++){
							xaxisTitle += splitDataTokens.get(i);
						}
					}else if(firstTokens.equals("Y")){
						// Y Title
						for(int i = 2;i<splitDataTokens.size();i++){
							yaxisTitle += splitDataTokens.get(i)+" ";
						}
					}
					
					if(splitDataTokens.size() == 3 && myUtil.isFloatValue(firstTokens)){
						xData.add(Float.parseFloat(splitDataTokens.get(1)));
						yData.add(Float.parseFloat(splitDataTokens.get(2)));
					}
				}
			}
		}
		
		public void parsing_history(){
			//System.out.println("HISTORY");
			ArrayList<String> splitDataTokens = new ArrayList<String>();
			
			xData = new ArrayList<Float>();
			yData = new ArrayList<Float>();
			
			for(String line : fileDataList){
				splitDataTokens = ParserDefault.splitLineData(line, " ");
				//System.out.println(splitDataTokens);
				if(splitDataTokens.size() != 0){
					String firstTokens = splitDataTokens.get(0);
					
					if(splitDataTokens.size() > 2  && firstTokens.equals("X")){
						// X Title
						for(int i = 2;i<splitDataTokens.size();i++){
							xaxisTitle += splitDataTokens.get(i);
						}
					}else if(splitDataTokens.size() > 2  && firstTokens.equals("Y")){
						// Y Title
						for(int i = 2;i<splitDataTokens.size()-2;i++){
							yaxisTitle += splitDataTokens.get(i)+" ";
						}
					}
					
					if(splitDataTokens.size() == 2 && myUtil.isFloatValue(firstTokens)){
						xData.add(Float.parseFloat(splitDataTokens.get(0)));
						yData.add(Float.parseFloat(splitDataTokens.get(1)));
					}
				}
			}
			//System.out.println("HISTORY END");
		}
		
		public ArrayList<Float> getxData() {
			return xData;
		}

		public ArrayList<Float> getyData() {
			return yData;
		}

		public String getXaxisTitle() {
			return xaxisTitle;
		}

		public void setXaxisTitle(String xaxisTitle) {
			this.xaxisTitle = xaxisTitle;
		}

		public String getYaxisTitle() {
			return yaxisTitle;
		}

		public void setYaxisTitle(String yaxisTitle) {
			this.yaxisTitle = yaxisTitle;
		}
		
	}
}


