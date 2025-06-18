package com.ontologycentral.ldspider;

import java.net.URI;

import org.semanticweb.yars.util.CallbackNxOutputStream;

import junit.framework.TestCase;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilter;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterRdfXml;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterSuffix;
import com.ontologycentral.ldspider.hooks.links.LinkFilterDummy;

// Added new imports:
import com.ontologycentral.ldspider.seen.Seen;
import com.ontologycentral.ldspider.seen.SeenMem;
import com.ontologycentral.ldspider.queue.Redirects;
import com.ontologycentral.ldspider.queue.DummyRedirects;

public class CrawlerMultipleTest extends TestCase {
	public void testCrawl() throws Exception {
		Crawler c = new Crawler(CrawlerConstants.DEFAULT_NB_THREADS);

		ErrorHandler eh = new ErrorHandlerLogger(null, null);
		c.setErrorHandler(eh);
        c.setFetchFilter(new FetchFilterRdfXml());
        c.setLinkFilter(new LinkFilterDummy());
        c.setOutputCallback(new CallbackNxOutputStream(System.out));

		Frontier frontier = new BasicFrontier();
		frontier.add(new URI("http://harth.org/andreas/foaf.rdf"));
		frontier.add(new URI("http://dbpedia.org/resource/France"));

		// Updated call to evaluateLoadBalanced:
		Seen seenLb = new SeenMem();
		c.evaluateLoadBalanced(frontier, seenLb, 1);

		System.out.println("===============load balanced done====================");

		frontier = new BasicFrontier();
		frontier.add(new URI("http://harth.org/andreas/foaf.rdf"));
		frontier.add(new URI("http://umbrich.net/foaf.rdf"));
		frontier.add(new URI("http://dbpedia.org/resource/Germany"));

		FetchFilter BLACKLIST_FILTER = new FetchFilterSuffix(CrawlerConstants.BLACKLIST);
		c.setBlacklistFilter(BLACKLIST_FILTER);

		int breadthfirstdepth = 1;
		// Updated call to evaluateBreadthFirst:
		Seen seenBf = new SeenMem();
		Redirects redirectsBf = new DummyRedirects();
		c.evaluateBreadthFirst(frontier, seenBf, redirectsBf, breadthfirstdepth, CrawlerConstants.DEFAULT_NB_URIS, 12, -1, false);
	}
}
