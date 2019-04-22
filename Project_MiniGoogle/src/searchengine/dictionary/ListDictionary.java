package searchengine.dictionary;

import java.util.ArrayList;

class Value
{
	String value;
	int count=0;
	public Value(String value, int count) 
	{
		this.value = value;
		this.count = count;
	}
	public String toString() 
	{
		return "value=" + value + ",count=" + count;
	}
	
	
}
class Node
{
	String key;
	ArrayList<Value>val;
	Node next;
	Node()
	{
		val=new ArrayList<Value>();
	}
	Node(String Key,Object value)
	{
		this.key=Key;
		val=new ArrayList<Value>();
		val.add(new Value((String)value,1));
		
	}		
}
class link
{
	Node Head;
	int size=0;
	public void addLast(String key,Object val)
	{
		Node n=new Node(key,val);
		if(Head==null)
		{
			Head=n;
			size++;
		}
	
		else
		{
			if(value(key)==null)
			{
			n.next=Head;
			Head=n;
			size++;
			}
			else
			{
				Node temp=Head;
				while(temp!=null)
				{
					int flag=0;
					if(temp.key.equals(key))
					{
						 for(int i=0;i<temp.val.size();i++)
						 {
							 if(temp.val.get(i).value.equals(val))
							 {
								temp.val.get(i).count++;
								flag=1;
							 }
						 }
						 if(flag==0)
						 {
							 temp.val.add(new Value((String)val,1));
						 }
					}
					temp=temp.next;
				}
			}
		}
	}
	public String[] keys()
	{
		Node temp=Head;
		String k[]=new String[size];
		int i=0;
		if(temp==null)
			return k;
		
		else
		{			
			while(temp!=null)
			{
				k[i]=(String) temp.key;
				//System.out.println(temp.key+"as");				
				temp=temp.next;
				i++;
			}
			return k;
		}
	}
	public Object value(String k)
	{
		Node temp=Head;
		if(Head==null)
		{
			System.out.println("No keys present");
			return null;
		}
	else
		{
			while(temp!=null)
			{
				if(temp.key.equals(k))
				{
					return temp.val;
				}
				temp=temp.next;
			}
			return null;
		}
	}
	public Node remove(String key)
	{
		
		if(Head==null)
		{
			System.out.println("Nothing to remove");
			return  null;
		}
		else if(Head.key.equals(key))
		{
			Head=Head.next;
			size--;
			return null;
		}
		else
		{
			Node temp=Head;
			Node temp1=Head;
			while(temp!=null)
			{
				if(temp.key.equals(key))
				{
					Node a=temp;
					temp1.next=temp.next;
					size--;
					return a;
				}
				temp1=temp;
				temp=temp.next;
			}
			System.out.println("No such key");
			return null;
		}
	}
}
public class ListDictionary implements DictionaryInterface {

	link n=new link();
	@Override
	
	public String[] getKeys() {
		// TODO Auto-generated method stub
		return (String[]) n.keys();
	}

	@Override
	public Object getValue(String str) {
		// TODO Auto-generated method stub
		return n.value(str);
	}

	@Override
	public void insert(String key, Object value) {
		// TODO Auto-generated method stub
		n.addLast(key, value);
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		n.remove(key);
		
	}

}



