package com.moobin.cache.test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest {

	static Counter counter = new Counter();
	
	public static void main(String[] args) throws InterruptedException {
		
		

		List<R> threads = new ArrayList<R>();
		for (int i = 0; i < 20; i++) {
			threads.add(new R());
		}
		long time = System.currentTimeMillis();
		threads.forEach((t) -> t.start());
		int q = 0;
		while (counter.c < 10000000) {
			try {
				Thread.sleep(1);
			}
			catch (InterruptedException e) {
				//
			}
		};
		System.out.println("T: " + (System.currentTimeMillis() - time));
		threads.forEach((t) -> t.interrupt());
		threads.forEach((t) -> System.out.println("  " + t.count));
		System.out.println(counter.c);
		System.out.println(threads.stream().mapToInt((t) -> t.count).sum());
	}
	
	static class Counter {
	    private int c = 0;

	    public void increment() {
    		c++;
	    }

	    public void decrement() {
	        c--;
	    }

	    public int value() {
	        return c;
	    }
	}
	
	static class R extends Thread {
		
		public int count;
		
		@Override
		public void run() {
			r3();
		}
		void r3() {
			while (!interrupted()) {
				count++;
				counter.increment();
				yield();
			}
		}
		
		void r2() {
			while (true) {
				count++;
				counter.increment();
				try {
					sleep(0);
				} catch (InterruptedException e) {
					return; 
				}
			}
			
		}
		void r1() {
			while (!interrupted()) {
				count++;
				counter.increment();
			}
		}
	}
}
