package com.ontologycentral.ldspider;

import java.net.URI;

import junit.framework.TestCase;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterRdfXml;

public class SeedTest {
//	public void testSeed() throws Exception {
//		Crawler c = new Crawler(2);
//		
//		Frontier f = new BasicFrontier();
//		f.add(new URI("http://www.w3.org/People/Berners-Lee/card"));
//
//		ErrorHandler eh = new ErrorHandlerLogger(System.out, null);
//		c.setErrorHandler(eh);
//		c.setFetchFilter(new FetchFilterRdfXml());
//		
//		// FIXME: does not compile at all
//		c.evaluateBreadthFirst(f, 1, CrawlerConstants.DEFAULT_NB_URIS,12);
//		
//		eh.close();
//	}	
}
