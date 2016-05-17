import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Klasa maj¹ca na celu przekazywanie danych z gry do algorytmu
 * 
 * Zastanawiam siê, czy metody, które tu umieœci³em nie powinny byæ w³aœnie w tym "abstrakcyjnym interfejsie",
 *  a klasa Algorithm implementowa³aby ten interfejs
 */
public class AlgorithmControler implements ActionListener
{
	Algorithm algorithm;
	GameControl game;
	private int clockTick = 500;//ms
	private int pauseTick = 500; /** wartoœæ timera w przypadku u¿ycia pauzy*/
	private boolean paused = false;
	ArrayList<Direction>  stepSolution;
	
	AlgorithmControler(GameControl game) 
	{
		algorithm = new Algorithm();
		this.game = game;
		game.linkAlgController(this);
	}
	
	public void sendStartPoint(Point start)
	{
		algorithm.setStart(start);
	}
	
	public void sendFinishPoint(Point end)
	{
		algorithm.setEnd(end);
	}
	
	public void solveStage()
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

	public void solveGame() throws InterruptedException
	{
		for (int i=0; i<(GameControl.WIDTH*GameControl.WIDTH-1); ++i)
		{
			for (int j=0; j<2; ++j)
			{
				Point end = new Point((i%GameControl.WIDTH),(int)i/GameControl.WIDTH);
				Point tmp = findPuzzel(i+1);
				if (end.x!=tmp.x || end.y!=tmp.y)
				{	
					sendFinishPoint(end);
					if (j==0) //move hole
					{
						Point start = findBlank(), current = findBlank();
						sendStartPoint(start);
						solveStage();
						for (int k=0; k<stepSolution.size(); ++k)
						{
							while (paused) Thread.sleep(pauseTick);
							current = game.moveHole(stepSolution.get(k), current.x, current.y);
							Thread.sleep(clockTick);
						}
						
					}
					else //move puzzel
					{
						Point current = findPuzzel(i+1), start = findPuzzel(i+1);
						sendStartPoint(start);
						solveStage();
						for (int k=0; k<stepSolution.size(); ++k)
						{
							while (paused) Thread.sleep(pauseTick);
							current = game.move(stepSolution.get(k), current.x, current.y);
							Thread.sleep(clockTick);
						}
					}
				}
					
			}
		}
		game.done();
	}

	public void pause()
	{
		paused = true;
	}

	public void unPause()
	{
		paused = false;
	}
	
	public void setClockTick(int i)
	{
		clockTick = i;
		System.out.println("clockTick: "+i);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
