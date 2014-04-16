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
		// This is a naive replacement rule, only consider number
		// But there are more, like C++, C(?)
		
		for (int i=0; i<inText.length(); i++)
		{
			char before;
			if (i!=0)
				before=inText.charAt(i-1);
			else before='_';
			char now=inText.charAt(i);
			char after;
			if (i==inText.length()-1)
				after='_';
			else after=inText.charAt(i+1);
			
			int wordCheck=checkWord(before, now, after);
			if (wordCheck==0)
				inText=inText.substring(0, i)+"CHOMSKYDOT"+inText.substring(i+1);
			else if (wordCheck==1)
				inText=inText.substring(0, i)+"CHOMSKYCOMMA"+inText.substring(i+1);
			else if (wordCheck==2)
				inText=inText.substring(0, i)+"CHOMSKYBAR"+inText.substring(i+1);
			else if (wordCheck==3)
				inText=inText.substring(0, i)+"CHOMSKYQUOTE"+inText.substring(i+1);
		}
		
		String OPERATORS=" ,.!?()[]{}<>;`'\"&\n-"; // modify it to consider more cases
		StringTokenizer tokens = new StringTokenizer(inText, OPERATORS, true);
		
		while (tokens.hasMoreTokens())
		{
			String t=processToken(tokens.nextToken());
			if (checkToken(t)) temp.add(t);
		}
		
		// recover on number
		
		for (int i=0; i<temp.size(); i++)
		{
			int t1=temp.get(i).indexOf("CHOMSKYDOT");
			int t2=temp.get(i).indexOf("CHOMSKYCOMMA");
			int t3=temp.get(i).indexOf("CHOMSKYBAR");
			int t4=temp.get(i).indexOf("CHOMSKYQUOTE");
			if (t1!=-1 || t2!=-1 || t3!=-1 || t4!=-1)
			{
				String tempStr=temp.get(i);
				tempStr=tempStr.replaceAll("CHOMSKYDOT", ".");
				tempStr=tempStr.replaceAll("CHOMSKYCOMMA", ",");
				tempStr=tempStr.replaceAll("CHOMSKYBAR", "-");
				tempStr=tempStr.replaceAll("CHOMSKYQUOTE", "'");
				temp.set(i, tempStr);
			}
		}
		
		return temp;
	}
	
	public String processToken(String token)
	{
		String temp="";
		
		for (int i=0; i<token.length(); i++)
		{
			//if ((token.charAt(i)>='a' && token.charAt(i)<='z') ||
			//	(token.charAt(i)>='A' && token.charAt(i)<='Z') ||
			//	(token.charAt(i)>='0' && token.charAt(i)<='9'))
			temp+=token.charAt(i);
		}
		
		return temp;
	}
	
	public int checkWord(char before, char now, char after)
	{
		if (before!=' ' && after!=' ')
		{
			if (now=='.') return 0;
			if (now==',') return 1;
			if (now=='-') return 2;
		}
		
		if (before!=' ' && after!=' ' && before!='_' && after!='_')
			if (now=='\'') return 3;
		
		return -1;
	}
	
	public boolean checkToken(String token)
	{
		if (token.isEmpty())
			return false;
		
		// process number and number mixed word
		for (int i=0; i<token.length(); i++)
		{
			if (token.charAt(i)>='0' && token.charAt(i)<='9')
				return true;
		}
		
		// process word
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
