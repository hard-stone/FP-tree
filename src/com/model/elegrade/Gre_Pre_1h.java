package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 *  PRE_1h ��ˮ��  ����     Decimal
 */
public class Gre_Pre_1h {
	
	private static String description;
	private static String grade;
	private static float pre_1h_max;
	
	public static String getgrade(float eleval) {
		if (eleval<=2.5&&eleval>=0) {
			description="С��";
			grade="per1";
		}else if (eleval<=8.0) {
			description="����";
			grade="per2";
		}else if (eleval<=15) {
			description="����";
			grade="per3";
		}else if (eleval<=pre_1h_max) {
			description="����";
			grade="per4";
		}else {
			description="����Сʱ����ֵ���ֵ����Сʱ����ֵΪ��";
			grade="per0";//���������쳣
		}
		return description;
	}
}
