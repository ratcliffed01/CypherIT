// to compile do from folder above - C:\projects\Cypher>javac -cp ../ Cypher.java
// to execute from same folder  - C:\projects\Cypher>java -cp ../ Cypher.Cypher D decypher2.txt 874

//===============================================================================================================
//	Cypher.java		Authour David Ratcliffe		Version: v1.0
//
//	This program is designed to convert a plain text message from a text file to cypherable text or vice versa.
//	to execute do as follows :	java -cp ../ Cypher.Cypher D decypher2.txt 874
//					java -cp ../ Cypher.Cypher C example2.txt 874
//
//	parameter 1 - can be C or D; if C then converts normal text to cyphred text, if D converts cyphered text to normal text
//	parameter 2 - filename of file containing text to be translated, can include path if required
//	parameter 3 - numeric string can be any length, the longer the slower

package Cypher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;

public class Cypher
{
				//	 012345678901234567890123456789012345678901234567890123456789012345678901234567890123
	private String baseStr = 	"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:;?[]-+=_{}#£$%!()*";
	private String[] cypherArray = {"mF8{dO tG£kB1[oV}uH(iAp0;jR!fU]aT$sQ%gy6eN7#lW9?bP4+K=rw5*nx2I:M.Y-Zchq3,LSX_zC)JDvE",
					"G£kB1[oV}uR!fU]aTbP4+K=rw5*nxchq3,LSXp_zC)JDvEmF8{dO tQ%gy6eN7H(iA0;j$s#lW9?2I:M.Y-Z",
					"oV}]aTbP4+nLSX_zCF8{dO tQ%gy6e;j$s#lW9?2I:M.Y-ZG£kB1[xchqp3,)JDvEmN7H(iA0K=rw5*uR!fU",
					"P4+nLSX_zCF8{dO tQj$s#lW9?2I:B1[xcDvEmN7H(iA0K=rw5*uR!fUoV}]aTb%gy6e;pM.Y-ZG£khq3,)J",
					"SXj$s:B1[xcDvEiA0K=rw5*uR!fUoV}]aTb%gy6e;M.Yq3,)JP4+nL{dO tQmNp7H(#lW9?2I-ZG£kh_zCF8",
					"B1vEiA0K=rw5*uR!fUoV}]aTb%gy6e;M)JdO tQmN7H(#lW9?2I-ZG£kh_zCF8SXj$s:.Yq3,P4+nL{p[xcD",
					"0K*uR!fUoV}gy6e;M)JdO tQmN7H(#lW9?2I_zCF8SXj$s:.Yq3,P4+nL{[xcDB1vEiA]aTb%-ZG£kh=rwp5",
					"R}pgy6e;M)JdO tQ(#lW9?2I_zCF8SXj$s:.+nL{[xcDB1vEiA]aTb%-ZG£kh=rw50K*uYq3,P4mN7H!fUoV",
					"M tQ(#lW9?F8SXj$s:.+nL{pB1vEiA-ZG£kh=rw50K*uYq3,P4mN7H!fUoVR}gy6e;]aTb%2I_zC)JdO[xcD",
					"#F8SXj$s:.+nLA-ZG£kh=rw50K,P4mN7HR}gy6pe;]aTb%2I_zC)JdO[xcDM tQ(*uYq3!fUoV{B1vEilW9?"};

	//===================================================================================
	public void debug(String msg){
		System.out.println(msg);
	}
    	//===================================================================================
	// read txt files from local folder and load into 2darray
    	public String readFile(String path)
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
        	    	System.out.println("reading file IOException - "+ioe.getMessage());
	    		return null;
		}
		catch (Exception e)
		{
            		System.out.println("reading files Exception - "+e.getMessage());
	    		return null;
		}
    	}

	//===================================================================================
	public String encrypt(String txt, String key){

		String ntxt = "";
		String oneChar = "";
		int i = 0;
		int j = 0;
		int pos = 0;
		try
		{
			debug("txtl="+txt.length()+" key="+key);
			for (int k = 0; k < key.length(); k++){
				j = Integer.parseInt(key.substring(k,k+1));
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
			System.out.println("Excep - i="+i+" j="+j+" pos="+pos+" 1char="+oneChar+" "+ntxt+" "+ex.getMessage());
			return "";
		}
		return txt;
	}
	//===================================================================================
	public String decypher(String txt, String key){

		String ntxt = "";
		String oneChar = "";
		int i = 0;
		int j = 0;
		int pos = 0;
		try
		{
			debug("txtl="+txt.length());
			for (int k = key.length() - 1; k > -1 ; k--){
				j = Integer.parseInt(key.substring(k,k+1));
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
			System.out.println("Excep - i="+i+" j="+j+" pos="+pos+" 1char="+oneChar+" "+ntxt+" "+ex.getMessage());
			return "";
		}
		return txt;
	}
	//===================================================================================
	public boolean validateArgs(String CorD, String fn, String key){
		boolean valid = true;

		if (CorD.equals("C")||CorD.equals("D")) valid = true; else return false;
		if (fn.indexOf(".") == -1) return false; 
		valid = key.matches("^[0-9]+$");

		return valid;
	}
    	//===================================================================================
    	static public void main(String[] args)
    	{
		String CorD = args[0];
		String fn = args[1];
		String key = args[2];
		String resultstr = "";
		Cypher cy = new Cypher();
		if (cy.validateArgs(CorD, fn, key)){
			String xx = cy.readFile(fn);
			if (xx != null){
				System.out.println("Processing "+fn);
				if (CorD.equals("C")){ 
					resultstr = cy.encrypt(xx,key);
					System.out.println("new cyphered txt="+resultstr);
				}
				if (CorD.equals("D")){ 
					resultstr = cy.decypher(xx,key);
					System.out.println("new decyphered txt="+resultstr);
				}
			}
		}else{
			System.out.println("Invalid arguments - <'C' or 'D'> <filename> <numeric key>");
		}
	}
}
