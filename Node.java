import java.io.*;
import java.lang.*;
import java.util.*;

public class Node {
	String value;
	int frequency;
	Node left;
	Node right;
	
	public Node(String value,int frequency)
	{
		this.value = value;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
	public Node(String value,int frequency,Node left,Node right)
	{
		this.value = value;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	public boolean isLeaf()
	{
		return (this.left == null && this.right == null);
	}

}
