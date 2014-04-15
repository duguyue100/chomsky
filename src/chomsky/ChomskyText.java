package chomsky;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChomskyText {
	private String rawText;
	private ArrayList<String> text;
	
	public ChomskyText()
	{
		rawText="";
		text=new ArrayList<String>();
	}
	
	public ChomskyText(String inText)
	{
		rawText=inText;
		text=processRawText(inText);
	}
	
	// processing
	
	public ArrayList<String> processRawText(String inText)
	{
		ArrayList<String> temp=new ArrayList<String>();
		
		// find special on number
		
		String OPERATORS=" ,.!?();`'\"&\n"; // modify it to consider more cases
		StringTokenizer tokens = new StringTokenizer(inText, OPERATORS, true);
		
		while (tokens.hasMoreTokens())
		{
			String t=processToken(tokens.nextToken());
			if (checkToken(t)) temp.add(t);
		}
		
		// recover on number
		
		return temp;
	}
	
	public String processToken(String token)
	{
		String temp="";
		
		for (int i=0; i<token.length(); i++)
		{
			if ((token.charAt(i)>='a' && token.charAt(i)<='z') ||
				(token.charAt(i)>='A' && token.charAt(i)<='Z'))
				temp+=token.charAt(i);
		}
		
		return temp;
	}
	
	public boolean checkToken(String token)
	{
		if (token.isEmpty())
			return false;
		
		for (int i=0; i<token.length(); i++)
		{
			if (!((token.charAt(i)>='a' && token.charAt(i)<='z') ||
				(token.charAt(i)>='A' && token.charAt(i)<='Z')))
				return false;
		}
		
		return true;
	}
	
	public void setRawText(String inText)
	{
		rawText=inText;
	}
	
	public String getRawText()
	{
		return rawText;
	}
	
	public ArrayList<String> getText()
	{
		return text;
	}
	
	public void process(ChomskyData DATA)
	{
		// main processing procedures
		
		// consider on number.
	}
	
	public String formText()
	{
		String outText="";
		
		// processing
		for (int i=0; i<text.size(); i++)
			outText=outText+text.get(i)+" ";
		
		return outText;
	}
	
	public void printText()
	{
		System.out.println(formText());
	}
}
