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
			description = "������";
			grade = "NNE";
		} else if (eleval >= 33.76 && eleval <= 56.25) {
			description = "����";
			grade = "NE";
		} else if (eleval >= 56.26 && eleval <= 78.75) {
			description = "������";
			grade = "ENE";
		} else if (eleval >= 78.76 && eleval <= 101.25) {
			description = "��";
			grade = "E";
		} else if (eleval >= 101.26 && eleval <= 123.75) {
			description = "������";
			grade = "ESE";
		} else if (eleval >= 123.76 && eleval <= 146.25) {
			description = "����";
			grade = "SE";
		} else if (eleval >= 146.26 && eleval <= 168.75) {
			description = "�϶���";
			grade = "SSE";
		} else if (eleval >= 168.76 && eleval <= 191.25) {
			description = "��";
			grade = "S";
		} else if (eleval >= 191.76 && eleval <= 213.75) {
			description = "������";
			grade = "SSW";
		} else if (eleval >= 213.76 && eleval <= 236.25) {
			description = "����";
			grade = "SW";
		} else if (eleval >= 258.76 && eleval <= 281.25) {
			description = "��";
			grade = "W";
		} else if (eleval >= 281.26 && eleval <= 303.75) {
			description = "������";
			grade = "WNW";
		} else if (eleval >= 303.76 && eleval <= 326.25) {
			description = "����";
			grade = "NW";
		} else if (eleval >= 326.26 && eleval <= 348.75) {
			description = "������";
			grade = "NNW";
		} else if ((eleval >= 348.76 && eleval <= 360)
				|| (eleval > 0 && eleval <= 11.25)) {
			description = "��";
			grade = "N";
		} else {
			description = "�����쳣";
			grade = "wind_dir_0";
		}

		return description;
	}
}
