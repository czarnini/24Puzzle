import java.awt.Point;

public class _24Puzzle {
//Event Dispatch thread???
	public static void main(String[] args) throws InterruptedException 
	{
		
			GameControl game = new GameControl();
			AlgorithmControler algContr = new AlgorithmControler(game);
			GVControler GVContr = new GVControler(game);
			game.AddPuzzles();
			Point tmp = algContr.findBlank();
			game.print();
			game.randomize();
			game.print();
			
			algContr.solveGame();
			game.print();
	}

}
