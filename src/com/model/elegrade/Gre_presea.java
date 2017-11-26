package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 PRS_Sea   海平面气压       百帕     Decimal  
 */
public class Gre_presea {
	private static String description;
	private static String grade;
	private static float prs_sea_max;
	private static float prs_sea_min;

	public static String getgrade(float eleval) {

		if (eleval <= 996) {
			description="海气压异常:低于907";
			grade="prs0_sea";
		} else if (eleval <= 1006) {
			description="海气压一级";
			grade="prs1_sea";
		} else if (eleval <= 1016) {
			description="海气压二级";
			grade="prs2_sea";
		} else if (eleval <= 1026) {
			description="海气压三级";
			grade="prs3_sea";
		} else if (eleval <= 1036) {
			description="海气压四级";
			grade="prs4_sea";
		} else if (eleval <= 1046) {
			description="海气压五级";
			grade="prs5_sea";
		} else if (eleval <= 1056) {
			description="海气压六级";
			grade="prs6_sea";
		} else {
			description="海气压异常:高于1056";
			grade="prs0_sea";
		}
		return description;
	}
}
