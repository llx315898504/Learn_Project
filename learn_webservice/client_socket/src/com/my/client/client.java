package com.my.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws  
	 */
	public static void main(String[] args) throws  Exception {
		Socket s=new Socket("192.168.1.101", 8888);
		 BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		 String line=null;
		 while((line=br.readLine())!=null){
			 System.out.println(line);
		 }
         br.close();
         s.close();
	}

}
