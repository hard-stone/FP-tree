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

			/*ȡ����i����������*/
			testdate = testtransaction.get(i);
			if (ismatch(testdate)) {
				countok++;
			}else {
				countnot++;
			}
		}
		System.out.println("��"+countok+"����������");
		System.out.println("��"+countnot+"�����ݲ�����");
	}

	
	/**
	 * ������������������Ƚϣ����������ÿ������Ҫ�ض�������������ƥ�����ƥ��
	 * @param testdate ����һ����������
	 * @return �����Ƿ�ƥ��
	 */
	private boolean ismatch(List<String> testdate) {
		// TODO Auto-generated method stub
		HashMap<String, String> testmap= new HashMap<String, String>();
		HashMap<String, Integer> flag= new HashMap<String, Integer>();
		/*ȡ���������ݵ�string��Ϊ���*/
		for (int i = 0; i < testdate.size(); i++) {
			String test = testdate.get(i);//test��Ϊ���
			String[] date = test.split(":");
			testmap.put(date[0], date[1]);
			flag.put(date[0], 0);
		}
		
		for (int i = 0; i < testdate.size(); i++) {
			String test = testdate.get(i);//test��Ϊ���
			String[] dates = test.split(":");
			String testvalue =  testmap.get(dates[0]);//ȡ��������test��value��Ϊ���ֵ
			ArrayList<StrongAssociationRule> rules = null;
			if (rulebase.containsKey(test)) {
				 rules = rulebase.get(test);//ȡ�����Ĺ���
			}else {
				continue;
			}
			HashMap<String, String> rulemap= null;
			for (int j = 0; j < rules.size(); j++) {//��Ӧÿһ������
				rulemap = new HashMap<String, String>();
				StrongAssociationRule strongAssociationRule = rules.get(j);
				List<String> rulecondition = strongAssociationRule.condition;/*ȡ���ù����������*/
				for (int k = 0; k < rulecondition.size(); k++) {//ȡ��ÿһ������������
					String conditon = rulecondition.get(k);
					String[] date = conditon.split(":");
					rulemap.put(date[0], date[1]);//�����map
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
					//˵�������������������������
					for (String string : rulemap.keySet()) {
						flag.put(string, 1);
					}
					flag.put(testvalue, 1);
				}
			}
		}
		
		
		
/*		for (String s : testmap.keySet()) {
			if (flag.get(s)==1) {//˵���������Ϊs�������Ѿ���������
				continue;
			}
			String testvalue =  testmap.get(s);//ȡ��������test��value��Ϊ���ֵ
			ArrayList<StrongAssociationRule> rules = null;
			if (rulebase.containsKey(testvalue)) {
				 rules = rulebase.get(testvalue);//ȡ�����Ĺ���
			}else {
				continue;
			}
			
			HashMap<String, String> rulemap= null;
			for (int j = 0; j < rules.size(); j++) {//��Ӧÿһ������
				rulemap = new HashMap<String, String>();
				StrongAssociationRule strongAssociationRule = rules.get(j);
				List<String> rulecondition = strongAssociationRule.condition;ȡ���ù����������
				for (int k = 0; k < rulecondition.size(); k++) {//ȡ��ÿһ������������
					String conditon = rulecondition.get(k);
					String[] date = conditon.split(":");
					rulemap.put(date[0], date[1]);//�����map
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
					//˵�������������������������
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
