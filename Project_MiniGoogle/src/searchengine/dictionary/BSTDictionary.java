package searchengine.dictionary;
class BstNode{
	
	String key;
	Object value;
	BstNode left,right;
	BstNode(String key,Object value){
		this.key=key;
		this.value=value;
		this.left=null;
		this.right=null;
	}
}
class list1
{
	BstNode root;
	int size=0;
	public void put(String key,Object val) {
		root=put(root,key,val);
	}
	public BstNode put(BstNode node,String key,Object val) {
		if(node==null) {
			//System.out.println("T1:"+key);
			size++;
			return new BstNode(key,val);
		}
		
		int cmp=key.compareTo((String) node.key);
		if(cmp<0) {
			node.left=put(node.left,key,val);
			
		}
		else if(cmp>0) {
			node.right=put(node.right,key,val);
			
		}
		else {
			node.value=val;
		}
		return node;
	}
	public void delete(String key) {
		root = delete(root,key);
	}

	public BstNode delete(BstNode x, String key) {
		if (x == null)
			return null;
		int cmp = key.compareTo((String) x.key);
		if (cmp < 0)
		{
			x.left = delete(x.left, key);
		
		}
		else if (cmp > 0)
		{
			x.right = delete(x.right, key);
		
		}
		else {
			if (x.right == null)
			{
				size--;
				return x.left;
			}
			if (x.left == null)
			{
				size--;
				return x.right;
			}
		}
		return x;
	}
	public Object get(String Key)
	{
		BstNode node=root;
		while(node!=null)
		{
			if(Key.compareTo((String) node.key)<0)
				node=node.left;
			else if(Key.compareTo((String) node.key)>0)
				node=node.right;
			else
				return (Object) node.value;
			
		}
		return null;
	}
	public String[] getkeys()
	{
		//System.out.println("++++++++");
		String[] a=new String[size];
		int i=0;
		a=inorder(root,a,i);
		return a;
	}
	public String[] inorder(BstNode node,String[] S,int i)
	{
		//System.out.println("++++++++");
		if(node!=null)
		{
			//System.out.println("++++++++");
			//System.out.println(node.key);
		S[i]=(String) node.key;
		i++;
		inorder(node.left,S,i);
		inorder(node.right,S,i);
		}
		return S;		
	}
		
}
public class BSTDictionary implements DictionaryInterface {


	list1 bsnode=new list1();
	@Override
	public String[] getKeys() {
		// TODO Auto-generated method stub
		//System.out.println("++++++++");
		return (String[]) bsnode.getkeys();
	}

	@Override
	public Object getValue(String str) {
		// TODO Auto-generated method stub
		return bsnode.get(str);
	}

	@Override
	public void insert(String key, Object value) {
		bsnode.put(key, value);
	}
	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		bsnode.delete(key);
		
	}

}
