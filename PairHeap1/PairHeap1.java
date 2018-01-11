import java.util.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
public class PairHeap1 {
	static PairNode root;
	static PairNode[] n;
	static  Node[] n2;
	static Node1 root_node = new Node1("0");
	static Map<String,String> hm1 = new HashMap<String,String>();
	
	static int length;
	PairHeap1(int x)
	{
		n = new PairNode[x];
		n2 = new Node[x];
		root = null;
		this.length = 0;
	}
	public static boolean isEmpty()
	{
		return root == null;
	}
	public void makeEmpty()
	{
		root = null;
	}
	public static void insert(PairNode n1)
	{
		
		if(root == null)
		{
			root = n1;
			//System.out.println( n1.frequency +  "Freqeuncy Added" + root.frequency + " becomes a root ");
		}
		else
		{
			root = CompareAndLink(root,n1);
			//System.out.println(n1.frequency + "Frequency Added" + root.frequency + " becomes a root ");
		}
				
	}
	public static PairNode CompareAndLink(PairNode first,PairNode second)
	{
		if(second == null)
			return first;
		if(second.frequency < first.frequency)
		{
			 	second.prev = first.prev;
	            first.prev = second;
	            first.nextSibling = second.leftChild;
	            if (first.nextSibling != null)
	                first.nextSibling.prev = first;
	            second.leftChild = first;            
	            return second;
		}
		else
		{
			second.prev = first;
            first.nextSibling = second.nextSibling;
            if (first.nextSibling != null)
                first.nextSibling.prev = first;
            second.nextSibling = first.leftChild;
            if (second.nextSibling != null)
                second.nextSibling.prev = second;
            first.leftChild = second;
            return first;
		}
		
	}
	public static PairNode removeMin()
	{
		if(isEmpty())
			return null;
		PairNode n1 = new PairNode(root.value,root.frequency,root.lchild,root.rchild);
		if (root.leftChild == null)
		{
			root = null;
		}
		else
		{
			root = combineSiblings( root.leftChild );
		}
		return n1;
		
	}
	public static PairNode combineSiblings(PairNode firstSibling)
	{
		if( firstSibling.nextSibling == null )
			return firstSibling;
	        /* Store the subtrees in an array */
	    	int numSiblings = 0;
	        for ( ; firstSibling != null; numSiblings++)
	        {
	            n = doubleIfFull( n, numSiblings );
	            n[ numSiblings ] = firstSibling;
	            /* break links */
	            firstSibling.prev.nextSibling = null;  
	            firstSibling = firstSibling.nextSibling;
	        }
	        n = doubleIfFull( n, numSiblings );
	        n[ numSiblings ] = null;
	        /* Combine subtrees two at a time, going left to right */
	        int i = 0;
	        for ( ; i + 1 < numSiblings; i += 2)
	            n[ i ] = CompareAndLink(n[i], n[i + 1]);
	        int j = i - 2;
	        /* j has the result of last compareAndLink */
	        /* If an odd number of trees, get the last one */
	        if (j == numSiblings - 3)
	            n[ j ] = CompareAndLink( n[ j ], n[ j + 2 ] );
	        /* Now go right to left, merging last tree with */
	        /* next to last. The result becomes the new last */
	        for ( ; j >= 2; j -= 2)
	            n[j - 2] = CompareAndLink(n[j-2], n[j]);
	        return n[0];
	    }
		public static PairNode[] doubleIfFull(PairNode [ ] array, int index)
	    {
	        if (index == array.length)
	        {
	            PairNode [ ] oldArray = array;
	            array = new PairNode[index * 2];
	            for( int i = 0; i < index; i++ )
	                array[i] = oldArray[i];
	        }
	        return array;
	    }
	public static void build_tree()
	{
		
		
		while(root.leftChild!=null)
		{
			PairNode a = removeMin();
			PairNode b = removeMin();
			int tmp = a.frequency + b.frequency;
			
			PairNode temp = new PairNode(null,tmp,a,b);
			//System.out.println(temp.lchild.frequency);
			//System.out.println(temp.rchild.frequency);
			insert(temp);
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
	public static void print_codetable(PairNode root, StringBuffer s,BufferedWriter bw) throws IOException
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
	         print_codetable(root.lchild, s, bw);
	         s.deleteCharAt(s.length()-1);
	         
	         s.append('1');
	         print_codetable(root.rchild, s,bw);
	         s.deleteCharAt(s.length()-1);
	     }
	    // System.out.println("File Writing Done");
	}
	public static void main(String[] args) throws IOException
	{
		
		Long T1 = System.nanoTime();
		PairHeap1 ph = new PairHeap1(100000000);
		
		Input_Frequency_Reader r = new Input_Frequency_Reader();
		Map<String,Integer> hm = r.frequency_reader(args[0]);
		/*Set set = hm.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	      }*/
		
		for(String k : hm.keySet())
		{
			PairNode temp = new PairNode(k,hm.get(k));
			ph.insert(temp);
		}
		
		
		ph.build_tree(); 
		
		String FILENAME = "code_table1.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
		 
		ph.print_codetable(root, new StringBuffer(),bw);
		bw.close();
		

		String FILENAME1 = "encoded1.bin";
		

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
		Long T2 = System.nanoTime();
		Long T = T2- T1;
		
		
	}

}
