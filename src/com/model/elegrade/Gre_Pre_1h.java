package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 *  PRE_1h 降水量  毫米     Decimal
 */
public class Gre_Pre_1h {
	
	private static String description;
	private static String grade;
	private static float pre_1h_max;
	
	public static String getgrade(float eleval) {
		if (eleval<=2.5&&eleval>=0) {
			description="小雨";
			grade="per1";
		}else if (eleval<=8.0) {
			description="中雨";
			grade="per2";
		}else if (eleval<=15) {
			description="大雨";
			grade="per3";
		}else if (eleval<=pre_1h_max) {
			description="暴雨";
			grade="per4";
		}else {
			description="超过小时雨量值最大值或者小时雨量值为负";
			grade="per0";//代表雨量异常
		}
		return description;
	}
}
