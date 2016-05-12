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
		//algorithm.setStart(start);
	}
	
	public void sendFinishPoint(Point finish)
	{
		//algorithm.setFinish(finish);
	}
	
	public void solveStep()
	{
		stepSolution = algorithm.solve();
	}

	public Point findPuzzel (int id)
	{
		Point tmp = new Point(0,0);
		for(int i=0; i<GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				tmp.x=i;
				tmp.y=j;
				if(id == game.getInfo(tmp));
					return tmp;
			}
		}
		return new Point(-1,-1);
	}
	
	public Point findBlank()
	{
		Point tmp = new Point(0,0);
		for(int i=0; i<GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				tmp.x=i;
				tmp.y=j;
				if(0 == game.getInfo(tmp));
					return tmp;
			}
		}
		return new Point(-1,-1);
	}

	public void solveGame()
	{
		
	}
	
	

}
