package com.fpgrowth3;

import java.util.ArrayList;
import java.util.Map;

import com.model.elegrade.Gre_PRS;
import com.model.elegrade.Gre_Pre_1h;
import com.model.elegrade.Gre_RHU;
import com.model.elegrade.Gre_presea;
import com.model.elegrade.Gre_temple;
import com.model.elegrade.Gre_vap;
import com.model.elegrade.Gre_wind_dir_2m;
import com.model.elegrade.Gre_wind_power;
import com.model.elegrade.Gre_wind_speed_2m;

public class RuleBase {
	
	public static Map<String, ArrayList<StrongAssociationRule>> rulebase= null;
	
	public static void addrule(String result, StrongAssociationRule rule) {
		ArrayList<StrongAssociationRule> rulelist = null;
		if (rulebase.containsKey(result)) {
			rulelist = rulebase.get(result);
		}else {
			rulelist = new ArrayList<StrongAssociationRule>();
		}
		rulelist.add(rule);
		rulebase.put(result, rulelist);
	}
	
	public static boolean isMatch(String[] str) {
        /*数据离散化**********************************/
		String grade =  null;
		grade = Gre_PRS.getgrade(Float.parseFloat(str[1]));
		str[1] = grade;//气压
		grade = Gre_presea.getgrade(Float.parseFloat(str[2]));
		str[2] = grade;//海平面气压
		grade = Gre_wind_dir_2m.getgrade(Float.parseFloat(str[3]));
		str[3] = grade;//2min风向
		grade = Gre_wind_speed_2m.getgrade(Float.parseFloat(str[4]));
		str[4] = grade;//2min风速
		grade = Gre_temple.getgrade(Float.parseFloat(str[5]));
		str[5] = grade;//温度
		grade = Gre_RHU.getgrade(Float.parseFloat(str[6]));
		str[6] = grade;//相对湿度
		grade = Gre_vap.getgrade(Float.parseFloat(str[7]));
		str[7] = grade;//水气压
		grade = Gre_Pre_1h.getgrade(Float.parseFloat(str[8]));
		str[8] = grade;//一小时降雨量
		grade = Gre_wind_power.getgrade(Float.parseFloat(str[9]));
		str[9] = grade;//风力
		/*数据离散化**********************************/
		
		
		return false;
		
	}
}
