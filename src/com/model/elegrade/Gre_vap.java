package com.model.elegrade;

/**
 * @author renbin 
 * 2017.11.21 
 * ˮ��ѹ��Χû�в鵽���ϣ���ʱ�����ݱ������ֵ��Сֵ����
 * VAP  ˮ��ѹ        ����                    Decimal  
 */
public class Gre_vap {
	private static String description;
	private static String grade;
	private static float vap;
	
	public static String getgrade(float eleval) {
		if (eleval<=9.6) {
			description="ˮ��ѹ�쳣:����9.6";
			grade="vap0";
		}else if (eleval<=10.6) {
			description="ˮ��ѹһ��";
			grade="vap1";
		}else if (eleval<=11.6) {
			description="ˮ��ѹ����";
			grade="vap2";
		}else if (eleval<=12.6) {
			description="ˮ��ѹ����";
			grade="vap3";
		}else if (eleval<=13.6) {
			description="ˮ��ѹ�ļ�";
			grade="vap4";
		}else if (eleval<=14.6) {
			description="ˮ��ѹ�弶";
			grade="vap5";
		}else if (eleval<=15.6) {
			description="ˮ��ѹ����";
			grade="vap6";
		}else if (eleval<=16.6) {
			description="ˮ��ѹ�߼�";
			grade="vap7";
		}else if (eleval<=17.6) {
			description="ˮ��ѹ�˼�";
			grade="vap8";
		}else if (eleval<=18.6) {
			description="ˮ��ѹ�ż�";
			grade="vap9";
		}else if (eleval<=19.6) {
			description="ˮ��ѹʮ��";
			grade="vap10";
		}else {
			description="ˮ��ѹ�쳣:����19.6";
			grade="vap0";
		}
		return description;
		
	}
	
}
