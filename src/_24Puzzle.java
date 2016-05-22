import java.io.IOException;

import javax.swing.SwingUtilities;
/**
 * 
 * @author Marcin Janeczko, Micha³ bogucki, Aleksander Tym
 * Nadrzêdna metoda programu, zawiera funkcjê main i uruchamia pozosta³e sk³adowe
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
	 * Funkcja main, inicjuj¹ca dzia³anie programu
	 * @param args - domyœlny parametr funkcji main
	 * @throws InterruptedException - wyj¹tek w przypadku przerwania funkcji uœpienia programu
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
