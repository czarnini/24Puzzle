import java.awt.Point;
import java.util.ArrayList;

/**
 * Klasa maj¹ca na celu przekazywanie danych z gry do algorytmu
 */
public class AlgorithmControler
{
	Algorithm algorithm;
	GameControl game;
	private int clockTick = 500;//ms
	private boolean paused = false;
	ArrayList<Direction>  stepSolution;
	/**
	 * Konstruktor kontrolera
	 * @param game - klasa gry do pod³¹czenia do algorytmu
	 */
	AlgorithmControler(GameControl game) 
	{
		algorithm = new Algorithm();
		this.game = game;
		game.linkAlgController(this);
	}
	/**
	 * Funkcja informuj¹ca algorytm o wspó³rzêdnych punktu pocz¹tkowego
	 * @param start - wspó³rzêdnie X,Y startu
	 */
	public void sendStartPoint(Point start)
	{
		algorithm.setStart(start);
	}
	/**
	 * Funkcja informuj¹ca algorytm o wspó³rzednych punktu koñcowego
	 * @param end - wspó³rzêdne X,Y koñca
	 */
	public void sendFinishPoint(Point end)
	{
		algorithm.setEnd(end);
	}
	/**
	 * Funkcja wywo³uj¹ca rozwiazanie algorytmu. Algorytm zajmuje siê TYLKO wyznaczeniem optymajnej trasy 
	 * miedzy dwoma punktami, wiêc funkcjê nalezy wykonaæ zawsze gdy chcemy przesun¹æ klocek lub dziurê
	 * z miejsca A do B (oko³o 24*2=48 wywo³añ, mniej w przypadku wystapienia puzzli na odpowiednich miejscach)
	 * Wynik dzia³ania algorytmu zapisywaniy jest do listy stepSolution
	 */
	public void solveStage()
	{
		stepSolution = algorithm.solve();
	}
	/**
	 * Metoda znajduj¹ca okreœlony puzzel na planszy
	 * @param id - ID szukanego puzzla
	 * @return - wspó³rzêdne X,Y miejsca, na którym znajduje siê puzzel, -1 w przypadku wyst¹pienia b³êdu
	 */
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
	/**
	 * Analogiczna metoda znajduj¹ca dziurê na planszy
	 * @return wspó³rzêdne X,Y dziury, -1 w przypadu b³êdu (braku dziury)
	 */
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
	/**
	 * Metoda rozwiazuj¹ca grê. Dla puzzla o kazdym kolejnym ID wyznacza po³o¿enie dziury, przesuwa dziurê na miejsce
	 * puzzla, wyznacza po³ozenie puzzla i za pomoc¹ algorytmu przesuwa go na odpowiednie miejsce po okreslonym czasie
	 * @throws InterruptedException - wyj¹tek w przypadku przerwania uspienia programu
	 */
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
							while (paused) Thread.sleep(clockTick);
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
							while (paused) Thread.sleep(clockTick);
							current = game.move(stepSolution.get(k), current.x, current.y);
							Thread.sleep(clockTick);
						}
					}
				}
					
			}
		}
		/** Na koñcu metoda informuje o u³o¿eniu uk³adanki*/
		game.done();
	}
	/**
	 * Funkcja pauzuj¹ca grê
	 */
	public void pause()
	{
		paused = true;
	}
	/**
	 * Funkcja wznawiaj¹ca grê
	 */
	public void unPause()
	{
		paused = false;
	}
	/**
	 * Funkcja okreslaj¹ca okres po jakim prezentowane s¹ kolejne kroki
	 * @param i - ustalony przez uzytkownika okres
	 */
	public void setClockTick(int i)
	{
		clockTick = i;
		System.out.println("clockTick: "+i);
	}

}
