import java.awt.EventQueue;
import java.io.IOException;

public class _24Puzzle {
//Event Dispatch thread???
	
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
			
			GameControl game = new GameControl();
			AlgorithmControler algContr = new AlgorithmControler(game);
			GVControler GVContr = new GVControler(game);
			game.AddPuzzles();
			//game.randomize();
			//algContr.solveGame();
	}
}
