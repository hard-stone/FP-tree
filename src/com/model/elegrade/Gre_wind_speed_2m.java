package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 * �����ͷ��ٵȼ����Ǹ��ݷ��ٴ�С��
 */
public class Gre_wind_speed_2m {

	private static String description;
	private static String grade;
	private static float win_s_2m_max;
	private static float win_s_2m_min;
	
	/**
	 * @param eleval ���ٵ�λ(��/��)
	 * @return
	 */
	public static String getgrade(float eleval) {
		if (eleval<0) {
			description="�����쳣";
			grade="win_s0";
		}else if (eleval<=0.2) {
			description="�޷�";
			grade="win_s1";
		}else if (eleval<=1.5) {
			description="���";
			grade="win_s2";
		}else if (eleval<=1.5) {
			description="���";
			grade="win_s3";
		}else if (eleval<=3.3) {
			description="΢��";
			grade="win_s4";
		}else if (eleval<=5.4) {
			description="�ͷ�";
			grade="win_s5";
		}else if (eleval<=7.9) {
			description="���";
			grade="win_s6";
		}else if (eleval<=10.7) {
			description="ǿ��";
			grade="win_s7";
		}else if (eleval<=13.8) {
			description="����";
			grade="win_s8";
		}else if (eleval<=17.1) {
			description="���";
			grade="win_s9";
		}else if (eleval<=20.7) {
			description="�ҷ�";
			grade="win_s10";
		}else if (eleval<=24.4) {
			description="���";
			grade="win_s11";
		}else if (eleval<=28.4) {
			description="����";
			grade="win_s12";
		}else if (eleval<=32.6) {
			description="һ��쫷�-̨��";
			grade="win_s13";
		}else if (eleval<=36.9) {
			description="һ��쫷�-ǿ̨��";
			grade="win_s14";
		}else if (eleval<=61.9) {
			description="14-17���缶";
			grade="win_s15";
		}else {
			description="�����쳣";
			grade="win_s0";
		}
		
		return description;
	}
}
