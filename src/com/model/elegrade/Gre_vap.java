package com.model.elegrade;

/**
 * @author renbin 
 * 2017.11.21 
 * 水气压范围没有查到资料，暂时用数据表中最大值最小值代替
 * VAP  水汽压        百帕                    Decimal  
 */
public class Gre_vap {
	private static String description;
	private static String grade;
	private static float vap;
	
	public static String getgrade(float eleval) {
		if (eleval<=9.6) {
			description="水气压异常:低于9.6";
			grade="vap0";
		}else if (eleval<=10.6) {
			description="水气压一级";
			grade="vap1";
		}else if (eleval<=11.6) {
			description="水气压二级";
			grade="vap2";
		}else if (eleval<=12.6) {
			description="水气压三级";
			grade="vap3";
		}else if (eleval<=13.6) {
			description="水气压四级";
			grade="vap4";
		}else if (eleval<=14.6) {
			description="水气压五级";
			grade="vap5";
		}else if (eleval<=15.6) {
			description="水气压六级";
			grade="vap6";
		}else if (eleval<=16.6) {
			description="水气压七级";
			grade="vap7";
		}else if (eleval<=17.6) {
			description="水气压八级";
			grade="vap8";
		}else if (eleval<=18.6) {
			description="水气压九级";
			grade="vap9";
		}else if (eleval<=19.6) {
			description="水气压十级";
			grade="vap10";
		}else {
			description="水气压异常:高于19.6";
			grade="vap0";
		}
		return description;
		
	}
	
}
