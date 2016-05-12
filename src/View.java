import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
