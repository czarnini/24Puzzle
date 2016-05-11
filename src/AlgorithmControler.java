import java.awt.List;
import java.awt.Point;

/**
 * Klasa maj¹ca na celu przekazywanie danych z gry do algorytmu
 * 
 * Zastanawiam siê, czy metody, które tu umieœci³em nie powinny byæ w³aœnie w tym "abstrakcyjnym interfejsie",
 *  a klasa Algorithm implementowa³aby ten interfejs
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
