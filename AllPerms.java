// to compile do from folder above - C:\projects\Cypher>javac -cp ../ Cypher.java
// to execute from same folder  - C:\projects\Cypher>java -cp ../ Cypher.Cypher D decypher2.txt 874

package Cypher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;

public class AllPerms
{
				//	 012345678901234567890123456789012345678901234567890123456789012345678901234567890123
	private static String baseStr = 	"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:;?[]-+=_{}#£$%!()*";
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

	private String permStr = "1234";
	private char[] charArray = new char[4];
	static long cnt = 0;
	//===================================================================================
	public void debug(String msg){
		System.out.println(msg);
	}

	public static void permute(char[] arr){
    		permuteHelper(arr, 0);
	}

	private static void permuteHelper(char[] arr, int index){
    		if(index >= arr.length - 1){ //If we are at the last element - nothing left to permute
        		//System.out.println(Arrays.toString(arr));
        		//Print the array
        		//System.out.print("[");
        		//for(int i = 0; i < arr.length - 1; i++){
        	    	//	System.out.print(arr[i] + ", ");
        		//}
        		//if(arr.length > 0) 
            		//	System.out.print(arr[arr.length - 1]);
        		//System.out.println("]");
			cnt++;
			if (cnt % 1000000 == 0) System.out.println("cnt="+cnt);
        		return;
    		}

    		for(int i = index; i < arr.length; i++){ //For each index in the sub array arr[index...end]

	        	//Swap the elements at indices index and i
        		char t = arr[index];
        		arr[index] = arr[i];
        		arr[i] = t;

		        //Recurse on the sub array arr[index+1...end]
		        permuteHelper(arr, index+1);

		        //Swap the elements back
		        t = arr[index];
	        	arr[index] = arr[i];
	        	arr[i] = t;
		}
	}
    	//===================================================================================
	public static void main(String[] args) {
		char[] xx = new char[baseStr.length()];
		for (int i = 0; i < xx.length; i++) xx[i] = baseStr.charAt(i);
    		permute(xx);
		System.out.println("num of perms = "+cnt);
	}
}
