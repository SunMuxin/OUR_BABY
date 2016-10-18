package com.realsight.brain.timeseries.test;


import java.io.File;
//import java.nio.file.Paths;

import com.realsight.brain.timeseries.main.*;

/**
 * @author qefee
 * 
 */ 
public class Main {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String root = new File(new File(System.getProperty("user.dir")).getParent()).getParent();
//		String nabPath = Paths.get(root, "NAB").toString();
		AnormalyMain.run(root);
	}
}
