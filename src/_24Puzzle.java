import java.io.IOException;

import javax.swing.SwingUtilities;
/**
 * 
 * @author Marcin Janeczko, Micha� bogucki, Aleksander Tym
 * Nadrz�dna metoda programu, zawiera funkcj� main i uruchamia pozosta�e sk�adowe
 */
public class _24Puzzle {
	GameControl game; /** Model gry*/
	AlgorithmControler algContr; /** Kontroler na linii gra - algorytm*/
	GVControler GVContr; /** Kontroler na linii gra - widok*/
	/**
	 * Konstruktor klasy
	 * @throws IOException
	 */
	public _24Puzzle() throws IOException {
		game = new GameControl();
		algContr = new AlgorithmControler(game);
		GVContr = new GVControler(game);
		game.AddPuzzles();
	}
	/**
	 * Funkcja main, inicjuj�ca dzia�anie programu
	 * @param args - domy�lny parametr funkcji main
	 * @throws InterruptedException - wyj�tek w przypadku przerwania funkcji u�pienia programu
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		SwingUtilities.invokeLater(new  Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					_24Puzzle newGame = new _24Puzzle();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
	}
}
