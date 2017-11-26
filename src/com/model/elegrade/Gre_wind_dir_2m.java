package com.model.elegrade;

/**
 * @author renbin
 * 2017.11.21
 */
public class Gre_wind_dir_2m {

	private static String description;
	private static String grade;
	private static float win_dir_2m_max;
	private static float win_dir_2m_min;

	public static String getgrade(float eleval) {
		if (eleval >= 11.26 && eleval <= 33.75) {
			description = "北东北";
			grade = "NNE";
		} else if (eleval >= 33.76 && eleval <= 56.25) {
			description = "东北";
			grade = "NE";
		} else if (eleval >= 56.26 && eleval <= 78.75) {
			description = "东东北";
			grade = "ENE";
		} else if (eleval >= 78.76 && eleval <= 101.25) {
			description = "东";
			grade = "E";
		} else if (eleval >= 101.26 && eleval <= 123.75) {
			description = "东东南";
			grade = "ESE";
		} else if (eleval >= 123.76 && eleval <= 146.25) {
			description = "东南";
			grade = "SE";
		} else if (eleval >= 146.26 && eleval <= 168.75) {
			description = "南东南";
			grade = "SSE";
		} else if (eleval >= 168.76 && eleval <= 191.25) {
			description = "南";
			grade = "S";
		} else if (eleval >= 191.76 && eleval <= 213.75) {
			description = "南西南";
			grade = "SSW";
		} else if (eleval >= 213.76 && eleval <= 236.25) {
			description = "西南";
			grade = "SW";
		} else if (eleval >= 258.76 && eleval <= 281.25) {
			description = "西";
			grade = "W";
		} else if (eleval >= 281.26 && eleval <= 303.75) {
			description = "西西北";
			grade = "WNW";
		} else if (eleval >= 303.76 && eleval <= 326.25) {
			description = "西北";
			grade = "NW";
		} else if (eleval >= 326.26 && eleval <= 348.75) {
			description = "北西北";
			grade = "NNW";
		} else if ((eleval >= 348.76 && eleval <= 360)
				|| (eleval > 0 && eleval <= 11.25)) {
			description = "北";
			grade = "N";
		} else {
			description = "数据异常";
			grade = "wind_dir_0";
		}

		return description;
	}
}
