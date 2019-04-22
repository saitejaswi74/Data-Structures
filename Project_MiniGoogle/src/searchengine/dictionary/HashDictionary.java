/**  
 *  
 * This program is part of an implementation for the Mini-Google project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Created by Mahender String on 04-10-2009
 */

package searchengine.dictionary;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This Class implements a HashDictionary implementing the interface
 * DictionaryInterface,which maps key's to values. Any
 * non-<code>null</code> object can be used as a key or as a value. <p>
 * 
 * To successfully store and retrieve objects from HashDictionary, the
 * String Object used as keys and the Object as value's for the key is stored.
 * 
 * An instance of <code>HashDictionary</code> has no parameters, use's the 
 * the default load factor (.75) offers a good tradeoff between
 * time and space costs.  Higher values decrease the space overhead but
 * increase the time cost to look up an entry (which is reflected in most
 * <tt>Hashtable</tt> operations, including <tt>get</tt> and <tt>put</tt>).<p>
 * 
 * 
 * This example creates a HashDictionary of words. It uses the names of
 * the word as keys:
 * <p><blockquote><pre>
 *     HashDictionary  words= new HashDictionary();
 *     words.insert("one", new Integer(1));
 *     words.insert("two", new Integer(2));
 *     words.insert("three", new Integer(3));
 * </pre></blockquote>
 * <p>
 * To retrieve a number, use the following code:
 * <p><blockquote><pre>
 *     Integer n = (Integer)words.getValue("two");
 *     if (n != null) {
 *         System.out.println("two = " + n);
 *     }
 * </pre></blockquote>
 * <p>
 */

public class HashDictionary implements DictionaryInterface {


		Hashtable hm=new Hashtable();
		@SuppressWarnings("unchecked")
		@Override
		public String[] getKeys() {
			// TODO Auto-generated method stub
			int a=hm.size();
			int i=0;
			String st[]=new String[a];
			Enumeration<String	> en=hm.keys();
			while(en.hasMoreElements())
			{
				st[i]=(String) en.nextElement();
				i++;
			}
			return (String[]) st;
		}

		@Override
		public Object getValue(String str) {
			// TODO Auto-generated method stub
			return hm.get(str);
		}

		@Override
		public void insert(String key, Object value) {
			// TODO Auto-generated method stub
			hm.put(key, value);
			
		}

		@Override
		public void remove(String key) {
			// TODO Auto-generated method stub
			hm.remove(key);
		}

	}
