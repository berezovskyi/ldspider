package com.ontologycentral.ldspider;
import java.net.URI;

import junit.framework.TestCase;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;

// Added new imports:
import com.ontologycentral.ldspider.seen.Seen;
import com.ontologycentral.ldspider.seen.SeenMem;
import com.ontologycentral.ldspider.queue.Redirects;
import com.ontologycentral.ldspider.queue.DummyRedirects;


public class CrawlerStatsTest extends TestCase {
	public void testCrawl() throws Exception {
		Crawler c = new Crawler(1);

		Frontier frontier = new BasicFrontier();
		frontier.add(new URI("http://harth.org/andreas/foaf.rdf"));

		ErrorHandler eh = new ErrorHandlerLogger(System.out, null);
		c.setErrorHandler(eh);

		// Updated call to evaluateBreadthFirst:
		Seen seen = new SeenMem();
		Redirects redirects = new DummyRedirects();
		c.evaluateBreadthFirst(frontier, seen, redirects, 1, CrawlerConstants.DEFAULT_NB_URIS, 12, -1, false);
	}
}
