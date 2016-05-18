import java.io.IOException;

import javax.swing.SwingUtilities;

public class _24Puzzle {
	GameControl game;
	AlgorithmControler algContr;
	GVControler GVContr;
	
	public _24Puzzle() throws IOException {
		game = new GameControl();
		algContr = new AlgorithmControler(game);
		GVContr = new GVControler(game);
		game.AddPuzzles();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		SwingUtilities.invokeLater(new  Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					_24Puzzle newGame = new _24Puzzle();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
	}
}
