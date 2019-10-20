package com.my.service;

import javax.xml.ws.Endpoint;

public class Publish {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://192.168.1.101:8050/User", new UserServiceIMpl());
		System.out.println("...发布成功...");
	}

}
