package com.fpgrowth3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public static Map<String, ArrayList<StrongAssociationRule>> rulebase= new HashMap<String, ArrayList<StrongAssociationRule>>();
	public List<List<String>> testtransaction = null;
	
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
	
	public List<List<String>> readTransRocords(String[] filenames) {
        Set<String> set = new HashSet<String>();
        List<List<String>> transaction = null;
        if (filenames.length > 0) {
            transaction = new LinkedList<List<String>>();
            for (String filename : filenames) {
                try {
                    FileReader fr = new FileReader(filename);
                    BufferedReader br = new BufferedReader(fr);
                    try {
                        String line = null;
                        // 一项事务占一行
                        while ((line = br.readLine()) != null) {
                            if (line.trim().length() > 0) {
                                // 每个item之间用","分隔
                                String[] str = line.split(",");
                                
                                /*数据离散化**********************************/
                    			String grade =  null;
                    			str[0] = "station:"+ str[0];
                    			grade = Gre_PRS.getgrade(Float.parseFloat(str[1]));
                    			str[1] = "prs:"+grade;//气压
                    			grade = Gre_presea.getgrade(Float.parseFloat(str[2]));
                    			str[2] = "presea:"+grade;//海平面气压
                    			grade = Gre_wind_dir_2m.getgrade(Float.parseFloat(str[3]));
                    			str[3] = "wind_dir:"+grade;//2min风向
                    			grade = Gre_wind_speed_2m.getgrade(Float.parseFloat(str[4]));
                    			str[4] = "wind_speed:"+grade;//2min风速
                    			grade = Gre_temple.getgrade(Float.parseFloat(str[5]));
                    			str[5] = "temple:"+grade;//温度
                    			grade = Gre_RHU.getgrade(Float.parseFloat(str[6]));
                    			str[6] = "rhu:"+grade;//相对湿度
                    			grade = Gre_vap.getgrade(Float.parseFloat(str[7]));
                    			str[7] = "vap:"+grade;//水气压
                    			grade = Gre_Pre_1h.getgrade(Float.parseFloat(str[8]));
                    			str[8] =  "pre_1h:"+grade;//一小时降雨量
                    			grade = Gre_wind_power.getgrade(Float.parseFloat(str[9]));
                    			str[9] =  "wind_power:"+grade;//风力
                                
                                
                                //每一项事务中的重复项需要排重
                                List<String> rl = new ArrayList<String>();
                                for (String w : str) {
                                    rl.add(w);
                                }
                                transaction.add(rl);//事务数据库
                            }
                        }
                    } finally {
                        br.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Read transaction records failed." + ex.getMessage());
                    System.exit(1);
                }
            }
        }

        return transaction;
    }

	
	
	public void testIsmatch(String[] filenames) {
		FPTree fpTree = new FPTree();
		testtransaction = readTransRocords(filenames);
		List<String> testdate = null;
		int countok = 0;
		int countnot = 0;
		for (int i = 0; i < testtransaction.size(); i++) {
			/*取出第i条测试数据*/
			testdate = testtransaction.get(i);
			/*打印第i条数据*/
			System.out.print("第"+i+"条数据是：");
			for (String string : testdate) {
				System.out.print(string+",");
			}
			System.out.println();
			if (ismatch(testdate)) {
				countok++;
			}else {
				System.out.println("第"+i+"条数据异常");
				countnot++;
			}
		}
		System.out.println("有"+countok+"条数据正常");
		System.out.println("有"+countnot+"条数据不正常");
	}

	
	/**
	 * 单条测试数据与规则库比较，规则是如果每个气象要素都符合条件，则匹配否则不匹配
	 * @param testdate 输入一条测试数据
	 * @return 返回是否匹配
	 */
	private boolean ismatch(List<String> testdate) {
		// TODO Auto-generated method stub
		HashMap<String, String> testmap= new HashMap<String, String>();
		HashMap<String, Integer> flag= new HashMap<String, Integer>();
		/*取出测试数据的string作为结果*/
		for (int i = 0; i < testdate.size(); i++) {
			String test = testdate.get(i);//test作为结果
			String[] date = test.split(":");
			testmap.put(date[0], date[1]);
			flag.put(date[0], 0);
		}
		
		for (int i = 0; i < testdate.size(); i++) {
			String test = testdate.get(i);//test作为结果
			String[] dates = test.split(":");
			String testvalue =  testmap.get(dates[0]);//取测试数据test的value作为结果值
			ArrayList<StrongAssociationRule> rules = null;
			if (rulebase.containsKey(test)) {
				 rules = rulebase.get(test);//取出来的规则集
//				 for (StrongAssociationRule strongAssociationRule : rules) {
//					 List<String> condition = strongAssociationRule.condition;
//					 System.out.print("输出结果为"+test+",的规则是:");
//					 for (String string : condition) {
//						System.out.println(string+",");
//					 }
//					 System.out.println();
//				}
			}else {
				continue;
			}
			HashMap<String, String> rulemap= null;
			for (int j = 0; j < rules.size(); j++) {//对应每一条规则
				rulemap = new HashMap<String, String>();
				StrongAssociationRule strongAssociationRule = rules.get(j);
				List<String> rulecondition = strongAssociationRule.condition;/*取出该规则的条件集*/
				for (int k = 0; k < rulecondition.size(); k++) {//取出每一个单独的条件
					String conditon = rulecondition.get(k);
					String[] date = conditon.split(":");
					rulemap.put(date[0], date[1]);//规则的每个条件形成的MAP
				}
				int count = 0;
				for (String string : rulemap.keySet()) {
					if (rulemap.get(string).equals(testmap.get(string))) {
						count ++;
					}else {
						break;
					}
				}
				if (count==rulemap.size()) {
					//说明该条规则的所有条件都符合
					for (String string : rulemap.keySet()) {
						flag.put(string, 1);
					}
					flag.put(dates[0], 1);
				}
			}
		}

		for (String s: flag.keySet()) {
			if (flag.get(s)!=1) {
				System.out.print("发现"+s+"不是符合的要素,所以:");
				return false;
			}
		}
		return true;
	}
	
	
}
