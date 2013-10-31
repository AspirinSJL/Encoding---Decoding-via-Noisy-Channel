import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.sun.java_cup.internal.runtime.Scanner;

public class encodeCombined
{
	private static String[] codewordTable = new String[] {
		"0000000", "0001011", "0010111", "0011100", 
		"0100110", "0101101", "0110001", "0111010", 
		"1000101", "1001110", "1010010", "1011001",
		"1100011", "1101000", "1110100", "1111111"
	};
	
	public static void main(String[] args) throws IOException
	{
		encodeCombined encoder = new encodeCombined();
	
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
		
		int c;
		while((c = in.read()) != -1)
		{
			out.write(encoder.CharToCombined((char)c));
			out.write("\n");
		}
		
		out.close();
	}
	
	private String CharToCombined(char c) 
	{
		// binary format of ascii of char c
		String binaryStringShort = Integer.toBinaryString((int)c);
		
		// fill the 0s to get 8 bits
		StringBuffer paddingBuffer = new StringBuffer();
		while(paddingBuffer.length() < 8 - binaryStringShort.length())
		{
			paddingBuffer.append("0");
		}		
		String binaryString = paddingBuffer.toString() + binaryStringShort;
		
		// split into 2 parts, encode each with PN followed by Hamming74
		String[] binaryStrings = new String[] {
			binaryString.substring(0, 4),
			binaryString.substring(4, 8)
		};
		
		StringBuffer codewordBuffer = new StringBuffer();
		for (String bits : binaryStrings) 
		{
			// if count #one is odd, check is the same as info, or complement
			String check;
			if(CountOne(bits) % 2 == 1)
			{
				check = bits;
			}
			else
			{
				check = Complement(bits);
			}
			
			// combine info with check after Hamminged
			int index1 = Integer.valueOf(bits, 2);
			int index2 = Integer.valueOf(check, 2);
			codewordBuffer.append(codewordTable[index1]	+ codewordTable[index2]);
		}
		
		return codewordBuffer.toString();
	}
	
	private int CountOne(String binaryString) 
	{
		int countOne = 0;
		for(int i = 0; i < binaryString.length(); ++i)
		{
			if(binaryString.charAt(i) == '1')
				countOne++;
		}
		
		return countOne;
	}
	
	private String Complement(String binaryString) 
	{
		StringBuilder complementBuilder = new StringBuilder(binaryString);
		for(int i = 0; i < binaryString.length(); ++i) 
		{
			char flip = binaryString.charAt(i) == '0' ? '1' : '0';
			complementBuilder.setCharAt(i, flip);
		}
		
		return complementBuilder.toString();
	}
}