import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class View
{
	public final int boardWidth = 5;
	public final int boardHeight = 5;
	GVControler controler;
	JPanel mainPanel;
	JFrame mainFrame;
	JButton btnRozmieszaj;
	JButton btnPauza;
	JButton btnRozwiaz;
	JButton btnBoardField;
	JButton gameButtons[][]; //tablica puzzli
	
	private int screenWidth, screenHeight;
	boolean paused = false;
	@SuppressWarnings({ "unchecked", "rawtypes" })
/*wygenerowany polautomatycznie (WindowBuilder pro) widok, na ktory skaladaja sie guziki do obslugi
 * oraz dwuwymiarowa tablica 25-1 guzikow (puzzli)*/
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
			//	GVControler.solve();
			}
		});
		btnRozwiaz.setBounds(731, 124, 160, 40);
		mainFrame.getContentPane().add(btnRozwiaz);
		
		btnBoardField = new JButton("");
		btnBoardField.setBounds(80, 80, 400, 400);
		btnBoardField.setEnabled(false);
		btnBoardField.setBackground(Color.BLACK);
		mainFrame.getContentPane().add(btnBoardField);
		btnBoardField.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0,1", "0,2", "0,5", "1", "2", "5"}));
		comboBox.setBounds(874, 236, 48, 22);
		comboBox.setSelectedIndex(3);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				int i = comboBox.getSelectedIndex();
				switch (i)
				{
				case 0:
				{
					controler.setClockTick(100);
					break;
				}
				case 1:
				{
					controler.setClockTick(200);
					break;
				}
				case 2:
				{
					controler.setClockTick(500);
					break;
				}
				case 3:
				{
					controler.setClockTick(1000);
					break;
				}
				case 4:
				{
					controler.setClockTick(2000);
					break;
				}
				case 5:
				{
					controler.setClockTick(5000);
					break;
				}
				}
			}
		});
		mainFrame.getContentPane().add(comboBox);
		
		JLabel lblWykonujKolejnyKrok = new JLabel("Wykonuj kolejny krok co");
		lblWykonujKolejnyKrok.setBounds(731, 239, 138, 16);
		mainFrame.getContentPane().add(lblWykonujKolejnyKrok);
		
		JLabel lblSekundy = new JLabel("sekund(y)");
		lblSekundy.setBounds(924, 239, 56, 16);
		mainFrame.getContentPane().add(lblSekundy);
		
		JButton btnMowtemp = new JButton("mowtemp");
		btnMowtemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controler.move(Direction.left, 1, 0);
			}
		});
		btnMowtemp.setBounds(666, 413, 97, 25);
		mainFrame.getContentPane().add(btnMowtemp);
		
		JButton btnMowtemp_1 = new JButton("mowtemp2");
		btnMowtemp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controler.move(Direction.right, 0, 0);
			}
		});
		btnMowtemp_1.setBounds(666, 455, 97, 25);
		mainFrame.getContentPane().add(btnMowtemp_1);
		
		JButton btnHoletemp = new JButton("holetemp");
		btnHoletemp.setBounds(666, 493, 97, 25);
		mainFrame.getContentPane().add(btnHoletemp);
		
		JButton btnHoletemp_1 = new JButton("holetemp2");
		btnHoletemp_1.setBounds(666, 531, 97, 25);
		mainFrame.getContentPane().add(btnHoletemp_1);
//schemat tablicy: TABLICA[<--->][/\ \/]
		gameButtons = new JButton[boardWidth][boardHeight];
		for (int j=0,k=1;j < boardWidth; ++j)
		{
			for (int i=0; i < boardHeight ; ++i, ++k)
			{
				gameButtons[i][j] = new JButton(String.valueOf(k));
				gameButtons[i][j].setEnabled(false);
				gameButtons[i][j].setFont(new Font("Tahoma", Font.PLAIN, 30));
				gameButtons[i][j].setBounds(80*(i+1), 80*(j+1), 80, 80);
				mainFrame.getContentPane().add(gameButtons[i][j]);
			}
		}
		/*Dosc manualne ustawienie koloru, prawdopodobnie do usuniecia lub zmiany na tekstury*/
		gameButtons[0][0].setBackground(Color.GREEN);	
		gameButtons[0][1].setBackground(new Color(173, 255, 47));	
		gameButtons[0][2].setBackground(Color.YELLOW);
		gameButtons[0][3].setBackground(new Color(255, 215, 0));
		gameButtons[0][4].setBackground(Color.ORANGE);
		gameButtons[1][0].setBackground(new Color(0, 250, 154));
		gameButtons[1][1].setBackground(new Color(127, 255, 212));
		gameButtons[1][2].setBackground(new Color(255, 255, 150));
		gameButtons[1][3].setBackground(new Color(244, 164, 96));
		gameButtons[1][4].setBackground(new Color(255, 99, 71));
		gameButtons[2][0].setBackground(Color.CYAN);
		gameButtons[2][1].setBackground(new Color(175, 238, 238));
		gameButtons[2][2].setBackground(Color.WHITE);	
		gameButtons[2][3].setBackground(new Color(240, 128, 128));
		gameButtons[2][4].setBackground(Color.RED);
		gameButtons[3][0].setBackground(new Color(30, 144, 255));;
		gameButtons[3][1].setBackground(new Color(123, 104, 238));
		gameButtons[3][2].setBackground(new Color(238, 130, 238));
		gameButtons[3][3].setBackground(new Color(255, 130, 238));
		gameButtons[3][4].setBackground(new Color(139, 0, 0));
		gameButtons[4][0].setBackground(Color.BLUE);
		gameButtons[4][1].setBackground(new Color(138, 43, 226));
		gameButtons[4][2].setBackground(Color.MAGENTA);
		gameButtons[4][3].setBackground(new Color(128, 0, 128));
		gameButtons[4][4].setBackground(Color.BLACK);
		gameButtons[boardWidth-1][boardHeight-1].setVisible(false);
		
		mainPanel = new JPanel();
		mainFrame.setVisible(true);
	}
/*funkcja laczaca widok z jego kontrolerem*/	
	public void linkController(GVControler controler)
	{
		this.controler = controler;
	}
/*funkcja wywolywana przy zakonczeniu ukladania, resetuje ona dostepnosc przyciskow*/
	void done()
	{
		btnPauza.setEnabled(false);
		btnRozmieszaj.setEnabled(true);
		btnRozwiaz.setEnabled(true);
	}
/*funkcja przerysowujaca klocek o ID na pozycje (x,y). Zaklada, ze klocek przerysowany jest tylko jeden, bez wzgledu na kolejnosc.
 * Uzywana przy randomizacji, odswiezaniu widoku (przy klasycznej grze, ktora uniemozliwia kladzenie kolckow na siebie)*/	
	void redraw (int x, int y, int ID)
	{
		int order = mainFrame.getContentPane().getComponentZOrder( gameButtons[(ID-1)%5][(int)(ID-1)/5]);
		mainFrame.getContentPane().setComponentZOrder(gameButtons[(ID-1)%5][(int)(ID-1)/5], 1);
		gameButtons[(ID-1)%5][(int)(ID-1)/5].setBounds((x+1)*80, (y+1)*80, 80, 80);
		mainFrame.getContentPane().setComponentZOrder(gameButtons[(ID-1)%5][(int)(ID-1)/5], order);
	}
	
	
}
