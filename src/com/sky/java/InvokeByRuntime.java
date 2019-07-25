package com.sky.java;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


public class InvokeByRuntime {
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		String exe = "python";
		String command = "calc.py";
		String num1 = "1";
		String num2 = "2";
		String[] cmdArr = new String[] { exe, command, num1, num2 };
		Process process = Runtime.getRuntime().exec(cmdArr);
		InputStream is = process.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		String str = dis.readLine();
		System.out.println(str);
		str = dis.readLine();
		System.out.println(str);

//		process.waitFor();
	}
}
