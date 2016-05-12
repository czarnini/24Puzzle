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
	//	game.randomize();
	//	game.move(Direction.left, 1, 0);
	//	game.print();
	//	game.move(Direction.right, 0, 0);
	//	game.print();

		tmp = algContr.findBlank();
		System.out.println(tmp.x+"  "+tmp.y);
		
	}

}
