package com.model.elegrade;

/**
 * @author renbin 
 * 2017.11.21 
 * ���ʪ�ȷ�Χû�в鵽���ϣ���ʱ�����ݱ������ֵ��Сֵ����
 */
public class Gre_RHU {
	private static String description;
	private static String grade;
	private static float rhu;
	private static float rhu_min;
	
	public static String getgrade(float eleval) {
		if (eleval<=40) {
			description="���ʪ�������쳣����40";
			grade="rhu0";
		}else if (eleval<=50) {
			description="���ʪ�ȵȼ�1";
			grade="rhu1";
		}else if (eleval<=60) {
			description="���ʪ�ȵȼ�2";
			grade="rhu2";
		}else if (eleval<=70) {
			description="���ʪ�ȵȼ�3";
			grade="rhu3";
		}else if (eleval<=80) {
			description="���ʪ�ȵȼ�4";
			grade="rhu4";
		}else if (eleval<=90) {
			description="���ʪ�ȵȼ�5";
			grade="rhu5";
		}else if (eleval<=100) {
			description="���ʪ�ȵȼ�6";
			grade="rhu6";
		}else {
			description="���ʪ�������쳣����100";
			grade="rhu0";
		}
		
		
		return description;
	}
	
}
