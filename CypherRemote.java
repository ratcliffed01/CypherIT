// to compile do from folder above - C:\projects\Cypher>javac -cp ../ CypherRemote.java
// to cypher from same folder  -   C:\projects\Cypher>java -cp ../ Cypher.CypherRemote C example2.txt 1234-abcd-5678-efgh <cypherfile>>x.x
// to decypher from same folder  - C:\projects\Cypher>java -cp ../ Cypher.CypherRemote  D x.x 1234-abcd-5678-efgh <cypherfile>

//===============================================================================================================
//	CypherRemote.java		Authour David Ratcliffe		Version: v1.0
//
//	This program is designed to convert a plain text message from a text file to cypherable text or vice versa.
//	The cypher array is arrayed 100 times with the key can be alpha/nmeric in this programs,
//	to execute do as follows :	java -cp ../ Cypher.CypherRemote C example2.txt 1234-abcd-5678-efgh CypherArrray_v1_0_10000.txt>x.x
//					java -cp ../ Cypher.CypherRemote D x.x 1234-abcd-5678-efgh CypherArrray_v1_0_10000.txt
//
//	parameter 1 - can be C or D; if C then converts normal text to cyphred text, if D converts cyphered text to normal text
//	parameter 2 - filename of file containing text to be translated, can include path if required
//	parameter 3 - alpha/numeric string can be any length, the longer the slower
//	parameter 4 - cypherarray file name used to cypher/decyphrt, file is loaded into cypherarray. can be any size
//
//	The cypher array is now read from a file (p4) and can be any size up to the memory allowance
//	The basestr is now taken from the 1st element in the cypher array
//	The keystr is now taken from the last element in the cypher array
//	With the keystr is a factor whish is based on the size of the array ie if array is 100 then factor is 1
//	if 10 then fact is 0.1, if 1000 yhen 10. This allows the whole cypher array to be used.

package Cypher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.math.*;

public class CypherRemote
{
				//	 012345678901234567890123456789012345678901234567890123456789012345678901234567890123
	private String keyStr = 	"";
	private String baseStr = 	"";

	private List<String> mess = new ArrayList<String>();

	//===================================================================================
	public void debug(String msg){
		//System.out.println(msg);
	}
	//===================================================================================
	public void debug1(String msg){
		System.out.println(msg);
	}
    	//===================================================================================
	// read txt files from local folder and load into 2darray
    	public String readFileNTxt(String path)
    	{
		try
		{
			String xx = "";
			String txt = "";

			RandomAccessFile cp = new RandomAccessFile(path, "r");
			while ((xx=cp.readLine())!=null) txt += xx + "<CRLF>";
			cp.close();

			return txt.substring(0,txt.length() - 6);	//remove last crlf
		}
		catch (IOException ioe)
		{
        	    	debug("reading file IOException - "+ioe.getMessage());
	    		return null;
		}
		catch (Exception e)
		{
            		debug("reading files Exception - "+e.getMessage());
	    		return null;
		}
    	}
    	//===================================================================================
    	public String readFileCTxt(String path)
    	{
		try
		{
			String xx = "";
			String txt = "";

			RandomAccessFile cp = new RandomAccessFile(path, "r");
			while ((xx=cp.readLine())!=null) txt += xx;
			cp.close();

			return txt;
		}
		catch (IOException ioe)
		{
        	    	debug("reading file IOException - "+ioe.getMessage());
	    		return null;
		}
		catch (Exception e)
		{
            		debug("reading files Exception - "+e.getMessage());
	    		return null;
		}
    	}
    	//===================================================================================
    	public String[] readFileCypher(String path)
    	{
		try
		{
			String xx = "";
			String txt = "";

			RandomAccessFile cp = new RandomAccessFile(path, "r");
			while ((xx=cp.readLine())!=null) mess.add(xx);
			cp.close();

			Object[] o = mess.toArray();
			mess.clear();
			String[] sarr = new String[o.length];
			for (int i = 0; i < o.length; i++) sarr[i] = (String) o[i];
			return sarr;
		}
		catch (IOException ioe)
		{
        	    	debug("reading file IOException - "+ioe.getMessage());
	    		return null;
		}
		catch (Exception e)
		{
            		debug("reading files Exception - "+e.getMessage());
	    		return null;
		}
    	}

	//===================================================================================
	public int getKeyNum(String key,float factor){
		int num = (int)Math.floor(this.keyStr.indexOf(key)*factor);
		//debug1("gn - n="+num+" f="+factor+" k="+keyStr.indexOf(key));
		return num;
	}
	//===================================================================================
	public String encrypt(String txt, String key, String[] cypherArray){

		String ntxt = "";
		String oneChar = "";
		int i = 0;
		int j = 0;
		int pos = 0;
		float factor = (float)cypherArray.length / 100.0f;

		try
		{
			debug("txtl="+txt.length()+" key="+key);
			for (int k = 0; k < key.length(); k++){
				j = getKeyNum(key.substring(k,k+1),factor);
				if (j < 0) throw new Exception("key character not valid - "+key);
				for (i = 0; i < txt.length(); i++){
					oneChar = txt.substring(i,i+1);
					pos = baseStr.indexOf(oneChar);
					if (pos == -1) ntxt += oneChar; else ntxt += cypherArray[j].substring(pos,pos+1);
					j++;
					if (j > cypherArray.length - 1) j = 0;
				}
				txt = ntxt;
				ntxt = "";
			}
		}catch (Exception ex){
			debug("Excep - i="+i+" j="+j+" pos="+pos+" 1char="+oneChar+" "+ntxt+" "+ex.getMessage());
			return "";
		}
		return txt;
	}
	//===================================================================================
	public String decypher(String txt, String key, String[] cypherArray){

		String ntxt = "";
		String oneChar = "";
		int i = 0;
		int j = 0;
		int pos = 0;
		float factor = cypherArray.length / 100;

		try
		{
			debug("txtl="+txt.length());
			for (int k = key.length() - 1; k > -1 ; k--){
				j = getKeyNum(key.substring(k,k+1),factor);
				if (j < 0) throw new Exception("key character not valid - "+key);
				for (i = 0; i < txt.length(); i++){
					oneChar = txt.substring(i,i+1);
					pos = cypherArray[j].indexOf(oneChar);
					if (pos == -1) ntxt += oneChar; else ntxt += baseStr.substring(pos,pos+1);
					j++;
					if (j > cypherArray.length - 1) j = 0;
				}
				txt = ntxt;
				ntxt = "";
			}
		}catch (Exception ex){
			debug("Excep - i="+i+" j="+j+" pos="+pos+" 1char="+oneChar+" "+ntxt+" "+ex.getMessage());
			return "";
		}
		return txt;
	}
	//===================================================================================
	public boolean validateArgs(String CorD, String fn, String key, String cfn){
		boolean valid = true;

		if (CorD.equals("C")||CorD.equals("D")) valid = true; else return false;
		if (fn.indexOf(".") == -1) return false; 
		if (cfn.indexOf(".") == -1) return false; 
		for (int k = key.length() - 1; k > -1 ; k--){
			if (getKeyNum(key.substring(k,k+1),1) < 0){
				valid = false;
				break;
			}
		}
		return valid;
	}
    	//===================================================================================
    	public String[] CypherMain (String CorD, String fn, String key, String cfn) throws Exception
    	{
		String resultstr = "";
		CypherRemote cy = new CypherRemote();
		String[] cypherArray = cy.readFileCypher(cfn);
		if (cypherArray == null) throw new Exception("Imported cypherArray less than keystr");

		cy.baseStr = cypherArray[0];
		cy.keyStr = cypherArray[cypherArray.length - 1];

		if (!cy.validateArgs(CorD, fn, key, cfn)) throw new Exception("Invalid arguments - <'C' or 'D'> <filename> <key> <array>");

		String xx = "";

		if (CorD.equals("C")){ 
			xx = cy.readFileNTxt(fn);
			if (xx == null) throw new Exception("No text to process");
			xx = cy.encrypt(xx,key,cypherArray);
		}
		if (CorD.equals("D")){ 
			xx = cy.readFileCTxt(fn);
			if (xx == null) throw new Exception("No text to process");
			xx = cy.decypher(xx,key,cypherArray);
		}
		return xx.split("<CRLF>");
			
	}
    	//===================================================================================
    	static public void main(String[] args)
    	{
		String CorD = args[0];
		String fn = args[1];
		String key = args[2];
		String cfn = args[3];
		CypherRemote cy = new CypherRemote();
		try {
			String[] str = cy.CypherMain(CorD, fn, key, cfn);
			for (String resultStr : str) System.out.println(resultStr);
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
