import java.io.*;
import java.util.*;
import java.lang.*;

public class encoder{
	static int length;
	static Node[] n;
	static Map<String,String> hm1 = new HashMap<String,String>();
	static Node1 root_node = new Node1("0");
	encoder(int length)
	{
		this.length = 3;
		n = new Node[length];
	}
	public void insert(String Value,int frequency)
	{
		Node n1 = new Node(Value,frequency);
		n[this.length] = n1;
		
		//System.out.println("Data Inserted : " + n[this.length].value + "," + n[this.length].frequency);
		goup(this.length);
		this.length++;
		
	}
	public static void goup(int length)
	{
		if(length>=3 && parent(length)>=3 && n[parent(length)].frequency > n[length].frequency )
		{
			Node temp = n[parent(length)];
			n[parent(length)] = n[length];
			n[length] = temp;
			goup(parent(length));
		}
	}
	public static int parent(int child)
	{
		int p = (child/4)+2;
		if(p>=3)
		{
			return p;
		}
		else
		{
			return -1;
		}
	}
	public void Displayheap()
	{
		int i;
		for(i=3;i<this.length;i++)
		{
			System.out.print( n[i].frequency + " * ");
		}
	}
	public Node removeMin()
	{
		if(n[3]==null)
		{
			return null;
		}
		else
		{
			Node x = n[3];
			n[3] = n[length-1];
			length--;
			shiftDown(3);
			return x;
		}
	}
	void shiftDown(int nodeIndex) 
	{
        int l1,l2,l3,l4, minIndex1,minIndex2,minIndex;
        l1 = l1(nodeIndex);
        l2 = l2(nodeIndex);
        l3 = l3(nodeIndex);
        l4 = l4(nodeIndex);
        
        if(l1==-1)
        {
        	return;
        }
        else if(l2==-1 && l1!=-1)
        {
        	minIndex1 = l1;
        }
        else
        {
        	//System.out.println(l1 + "[]" + l2);
        	
        	if(l2>=length)
        	{
        		if(l1>=length)
        			return;
        		else
        			minIndex1 = l1;
        	}
        	else
        	{	
        		int temp1 = Math.min(n[l1].frequency, n[l2].frequency);
        	
        		if(temp1 == n[l1].frequency)
        		{
        			minIndex1 = l1;
        		}
        		else
        		{
        			minIndex1 = l2;
        		}
        	}
        }
        if(l3==-1 && l4 == -1)
        {
        	minIndex2 = minIndex1;
        }
        else if(l4==-1 && l3!=-1)
        {
        	minIndex2 = l3;
        }
        else
        {
        	if(l4>=length)
        	{
        		if(l3>=length)
        			minIndex2 = minIndex1;
        		else
        			minIndex2 = l3;
        	}
        	else
        	{	
        		int temp2 = Math.min(n[l3].frequency,n[l4].frequency);
        		if(temp2 == n[l3].frequency)
        		{
        			minIndex2 = l3;
        		}
        		else
        		{
        			minIndex2 = l4;
        		}
        	}
        }
        int temp3 = Math.min(n[minIndex1].frequency, n[minIndex2].frequency);
        if(temp3 == n[minIndex1].frequency)
        {
        	minIndex = minIndex1;
        }
        else
        {
        	minIndex = minIndex2;
        }
        int temp = Math.min(n[nodeIndex].frequency, n[minIndex].frequency);
        if(temp == n[minIndex].frequency)
        {
        	Node a = n[nodeIndex];
        	n[nodeIndex] = n[minIndex];
        	n[minIndex] = a;
        	shiftDown(minIndex);
        }
        
	}
	
	public static int l1(int parent)
	{
		int l = 4*(parent-2);
		if(l<n.length)
			return l;
		else if(l>=length)
			return -1;
		else
			return -1;
		
	}
	public static int l2(int parent)
	{
			int l = 4*(parent-2)+1;
			if(l<n.length)
			{
				return l;
			}
			else if(l>=n.length)
				return -1;
			else
				return -1;
		
	}
	public static int l3(int parent)
	{
			int l = 4*(parent-2)+2;
			if(l<n.length)
			{
				return l;
			}
			else if(l>=n.length)
				return -1;
			else
				return -1;
		
	}
	public static int l4(int parent)
	{
			int l = 4*(parent-2)+3;
			if(l<n.length)
			{
				return l;
			}
			else if(l>=n.length)
				return -1;
			else
				return -1;
		
	}
	public static int findMinimum(int a,int b)
	{
		if(n[a].frequency>n[b].frequency)
		{
			return b;
		}
		else
		{
			return a;
		}
	}
	public void Insert(String value,int frequency,Node a, Node b)
	{
		Node x = new Node(value,frequency,a,b);
		n[this.length] = x;
		goup(this.length);
		this.length++;
	}
	
	public Node build_tree()
	{
		while(length>4)
		{
			
			Node a = removeMin();
			Node b = removeMin();
			int tmp = a.frequency + b.frequency;
			//System.out.println(a.frequency + " and " + b.frequency + " deleted  "+  (a.frequency+b.frequency) + " is added");
			//Node c = new Node(null,tmp,a,b);
			Insert(null,tmp,a,b);
			//System.out.println();
			//Displayheap();
		}
		//System.out.println("Done");
		//System.out.println(n[0].left.frequency);
		return n[3];
	}
	public static void print_codetable(Node root, StringBuffer s,BufferedWriter bw) throws IOException
	{
		if(root.isLeaf())
	     {
			StringBuffer s1 = new StringBuffer(root.value);
			s1.append(" ");
			s1.append(s);
			hm1.put(root.value.toString(),s.toString());
			//System.out.println(s1.toString());
	        bw.write(s1.toString());
	        bw.newLine();
	       // System.out.println(root.value + " ** "+ s);
	     }
	     
	     if(!root.isLeaf())
	     {
	         s.append('0');
	         print_codetable(root.left, s,bw);
	         s.deleteCharAt(s.length()-1);
	         
	         s.append('1');
	         print_codetable(root.right, s,bw);
	         s.deleteCharAt(s.length()-1);
	     }
	}
	public static void main(String[] args) throws IOException{
		
			//Long T1 = System.nanoTime();
		encoder h = new encoder(100000000);
		
		Input_Frequency_Reader r = new Input_Frequency_Reader();
		Map<String,Integer> hm = r.frequency_reader(args[0]);
		
		/* Set set = hm.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }*/
		for(String k :hm.keySet())
			h.insert(k,hm.get(k));
		
		//h.Displayheap();
		Node root = null;
		
		root = h.build_tree();
		//Long T2 = System.nanoTime();
		
		//Long T = T2 - T1;
		//sum = sum + T;
		//System.out.println(T);
		
		//System.out.println("Average Time is " + sum/10 );
		 //System.out.println(root.frequency);
		String FILENAME = "code_table.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
		h.print_codetable(root, new StringBuffer(),bw);
		bw.close();
		
		
		
		String FILENAME1 = "encoded.bin";
		
		OutputStream bw1 = new FileOutputStream(FILENAME1);
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		String line;
		int i = 0;
		StringBuilder s = new StringBuilder();
		while((line=br.readLine()) !=null && !line.equals(""))
		{
			if(hm1.containsKey(line))
			{
				s.append(hm1.get(line));
			}
		}
		
		while(i<s.length())
		{
			//bw1.write(Byte.parseByte(s.substring(i,i+8),2));
			bw1.write((byte)Integer.parseInt(s.substring(i,i+8),2));
			//System.out.println((byte)Integer.parseInt(s.substring(i,i+8),2) + "+++");
			i = i+8;
		}
		
		bw1.close();
		
	}
}

/*C:\Users\JAY SHAH\workspace\Input_Frequency_Reader\sample_input_small.txt*/
