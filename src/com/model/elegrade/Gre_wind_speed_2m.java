package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 * 风力和风速等级都是根据风速大小；
 */
public class Gre_wind_speed_2m {

	private static String description;
	private static String grade;
	private static float win_s_2m_max;
	private static float win_s_2m_min;
	
	/**
	 * @param eleval 风速单位(米/秒)
	 * @return
	 */
	public static String getgrade(float eleval) {
		if (eleval<0) {
			description="风速异常";
			grade="win_s0";
		}else if (eleval<=0.2) {
			description="无风";
			grade="win_s1";
		}else if (eleval<=1.5) {
			description="软风";
			grade="win_s2";
		}else if (eleval<=1.5) {
			description="轻风";
			grade="win_s3";
		}else if (eleval<=3.3) {
			description="微风";
			grade="win_s4";
		}else if (eleval<=5.4) {
			description="和风";
			grade="win_s5";
		}else if (eleval<=7.9) {
			description="清风";
			grade="win_s6";
		}else if (eleval<=10.7) {
			description="强风";
			grade="win_s7";
		}else if (eleval<=13.8) {
			description="疾风";
			grade="win_s8";
		}else if (eleval<=17.1) {
			description="大风";
			grade="win_s9";
		}else if (eleval<=20.7) {
			description="烈风";
			grade="win_s10";
		}else if (eleval<=24.4) {
			description="狂风";
			grade="win_s11";
		}else if (eleval<=28.4) {
			description="暴风";
			grade="win_s12";
		}else if (eleval<=32.6) {
			description="一级飓风-台风";
			grade="win_s13";
		}else if (eleval<=36.9) {
			description="一级飓风-强台风";
			grade="win_s14";
		}else if (eleval<=61.9) {
			description="14-17级风级";
			grade="win_s15";
		}else {
			description="风速异常";
			grade="win_s0";
		}
		
		return description;
	}
}
