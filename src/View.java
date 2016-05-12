import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class View
{
	GVControler controler;
	JPanel mainPanel;
	JFrame mainFrame;
	JButton btnRozmieszaj;
	JButton btnPauza;
	JButton btnRozwiaz;
	
	private int screenWidth, screenHeight;
	boolean paused = false;
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		mainFrame.getContentPane().setLayout(null);
		
		btnRozmieszaj = new JButton("Rozmieszaj");
		btnRozmieszaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GVControler.randomize();
			}
		});
		btnRozmieszaj.setBounds(731, 71, 160, 40);
		mainFrame.getContentPane().add(btnRozmieszaj);
		
		btnPauza = new JButton("Pauza");
		btnPauza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!paused)
				{
					GVControler.pause();
					paused = true;
					btnPauza.setText("Wznow");
				}
				else
				{
					GVControler.unPause();
					paused = false;
					btnPauza.setText("Pauza");
				}
			}
		});
		btnPauza.setEnabled(false);
		btnPauza.setBounds(731, 177, 160, 40);
		mainFrame.getContentPane().add(btnPauza);
		
		btnRozwiaz = new JButton("Rozwi\u0105\u017C");
		btnRozwiaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRozwiaz.setEnabled(false);
				btnRozmieszaj.setEnabled(false);
				btnPauza.setEnabled(true);
				GVControler.solve();
			}
		});
		btnRozwiaz.setBounds(731, 124, 160, 40);
		mainFrame.getContentPane().add(btnRozwiaz);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0,1", "0,2", "0,5", "1", "2", "5"}));
		comboBox.setBounds(874, 236, 48, 22);
		mainFrame.getContentPane().add(comboBox);
		
		JLabel lblWykonujKolejnyKrok = new JLabel("Wykonuj kolejny krok co");
		lblWykonujKolejnyKrok.setBounds(731, 239, 138, 16);
		mainFrame.getContentPane().add(lblWykonujKolejnyKrok);
		
		JLabel lblSekundy = new JLabel("sekund(y)");
		lblSekundy.setBounds(924, 239, 56, 16);
		mainFrame.getContentPane().add(lblSekundy);
		
		JButton btnP11 = new JButton("1");
		btnP11.setBackground(Color.GREEN);
		btnP11.setEnabled(false);
		btnP11.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP11.setBounds(80, 80, 80, 80);
		mainFrame.getContentPane().add(btnP11);
		
		JButton btnP12 = new JButton("2");
		btnP12.setBackground(new Color(173, 255, 47));
		btnP12.setEnabled(false);
		btnP12.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP12.setBounds(160, 80, 80, 80);
		mainFrame.getContentPane().add(btnP12);
		
		JButton btnP13 = new JButton("3");
		btnP13.setBackground(Color.YELLOW);
		btnP13.setEnabled(false);
		btnP13.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP13.setBounds(240, 80, 80, 80);
		mainFrame.getContentPane().add(btnP13);
		
		JButton btnP14 = new JButton("4");
		btnP14.setBackground(new Color(255, 215, 0));
		btnP14.setEnabled(false);
		btnP14.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP14.setBounds(320, 80, 80, 80);
		mainFrame.getContentPane().add(btnP14);
		
		JButton btnP15 = new JButton("5");
		btnP15.setBackground(Color.ORANGE);
		btnP15.setEnabled(false);
		btnP15.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP15.setBounds(400, 80, 80, 80);
		mainFrame.getContentPane().add(btnP15);
		
		JButton btnP21 = new JButton("6");
		btnP21.setBackground(new Color(0, 250, 154));
		btnP21.setEnabled(false);
		btnP21.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP21.setBounds(80, 160, 80, 80);
		mainFrame.getContentPane().add(btnP21);
		
		JButton btnP22 = new JButton("7");
		btnP22.setBackground(new Color(127, 255, 212));
		btnP22.setEnabled(false);
		btnP22.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP22.setBounds(160, 160, 80, 80);
		mainFrame.getContentPane().add(btnP22);
		
		JButton btnP23 = new JButton("8");
		btnP23.setBackground(new Color(255, 255, 150));
		btnP23.setEnabled(false);
		btnP23.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP23.setBounds(240, 160, 80, 80);
		mainFrame.getContentPane().add(btnP23);
		
		JButton btnP24 = new JButton("9");
		btnP24.setBackground(new Color(244, 164, 96));
		btnP24.setEnabled(false);
		btnP24.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP24.setBounds(320, 160, 80, 80);
		mainFrame.getContentPane().add(btnP24);
		
		JButton btnP25 = new JButton("10");
		btnP25.setBackground(new Color(255, 99, 71));
		btnP25.setEnabled(false);
		btnP25.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP25.setBounds(400, 160, 80, 80);
		mainFrame.getContentPane().add(btnP25);
		
		JButton btnP31 = new JButton("11");
		btnP31.setBackground(Color.CYAN);
		btnP31.setEnabled(false);
		btnP31.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP31.setBounds(80, 240, 80, 80);
		mainFrame.getContentPane().add(btnP31);
		
		JButton btnP32 = new JButton("12");
		btnP32.setBackground(new Color(175, 238, 238));
		btnP32.setEnabled(false);
		btnP32.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP32.setBounds(160, 240, 80, 80);
		mainFrame.getContentPane().add(btnP32);
		
		JButton btnP33 = new JButton("13");
		btnP33.setBackground(Color.WHITE);
		btnP33.setEnabled(false);
		btnP33.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP33.setBounds(240, 240, 80, 80);
		mainFrame.getContentPane().add(btnP33);
		
		JButton btnP34 = new JButton("14");
		btnP34.setBackground(new Color(240, 128, 128));
		btnP34.setEnabled(false);
		btnP34.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP34.setBounds(320, 240, 80, 80);
		mainFrame.getContentPane().add(btnP34);
		
		JButton btnP35 = new JButton("15");
		btnP35.setBackground(Color.RED);
		btnP35.setEnabled(false);
		btnP35.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP35.setBounds(400, 240, 80, 80);
		mainFrame.getContentPane().add(btnP35);
		
		JButton btnP41 = new JButton("16");
		btnP41.setForeground(Color.BLACK);
		btnP41.setEnabled(false);
		btnP41.setBackground(new Color(30, 144, 255));
		btnP41.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP41.setBounds(80, 320, 80, 80);
		mainFrame.getContentPane().add(btnP41);
		
		JButton btnP42 = new JButton("17");
		btnP42.setBackground(new Color(123, 104, 238));
		btnP42.setEnabled(false);
		btnP42.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP42.setBounds(160, 320, 80, 80);
		mainFrame.getContentPane().add(btnP42);
		
		JButton btnP43 = new JButton("18");
		btnP43.setBackground(new Color(238, 130, 238));
		btnP43.setEnabled(false);
		btnP43.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP43.setBounds(240, 320, 80, 80);
		mainFrame.getContentPane().add(btnP43);
		
		JButton btnP44 = new JButton("19");
		btnP44.setBackground(new Color(255, 130, 238));
		btnP44.setEnabled(false);
		btnP44.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP44.setBounds(320, 320, 80, 80);
		mainFrame.getContentPane().add(btnP44);
		
		JButton btnP45 = new JButton("20");
		btnP45.setBackground(new Color(139, 0, 0));
		btnP45.setEnabled(false);
		btnP45.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP45.setBounds(400, 320, 80, 80);
		mainFrame.getContentPane().add(btnP45);
		
		JButton btnP51 = new JButton("21");
		btnP51.setBackground(Color.BLUE);
		btnP51.setEnabled(false);
		btnP51.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP51.setBounds(80, 400, 80, 80);
		mainFrame.getContentPane().add(btnP51);
		
		JButton btnP52 = new JButton("22");
		btnP52.setBackground(new Color(138, 43, 226));
		btnP52.setEnabled(false);
		btnP52.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP52.setBounds(160, 400, 80, 80);
		mainFrame.getContentPane().add(btnP52);
		
		JButton btnP53 = new JButton("23");
		btnP53.setBackground(Color.MAGENTA);
		btnP53.setEnabled(false);
		btnP53.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP53.setBounds(240, 400, 80, 80);
		mainFrame.getContentPane().add(btnP53);
		
		JButton btnP54 = new JButton("24");
		btnP54.setBackground(new Color(128, 0, 128));
		btnP54.setEnabled(false);
		btnP54.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnP54.setBounds(320, 400, 80, 80);
		mainFrame.getContentPane().add(btnP54);
		
		JButton btnP55 = new JButton(" ");
		btnP55.setBackground(Color.BLACK);
		btnP55.setEnabled(false);
		btnP55.setBounds(400, 400, 80, 80);
		mainFrame.getContentPane().add(btnP55);
		
		mainPanel = new JPanel();
		mainFrame.setVisible(true);
	}
	
	public void linkController(GVControler controler)
	{
		this.controler = controler;
	}
	
	public void done()
	{
		btnPauza.setEnabled(false);
		btnRozmieszaj.setEnabled(true);
		btnRozwiaz.setEnabled(true);
	}
}
