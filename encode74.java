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

public class encode74 
{
	private static String[] codewordTable = new String[] {
			"0000000", "0001011", "0010111", "0011100", 
			"0100110", "0101101", "0110001", "0111010", 
			"1000101", "1001110", "1010010", "1011001",
			"1100011", "1101000", "1110100", "1111111"
	};
	
	public static void main(String[] args) throws IOException
	{
		encode74 encoder = new encode74();
	
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
		
		int c;
		while((c = in.read()) != -1)
		{
			out.write(encoder.CharToHamming((char)c));
			out.write("\n");
		}
		
		out.close();
	}
	
	private String CharToHamming(char c)
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
		
		// split the source into 2 parts
		String binaryString1 = binaryString.substring(0, 4);
		String binaryString2 = binaryString.substring(4, 8);
		
		// encode each part
		int index1 = Integer.valueOf(binaryString1, 2);
		int index2 = Integer.valueOf(binaryString2, 2);
		
		// the final codeword
		String codewordString = codewordTable[index1] + codewordTable[index2];
		
		return codewordString; 
	}
}