package com.model.elegrade;

/**
 * @author renbin 
 * 2017.11.21 
 * 气压范围没有查到资料，暂时用数据表中最大值最小值代替
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
			description="气压异常:低于907";
			grade="prs0";
		} else if (eleval <= 937) {
			description="气压一级";
			grade="prs1";
		} else if (eleval <= 947) {
			description="气压二级";
			grade="prs2";
		} else if (eleval <= 957) {
			description="气压三级";
			grade="prs3";
		} else if (eleval <= 967) {
			description="气压四级";
			grade="prs4";
		} else if (eleval <= 977) {
			description="气压五级";
			grade="prs5";
		} else if (eleval <= 987) {
			description="气压六级";
			grade="prs6";
		} else {
			description="气压异常:高于987";
			grade="prs0";
		}
		return description;
	}

}
