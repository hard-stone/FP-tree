package com.model.elegrade;

public class Gre_wind_power {
	private static String description;
	private static String grade;
	
	public static String getgrade(float eleval) {
		if (eleval<0) {
			description="风速异常";
			grade="win_power_0";
		}else if (eleval<=1) {
			description="无风";
			grade="win_power_1";
		}else if (eleval<=2) {
			description="软风";
			grade="win_power_2";
		}else if (eleval<=3) {
			description="轻风";
			grade="win_power_3";
		}else if (eleval<=4) {
			description="微风";
			grade="win_power_4";
		}else if (eleval<=5) {
			description="和风";
			grade="win_power_5";
		}else if (eleval<=6) {
			description="清风";
			grade="win_power_6";
		}else if (eleval<=7) {
			description="强风";
			grade="win_power_7";
		}else if (eleval<=8) {
			description="疾风";
			grade="win_power_8";
		}else if (eleval<=9) {
			description="大风";
			grade="win_power_9";
		}else if (eleval<=10) {
			description="烈风";
			grade="win_power_10";
		}else if (eleval<=11) {
			description="狂风";
			grade="win_power_11";
		}else if (eleval<=12) {
			description="暴风";
			grade="win_power_12";
		}else if (eleval<=13) {
			description="一级飓风-台风";
			grade="win_power_13";
		}else if (eleval<=14) {
			description="一级飓风-强台风";
			grade="win_power_14";
		}else if (eleval<=17) {
			description="14-17级风级";
			grade="win_power_15";
		}else {
			description="风速异常";
			grade="win_power_0";
		}
		
		return description;
	}
}
