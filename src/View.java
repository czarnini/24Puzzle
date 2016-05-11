import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class View
{
	GVControler controler;
	JPanel mainPanel;
	JFrame mainFrame;
	JButton randomButton;
	JButton solveButton;
	JButton pauseButton;
	JLabel stepEveryLabel;
	JLabel secondsLabel;
	//TODO: Trzeba zoribæ mapê ca³¹ guzików 5x5
	private int screenWidth, screenHeight;
	View()
	{
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("24 puzzle solver");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		mainFrame.setBounds((screenWidth/2)-500, (screenHeight/2)-350, 1000, 700);
		mainFrame.setResizable(false);
		
		mainPanel = new JPanel();
	}
	
	public void linkController(GVControler controler)
	{
		this.controler = controler;
	}
	
	public void createFrame()
	{
		SpringLayout layout = new SpringLayout();
		mainPanel.setLayout(layout);
		
		Dimension buttonSize = new Dimension (100, 25);
	}
}
