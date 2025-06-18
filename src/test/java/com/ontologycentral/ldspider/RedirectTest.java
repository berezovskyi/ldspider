package com.ontologycentral.ldspider;

import java.net.URI;
import junit.framework.TestCase;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.links.LinkFilterDummy;

// Added necessary imports:
import com.ontologycentral.ldspider.seen.Seen;
import com.ontologycentral.ldspider.seen.SeenMem;
import com.ontologycentral.ldspider.queue.Redirects; // Kept as it might be used by DummyRedirects or other parts if uncommented
import com.ontologycentral.ldspider.queue.DummyRedirects;


public class RedirectTest extends TestCase {
//	public void testRedirectLoop() throws Exception {
//		Crawler c = new Crawler(1);
//		
//		List<URI> seeds = new ArrayList<URI>();
//		//seeds.add(new URI("https://secure.domaintools.com/login/?r=http://whois.domaintools.com/fenixdirecto.com"));
//		seeds.add(new URI("http://whois.domaintools.com/fenixdirecto.com"));
//		ErrorHandler eh = new ErrorHandlerLogger(null, null);
//		c.setErrorHandler(eh);
//
//		c.evaluate(seeds, 0);
//	}
	
	public void testRedirect() throws Exception {
		Crawler c = new Crawler(1);

		Frontier f = new BasicFrontier();
		f.add(new URI("http://dbpedia.org/resource/Karlsruhe"));

		ErrorHandler eh = new ErrorHandlerLogger(null, null);
		c.setErrorHandler(eh);
		c.setLinkFilter(new LinkFilterDummy());

		// Updated call to evaluateBreadthFirst:
		Seen seen = new SeenMem();
		Redirects redirectsCall = new DummyRedirects(); // Using redirectsCall as per plan
		c.evaluateBreadthFirst(f, seen, redirectsCall, 1, -1, -1, -1, false);
	}
}
