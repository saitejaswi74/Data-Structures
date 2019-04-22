/**  
 * 
 * Copyright: Copyright (c) 2004 Carnegie Mellon University
 * 
 * This program is part of an implementation for the PARKR project which is 
 * about developing a search engine using efficient Datastructures.
 * 
 * Modified by Mahender on 12-10-2009
 */
package searchengine.spider;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import searchengine.dictionary.ObjectIterator;
import searchengine.element.PageElementInterface;
import searchengine.element.PageHref;
import searchengine.element.PageWord;
import searchengine.indexer.Indexer;
import searchengine.parser.PageLexer;
import searchengine.url.URLFixer;
import searchengine.url.URLTextReader;

/** Web-crawling objects.  Instances of this class will crawl a given
 *  web site in breadth-first order.
 */
public class BreadthFirstSpider implements SpiderInterface {

	/** Create a new web spider.
	@param u The URL of the web site to crawl.
	@param i The initial web index object to extend.
	 */

	private Indexer i = null;
	private URL u; 

	public BreadthFirstSpider (URL u, Indexer i) {
		this.u = u;
		this.i = i;

	}

	/** Crawl the web, up to a certain number of web pages.
	@param limit The maximum number of pages to crawl.
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public Indexer crawl (int limit) throws Exception {
	
		Queue<String> q = new LinkedList<String>();
		q.add(u.toString());
		
		int k = 0;
		while(!q.isEmpty() && k < limit)
		{
			k++;
			
			URLTextReader in = new URLTextReader(u);
			Vector<String> v = new Vector<String>();
			PageLexer<PageElementInterface> elts = null;
			
				elts = new PageLexer<PageElementInterface>(in, u);
			 

			int count = 0;
		
			while(elts.hasNext())
			{
				count++;
				
				PageElementInterface elt = (PageElementInterface)elts.next();
				if (elt instanceof PageWord)
				{
					v.addElement(elt.toString());
					System.out.println("word: "+elt);
				}
				else if (elt instanceof PageHref)
				{
					q.add(elt.toString());
					System.out.println("link: "+elt);
				}
			}
		
			i.addPage(u, new ObjectIterator(v));
			
			String str = q.poll();
			URL u1 = u;
			URLFixer urlf = new URLFixer();
			
				u=	urlf.fix(u1,str);
				//System.out.println(u);
			
		}
		return i;
	}


	/** Crawl the web, up to the default number of web pages.
	 */
	public Indexer  crawl() throws Exception{
		// This redirection may effect performance, but its OK !!
		System.out.println("Crawling: "+u.toString());
		return  crawl(crawlLimitDefault);
	}

	/** The maximum number of pages to crawl. */
	public int crawlLimitDefault = 10;

}
