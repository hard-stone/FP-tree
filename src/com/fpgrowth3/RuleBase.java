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
                        // һ������ռһ��
                        while ((line = br.readLine()) != null) {
                            if (line.trim().length() > 0) {
                                // ÿ��item֮����","�ָ�
                                String[] str = line.split(",");
                                
                                /*������ɢ��**********************************/
                    			String grade =  null;
                    			str[0] = "station:"+ str[0];
                    			grade = Gre_PRS.getgrade(Float.parseFloat(str[1]));
                    			str[1] = "prs:"+grade;//��ѹ
                    			grade = Gre_presea.getgrade(Float.parseFloat(str[2]));
                    			str[2] = "presea:"+grade;//��ƽ����ѹ
                    			grade = Gre_wind_dir_2m.getgrade(Float.parseFloat(str[3]));
                    			str[3] = "wind_dir:"+grade;//2min����
                    			grade = Gre_wind_speed_2m.getgrade(Float.parseFloat(str[4]));
                    			str[4] = "wind_speed:"+grade;//2min����
                    			grade = Gre_temple.getgrade(Float.parseFloat(str[5]));
                    			str[5] = "temple:"+grade;//�¶�
                    			grade = Gre_RHU.getgrade(Float.parseFloat(str[6]));
                    			str[6] = "rhu:"+grade;//���ʪ��
                    			grade = Gre_vap.getgrade(Float.parseFloat(str[7]));
                    			str[7] = "vap:"+grade;//ˮ��ѹ
                    			grade = Gre_Pre_1h.getgrade(Float.parseFloat(str[8]));
                    			str[8] =  "pre_1h:"+grade;//һСʱ������
                    			grade = Gre_wind_power.getgrade(Float.parseFloat(str[9]));
                    			str[9] =  "wind_power:"+grade;//����
                                
                                
                                //ÿһ�������е��ظ�����Ҫ����
                                List<String> rl = new ArrayList<String>();
                                for (String w : str) {
                                    rl.add(w);
                                }
                                transaction.add(rl);//�������ݿ�
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
			/*ȡ����i����������*/
			testdate = testtransaction.get(i);
			/*��ӡ��i������*/
			System.out.print("��"+i+"�������ǣ�");
			for (String string : testdate) {
				System.out.print(string+",");
			}
			System.out.println();
			if (ismatch(testdate)) {
				countok++;
			}else {
				System.out.println("��"+i+"�������쳣");
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
//				 for (StrongAssociationRule strongAssociationRule : rules) {
//					 List<String> condition = strongAssociationRule.condition;
//					 System.out.print("������Ϊ"+test+",�Ĺ�����:");
//					 for (String string : condition) {
//						System.out.println(string+",");
//					 }
//					 System.out.println();
//				}
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
					rulemap.put(date[0], date[1]);//�����ÿ�������γɵ�MAP
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
					flag.put(dates[0], 1);
				}
			}
		}

		for (String s: flag.keySet()) {
			if (flag.get(s)!=1) {
				System.out.print("����"+s+"���Ƿ��ϵ�Ҫ��,����:");
				return false;
			}
		}
		return true;
	}
	
	
}
