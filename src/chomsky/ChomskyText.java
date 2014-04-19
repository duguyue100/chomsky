package chomsky;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.dictionary.Dictionary;

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
	
	public ArrayList<Integer> initProcRecord(int size)
	{
		ArrayList<Integer> temp=new ArrayList<Integer>();
		
		for (int i=0; i<size; i++) temp.add(0);
		
		return temp;
	}
	
	public boolean checkMatch(String word, ArrayList<String> wordList)
	{
		for (int i=0;i<wordList.size();i++)
		{
			if (word.equalsIgnoreCase(wordList.get(i)))
				return true;
		}
		
		return false;
	}
	
	public String lookupBase(String pos, String word)
	{
		
		String propsFile="./resource/jwnl14-rc2/config/file_properties.xml";
		
		try
		{
			JWNL.initialize(new FileInputStream(propsFile));
		}
		catch (Exception ex)
		{
			System.out.println("dictionary wrong");
		}
		
		Dictionary wordnet=Dictionary.getInstance();
		
		String can="";
		try
		{
			if (pos.equals("VERB"))
				can=wordnet.lookupIndexWord(POS.VERB, word).getLemma();
			else if (pos.equals("ADJECTIVE"))
				can=wordnet.lookupIndexWord(POS.ADJECTIVE, word).getLemma();
			else if (pos.equals("NOUN"))
				can=wordnet.lookupIndexWord(POS.NOUN, word).getLemma();
			else if (pos.equals("ADVERB"))
				can=wordnet.lookupIndexWord(POS.ADVERB, word).getLemma();
		}
		catch(Exception ex)
		{
			System.out.println("word wrong");
		}
		
		return can;
	}
	
	public void process(ChomskyData DATA)
	{
		// keep processing record for each word.
		// record how many times the algorithm processed the word.
		ArrayList<Integer> procRecord=initProcRecord(text.size());
		
		// First level: processing be-verb
		
		int noWords=text.size();
		
		for (int i=0;i<noWords;i++)
		{
			if (procRecord.get(i)==0 && checkMatch(text.get(i), DATA.beVerbDB))
			{
				String can=lookupBase("VERB", text.get(i));
				procRecord.set(i, 1);
				
				text.set(i, can);
			}
		}
		
		// Second level: Remove verbs with be
		
		int noCount=0;
		while (noCount<text.size())
		{
			String word=text.get(noCount);
			
			if ((word.equals("be") || word.equals("seem")) && (noCount!=text.size()-1))
			{
				String nextWord=text.get(noCount+1);
				
				if (nextWord.contains("ing"))
				{
					// recover to base form
					
					String can=lookupBase("VERB", text.get(noCount+1));
					
					text.set(noCount+1, can);
					
					// remove be
					text.remove(noCount);
					procRecord.remove(noCount);
					
					// update verb
					procRecord.set(noCount, 1);
				}
			}
			
			noCount++;
		}
		
		// third level: remove useless words
		
		noCount=0;
		while (noCount<text.size())
		{
			if (checkMatch(text.get(noCount), DATA.uselessDB))
			{
				text.remove(noCount);
				procRecord.remove(noCount);
			}
			else noCount++;
		}
		
		// fourth level: recover verb
		
		for (int i=0; i<text.size();i++)
		{
			if (procRecord.get(i)!=1 && !checkMatch(text.get(i), DATA.beVerbDB))
			{
				String can=lookupBase("VERB", text.get(i));
				if (can.length()!=0)
				{
					text.set(i, can);
					procRecord.set(i, 1);
				}
			}
		}
		
		// fifth level: recover noun
		// todo: define exception, preserve captial letters
		
		for (int i=0; i<text.size();i++)
		{
			if (procRecord.get(i)!=1)
			{
				String can=lookupBase("NOUN", text.get(i));
				if (can.length()!=0)
				{
					text.set(i, can);
					procRecord.set(i, 1);
				}
			}
		}
		
		// to-do: refine special cases
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
