import java.awt.Dimension;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
/**
 * 
 * @author Marcin Janeczko, Micha³ Bogucki, Aleksander Tym
 * Klasa odpowiadaj¹ca za interfejs graficzny programu
 */
public class View
{
	public final int boardWidth = 5; /** Szerokoœæ planszy*/
	public final int boardHeight = 5; /** Wysokoœæ planszy*/
	GVControler controler; /** Klasa kontrolera miêdzy widokiem a modelem gry*/
	JPanel mainPanel;
	JFrame mainFrame;
	JButton btnRozmieszaj;
	JButton btnPauza;
	JButton btnRozwiaz;
	JButton btnBoardField;
	JButton gameButtons[][]; /**tablica puzzli*/
	ImageIcon texture[]; /** tablica tekstur puzzli*/
	private int stepsNo = 0; /** licznik kroków*/
	private int screenWidth, screenHeight;
	boolean paused = false;
	JLabel lblSteps;
	@SuppressWarnings({ "unchecked", "rawtypes" })
/** wygenerowany polautomatycznie(za pomoc¹ wtyczki WindowBuilder pro) widok, na ktory skaladaja sie guziki do obslugi
 * oraz dwuwymiarowa tablica 24 guzikow (puzzli)*/
	View() throws IOException
	{
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("24 puzzle solver");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //ustawienie okna na œrodku ekranu
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
		mainFrame.setBounds((screenWidth/2)-500, (screenHeight/2)-350, 1000, 700);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setLayout(null);
		btnRozwiaz = new JButton("Rozwi\u0105\u017C");
		btnRozwiaz.setEnabled(false);
		btnRozmieszaj = new JButton("Rozmieszaj");
		btnRozmieszaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblSteps.setText(""+stepsNo);
				btnRozwiaz.setEnabled(true);
				controler.randomize();
			}
		});
		btnRozmieszaj.setBounds(731, 71, 160, 40);
		mainFrame.getContentPane().add(btnRozmieszaj);
		
		btnPauza = new JButton("Pauza");
		btnPauza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!paused)
				{
					controler.pause();
					paused = true;
					btnPauza.setText("Wznow");
				}
				else
				{
					controler.unPause();
					paused = false;
					btnPauza.setText("Pauza");
				}
			}
		});
		btnPauza.setEnabled(false);
		btnPauza.setBounds(731, 177, 160, 40);
		mainFrame.getContentPane().add(btnPauza);
		
		btnRozwiaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRozwiaz.setEnabled(false);
				btnRozmieszaj.setEnabled(false);
				btnPauza.setEnabled(true);
				Thread solveThread = new Thread(){
					public void run()
					{
						try {
							controler.solve();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				};
				solveThread.start();
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
		comboBox.setSelectedIndex(2);
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
		
		JLabel lblLiczbaKrokw = new JLabel("Liczba krok\u00F3w: ");
		lblLiczbaKrokw.setBounds(731, 287, 89, 16);
		mainFrame.getContentPane().add(lblLiczbaKrokw);
		
		lblSteps = new JLabel("0");
		lblSteps.setBounds(874, 287, 56, 16);
		mainFrame.getContentPane().add(lblSteps);
		
		//schemat tablicy: TABLICA[<--->][/\ \/]
		gameButtons = new JButton[boardWidth][boardHeight];
		texture = new ImageIcon[25];
		/** inicjacja tablicy guzików, nadanie im wartosci oraz przypisuje tekstury*/
		for (int j=0,k=1;j < boardWidth; ++j)
		{
			for (int i=0; i < boardHeight ; ++i, ++k)
			{
				StringBuilder sb = new StringBuilder("src/textures/"+k+".png");
				String string = new String(sb);
				texture[k-1] = new ImageIcon(string);
				gameButtons[i][j] = new JButton();
				gameButtons[i][j].setFont(new Font("Tahoma", Font.PLAIN, 30));
				gameButtons[i][j].setBounds(80*(i+1), 80*(j+1), 80, 80);
				gameButtons[i][j].setIcon(texture[k-1]);
				mainFrame.getContentPane().add(gameButtons[i][j]);
			}
		}
		gameButtons[boardWidth-1][boardHeight-1].setVisible(false);
		mainPanel = new JPanel();
		mainFrame.setVisible(true);
	}
/**
 * Metoda ³¹cz¹ca widok z kontrolerem obs³uguj¹cym widok	
 * @param controler - kontroler programu
 */
	public void linkController(GVControler controler)
	{
		this.controler = controler;
	}
/**
 * Metoda wywo³ywana w przypadku u³o¿enia uk³adanki przez algorytm, resetuje ona stan guzików
 */
	void done()
	{
		btnPauza.setEnabled(false);
		btnRozmieszaj.setEnabled(true);
		btnRozwiaz.setEnabled(false);
		stepsNo = 0;
	}
	/**
	 * Najwa¿niejsza metoda widoku, przerysowuj¹ca klocek na now¹ pozycjê. Zak³ada, ¿e przesuwamy tylko jeden klocek
	 * u¿ywana przy randomizacji i odœwiezaniu widoku w czasie uk³adania
	 * @param x - wspó³rzêdna xowa docelowej pozycji
	 * @param y - wspó³rzêdna ykowa docelowej pozycji
	 * @param ID - ID przesuwanego klocka
	 */	
	void redraw (int x, int y, int ID)
	{
		int order = mainFrame.getContentPane().getComponentZOrder( gameButtons[(ID-1)%5][(int)(ID-1)/5]);
		mainFrame.getContentPane().setComponentZOrder(gameButtons[(ID-1)%5][(int)(ID-1)/5], 1);
		gameButtons[(ID-1)%5][(int)(ID-1)/5].setBounds((x+1)*80, (y+1)*80, 80, 80);
		mainFrame.getContentPane().setComponentZOrder(gameButtons[(ID-1)%5][(int)(ID-1)/5], order);
	}
	/**
	 * Metoda inkrementuj¹ca i odœwie¿aj¹ca licznik kroków
	 */
	void updateSteps()
	{
		++stepsNo;
		lblSteps.setText(""+stepsNo);
	}
}
