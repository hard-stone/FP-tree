package com.model.elegrade;

/**
 * @author renbin 
 * 2017.11.21 
 * ��ѹ��Χû�в鵽���ϣ���ʱ�����ݱ������ֵ��Сֵ����
 */
public class Gre_PRS {
	private static String description;
	private static String grade;
	private static float prs_max;
	private static float prs_min;
	private static float prs_sea_max;
	private static float prs_sea_min;

	public static String getgrade(float eleval) {

		if (eleval <= 907) {
			description="��ѹ�쳣:����907";
			grade="prs0";
		} else if (eleval <= 937) {
			description="��ѹһ��";
			grade="prs1";
		} else if (eleval <= 947) {
			description="��ѹ����";
			grade="prs2";
		} else if (eleval <= 957) {
			description="��ѹ����";
			grade="prs3";
		} else if (eleval <= 967) {
			description="��ѹ�ļ�";
			grade="prs4";
		} else if (eleval <= 977) {
			description="��ѹ�弶";
			grade="prs5";
		} else if (eleval <= 987) {
			description="��ѹ����";
			grade="prs6";
		} else {
			description="��ѹ�쳣:����987";
			grade="prs0";
		}
		return description;
	}

}
