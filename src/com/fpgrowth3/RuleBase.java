package com.fpgrowth3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleBase {
	
	public static Map<String, ArrayList<StrongAssociationRule>> rulebase= new HashMap<String, ArrayList<StrongAssociationRule>>();
	private FPTree fpTree = new FPTree();
	static List<List<String>> testtransaction = null;
	
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
	
	public void testIsmatch(String[] filenames) {
		testtransaction = fpTree.readTransRocords(filenames);
		List<String> testdate = null;
		int countok = 0;
		int countnot = 0;
		for (int i = 0; i < testtransaction.size(); i++) {

			/*取出第i条测试数据*/
			testdate = testtransaction.get(i);
			if (ismatch(testdate)) {
				countok++;
			}else {
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
					rulemap.put(date[0], date[1]);//规则的map
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
					flag.put(testvalue, 1);
				}
			}
		}
		
		
		
/*		for (String s : testmap.keySet()) {
			if (flag.get(s)==1) {//说明这个类型为s的条件已经符合条件
				continue;
			}
			String testvalue =  testmap.get(s);//取测试数据test的value作为结果值
			ArrayList<StrongAssociationRule> rules = null;
			if (rulebase.containsKey(testvalue)) {
				 rules = rulebase.get(testvalue);//取出来的规则集
			}else {
				continue;
			}
			
			HashMap<String, String> rulemap= null;
			for (int j = 0; j < rules.size(); j++) {//对应每一条规则
				rulemap = new HashMap<String, String>();
				StrongAssociationRule strongAssociationRule = rules.get(j);
				List<String> rulecondition = strongAssociationRule.condition;取出该规则的条件集
				for (int k = 0; k < rulecondition.size(); k++) {//取出每一个单独的条件
					String conditon = rulecondition.get(k);
					String[] date = conditon.split(":");
					rulemap.put(date[0], date[1]);//规则的map
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
					flag.put(testvalue, 1);
				}
			}
		}*/

		for (String s: flag.keySet()) {
			if (flag.get(s)!=1) {
				return false;
			}
		}
		return true;
	}
	
	
}
