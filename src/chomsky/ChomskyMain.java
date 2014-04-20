package chomsky;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;

public class ChomskyMain {
	public static int X=100;
	public static int Y=100;
	public static int WIDTH=800;
	public static int HEIGHT=600;
	public static String PROJECT_TITLE="ChomSky";
	
	public static void main(String[] args)
	{
		if (args.length==0)
		{
			ChomskyFrame frame=new ChomskyFrame();
		
			frame.setTitle(PROJECT_TITLE);
			frame.setBounds(X, Y, WIDTH, HEIGHT);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setVisible(true);
		}
		else
		{
			if (args.length!=3 && args.length>0)
			{
				if (args[0].equals("--version"))
				{
					System.out.println("ChomSky Beta\n"+
										"This tool is under GPL v3.");
					System.exit(1);
				}
				
				if (args[0].equals("-h") || args[0].equals("-help") || args[0].equals("--help"))
				{
					System.out.println("Usage: java -jar chomsky.jar -f <input file path> <output file path>");
					System.exit(1);
				}
				
				if (!args[0].equals("-f"))
				{
					System.out.println("Usage: java -jar chomsky.jar -f <input file path> <output file path>");
					System.exit(1);
				}
			}
			
			ChomskyText TEXT;
			ChomskyData DATA=new ChomskyData();
			
			if (args[0].equals("-f"))
			{
				String text="";
				Scanner input=null;
				PrintWriter output=null;
				try
				{
					input=new Scanner(new File(args[1]));
					output=new PrintWriter(new File(args[2]));
				}
				catch (Exception ex)
				{
					System.out.println("wrong file");
					System.exit(1);
				}
				
				while (input.hasNextLine())
		    	{
		    		text+=input.nextLine()+"\n";
		    	}
		    	
		    	input.close();
		    	
		    	TEXT=new ChomskyText(text);
		    	TEXT.process(DATA);
		    	
		    	output.println(TEXT.formText());
		    	output.close();
			}
		}
	}
}