## Introduction ##

[![Java CI with Maven](https://github.com/berezovskyi/ldspider/actions/workflows/maven-ci.yml/badge.svg)](https://github.com/berezovskyi/ldspider/actions/workflows/maven-ci.yml)

The **LDSpider** project provides a web crawling framework for the Linked Data web.

Requirements and challenges for crawling the Linked Data web are different from regular web crawling, thus the LDSpider project offers a web crawler adapted to traverse and harvest content from the Linked Data web.

The project is a co-operation between [Andreas Harth](http://harth.org/andreas/) at [AIFB](http://www.aifb.kit.edu/) and [Juergen Umbrich](http://umbrich.net) at [DERI](http://www.deri.ie/). [Aidan Hogan](http://sw.deri.org/~aidanh/), Tobias Kaefer and [Robert Isele](http://www.wiwiss.fu-berlin.de/en/institute/pwo/bizer/team/IseleRobert.html) are contributing.

Cite as
```
@inproceedings{ldspider,
author = { Robert Isele and J\"{u}rgen Umbrich and Chris Bizer and Andreas Harth},
title = { {LDSpider}: An open-source crawling framework for the Web of Linked Data} ,
year = { 2010 },
booktitle = { Proceedings of 9th International Semantic Web Conference (ISWC 2010) Posters and Demos},
url = { http://iswc2010.semanticweb.org/pdf/495.pdf }
}
```

## Features ##
  * **Content Handlers for different formats**:
    * Includes handlers to read RDF/XML, N-TRIPLES and N-QUADS;
    * [Any23](http://any23.apache.org/) handlers for other RDF serialisations, e.g. RDFa
    * Simple interface design to implement own handlers (e.g. to handle additional formats).
  * **Different crawling strategies**
    * Breadth-first crawl;
    * Depth-first crawl;
    * optionally crawl schema information (TBox).
  * **Crawling scope**
    * crawl can easily be restricted to specific pages e.g. pages with a specific domain prefix.
  * **Output formats** - The crawled data can be written in various ways:
    * The output can be written to files in different formats, such as RDF/XML or N-QUADS
    * The crawler can write all statements to a Triple Store using SPARQL/Update. Optionally uses named graphs to structure the written statements by their source page.
    * Optionally, the output include provenance information.

### Getting Started ###
**LDSpider** can be used in two ways:
  * Through a command line application.
  * Through a flexible API, which provides various Hooks to extend the behavior of the crawler.

**TODO:** *The original README linked to wiki pages for "Getting Started (CLI)" and "Getting Started (API)". Please copy the most relevant content from those wiki pages (if still applicable) directly into this README or link to updated documentation if available.*

### Community ###
**TODO:** *The original README mentioned a Google Groups mailing list. Please verify if this is still the primary communication channel. If so, update the links. If not, provide current community/support information or remove this section.*

### Acknowledgements ###

![YourKit logo](https://www.yourkit.com/images/yklogo.png) YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/index.jsp) and [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/index.jsp), innovative and intelligent tools for profiling Java and .NET applications.
