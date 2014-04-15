package chomsky;

import javax.swing.JFrame;

public class ChomskyMain {
	public static int X=100;
	public static int Y=100;
	public static int WIDTH=800;
	public static int HEIGHT=600;
	public static String PROJECT_TITLE="Chomsky";
	
	public static void main(String[] args)
	{
		// argument processing (not support for now)
		
		ChomskyFrame frame=new ChomskyFrame();
		
		frame.setTitle(PROJECT_TITLE);
		frame.setBounds(X, Y, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}