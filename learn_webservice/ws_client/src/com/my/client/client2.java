package com.my.client;

import com.my.person.stup.RLPersonService;
import com.my.person.stup.RLPersonService_Service;
public class client2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RLPersonService_Service pService=new RLPersonService_Service();
	      RLPersonService ps=pService.getRLPersonServicePort();
	      ps.queryPerson(null);
		

	}

}
