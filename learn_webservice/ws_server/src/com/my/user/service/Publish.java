package com.my.user.service;

import javax.xml.ws.Endpoint;

public class Publish {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://192.168.1.101:9970/Person", new PersonService());
		System.out.println("==发布成功====");
	}

}
