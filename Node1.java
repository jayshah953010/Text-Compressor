import java.util.*;
import java.lang.*;
import java.io.*;
public class Node1 {
	Node1 left;
	Node1 right;
	String value;
	Node1(String x)
	{
		this.value = x;
		this.left = null;
		this.right = null;
	}
	public boolean isLeaf()
	{
		return (this.left == null && this.right == null);
	}
	public static void tree_for_decoder(Map<String,String> hm,Node1 root_node)
	{
		
		for(String k :hm.keySet())
		{
			String tmp = hm.get(k);
			//System.out.println(tmp);
			int i=0;
			Node1 temp = root_node;
			while(i<tmp.length())
			{
				//System.out.println(tmp.charAt(i));
				temp = insert1(temp,tmp.charAt(i));
				i++;
			}
			//System.out.println("K is " + k);
			temp.value = k;
				//h.insert(k,hm.get(k));
		}
	}
		public static void tree_for_decoder(Map<String,String> hm)
		{
			return;
			}
		
	
	public static Node1 insert1(Node1 x,char c)
	{
	
		if(c=='0')
		{
			if(x.left==null)
			{
				x.left = new Node1("0");
			}
			x = x.left;
		}
		else
		{
			if(x.right==null)
			{
				x.right = new Node1("0");
			}
			x = x.right;
		}
		return x;
	}
}
