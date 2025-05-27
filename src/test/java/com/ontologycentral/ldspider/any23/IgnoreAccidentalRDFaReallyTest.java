package com.ontologycentral.ldspider.any23;

// Removed old OpenRDF import:
// import org.openrdf.model.impl.URIImpl;

// Added new RDF4J imports:
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.IRI;

import junit.framework.TestCase;
// The import for IgnoreAccidentalRDFaReally is fine as it's in the same package.

public class IgnoreAccidentalRDFaReallyTest extends TestCase {
	public static void testisHTMLlinkAttributeInDocument() {
		// Added ValueFactory instantiation
		ValueFactory vf = SimpleValueFactory.getInstance();

		assertTrue(IgnoreAccidentalRDFaReally.isDocumentURIplusSomeText(
				vf.createIRI("http://example.org/blanofollow"), vf.createIRI( // Replaced new URIImpl
						"http://example.org/bla")));
		assertTrue(IgnoreAccidentalRDFaReally.isDocumentURIplusSomeText(
				vf.createIRI("http://example.org/blashortcut"), vf.createIRI( // Replaced new URIImpl
						"http://example.org/bla")));
		assertTrue(IgnoreAccidentalRDFaReally.isDocumentURIplusSomeText(
				vf.createIRI("http://example.org/bla/nofollow"), vf.createIRI( // Replaced new URIImpl
						"http://example.org/bla/")));
		assertTrue(IgnoreAccidentalRDFaReally.isDocumentURIplusSomeText(
				vf.createIRI("http://example.org/bla/nofollow"), vf.createIRI( // Replaced new URIImpl
						"http://example.org/bla/")));
		assertTrue(IgnoreAccidentalRDFaReally.isDocumentURIplusSomeText(
				vf.createIRI("http://example.org/bla/lightbox[id-of-group]"), vf.createIRI( // Replaced new URIImpl
						"http://example.org/bla/")));
		assertFalse(IgnoreAccidentalRDFaReally.isDocumentURIplusSomeText(
				vf.createIRI("http://example.org/bla#nofollow"), vf.createIRI( // Replaced new URIImpl
						"http://example.org/bla")));
	}
}
