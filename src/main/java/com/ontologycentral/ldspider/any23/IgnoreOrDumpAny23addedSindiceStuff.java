package com.ontologycentral.ldspider.any23;

import org.apache.any23.extractor.ExtractionContext;
import org.apache.any23.filter.ExtractionContextBlocker;
import org.apache.any23.vocab.SINDICE;
import org.apache.any23.writer.TripleHandler;
import org.apache.any23.writer.TripleHandlerException;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.IRI; // Changed from URI
import org.eclipse.rdf4j.model.Value;

/**
 * Any23 adds triples that state e.g. the date or the size of the processed
 * file. This {@link TripleHandler} drops such triples so the output of a file
 * that has been parsed two times and has not changed in the meantime, produce
 * the same output.
 * 
 * @author "Tobias Kaefer"
 * 
 */

public class IgnoreOrDumpAny23addedSindiceStuff implements TripleHandler {

	private final ExtractionContextBlocker blocker;
	private final TripleHandler headerTripleHandler;
	private final boolean dumpHeaders;

	public IgnoreOrDumpAny23addedSindiceStuff(TripleHandler wrapped) {
		this(null, false, wrapped);
	}

	public IgnoreOrDumpAny23addedSindiceStuff(
			TripleHandler headerTripleHandler, boolean dumpHeaders,
			TripleHandler wrapped) {
		blocker = new ExtractionContextBlocker(wrapped);
		this.headerTripleHandler = headerTripleHandler;
		this.dumpHeaders = dumpHeaders;

	}

	@Override
	public void startDocument(org.apache.any23.model.IRI documentIRI) throws TripleHandlerException { // Changed URI to org.apache.any23.model.IRI
		blocker.startDocument(documentIRI);
		blocker.unblockDocument();
	}

	@Override
	public void openContext(ExtractionContext context)
			throws TripleHandlerException {
		blocker.openContext(context);
	}

	@Override
	public void receiveTriple(Resource s, IRI p, Value o, IRI g, // Changed URI to IRI for p and g
			ExtractionContext context) throws TripleHandlerException {
		if (p.stringValue().startsWith(SINDICE.NS))
			if (dumpHeaders && headerTripleHandler != null) // Added null check for headerTripleHandler
				headerTripleHandler.receiveTriple(s, p, o, g, context);
			else
				return;
		else
			blocker.receiveTriple(s, p, o, g, context);
	}

	@Override
	public void receiveNamespace(String prefix, String uri,
			ExtractionContext context) throws TripleHandlerException {
		blocker.receiveNamespace(prefix, uri, context);
	}

	@Override
	public void closeContext(ExtractionContext context)
			throws TripleHandlerException {
		blocker.closeContext(context);
	}

	@Override
	public void endDocument(org.apache.any23.model.IRI documentIRI) throws TripleHandlerException { // Changed URI to org.apache.any23.model.IRI
		blocker.endDocument(documentIRI);
	}

	@Override
	public void setContentLength(long contentLength) {
		blocker.setContentLength(contentLength);
	}

	@Override
	public void close() throws TripleHandlerException {
		blocker.close();
	}

}
