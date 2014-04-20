package chomsky;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ChomskyData {
	
	public ArrayList<String> beVerbDB;
	public ArrayList<String> uselessDB;
	
	public ChomskyData()
	{
		// init variables
		beVerbDB=new ArrayList<String>();
		uselessDB=new ArrayList<String>();
		
		// load all data
		loadBeVerb();
		loadUselessDB();
	}
	
	// Function: load word set of useless words
	// contains such as "a", "an", "the", etc
	public void loadUselessDB()
	{
		Scanner input=null;
		
		try
		{
			input=new Scanner(new File("./resource/UselessDB.in"));
		}
		catch(Exception e)
		{
			System.out.println("Wrong file");
		}
		
		while (input.hasNextLine())
		{
			uselessDB.add(input.nextLine());
		}
		
		input.close();
	}
	
	// Function: print out useless word list
	public void printUselessDB()
	{
		System.out.println(uselessDB.size());
		for (int i=0;i<uselessDB.size();i++)
			System.out.println(uselessDB.get(i));
	}
	
	// Function: load be-verb
	public void loadBeVerb()
	{
		Scanner input=null;
		
		try
		{
			input=new Scanner(new File("./resource/BeVerb.in"));
		}
		catch(Exception e)
		{
			System.out.println("Wrong file");
		}
		
		while (input.hasNextLine())
		{
			beVerbDB.add(input.nextLine());
		}
		
		input.close();
	}
	
	// Function: print be-verb
	public void printBeVerb()
	{
		System.out.println(beVerbDB.size());
		for (int i=0;i<beVerbDB.size();i++)
			System.out.println(beVerbDB.get(i));
	}
}
