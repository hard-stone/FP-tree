package com.model.elegrade;


/**
 * @author renbin
 * 2017.11.21
 * TEM �¶�/����   ���϶�(��) Decimal                                                                                   
 * TEM_Max �������  ���϶�(��) Decimal                                                                                   
 * TEM_Min �������   ���϶�(��) Decimal 
 */
public class Gre_temple {
	private static String description;
	private static String grade;
	private static float temple_max;//�������ڼ����ж�
	private static float temple_min;//�������ڼ����ж�
	
	
	public static String getgrade(float temple) {
		if (temple<=-40) {
			grade="tem0";
			description="�¶ȵ���-40";
		}else if (temple<=-35) {
			grade="tem1";
			description="�溮";
		}else if (temple<=-30) {
			grade="tem2";
			description="�Ắ";
		}else if (temple<=-20) {
			grade="tem3";
			description="�Ϻ�";
		}else if (temple<=-15) {
			grade="tem4";
			description="�";
		}else if (temple<=-10) {
			grade="tem5";
			description="��";
		}else if (temple<=-5) {
			grade="tem6";
			description="С��";
		}else if (temple<=0) {
			grade="tem7";
			description="�Ắ";
		}else if (temple<=5) {
			grade="tem8";
			description="΢��";
		}else if (temple<=10) {
			grade="tem9";
			description="��";
		}else if (temple<=12) {
			grade="tem10";
			description="΢��";
		}else if (temple<=14) {
			grade="tem11";
			description="΢����";
		}else if (temple<=16) {
			grade="tem12";
			description="�º�";
		}else if (temple<=18) {
			grade="tem13";
			description="΢�º�";
		}else if (temple<=20) {
			grade="tem14";
			description="��ů";
		}else if (temple<=22) {
			grade="tem15";
			description="ů";
		}else if (temple<=25) {
			grade="tem16";
			description="��";
		}else if (temple<=28) {
			grade="tem17";
			description="����";
		}else if (temple<=30) {
			grade="tem18";
			description="����";
		}else if (temple<=35) {
			grade="tem19";
			description="����";
		}else if (temple<=40) {
			grade="tem20";
			description="����";
		}else if (temple<=49) {
			grade="tem21";
			description="����";
		}else{
			grade="tem0";
			description="�¶ȴ���49���쳣";
		}
		return description;
	}
}
