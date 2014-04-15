package chomsky;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChomskyFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1964200924007490868L;
	JTextArea originalText;
	JTextArea processedText;
	JLabel originalLabel;
	JLabel processedLabel;
	JButton convertText;
	JButton loadTextButton;
	JButton exportResultButton;
	JButton aboutButton;
	JButton exitButton;
	JFileChooser chooser;
	
	// Text and data container
	
	ChomskyText TEXT;
	ChomskyData DATA;

	
	public ChomskyFrame()
	{
		super("Chomsky");
		this.setLayout(null);
		
		// set label
		
		originalLabel=new JLabel();
		originalLabel.setText("Original Text");
		originalLabel.setLocation(150, 10);
		originalLabel.setSize(100, 30);
		this.add(originalLabel);
		
		processedLabel=new JLabel();
		processedLabel.setText("Processed Text");
		processedLabel.setLocation(560, 10);
		processedLabel.setSize(100, 30);
		this.add(processedLabel);
		
		// set text
		
		originalText=new JTextArea();
		originalText.setEditable(true);
		originalText.setSize(300, 400);
		originalText.setLocation(50, 40);
		originalText.setLineWrap(true);
		originalText.setWrapStyleWord(true);
		JScrollPane originalScroll = new JScrollPane(originalText);
        originalScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        originalScroll.setBounds(50, 40, 300, 400);
		this.getContentPane().add(originalScroll);
		
		processedText=new JTextArea();
		processedText.setEditable(false);
		processedText.setSize(300, 400);
		processedText.setLocation(450, 40);
		processedText.setLineWrap(true);
		processedText.setWrapStyleWord(true);
		JScrollPane processedScroll = new JScrollPane(processedText);
        processedScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        processedScroll.setBounds(450, 40, 300, 400);
		this.getContentPane().add(processedScroll);
		
		// set button
		convertText=new JButton();
		convertText.setText(">>");
		convertText.setLocation(370, 200);
		convertText.setSize(60, 60);
		convertText.addActionListener(this);
		this.add(convertText);
		
		loadTextButton=new JButton();
		loadTextButton.setText("Load");
		loadTextButton.setSize(100, 50);
		loadTextButton.setLocation(50, 500);
		loadTextButton.addActionListener(this);
		this.add(loadTextButton);
		
		exportResultButton=new JButton();
		exportResultButton.setText("Export");
		exportResultButton.setSize(100, 50);
		exportResultButton.setLocation(250, 500);
		exportResultButton.addActionListener(this);
		this.add(exportResultButton);
		
		aboutButton=new JButton();
		aboutButton.setText("About");
		aboutButton.setSize(100, 50);
		aboutButton.setLocation(450, 500);
		aboutButton.addActionListener(this);
		this.add(aboutButton);
		
		exitButton=new JButton();
		exitButton.setText("Exit");
		exitButton.setSize(100, 50);
		exitButton.setLocation(650, 500);
		exitButton.addActionListener(this);
		this.add(exitButton);
		
		DATA=new ChomskyData();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object source=e.getSource();
		
		// exit button
		if (source==exitButton)
		{
			System.exit(0);
		}
		else if (source==convertText)
		{
			String text=originalText.getText();
			
			TEXT=new ChomskyText(text);
			//TEXT.process(DATA);
			
			processedText.setText(TEXT.formText());
		}
		else if (source==loadTextButton)
		{
			String inputFile="";
			chooser = new JFileChooser();
		    chooser.setDialogTitle("Chose a input text");
		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		    	inputFile=chooser.getSelectedFile().getAbsolutePath();
		    else inputFile="";
		    
		    if (!inputFile.isEmpty())
		    {
		    	String text="";
		    	
		    	Scanner input=null;
		    	try
		    	{
		    		input=new Scanner(new java.io.File(inputFile));
		    	}
		    	catch (Exception ex)
		    	{
		    		JOptionPane.showMessageDialog(null, "Wrong", "Invalid file or cannot open", JOptionPane.ERROR_MESSAGE);
		    	}
		    	
		    	while (input.hasNextLine())
		    	{
		    		text+=input.nextLine()+"\n";
		    	}
		    	
		    	input.close();
		    	
		    	originalText.setText(text);
		    }
		}
		else if (source==exportResultButton)
		{
			String text=processedText.getText();
			
			if (!text.isEmpty())
			{
				// write to file
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Empty data", "Empty data", JOptionPane.ERROR_MESSAGE);
			}
			// save to current directory
			// updated to save dialog
		}
		else if (source==aboutButton)
		{
			// add a window for about
			String msg="WAES2108 Project\n"+
						"Leong Chin Poh (WEK110029)\n"+
						"Tey Eng Yao (WEK110062)\n"+
						"Hu Yuhuang (WEK110709)";
			String msgTitle="About";
			JOptionPane.showMessageDialog(null, msg, msgTitle, JOptionPane.INFORMATION_MESSAGE);
		}
	}
}