/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */ 

package searchengine.indexer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import searchengine.dictionary.AVLDictionary;
import searchengine.dictionary.BSTDictionary;
import searchengine.dictionary.DictionaryInterface;
import searchengine.dictionary.HashDictionary;
import searchengine.dictionary.ListDictionary;
import searchengine.dictionary.MyHashDictionary;
import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageWord;


/**
 * Web-indexing objects.  This class implements the Indexer interface
 * using a list-based index structure.

A Hash Map based implementation of Indexing 

 */
public class Indexer implements IndexerInterface
{
	/** The constructor for ListWebIndex.
	 */

	// Index Structure 
	DictionaryInterface index;

	// This is for calculating the term frequency
	HashMap<String,Integer> wordFrequency;

	public Indexer(String mode)
	{
		// hash - Dictionary Structure based on a Hashtable or HashMap from the Java collections 
		// list - Dictionary Structure based on Linked List 
		// myhash - Dictionary Structure based on a Hashtable implemented by the students
		// bst - Dictionary Structure based on a Binary Search Tree implemented by the students
		// avl - Dictionary Structure based on AVL Tree implemented by the students

		if (mode.equals("hash")) 
			index = new HashDictionary();
		else if(mode.equals("list"))
			index = new ListDictionary();
		else if(mode.equals("myhash"))
			index = new MyHashDictionary();
		else if(mode.equals("bst"))
			index = new BSTDictionary();
		else if(mode.equals("avl"))
			index = new AVLDictionary();
	}

	/** Add the given web page to the index.
	 *
	 * @param url The web page to add to the index
	 * @param keywords The keywords that are in the web page
	 * @param links The hyperlinks that are in the web page
	 */
	public void addPage(URL url, ObjectIterator<?> keywords)	
	{
		wordFrequency = new HashMap<String,Integer>();
		while(keywords.hasNext())
		{
			String str = (String) keywords.next();
			//System.out.println(a);
			if(!wordFrequency.containsKey(str))
			{
				wordFrequency.put(str,1);
			}
			else
			{
				Integer c = wordFrequency.get(str) + 1;
				//System.out.println("c = "+c);
				wordFrequency.put(str, c);
				
			}
		}
		
		Set<String> sst = wordFrequency.keySet();
		Iterator i = sst.iterator();	
		while(i.hasNext())
		{
			String b = (String) i.next();
			//System.out.println("b = "+b);
			//System.out.println("wordFrequency.get(b) = "+wordFrequency.get(b));
			index.insert(b, url+"##"+wordFrequency.get(b));
		}
	}

	/** Produce a printable representation of the index.
	 *
	 * @return a String representation of the index structure
	 */
	public String toString()
	{
		String[] key = index.getKeys();
		String str = null;
		
		for(int i = 0;i < key.length;i++)
		{
			//System.out.println("index.getValue(key[i]) = "+index.getValue(key[i]));
			str = str + key[i] + "\t" + index.getValue(key[i]);
		}
		return str;
		//return index.toString();
	}

	/** Retrieve all of the web pages that contain the given keyword.
	 *
	 * @param keyword The keyword to search on
	 * @return An iterator of the web pages that match.
	 */
	public ObjectIterator<?> retrievePages(PageWord keyword)
	{
		////////////////////////////////////////////////////////////////////
	    //  Write your Code here as part of Integrating and Running Mini Google assignment
	    //  
	    ///////////////////////////////////////////////////////////////////
		return new ObjectIterator<PageElementInterface>(new Vector<PageElementInterface>());
	}

	/** Retrieve all of the web pages that contain any of the given keywords.
	 *	
	 * @param keywords The keywords to search on
	 * @return An iterator of the web pages that match.
	 * 
	 * Calculating the Intersection of the pages here itself
	 **/
	public ObjectIterator<?> retrievePages(ObjectIterator<?> keywords)
	{
		String[] keys = index.getKeys();
		String str;
		Vector<String> v = new Vector<String>();
		ObjectIterator<String> ot = new ObjectIterator<String>(v);
		while(keywords.hasNext())
		{
			str = (String)keywords.next();
			for(int i = 0;i < keys.length;i++)
			{
				if(index.getValue(keys[i]).equals(str))
				{
					v.add((String)keys[i]);
				}
			}
		}
		
		return ot;
	
	}

	/** Save the index to a file.
	 *
	 * @param stream The stream to write the index
	 */
	public void save(FileOutputStream stream) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream));
		String key[] = index.getKeys();
		
		System.out.println("Contents\n");
		for(int i = 0;i < key.length;i++)
		{
			String str="";	
			System.out.println("Value = "+index.getValue(key[i]));
			str = str + key[i]+"\t"+index.getValue(key[i]);
			bw.write(str);
			bw.newLine();
		}
		
		bw.close();
	}
	/** Restore the index from a file.
	 *
	 * @param stream The stream to read the index
	 */
	public void restore(FileInputStream stream) throws IOException
	{
		BufferedReader  br = new BufferedReader(new InputStreamReader(stream));
		while(br.readLine() != null)
		{
			
				StringTokenizer st = new StringTokenizer(br.readLine());
				index.insert(st.nextToken("\t"),st.nextToken());
			
		}
	

	}

	/* Remove Page method not implemented right now
	 * @see searchengine.indexer#removePage(java.net.URL)
	 */
	public void removePage(URL url) {
	}
};
