package com.ontologycentral.ldspider;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;

import com.ontologycentral.ldspider.queue.DummyRedirects;
import com.ontologycentral.ldspider.queue.Redirects;
import com.ontologycentral.ldspider.seen.HashSetSeen;
import com.ontologycentral.ldspider.seen.Seen;
import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.semanticweb.yars.nx.parser.Callback;
import org.semanticweb.yars.util.CallbackNxOutputStream;
import org.semanticweb.yars.util.CallbackSet;
import org.semanticweb.yars.util.Callbacks;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.links.LinkFilterDomain;
import com.ontologycentral.ldspider.hooks.sink.SinkCallback;

/**
 * Crawls the same seed URI with different settings for ABox/TBox crawling and whether to stay on the domain.
 * For each setting, it writes a output file.
 * 
 * @author RobertIsele
 */
public class ABoxTBoxTestIT {
	
	private URI uri = URI.create("http://dbpedia.org/resource/Steve_Jobs");
	
	@Test
//	@Ignore("Test is ignored because it takes a long time to run. " +
//			"To run the test, remove the @Ignore annotation and set the output file name in the code.")
	public void testCrawl() throws Exception {
		//crawl(1, Crawler.Mode.ABOX_ONLY, false);

		crawl(0, Crawler.Mode.ABOX_AND_TBOX_EXTRAROUND, false);
	  
		//crawl(2, Crawler.Mode.ABOX_ONLY, false);
		//crawl(2, Crawler.Mode.ABOX_AND_TBOX, false);
		//crawl(2, Crawler.Mode.ABOX_ONLY, true);
		//crawl(2, Crawler.Mode.ABOX_AND_TBOX, true);
	}
 
	private void crawl(int rounds, Crawler.Mode crawlingMode, boolean stayOnDomain) throws IOException {	
		Crawler c = new Crawler();
	    
		//Frontier
		Frontier frontier = new BasicFrontier();
		frontier.add(uri);
	    
		//Link Filter
		if(stayOnDomain) {
			LinkFilterDomain linkFilter = new LinkFilterDomain(frontier);
			linkFilter.addHost(uri.getHost());
			c.setLinkFilter(linkFilter);
		}
	    
		//Output
		CallbackSet cb = new org.semanticweb.yars.util.CallbackSet();
		Callback cbFile = new CallbackNxOutputStream(new FileOutputStream("output_rounds=" + rounds + "_mode=" + crawlingMode + "_stay=" + stayOnDomain + ".nx"));
		c.setOutputCallback(new SinkCallback(new Callbacks(cb, cbFile)));
		
		PrintStream accesslog = new PrintStream(new FileOutputStream("/tmp/access.log"));
		
		ErrorHandler eh = new ErrorHandlerLogger(accesslog, null, false);
		c.setErrorHandler(eh);
		
		//Run
//		c.evaluateBreadthFirst(frontier, rounds, 10000, 10000, crawlingMode);
		
		Seen seen = new HashSetSeen();
		Redirects redirects = new DummyRedirects();
		
		c.evaluateBreadthFirst(frontier, seen, redirects, 10_000, 10_000, 100_000, 10, false, crawlingMode);
		
		
		accesslog.close();
		
		System.out.println(eh);
	}
}
