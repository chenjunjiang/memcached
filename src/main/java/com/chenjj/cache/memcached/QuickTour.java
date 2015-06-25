package com.chenjj.cache.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.junit.Before;
import org.junit.Test;

import net.spy.memcached.MemcachedClient;

public class QuickTour {

	private static MemcachedClient client;
	
	public static void main(String[] args) {
		
		try {
			MemcachedClient client = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
			/*client.set("someKey", 3600, "123");
			String value =  (String) client.get("someKey");*/
			boolean addStatus = client.add("key", 900, "memcached").isDone();
			boolean addStatus1 = client.add("key", 900, "redis").isDone();
			String value = (String) client.get("key");
			System.out.println(value);
			System.out.println(addStatus);
			System.out.println(addStatus1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Before
	public void  initClient(){
		try {
			client =new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSet(){
		client.set("someKey", 3600, "123");
		String value =  (String) client.get("someKey");
		System.out.println(value);
	}
	
	@Test
	public void testAdd(){
		boolean addStatus = client.add("key", 900, "memcached").isDone();
		boolean addStatus1 = client.add("key", 900, "redis").isDone();
		String value = (String) client.get("key");
		System.out.println(value);
		System.out.println(addStatus);
		System.out.println(addStatus1);
	}
	
	@Test
	public void testReplace(){
		client.set("yiibai", 100, "memcached");
		System.out.println(client.get("yiibai"));
		client.replace("yiibai", 100, "redis");
		System.out.println(client.get("yiibai"));
	}
	
	@Test
	public void testAppend(){
		client.set("yiibai", 100, "memcached");
		System.out.println(client.get("yiibai"));
		client.append("yiibai", "redis");
		System.out.println(client.get("yiibai"));
	}
	
	@Test
	public void testPrepend(){
		client.set("yiibai", 100, "memcached");
		System.out.println(client.get("yiibai"));
		client.prepend("yiibai", "redis");
		System.out.println(client.get("yiibai"));
	}
}
