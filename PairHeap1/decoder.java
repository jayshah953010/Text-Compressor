

import java.util.*;
import java.lang.*;
import java.io.*;
public class decoder {
	static Map<String,String> hm1 = new HashMap<String,String>();
	static Node1 root_node = new Node1("0");
	
	
	public static void main(String[] args) throws IOException
	{
		Node root = null;
		BufferedReader br = new BufferedReader(new FileReader(args[1]));
		String FILENAME1 = args[0];
		String FILENAME2 = "decoded.txt";
		String line = "";
		StringBuilder s3 = new StringBuilder();
		while((line=br.readLine()) !=null && !line.equals(""))
		{
			String[] tmp = line.split(" ");
			hm1.put(tmp[0],tmp[1]);	
		}
		br.close();
		root_node.tree_for_decoder(hm1,root_node);

		File file = new File(FILENAME1);
		//init array with file length
		byte[] bFile = new byte[(int) file.length()];

		FileInputStream fis = new FileInputStream(file);
		fis.read(bFile); //read file into bytes[]
		fis.close();
		for (int k=0;k<bFile.length;k++)
		{
			//System.out.println(bFile[k]+"**");
			s3.append(String.format("%8s", Integer.toBinaryString(bFile[k] & 0xFF)).replace(' ', '0'));
		}
		BufferedWriter bw2 = new BufferedWriter(new FileWriter(FILENAME2));
		int j=0;
		while(j<s3.length())
		{
			//System.out.println(j);
			Node1 temp1 = root_node;
			while(!(temp1.left==null && temp1.right==null))
			{
				if(s3.charAt(j)=='0')
				{
					temp1 = temp1.left;
				}
				else
				{
					temp1 = temp1.right;
				}
				j++;
			}
			
			
			
			bw2.write(temp1.value);
			bw2.newLine();
			
		}
		bw2.close();
	}
	

}
