import java.awt.List;
import java.awt.Point;

/**
 * Klasa maj�ca na celu przekazywanie danych z gry do algorytmu
 * 
 * Zastanawiam si�, czy metody, kt�re tu umie�ci�em nie powinny by� w�a�nie w tym "abstrakcyjnym interfejsie",
 *  a klasa Algorithm implementowa�aby ten interfejs
 */
public class AlgorithmControler {
	Algorithm algorithm = new Algorithm();
	GameControl game = new GameControl();
	List  stepSolution;
	
	public AlgorithmControler(Algorithm algorithm, GameControl game) 
	{
		this.algorithm = algorithm;
		this.game = game;
	}
	
	
	public void sendStartPoint(Point start)
	{
		algorithm.setStart(start);
	}
	
	public void sendFinishPoint(Point finish)
	{
		algorithm.setFinish(finish);
	}
	
	public void solveStep()
	{
		stepSolution = algorithm.solve();
	}

	public Point findPuzzel (int id)
	{
		return new Point(0,0);
	}

	public void solveGame()
	{
		
	}
	
	

}
