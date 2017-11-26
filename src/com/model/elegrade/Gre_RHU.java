package com.model.elegrade;

/**
 * @author renbin 
 * 2017.11.21 
 * 相对湿度范围没有查到资料，暂时用数据表中最大值最小值代替
 */
public class Gre_RHU {
	private static String description;
	private static String grade;
	private static float rhu;
	private static float rhu_min;
	
	public static String getgrade(float eleval) {
		if (eleval<=40) {
			description="相对湿度数据异常低于40";
			grade="rhu0";
		}else if (eleval<=50) {
			description="相对湿度等级1";
			grade="rhu1";
		}else if (eleval<=60) {
			description="相对湿度等级2";
			grade="rhu2";
		}else if (eleval<=70) {
			description="相对湿度等级3";
			grade="rhu3";
		}else if (eleval<=80) {
			description="相对湿度等级4";
			grade="rhu4";
		}else if (eleval<=90) {
			description="相对湿度等级5";
			grade="rhu5";
		}else if (eleval<=100) {
			description="相对湿度等级6";
			grade="rhu6";
		}else {
			description="相对湿度数据异常高于100";
			grade="rhu0";
		}
		
		
		return description;
	}
	
}
