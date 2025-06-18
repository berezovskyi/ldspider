package com.ontologycentral.ldspider;

import java.net.URI;

import junit.framework.TestCase;

import org.semanticweb.yars.util.CallbackSet;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilter;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterSuffix;

// Added necessary imports:
import com.ontologycentral.ldspider.seen.Seen;
import com.ontologycentral.ldspider.seen.SeenMem;
import com.ontologycentral.ldspider.queue.Redirects;
import com.ontologycentral.ldspider.queue.DummyRedirects;

public class DBpediaTest extends TestCase {
	public void testCrawl() throws Exception {
		Frontier frontier = new BasicFrontier();


	    frontier.add(new URI("http://dbpedia.org/resource/France"));

	    CallbackSet cb = new org.semanticweb.yars.util.CallbackSet();

	    Crawler c = new Crawler(1);
	    c.setOutputCallback(cb);

		FetchFilter BLACKLIST_FILTER = new FetchFilterSuffix(CrawlerConstants.BLACKLIST);
		c.setBlacklistFilter(BLACKLIST_FILTER);

		// Updated first call
		Seen seen1 = new SeenMem();
		Redirects redirects1 = new DummyRedirects();
	    c.evaluateBreadthFirst(frontier, seen1, redirects1, 1, -1, -1, -1, false);

	    System.out.println(cb.getSet().size());
	    //    cb.getSet.foreach(nodes => println(nodes.mkString(" ")))

	    frontier = new BasicFrontier();
	     frontier.add(new URI("http://dbpedia.org/resource/Germany"));

	    cb = new org.semanticweb.yars.util.CallbackSet();

	    //c = new Crawler(1); // Crawler 'c' is already initialized

	    c.setOutputCallback(cb);

		// Updated second call
		Seen seen2 = new SeenMem();
		Redirects redirects2 = new DummyRedirects();
	    c.evaluateBreadthFirst(frontier, seen2, redirects2, 1, -1, -1, -1, false);

	    System.out.println(cb.getSet().size());
	}
}
