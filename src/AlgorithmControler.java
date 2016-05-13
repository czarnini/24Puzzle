import java.awt.Point;
import java.util.ArrayList;

/**
 * Klasa maj¹ca na celu przekazywanie danych z gry do algorytmu
 * 
 * Zastanawiam siê, czy metody, które tu umieœci³em nie powinny byæ w³aœnie w tym "abstrakcyjnym interfejsie",
 *  a klasa Algorithm implementowa³aby ten interfejs
 */
public class AlgorithmControler {
	Algorithm algorithm = new Algorithm();
	GameControl game = new GameControl();
	ArrayList<Direction>  stepSolution;
	
	public AlgorithmControler(Algorithm algorithm, GameControl game) 
	{
		this.algorithm = algorithm;
		this.game = game;
	}
	
	
	public void sendStartPoint(Point start)
	{
		algorithm.setStart(start);
	}
	
	public void sendFinishPoint(Point end)
	{
		algorithm.setEnd(end);
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
				if(id == game.getInfo(tmp))
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
				if(0 == game.getInfo(tmp))
					return tmp;
			}
		}
		return new Point(-1,-1);
	}

	public void solveGame()
	{
		for (int i=0; i<(GameControl.WIDTH*GameControl.WIDTH-1); ++i)
		{
			for (int j=0; j<2; ++j)
			{
				Point end = new Point((i%GameControl.WIDTH),(int)i/GameControl.WIDTH);
				sendFinishPoint(end);
				if (j==0) //move hole
				{
					Point start = findBlank(), current = findBlank();
					sendStartPoint(start);
					solveStep();
					for (int k=0; k<stepSolution.size(); ++k)
					{
						current = game.moveHole(stepSolution.get(k), current.x, current.y);
					}
					
				}
				else //move puzzel
				{
					Point current = findPuzzel(i+1), start = findPuzzel(i+1);
					sendStartPoint(start);
					solveStep();
					for (int k=0; k<stepSolution.size(); ++k)
					{
						current = game.move(stepSolution.get(k), current.x, current.y);
					}
				}
					
			}
		}
	}
	
	
	

}
