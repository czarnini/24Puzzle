import java.awt.Point;
import java.util.Random;
import java.util.Stack;

/**
 * Kontroler Gry, zawiera reprezentacjê planszy
 * Klasa umo¿liwia poruszanie puzzlami po planszy, zresetowanie obrazka i losowe pomieszanie puzzli
 *
 */
public class GameControl {
	
	/**
	 * szerokoœæ planszy
	 */
	public final  static  int   WIDTH = 5;
	
	/**
	 * Plansza w postaci dwuwymiarowej tablicy stosów obiektów typu Puzzel
	 * elementami tablicy s¹ stosy, bo zgodnie z zasadami projektu puzzle mo¿emy k³aœæ jeden na drugim w obrêbie tego samego pola
	 */
	private Stack<Puzzel> [][] board;
	
	/**
	 * Powi¹zane z gr¹ kontrolery widoku i algorytmu
	 */
	GVControler controler;
	AlgorithmControler algContr;
	
	/**
	 * Zlinkowanie kontrolera GV z gr¹
	 * @param controler kontroler z którym bêdziemy linkowaæ
	 */
	public void linkController(GVControler controler)
	{
		this.controler = controler;
	}
	/**
	 * Zlinkowanie kontrolera algorytmu z gr¹
	 * @param algorithmControler - kontroler z którym bêdziely linkowaæ
	 */
	public void linkAlgController(AlgorithmControler algorithmControler) {
		this.algContr = algorithmControler;
		
	}
	
	/**
	 * Konstruktor, alokuje pamiêæ na stosy tworz¹ce planszê do gry
	 */
	@SuppressWarnings("unchecked")
	GameControl()
	{
		board = (Stack <Puzzel>[][]) new Stack[GameControl.WIDTH][GameControl.WIDTH];
		
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				board [i][j] = new Stack<Puzzel>() ;	
			}
		}
	}
	
	/**
	 * Funkcja dostarczaj¹ca informacje na temat danego miejsca na planszy
	 * @param from wspó³rzêdne punktu, na temat ktrórego chcemy siê czegoœ dowiedzieæ
	 * @return 0 jeœli pole jest puste, id puzzla, który jest na szczycie stosu  w przeciwynym przypadku
	 */
	public int getInfo(Point from)
	{
		if(board[from.x][from.y].isEmpty())
		{
			return 0;
		}
		else
		{
			return board[from.x][from.y].peek().getID();
		}
	}
	
	/**
	 * Funkcja dodaje puzzle na ich nominalne miejsce (ustawienie wyjœciowe)
	 */
	public void AddPuzzles()
	{
		int id=1;
			for ( int i = 0; i < GameControl.WIDTH; ++i)
			{
				for(int j=0; j<GameControl.WIDTH; ++j)
				{
					Puzzel newPuzzel = new Puzzel(id);
					board[j][i].push(newPuzzel);
					
					if(id == 24)
						break;
					++id;
				}
			}
	}

	/**
	 * Funkcja generuje losowe ustawienie puzzli na planszy (w taki sposób ¿eby na ka¿dym polu by³ maksymalnie jeden puzzel)
	 */
	public void randomize()
	{
		Random generator = new Random();
		Puzzel tmpPuzzel;
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				
				if(!board[i][j].isEmpty())
				{
					tmpPuzzel = board[i][j].pop();
				}
				else
				{
					continue;
				}
				
				int newx = generator.nextInt(GameControl.WIDTH);
				int newy = generator.nextInt(GameControl.WIDTH);
				
				if(board[newx][newy].isEmpty())
				{
					board[newx][newy].push(tmpPuzzel);
				}
				else
				{
					board[i][j].push(board[newx][newy].pop());
					board[newx][newy].push(tmpPuzzel);
				}
			}
		}
		redraw();
	}
	
	/**
	 * Funkcja odpowiadaj¹ca za przesuniêcie puzzla po planszy
	 * @param where w któr¹ stronê
	 * @param x - wsp x klocka który bêdziemy ruszaæ
	 * @param y - wsp y klocka, który bêdziemy ruszaæ
	 */
	Point move(Direction where, int x, int y)
	{
		if(board[x][y].isEmpty())
		{
			return new Point(-1,-1);
		}
		Point p = new Point(-1,-1);
		switch(where)
		{
			case left:
			{
				if(x == 0)
				{
					p = new Point (x,y);
				}
				else
				{
					board[x-1][y].push(board[x][y].pop());
					p = new Point (x-1,y);
				}
				break;
			}
			case right:
			{
				if(x == GameControl.WIDTH-1)
				{
					p = new Point (x,y);
				}
				else
				{

					board[x+1][y].push(board[x][y].pop());
					p = new Point (x+1,y);
				}
				break;
			}
			case up:
			{
				if(y == 0)
				{
					p = new Point (x,y);
				}
				else
				{

					board[x][y-1].push(board[x][y].pop());
					p = new Point (x,y-1);
				}
				break;
			}
			case down:
			{
				if(y == GameControl.WIDTH-1)
				{
					p = new Point (x,y);
				}
				else
				{
					board[x][y+1].push(board[x][y].pop());
					p = new Point (x,y+1);
				}
				break;
			}
		}
		controler.updateSteps();
		redraw();
		return p;
	}
	/**
	 * Funkcja wyjœciowa poza klasê gry, wysy³aj¹ca polecenie przerysowania planszy
	 */
	public void redraw()
	{
		Puzzel tmpPuzzel;
		int ID;
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				if (board[i][j].size()!=0)
				{
					tmpPuzzel = board[i][j].peek();
					ID = tmpPuzzel.getID();
					controler.redraw(i,j,ID);
				}
			}
		}
	}
	/**
	 * Przesuniêcie pustego pola to tak naprawdê przesuniêcie odpowiedniego puzzla w przeciwnym kierunku
	 * @param where w któr¹ stronê (góra dó³ prawo lewo)
	 * @param x - wsp x wolnego pola, które chcemy przemieœciæ
	 * @param y - wsp y wolnego pola które chcemy przemieœciæ
	 */
	
	Point moveHole( Direction where, int x, int y)
	{
		if(!board[x][y].isEmpty())
		{
			return new Point(-1,-1);
		}
		Point p = new Point (-1,-1);
		switch(where)
		{
			case left:
			{
				if(x == 0 || board[x-1][y].size()>1)
				{
					p= new Point(x,y);
				}
				else
				{
					move(Direction.right, x-1, y);
					p= new Point(x-1,y);
				}
				break;
			}
			
			case right:
			{
				if(x == GameControl.WIDTH-1 || board[x+1][y].size()>1)
				{
					p= new Point (x,y);
				}
				else
				{
					move(Direction.left, x+1, y);
					p= new Point (x+1,y);
				}
				break;
			}
			
			case down:
			{
				if(y == GameControl.WIDTH-1 || board[x][y+1].size()>1)
				{
					p= new Point(x,y);
				}
				else
				{
					move(Direction.up, x, y+1);
					p= new Point(x,y+1);
				}
				break;
			}
			
			case up:
			{
				if(y == 0 || board[x][y-1].size()>1)
				{
					p= new Point (x,y);
				}
				else
				{
					move(Direction.down, x, y-1);
					p= new Point (x,y-1);
				}
				break;
			}
		}
		redraw();
		return p;
	}
	/**
	 * Funkcja przekazujaca do algorytmu akcjê zapauzowania uk³adania
	 */
	public void pause()
	{
		algContr.pause();
	}
	/**
	 * Funkcja przekazuj¹ca do algorytmu akcjê wznowienia uk³adania
	 */
	public void unPause()
	{
		algContr.unPause();
	}
	/**
	 * Funkcja przekazuj¹ca do algorytmu pocz¹tek uk³adania
	 * @throws InterruptedException - wyj¹tek w przypadku przerwania czasu uœpienia
	 */
	public void solve() throws InterruptedException
	{
		algContr.solveGame();
	}
	/**
	 * Funkcja przekazuj¹ca do kontrolera algorytmu czas miêdzy kolejnymi krokami
	 * @param i - czas w milisekundach
	 */
	public void setClockTick(int i)
	{
		algContr.setClockTick(i);
	}
	/**
	 * Funkcja przekazuj¹ca do widoku informacjê o skoñczeniu uk³adania algorytmu
	 */
	public void done() {
		controler.done();
	}
}
