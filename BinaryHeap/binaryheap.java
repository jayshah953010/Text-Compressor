import java.io.*;
import java.util.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class binaryheap{
	static int length;
	static Node[] n;
	static Map<String,String> hm1 = new HashMap<String,String>();
	static Node1 root_node = new Node1("0");
	
	binaryheap(int length)
	{
		this.length = 0;
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
		if(length>=0 && parent(length)>=0 && n[parent(length)].frequency > n[length].frequency )
		{
			Node temp = n[parent(length)];
			n[parent(length)] = n[length];
			n[length] = temp;
			goup(parent(length));
		}
	}
	public static int parent(int child)
	{
		int p = (child-1)/2;
		if(p>=0)
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
		for(i=0;i<this.length;i++)
		{
			System.out.print( n[i].frequency + " * ");
		}
	}
	public Node removeMin()
	{
		if(n[0]==null)
		{
			return null;
		}
		else
		{
			Node x = n[0];
			n[0] = n[length-1];
			length--;
			shiftDown(0);
			return x;
		}
	}
	void shiftDown(int nodeIndex) 
	{
        int l = left(nodeIndex);
        int r = right(nodeIndex);
        int minIndex;
        if(l==-1)
        {
        	return;
        }
        else if(r==-1 && l!=-1)
        {
        	minIndex = l;
        }
        else
        {
        	if(r>=length)
        	{
        		if(l>=length)
        			return;
        		else
        			minIndex = l;
        		
        	}
        	else
        	{
        		int temp = Math.min(n[l].frequency, n[r].frequency);
        		if(temp == n[l].frequency)
        		{
        			minIndex = l;
        		}
        		else
        		{
        			minIndex = r;
        		}
        	}
        	int temp = Math.min(n[minIndex].frequency,n[nodeIndex].frequency);
        	if(temp==n[minIndex].frequency)
        	{
        		Node x = n[nodeIndex];
        		n[nodeIndex] = n[minIndex];
        		n[minIndex] = x;
        		shiftDown(minIndex);
        	}
        }
	}
	public static int left(int parent)
	{
		int l = 2*parent + 1;
		if(l<n.length)
			return l;
		else
			return -1;
		
	}
	public static int right(int parent)
	{
			int r = 2*parent+2;
			if(r<n.length)
			{
				return r;
			}
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
		while(length>1)
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
		System.out.println("Done");
		//System.out.println(n[0].left.frequency);
		return n[0];
	}
	public static void print_codetable(Node root, StringBuffer s,BufferedWriter bw) throws IOException
	{
		
        
		if(root.isLeaf())
	     {
			StringBuffer s1 = new StringBuffer(root.value);
			s1.append(" ");
			s1.append(s);
			//System.out.println(s1.toString());
			hm1.put(root.value,s.toString());
	        bw.write(s1.toString());
	        bw.newLine();
	     }
	     
	     if(!root.isLeaf())
	     {
	         s.append('0');
	         print_codetable(root.left, s, bw);
	         s.deleteCharAt(s.length()-1);
	         
	         s.append('1');
	         print_codetable(root.right, s,bw);
	         s.deleteCharAt(s.length()-1);
	     }
	    // System.out.println("File Writing Done");
	}
	
	
	public static void main(String[] args) throws IOException{
		
		Long T1 = System.nanoTime();
		binaryheap h = new binaryheap(100000000);
		//h.insert("A",21);
		//h.insert("B",2);
		//h.insert("C",5);
		//h.insert("D",3);
		//h.insert("E",4);
		//h.Displayheap();
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
		Long T2 = System.nanoTime();
		
		Long T = T2 - T1;
		System.out.println(T);
		
		String FILENAME = "code_table2.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
		 
		h.print_codetable(root, new StringBuffer(),bw);
		bw.close();
	
		String FILENAME1 = "encoded2.bin";
		

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
		//String s = s2.toString();
		//System.out.println(s);
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