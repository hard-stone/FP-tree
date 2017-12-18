package com.fpgrowth2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * FP-tree�㷨:�����ھ���������ݿ��е�Ƶ������÷����Ƕ�APriori�㷨�ĸĽ�
 * 
 * @author ShiMin
 * @date   2015/10/17 
 */
public class FPTree
{
	private int minSupport;//��С֧�ֶ�
	
	public FPTree(int support)
	{
		this.minSupport = support;
	}
	
	public static void main(String[] args)
	{
		System.out.println("start");
		FPTree fptree = new FPTree(4);
		List<List<String>> transactions = fptree.loadTransaction("C:\\Users\\Administrator\\Desktop\\��������\\��Сʱ�۲�����\\test.csv");
		fptree.FPGrowth(transactions, null);
	}
	
	/**
	 * �����������ݿ�  
	 * @param file �ļ�·����  �ļ���ÿ��item�ɿո�ָ�
	 */
	public List<List<String>> loadTransaction(String file)
	{
		List<List<String>> transactions = new ArrayList<List<String>>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(file)));
			String line = ",";
			while((line = br.readLine()) != null)
			{
				transactions.add(Arrays.asList(line.split(",")));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return transactions;
	}
	
	public void FPGrowth(List<List<String>> transactions, List<String> postPattern)
	{
		//����ͷ���
		List<TNode> headerTable = buildHeaderTable(transactions);
		//����FP��
		TNode tree = bulidFPTree(headerTable, transactions);
		//����Ϊ��ʱ�˳�
		if (tree.getChildren()== null || tree.getChildren().size() == 0)
		{
			return;
		}
		//���Ƶ���
        if(postPattern!=null)
        {
            for (TNode head : headerTable) 
            {
                System.out.print(head.getCount() + " " + head.getItemName());
                for (String item : postPattern)
                {
                	System.out.print(" " + item);
                }
                System.out.println();
            }
        }
		//����ÿһ��ͷ���ڵ� 
		for(TNode head : headerTable)
		{
			List<String> newPostPattern = new LinkedList<String>();
			newPostPattern.add(head.getItemName());//��ӱ���ģʽ��
			//���Ͻ�ǰ���ۻ���ǰ׺ģʽ��
			if (postPattern != null)
			{
                newPostPattern.addAll(postPattern);
			}
			//�����µ��������ݿ�
		    List<List<String>> newTransaction = new LinkedList<List<String>>();
            TNode nextnode = head.getNext();
            //ȥ������Ϊhead.getItemName()��ģʽ���������µ��������ݿ�
            while(nextnode != null)
            {
            	int count = nextnode.getCount();
            	List<String> parentNodes = new ArrayList<String>();//nextnode�ڵ���������Ƚڵ�
            	TNode parent = nextnode.getParent();
            	while(parent.getItemName() != null)
            	{
            		parentNodes.add(parent.getItemName());
            		parent = parent.getParent();
            	}
            	//���������ݿ����ظ����count��parentNodes
            	while((count--) > 0)
            	{
            		newTransaction.add(parentNodes);//���ģʽ����ǰ׺ ��������յ�Ƶ����Ϊ:  parentNodes -> newPostPattern
            	}
            	//��һ��ͬ���ڵ�
            	nextnode = nextnode.getNext();
            }
			//ÿ��ͷ���ڵ��ظ��������в������ݹ�
            FPGrowth(newTransaction, newPostPattern);
		}
	}
	
	/**
	 * ����ͷ������ݼ��ź���
	 * @return
	 */
	public List<TNode> buildHeaderTable(List<List<String>> transactions)
	{
		List<TNode> list = new ArrayList<TNode>();
		Map<String,TNode> nodesmap = new HashMap<String,TNode>();
		//Ϊÿһ��item����һ���ڵ�
		for(List<String> lines : transactions)
		{
			for(int i = 0; i < lines.size(); ++i)
			{
				String itemName = lines.get(i);
				if(!nodesmap.keySet().contains(itemName)) //Ϊitem�����ڵ�
				{
					nodesmap.put(itemName, new TNode(itemName));
				}
				else //���Ѿ��������ýڵ㣬���ִ�����1
				{
					nodesmap.get(itemName).increaseCount(1);
				}
			}
		}
		//ɸѡ������С֧�ֶȵ�item�ڵ�
		for(TNode item : nodesmap.values())
		{
			if(item.getCount() >= minSupport)
			{
				list.add(item);
			}
		}
		//��countֵ�Ӹߵ�������
		Collections.sort(list);
		return list;
	}
	
	/**
	 * ����FR-tree
	 * @param headertable ͷ���
	 * @return 
	 */
	public TNode bulidFPTree(List<TNode> headertable, List<List<String>> transactions)
	{
		TNode rootNode = new TNode();
		for(List<String> items : transactions)
		{
			LinkedList<String> itemsDesc = sortItemsByDesc(items, headertable);
			//Ѱ�����itemsDescΪ�����ĸ��ڵ�
			TNode subtreeRoot = rootNode;
			if(subtreeRoot.getChildren().size() != 0)
			{
				TNode tempNode = subtreeRoot.findChildren(itemsDesc.peek());
				while(!itemsDesc.isEmpty() && tempNode != null)
				{
					tempNode.increaseCount(1);
					subtreeRoot = tempNode;
					itemsDesc.poll();
					tempNode = subtreeRoot.findChildren(itemsDesc.peek());
				}
			}
			//��itemsDesc��ʣ��Ľڵ������ΪsubtreeRoot������
			addSubTree(headertable, subtreeRoot, itemsDesc);
		}
		return rootNode;
	}
	
	/**
	 * @param headertable ͷ���
	 * @param subtreeRoot �������ڵ�
	 * @param itemsDesc ����ӵ�����
	 */
	public void addSubTree(List<TNode> headertable, TNode subtreeRoot, LinkedList<String> itemsDesc)
	{
		if(itemsDesc.size() > 0)
		{
			TNode thisnode = new TNode(itemsDesc.pop());//�����½ڵ�
			subtreeRoot.getChildren().add(thisnode);
			thisnode.setParent(subtreeRoot);
			//��thisnode����ͷ����Ӧ�ڵ������ĩβ
			for(TNode node : headertable)
			{
				if(node.getItemName().equals(thisnode.getItemName()))
				{
					TNode lastNode = node;
					while(lastNode.getNext() != null)
					{
						lastNode = lastNode.getNext();
					}
					lastNode.setNext(thisnode);
				}
			}
			subtreeRoot = thisnode;//���¸��ڵ�Ϊ��ǰ�ڵ�
			//�ݹ����ʣ���items
			addSubTree(headertable, subtreeRoot, itemsDesc);
		}
	}
	
	//��items��count�Ӹߵ�������
	public LinkedList<String> sortItemsByDesc(List<String> items, List<TNode> headertable)
	{
		LinkedList<String> itemsDesc = new LinkedList<String>();
		for(TNode node : headertable)
		{
			if(items.contains(node.getItemName()))
			{
				itemsDesc.add(node.getItemName());
			}
		}
		return itemsDesc;
	}
	
	/**
	 * fp-tree�ڵ�����ݽṹ��һ��item��ʾһ���ڵ㣩
	 * @author shimin
	 *
	 */
	public class TNode implements Comparable<TNode>
	{
		private String itemName; //��Ŀ��
		private int count; //�������ݿ��г��ִ���
		private TNode parent; //���ڵ�
		private List<TNode> children; //�ӽڵ�
		private TNode next;//��һ��ͬ���ڵ�
		
		public TNode()
		{
			this.children = new ArrayList<TNode>();
		}
		public TNode(String name)
		{
			this.itemName = name;
			this.count = 1;
			this.children = new ArrayList<TNode>();
		}
		public TNode findChildren(String childName)
		{
			for(TNode node : this.getChildren())
			{
				if(node.getItemName().equals(childName))
				{
					return node;
				}
			}
			return null;
		}
		public TNode getNext()
		{
			return next;
		}
		public TNode getParent()
		{
			return parent;
		}
		public void setNext(TNode next)
		{
			this.next = next;
		}
		public void increaseCount(int num)
		{
			count += num;
		}
		public int getCount()
		{
			return count;
		}
		public String getItemName()
		{
			return itemName;
		}
		public List<TNode> getChildren()
		{
			return children;
		}
		public void setParent(TNode parent)
		{
			this.parent = parent;
		}
		public int compareTo(TNode o)
		{
			return o.getCount() - this.getCount();
		}
	}
}