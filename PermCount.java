// to compile do from folder above - C:\projects\Cypher>javac -cp ../ Cypher.java
// to execute from same folder  - C:\projects\Cypher>java -cp ../ Cypher.Cypher D decypher2.txt 874

package Cypher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.math.*;

public class PermCount
{
	static long cnt = 0;

	//===================================================================================
	static public void debug(String msg){
		System.out.println(msg);
	}


    	//===================================================================================
	public static void main(String[] args) {

		BigInteger b1 , b2, b3;
		int exp = 64;
		String bstr = "";
		b1 = new BigInteger("2");
		b2 = b1.pow(exp);
		bstr = b2.toString();

		debug("2**64 = "+bstr.length()+" "+bstr);

		exp = 128;
		b2 = b1.pow(exp);
		bstr = b2.toString();
		debug("2**128 = "+bstr.length()+" "+bstr);

		exp = 512;
		b2 = b1.pow(exp);
		bstr = b2.toString();
		debug("2**512 = "+bstr.length()+" --- "+bstr.charAt(0)+"."+bstr.charAt(1)+" * 10^"+(bstr.length() - 1));

		long l = 0l;
		b2 = new BigInteger("1");
		b3 = new BigInteger("1");
		for (int i = 1; i < 87; i++){
			l = (long)i;
			b1 = BigInteger.valueOf(l);
			b3 = b1.multiply(b2);
			b2 = b3;
		}
		bstr = b3.toString();
		debug("86! = "+bstr.length()+" --- "+bstr.charAt(0)+"."+bstr.charAt(1)+" * 10^"+(bstr.length() - 1));

		l = 0l;
		b2 = new BigInteger("1");
		b3 = new BigInteger("1");
		for (int i = 1; i < 861; i++){
			l = (long)i;
			b1 = BigInteger.valueOf(l);
			b3 = b1.multiply(b2);
			b2 = b3;
		}
		bstr = b3.toString();
		debug("860! = "+bstr.length()+" --- "+bstr.charAt(0)+"."+bstr.charAt(1)+" * 10^"+(bstr.length() - 1));
	}
}
