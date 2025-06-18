package com.ontologycentral.ldspider;

import java.net.URI;

import junit.framework.TestCase;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterRdfXml;

// Added necessary imports:
import com.ontologycentral.ldspider.seen.Seen;
import com.ontologycentral.ldspider.seen.SeenMem;
import com.ontologycentral.ldspider.queue.Redirects;
import com.ontologycentral.ldspider.queue.DummyRedirects;

public class SeedTest extends TestCase {
	public void testSeed() throws Exception {
		Crawler c = new Crawler(2);

		Frontier f = new BasicFrontier();
		f.add(new URI("http://www.w3.org/People/Berners-Lee/card"));

		ErrorHandler eh = new ErrorHandlerLogger(System.out, null);
		c.setErrorHandler(eh);
		c.setFetchFilter(new FetchFilterRdfXml());

		// Updated call to evaluateBreadthFirst:
		Seen seen = new SeenMem();
		Redirects redirects = new DummyRedirects();
		c.evaluateBreadthFirst(f, seen, redirects, 1, CrawlerConstants.DEFAULT_NB_URIS, 12, -1, false);

		eh.close();
	}
}
