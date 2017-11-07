package com.js.chartViewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class PostPlotManager_ver2 {
	
	private ArrayList<String> filePahtList;
	private ArrayList<ArrayList<Float>> xDataList;
	private ArrayList<ArrayList<Float>> yDataList;
	private String xaxisTitle = "";
	private String yaxisTitle = "";
	
	private ArrayList<String> legendList;
	
	private final String TotalTitle = "Plate profile Result Plot";
	private final String x_Title = "Distance(mm)";
	private final String y_Title = "Thickness(mm)";
	
	public PostPlotManager_ver2(ArrayList<String> pathList){
		//this.resultType = type;
		this.filePahtList = pathList;
		legendList = new ArrayList<String>();
		/*
		for(String path : this.filePahtList){
			String fileName = myUtil.getFileName(path);
			legendList.add(ParserDefault.splitLineData(fileName,"\\.").get(0));
		}
		//*/
	}
	
	public void running(){
		GenPlotData();
		//ShowPlot("Plate profile Result Plot");
		ShowPlot(this.TotalTitle);
		
	}
	
	public void GenPlotData(){
		xDataList = new ArrayList<ArrayList<Float>>();
		yDataList = new ArrayList<ArrayList<Float>>();
		legendList = new ArrayList<String>();
		
		PlateProfilePlot plotData = new PlateProfilePlot(this.filePahtList);
		plotData.running();
		xDataList = plotData.getxDataList();
		yDataList = plotData.getyDataList();
		legendList = plotData.getLegendList();
		//xaxisTitle = plotData.getXaxisTitle();
		//yaxisTitle = plotData.getYaxisTitle();
		xaxisTitle = this.x_Title;
		yaxisTitle = this.y_Title;
		
	}
	
	public void ShowPlot(String title){
		
		//System.out.println("FRAME - "+title);
		ChartWrapper chartWrapper = new ChartWrapper(title,this.xaxisTitle,this.yaxisTitle);
		chartWrapper.setLegendList(this.legendList);
		chartWrapper.init(xDataList, yDataList);
		
		final JFrame f = new JFrame(this.TotalTitle);
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
	class PlateProfilePlot{
		private ArrayList<String> filePahtList;
		private ArrayList<ArrayList<Float>> xDataList;
		private ArrayList<ArrayList<Float>> yDataList;
		private ArrayList<String> LegendList;
		private String xaxisTitle = "";
		private String yaxisTitle = "";
		
		
		public PlateProfilePlot(ArrayList<String> pathList){
			this.filePahtList = pathList;
		}
		
		public void running(){
			xDataList = new ArrayList<ArrayList<Float>>();
			yDataList = new ArrayList<ArrayList<Float>>();
			LegendList = new ArrayList<String>();
			
			for(String path : filePahtList){
				Parser_ver2 p = new Parser_ver2(path);
				p.running();
				xDataList.add(p.getxData());
				yDataList.add(p.getyData());
				xaxisTitle = p.getXaxisTitle();
				yaxisTitle = p.getYaxisTitle();
				LegendList.add(p.getLegendTitle());
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
		
		public ArrayList<String> getLegendList() {
			return LegendList;
		}

		public void setLegendList(ArrayList<String> legendList) {
			LegendList = legendList;
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
	class Parser_ver2{
		private String filePath;
		private ArrayList<String> fileDataList;
		private ArrayList<Float> xData;
		private ArrayList<Float> yData;
		private String xaxisTitle = "Distance(mm)";
		private String yaxisTitle = "Thickness(mm)";
		private String legendTitle = "";
		
		public Parser_ver2(String filePath){
			this.filePath = filePath;
		}
		
		public void running(){
			Reader reader = new Reader(this.filePath);
			reader.running();
			fileDataList = reader.getFileDataList();
			parsing();
		}
		
		public void parsing(){
			/*
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
			//*/
			ArrayList<String> splitDataTokens = new ArrayList<String>();
			
			xData = new ArrayList<Float>();
			yData = new ArrayList<Float>();
			
			for(String line : fileDataList){
				splitDataTokens = ParserDefault.splitLineData(line, ",");
				if(splitDataTokens.size() == 2){
					if(splitDataTokens.get(0).toLowerCase().contains("plate") && splitDataTokens.get(0).toLowerCase().contains("profile")){
						legendTitle = splitDataTokens.get(1);
					}
				}
				
				if(splitDataTokens.size() == 4){
					if(myUtil.isFloatValue(splitDataTokens.get(0))){
						xData.add(Float.parseFloat(splitDataTokens.get(0)));
						yData.add(Float.parseFloat(splitDataTokens.get(3)));
					}
				}
			}
			
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
		
		public String getLegendTitle(){
			return legendTitle;
		}
	}
	
}


