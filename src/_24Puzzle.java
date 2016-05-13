import java.awt.Point;

public class _24Puzzle {

	public static void main(String[] args) 
	{
		GameControl game = new GameControl();
		Algorithm algorithm = new Algorithm();
		AlgorithmControler algContr = new AlgorithmControler(algorithm, game);
		View view = new View();
		GVControler GVContr = new GVControler(game, view);
		game.AddPuzzles();
		Point tmp = algContr.findBlank();
		game.print();

		game.randomize();
		game.print();
		
		algContr.solveGame();
		game.print();
		
		
	}

}
