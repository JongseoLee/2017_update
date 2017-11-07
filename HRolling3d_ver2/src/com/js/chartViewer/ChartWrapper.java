package com.js.chartViewer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class ChartWrapper {
	
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private XYPlot plot;
	
	private ArrayList<XYDataset> dataSetList;
	private ArrayList<String> legendList;
	private ArrayList<XYLineAndShapeRenderer> lineShape;
	
	
	private String title;
	private String xaxis;
	private String yaxis;
	
	public ChartWrapper(String chartTitle, String xaxisTitle, String yaxisTitle){
		this.title = chartTitle;
		this.xaxis = xaxisTitle;
		this.yaxis = yaxisTitle;
		this.dataSetList = new ArrayList<XYDataset>();
		this.legendList = new ArrayList<String>();
	}
	
	public void setLegendList(ArrayList<String> legendList){
		this.legendList = legendList;
	}
	
	public void init(ArrayList<ArrayList<Float>> xDataList,ArrayList<ArrayList<Float>> yDataList){
		initDataSet(xDataList,yDataList);
		chart = ChartFactory.createXYLineChart(title, xaxis, yaxis, null, PlotOrientation.VERTICAL, true, false, false);
		chart.setBackgroundPaint(Color.WHITE);

		this.plot = chart.getXYPlot();
		
		
		
		int index = 0;
		for(XYDataset dataset : dataSetList){
			this.plot.setDataset(index, dataset);
			index++;
		}
		
		for(int i=0;i<this.dataSetList.size();i++){
			
			Random randomGenerator = new Random();
			int red = randomGenerator.nextInt(256);
			int green = randomGenerator.nextInt(256);
			int blue = randomGenerator.nextInt(256);
			Color randomColour = new Color(red,green,blue);
			
			XYLineAndShapeRenderer obj = new XYLineAndShapeRenderer();
			this.plot.setRenderer(i, obj);
			this.plot.getRendererForDataset(this.plot.getDataset(i)).setSeriesPaint(0, randomColour);
		}
		
		
		this.plot.setDomainGridlinesVisible(true);
		this.plot.setDomainCrosshairVisible(true);
		
		this.chartPanel = new ChartPanel(this.chart);
		this.chartPanel.getInitialDelay();
		this.chartPanel.setInitialDelay(0);
	}
	
	public void initDataSet(ArrayList<ArrayList<Float>> xDataList,ArrayList<ArrayList<Float>> yDataList){
		for(int i=0; i<xDataList.size(); i++){
			XYSeries series = new XYSeries((i+1)+"."+this.legendList.get(i));
			for(int j=0; j<xDataList.get(i).size();j++){
				series.add(xDataList.get(i).get(j), yDataList.get(i).get(j));
			}
			XYDataset dataset = new XYSeriesCollection(series);
			dataSetList.add(dataset);
		}
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
	
}
