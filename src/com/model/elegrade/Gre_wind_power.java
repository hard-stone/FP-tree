package com.model.elegrade;

public class Gre_wind_power {
	private static String description;
	private static String grade;
	
	public static String getgrade(float eleval) {
		if (eleval<0) {
			description="�����쳣";
			grade="win_power_0";
		}else if (eleval<=1) {
			description="�޷�";
			grade="win_power_1";
		}else if (eleval<=2) {
			description="���";
			grade="win_power_2";
		}else if (eleval<=3) {
			description="���";
			grade="win_power_3";
		}else if (eleval<=4) {
			description="΢��";
			grade="win_power_4";
		}else if (eleval<=5) {
			description="�ͷ�";
			grade="win_power_5";
		}else if (eleval<=6) {
			description="���";
			grade="win_power_6";
		}else if (eleval<=7) {
			description="ǿ��";
			grade="win_power_7";
		}else if (eleval<=8) {
			description="����";
			grade="win_power_8";
		}else if (eleval<=9) {
			description="���";
			grade="win_power_9";
		}else if (eleval<=10) {
			description="�ҷ�";
			grade="win_power_10";
		}else if (eleval<=11) {
			description="���";
			grade="win_power_11";
		}else if (eleval<=12) {
			description="����";
			grade="win_power_12";
		}else if (eleval<=13) {
			description="һ��쫷�-̨��";
			grade="win_power_13";
		}else if (eleval<=14) {
			description="һ��쫷�-ǿ̨��";
			grade="win_power_14";
		}else if (eleval<=17) {
			description="14-17���缶";
			grade="win_power_15";
		}else {
			description="�����쳣";
			grade="win_power_0";
		}
		
		return description;
	}
}
