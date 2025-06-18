package com.ontologycentral.ldspider.hooks.content;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import junit.framework.TestCase;

import org.semanticweb.yars.nx.parser.Callback;
import org.semanticweb.yars.util.CallbackCount;
import org.semanticweb.yars.util.CallbackNxOutputStream;
import org.semanticweb.yars.util.Callbacks;

import com.ontologycentral.ldspider.Crawler;
import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.hooks.sink.Sink;
import com.ontologycentral.ldspider.hooks.sink.SinkCallback;

// Added necessary imports:
import com.ontologycentral.ldspider.seen.Seen;
import com.ontologycentral.ldspider.seen.SeenMem;
import com.ontologycentral.ldspider.queue.Redirects;
import com.ontologycentral.ldspider.queue.DummyRedirects;

public class ContentHandlerAny23Test extends TestCase {

	private static final String seedUri = "http://www.ifi.uzh.ch/pax/index.php/publication/rdfalist";

	private static final String outputFile = "ContentHandlerAny23Test.nq";

	private static final boolean includeProvenance = false;

	private static final Crawler.Mode crawlerMode = Crawler.Mode.ABOX_ONLY;

	public void testCrawl() throws Exception {
	    Crawler c = new Crawler(1);

	    //Frontier
	    Frontier frontier = new BasicFrontier();
	    frontier.add(new URI(seedUri));

	    //ContentHandler
	    ContentHandlerAny23 handler = new ContentHandlerAny23();
	    if(!handler.checkServer()) throw new IOException("Any23 server is not running"); // Assuming checkServer() is a valid method
	    c.setContentHandler(handler);

	    //Sink
	    Callback fileCb = new CallbackNxOutputStream(new FileOutputStream(outputFile));
	    CallbackCount countCb = new CallbackCount();
	    Sink sink = new SinkCallback(new Callbacks(new Callback[] { fileCb, countCb }), includeProvenance);
	    c.setOutputCallback(sink);

	    //Crawl
		// Updated call to evaluateBreadthFirst:
		Seen seen = new SeenMem();
		Redirects redirects = new DummyRedirects();
	    c.evaluateBreadthFirst(frontier, seen, redirects, 1, 10, 10, -1, false, crawlerMode);
	    System.out.println(countCb.getStmts() + " statements written to " + outputFile);
	}
}
