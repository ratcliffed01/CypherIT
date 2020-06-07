// to compile do from folder above - C:\projects\Cypher>javac -cp ../ RandomBaseStr.java
// to execute from same folder  - C:\projects\Cypher>java -cp ../ Cypher.RandomBaseStr 874

//========================================================================================
//	06/06/2020	David Ratcliffe		Version 1.9	RandomBaseStr.java
//
//	Designed to create an array of random chars based on the base string. The number of rows
//	out is the integer parameter
//
//	To execute 	- 
//		java -cp ../ Cypher.RandomBaseStr 100>CypherArray_v1_0_100.txt	
//	outputs 100 lines to cypherarray txt file
//		java -cp ../ Cypher.CypherRemote C example2.txt password  CypherArray_v1_0_1000.txt>x.x
//	turns plain text to cypher text in file x.x
//		java -cp ../ Cypher.CypherRemote D x.x password  CypherArray_v1_0_1000.txt
//	decyphers x.x back to plain text
package Cypher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import static java.lang.Math.*;

public class RandomBaseStr
{
				//	 012345678901234567890123456789012345678901234567890123456789012345678901234567890123
	private static String savebaseStr = 	"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:;?[]-+=_{}#£$%!()*";
	private static String baseStr = "";
	static long cnt = 0;
	//===================================================================================
	public void debug(String msg){
		System.out.println(msg);
	}

	public int randomCnt(int max){
		return ((int)(Math.random() * max));
	}
	public String getChar(int n){
		String oneChar = Character.valueOf(baseStr.charAt(n)).toString();
		n++;
		baseStr = baseStr.substring(0,n - 1) + baseStr.substring(n);
		return oneChar;
	}
	public void randomOutput(){
		String newStr = "";
		baseStr = savebaseStr;
		for (int i = baseStr.length(); i > 0; i--) newStr += getChar(randomCnt(i));
		debug(newStr);
	}
    	//===================================================================================
	public static void main(String[] args) {
		RandomBaseStr rbs = new RandomBaseStr();
		int loop = 1;
		if (args.length > 0) loop = Integer.parseInt(args[0]);
		for (int i = 0; i < loop; i++) rbs.randomOutput();
	}
}
