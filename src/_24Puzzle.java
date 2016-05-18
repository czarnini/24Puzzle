import java.awt.EventQueue;

public class _24Puzzle {
//Event Dispatch thread???
	
	GameControl game;
	AlgorithmControler algContr;
	GVControler GVContr;
	
	public  _24Puzzle() throws InterruptedException 
	{
		game = new GameControl();
		algContr = new AlgorithmControler(game);
		GVContr = new GVControler(game);
		game.AddPuzzles();
	}
	
	
	
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					try {
						_24Puzzle contrAll = new _24Puzzle();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

}
