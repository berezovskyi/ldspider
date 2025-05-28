package com.ontologycentral.ldspider.any23;

import static org.junit.Assert.*; // This import is fine, though not used in the snippet

import java.util.Arrays;

import org.apache.any23.extractor.ExtractionContext;
import org.apache.any23.writer.TripleHandler;
import org.apache.any23.writer.TripleHandlerException;
import org.junit.Test;
// Removed old OpenRDF imports:
// import org.openrdf.model.impl.BNodeImpl;
// import org.openrdf.model.impl.LiteralImpl;
// import org.openrdf.model.impl.URIImpl;

// Added new RDF4J imports:
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.IRI;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.parser.Callback;

public class CallbackNQuadTripleHandlerTest {

	@Test
	public void test() throws TripleHandlerException {
		Callback cb = new Callback() {
			public void startDocument() {
				;
			}

			public void endDocument() {
				;
			}

			public void processStatement(Node[] nx) {
				System.out.println(Arrays.toString(nx));
				for (Node n : nx)
					System.out.println(n.toN3());
			}
		};

		TripleHandler th = new CallbackNQuadTripleHandler(cb);
		
		// Added ValueFactory instantiation
		ValueFactory vf = SimpleValueFactory.getInstance();

		// Updated receiveTriple call
		th.receiveTriple(
		    vf.createBNode("subjBnode"), 
		    vf.createIRI("http://blubb.de/prädikat"), 
		    vf.createLiteral("aaääßßá", vf.createIRI("http://blöbb.de/dt")), 
		    null, // Graph IRI can be null
		    new ExtractionContext("bla", vf.createIRI("http://blübb.de/c")) // Updated ExtractionContext
		);
	}
}
