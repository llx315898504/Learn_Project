package com.my.sorcket;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


import com.my.service.Peresonservice;

public class ServerSorcket {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket sc=new ServerSocket(8888);
		Socket  s=sc.accept();
		String result=new Peresonservice().sayHello("хнаа");
		BufferedWriter out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		out.write(result);
		out.flush();
		out.close();
		s.close();
		sc.close();
	}

}
