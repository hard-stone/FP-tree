package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 PRS_Sea   ��ƽ����ѹ       ����     Decimal  
 */
public class Gre_presea {
	private static String description;
	private static String grade;
	private static float prs_sea_max;
	private static float prs_sea_min;

	public static String getgrade(float eleval) {

		if (eleval <= 996) {
			description="����ѹ�쳣:����907";
			grade="prs0_sea";
		} else if (eleval <= 1006) {
			description="����ѹһ��";
			grade="prs1_sea";
		} else if (eleval <= 1016) {
			description="����ѹ����";
			grade="prs2_sea";
		} else if (eleval <= 1026) {
			description="����ѹ����";
			grade="prs3_sea";
		} else if (eleval <= 1036) {
			description="����ѹ�ļ�";
			grade="prs4_sea";
		} else if (eleval <= 1046) {
			description="����ѹ�弶";
			grade="prs5_sea";
		} else if (eleval <= 1056) {
			description="����ѹ����";
			grade="prs6_sea";
		} else {
			description="����ѹ�쳣:����1056";
			grade="prs0_sea";
		}
		return description;
	}
}
