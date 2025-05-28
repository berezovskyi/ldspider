package com.ontologycentral.ldspider.hooks.sink;

import com.ontologycentral.ldspider.Crawler;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Test of SinkSparul.
 * Works with the default configuration of a local Joseki server.
 * For other Triple Stores, the constants 'sparqlEndpoint' and 'sparulEndpoint' have to be adjusted.
 *
 * @author RobertIsele
 */
public class SinkSparulTestIT {
    
    @Rule
    public GenericContainer oxigraph = new GenericContainer(DockerImageName.parse("oxigraph/oxigraph:latest"))
            .withExposedPorts(7878);
    
    
    private static final String seedUri = "http://dbpedia.org/resource/Germany";
    //private static final String seedUri = "http://libris.kb.se/data/bib/10294087";
    
    // Oxigraph uses port 7878
    private static final String sparqlEndpoint = "http://localhost:7878/query";
    private static final String sparulEndpoint = "http://localhost:7878/update";
    
    private static final String graph = "http://example.com/SinkSparulTestGraph";
    
    private static final boolean includeProvenance = false;
    
    private static final Crawler.Mode crawlerMode = Crawler.Mode.ABOX_ONLY;
    
    @Test
    public void testCrawl() throws Exception {
        dropGraph();
        crawl();
        queryGraph();
    }
    
    private void dropGraph() throws Exception {
        String query = "DROP SILENT GRAPH <" + graph + ">";
        URL url = new URL(sparulEndpoint + "?request=" + URLEncoder.encode(query, "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();
        System.out.println("Deleting existing graph. Response: " + responseCode + " " + connection.getResponseMessage() + ".");
    }
    
    private void crawl() throws Exception {
//        System.out.println("Crawling " + seedUri);
//        Crawler c = new Crawler();
//        
//        //Frontier
//        Frontier frontier = new BasicFrontier();
//        frontier.add(new URI(seedUri));
//        
//        //ContentHandler
//        ContentHandler handler = new ContentHandlerRdfXml();
//        c.setContentHandler(handler);
//        
//        //Sink
//        Sink sink = new SinkSparul(sparulEndpoint, includeProvenance, graph);
//        c.setOutputCallback(sink);
//        
//        //Crawl
//        // FIXME: does not compile at all
//        c.evaluateBreadthFirst(frontier, 2, 5, 5, crawlerMode);
//        System.out.println("Written to triple store");
    }
    
    private void queryGraph() throws Exception {
        //Issue SPARQL request
        System.out.println("Querying for written statements");
        String query = "SELECT * WHERE { GRAPH <" + graph + "> { ?s ?p ?o } }";
        URL url = new URL(sparqlEndpoint + "?query=" + URLEncoder.encode(query, "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        //Read response
        if (connection.getResponseCode() == 200) {
            BufferedReader in =
					new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
            }
            in.close();
        } else {
            throw new Exception("Query failed. Server response: " + connection.getResponseCode() + " " + connection.getResponseMessage() + ".");
        }
    }
}
