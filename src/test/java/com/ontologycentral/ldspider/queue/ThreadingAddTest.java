package com.ontologycentral.ldspider.queue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.zip.GZIPInputStream;

import junit.framework.TestCase;

import com.ontologycentral.ldspider.frontier.BasicFrontier;
import com.ontologycentral.ldspider.frontier.Frontier;
import com.ontologycentral.ldspider.frontier.RankedFrontier;
import com.ontologycentral.ldspider.queue.BreadthFirstQueue;
import com.ontologycentral.ldspider.queue.LoadBalancingQueue;
import com.ontologycentral.ldspider.queue.SpiderQueue;
import com.ontologycentral.ldspider.tld.TldManager;


public class ThreadingAddTest {
	public static int THREADS = 64;
	
//	public void testThreading() throws Exception {
//		TldManager tldm = new TldManager();
//		
//		// FIXME: does not compile at all
//		SpiderQueue fq = new BreadthFirstQueue(tldm, 5, 5);
//		Frontier f = new BasicFrontier();
//		
//		Thread[] ts = new Thread[THREADS];
//		
//		for (int i = 0; i < THREADS; i++) {
//			ts[i] = new Thread(new Worker(fq, f));
//			ts[i].start();
//		}
//		
//		for (int i = 0; i < THREADS; i++) {
//			ts[i].join();
//		}
//	}	
	
//	public void testThreadingLoadBalancing() throws Exception {
//		TldManager tldm = new TldManager();
//		
//		// FIXME: does not compile at all
//		SpiderQueue fq = new LoadBalancingQueue(tldm);
//		Frontier f = new RankedFrontier();
//		
//		Thread[] ts = new Thread[THREADS];
//		
//		for (int i = 0; i < THREADS; i++) {
//			ts[i] = new Thread(new Worker(fq, f));
//			ts[i].start();
//		}
//		
//		for (int i = 0; i < THREADS; i++) {
//			ts[i].join();
//		}
//	}	
}

class Worker implements Runnable {
	SpiderQueue _fq;
	Frontier _f;
	
	public Worker(SpiderQueue fq, Frontier f) {
		_fq = fq;
		_f = f;
	}

	public void run() {
		try {
			InputStream is = new GZIPInputStream(new FileInputStream("test/uris.txt.gz"));

			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int i = 0;

			String line = br.readLine();
			while (line != null) {
				i++;

				URI u = new URI(line);

				_f.add(u);

				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}