package com.model.elegrade;


/**
 * @author renbin
 * 2017.11.21
 * TEM 温度/气温   摄氏度(℃) Decimal                                                                                   
 * TEM_Max 最高气温  摄氏度(℃) Decimal                                                                                   
 * TEM_Min 最低气温   摄氏度(℃) Decimal 
 */
public class Gre_temple {
	private static String description;
	private static String grade;
	private static float temple_max;//可以用于极致判断
	private static float temple_min;//可以用于极致判断
	
	
	public static String getgrade(float temple) {
		if (temple<=-40) {
			grade="tem0";
			description="温度低于-40";
		}else if (temple<=-35) {
			grade="tem1";
			description="奇寒";
		}else if (temple<=-30) {
			grade="tem2";
			description="酷寒";
		}else if (temple<=-20) {
			grade="tem3";
			description="严寒";
		}else if (temple<=-15) {
			grade="tem4";
			description="深寒";
		}else if (temple<=-10) {
			grade="tem5";
			description="大寒";
		}else if (temple<=-5) {
			grade="tem6";
			description="小寒";
		}else if (temple<=0) {
			grade="tem7";
			description="轻寒";
		}else if (temple<=5) {
			grade="tem8";
			description="微寒";
		}else if (temple<=10) {
			grade="tem9";
			description="凉";
		}else if (temple<=12) {
			grade="tem10";
			description="微凉";
		}else if (temple<=14) {
			grade="tem11";
			description="微温凉";
		}else if (temple<=16) {
			grade="tem12";
			description="温和";
		}else if (temple<=18) {
			grade="tem13";
			description="微温和";
		}else if (temple<=20) {
			grade="tem14";
			description="温暖";
		}else if (temple<=22) {
			grade="tem15";
			description="暖";
		}else if (temple<=25) {
			grade="tem16";
			description="热";
		}else if (temple<=28) {
			grade="tem17";
			description="炎热";
		}else if (temple<=30) {
			grade="tem18";
			description="暑热";
		}else if (temple<=35) {
			grade="tem19";
			description="酷热";
		}else if (temple<=40) {
			grade="tem20";
			description="奇热";
		}else if (temple<=49) {
			grade="tem21";
			description="极热";
		}else{
			grade="tem0";
			description="温度大于49，异常";
		}
		return description;
	}
}
