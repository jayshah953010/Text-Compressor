import java.io.*;
import java.lang.*;
import java.util.*;

public class Input_Frequency_Reader {
		String s;
	public Map<String,Integer> frequency_reader(String s) throws IOException
	{
		//System.out.println("Enter File Path");
		//Scanner Sc = new Scanner(System.in);
		//s = Sc.nextLine();
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(s));
		Map<String,Integer> hm = new HashMap<String,Integer>();
		while((line=br.readLine()) !=null && !line.equals("")) /*Writing the frequency of each string */
		{
			if(hm.containsKey(line))
			{
				Integer s1 = hm.get(line);
				hm.put(line,++s1);
			}
			else
			{
				hm.put(line,1);	
			}
		}
		
		br.close();
		
		
	    return hm;
	}

	
	
}
