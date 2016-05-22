import java.awt.Point;
import java.util.ArrayList;

/**
 * Klasa maj�ca na celu przekazywanie danych z gry do algorytmu
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
	 * @param game - klasa gry do pod��czenia do algorytmu
	 */
	AlgorithmControler(GameControl game) 
	{
		algorithm = new Algorithm();
		this.game = game;
		game.linkAlgController(this);
	}
	/**
	 * Funkcja informuj�ca algorytm o wsp�rz�dnych punktu pocz�tkowego
	 * @param start - wsp�rz�dnie X,Y startu
	 */
	public void sendStartPoint(Point start)
	{
		algorithm.setStart(start);
	}
	/**
	 * Funkcja informuj�ca algorytm o wsp�rzednych punktu ko�cowego
	 * @param end - wsp�rz�dne X,Y ko�ca
	 */
	public void sendFinishPoint(Point end)
	{
		algorithm.setEnd(end);
	}
	/**
	 * Funkcja wywo�uj�ca rozwiazanie algorytmu. Algorytm zajmuje si� TYLKO wyznaczeniem optymajnej trasy 
	 * miedzy dwoma punktami, wi�c funkcj� nalezy wykona� zawsze gdy chcemy przesun�� klocek lub dziur�
	 * z miejsca A do B (oko�o 24*2=48 wywo�a�, mniej w przypadku wystapienia puzzli na odpowiednich miejscach)
	 * Wynik dzia�ania algorytmu zapisywaniy jest do listy stepSolution
	 */
	public void solveStage()
	{
		stepSolution = algorithm.solve();
	}
	/**
	 * Metoda znajduj�ca okre�lony puzzel na planszy
	 * @param id - ID szukanego puzzla
	 * @return - wsp�rz�dne X,Y miejsca, na kt�rym znajduje si� puzzel, -1 w przypadku wyst�pienia b��du
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
	 * Analogiczna metoda znajduj�ca dziur� na planszy
	 * @return wsp�rz�dne X,Y dziury, -1 w przypadu b��du (braku dziury)
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
	 * Metoda rozwiazuj�ca gr�. Dla puzzla o kazdym kolejnym ID wyznacza po�o�enie dziury, przesuwa dziur� na miejsce
	 * puzzla, wyznacza po�ozenie puzzla i za pomoc� algorytmu przesuwa go na odpowiednie miejsce po okreslonym czasie
	 * @throws InterruptedException - wyj�tek w przypadku przerwania uspienia programu
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
		/** Na ko�cu metoda informuje o u�o�eniu uk�adanki*/
		game.done();
	}
	/**
	 * Funkcja pauzuj�ca gr�
	 */
	public void pause()
	{
		paused = true;
	}
	/**
	 * Funkcja wznawiaj�ca gr�
	 */
	public void unPause()
	{
		paused = false;
	}
	/**
	 * Funkcja okreslaj�ca okres po jakim prezentowane s� kolejne kroki
	 * @param i - ustalony przez uzytkownika okres
	 */
	public void setClockTick(int i)
	{
		clockTick = i;
		System.out.println("clockTick: "+i);
	}

}
