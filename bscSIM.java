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

public class bscSIM
{
	private static double f = 0.2;
	
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
		
		String lineString;
		while((lineString = in.readLine()) != null)
		{
			StringBuilder transBuilder = new StringBuilder(lineString);
			
			for(int i = 0; i < 14; ++i)
			{
				// flip the bit with probability f
				if(Math.random() < f)
				{
					char flip = lineString.charAt(i) == '0' ? '1' : '0';  
					transBuilder.setCharAt(i, flip);
				}
			}
			
			out.write(transBuilder.toString());
			out.write("\n");
		}
		
		out.close();
	}
	
}