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
	 * 
	 */
	GVControler controler;
	
	/**
	 * Zlinkowanie kontrolera GV z kontrolerem gry
	 * @param controler kontroler z którym bêdziemy linkowaæ
	 */
	public void linkController(GVControler controler)
	{
		this.controler = controler;
	}
	
	/**
	 * Konstruktor alokuje pamiêæ na stosy tworz¹ce planszê do gry
	 */
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
	 * Funkcja dodaje puzzle na ich nominalne miejsce 
	 */
	public void AddPuzzles()
	{
		int id=1;
			for ( int i = 0; i < GameControl.WIDTH; ++i)
			{
				for(int j=0; j<GameControl.WIDTH; ++j)
				{
					Puzzel newPuzzel = new Puzzel(id);
					board[i][j].push(newPuzzel);
					
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
		print();
		redraw();
	}
	
	/**
	 * @TODO
	 * Jak tylko bêdzie GUI to to usuwamy
	 */
	public void print()
	{
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				if(!board[j][i].isEmpty())
				System.out.print(board[j][i].peek().getID()+"\t");	
				else
					System.out.print("\t");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	/**
	 * 
	 * @param where w któr¹ stronê
	 * @param x - wsp x klocka który bêdziemy ruszaæ
	 * @param y - wsp y klocka, który bêdziemy ruszaæ
	 */
	public void move(Direction where, int x, int y)
	{
		if(board[x][y].isEmpty())
		{
			return;
		}
		switch(where)
		{
			case left:
			{
				if(x == 0)
				{
					return;
				}
				else
				{
					board[x-1][y].push(board[x][y].pop());
				}
				break;
			}
			case right:
			{
				if(x == GameControl.WIDTH-1)
				{
					return;
				}
				else
				{

					board[x+1][y].push(board[x][y].pop());
				}
				break;
			}
			case up:
			{
				if(y == 0)
				{
					return;
				}
				else
				{

					board[x][y-1].push(board[x][y].pop());
				}
				break;
			}
			case down:
			{
				if(y == GameControl.WIDTH-1)
				{
					return;
				}
				else
				{
					board[x][y+1].push(board[x][y].pop());
				}
				break;
			}
		}
	}
	
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
				}
				else
					ID=0;
				controler.redraw(i,j,ID);
			}
		}
	}
	/**
	 * Przesuniêcie pustego pola to tak naprawdê przesuniêcie odpowiedniego puzzla (stosu puzzli?) w odpowiednim kierunku
	 * @param where w któr¹ stronê (góra dó³ prawo lewo)
	 * @param x - wsp x wolnego pola, które chcemy przemieœciæ
	 * @param y - wsp y wolnego pola które chcemy przemieœciæ
	 */
	
	void moveHole( Direction where, int x, int y)
	{
		if(!board[x][y].isEmpty())
		{
			return;
		}
		switch(where)
		{
			case left:
			{
				if(x == 0 || board[x-1][y].size()>1)
				{
					return;
				}
				else
				{
					move(Direction.right, x-1, y);
				}
				break;
			}
			
			case right:
			{
				if(x == GameControl.WIDTH-1 || board[x+1][y].size()>1)
				{
					return;
				}
				else
				{
					move(Direction.left, x+1, y);
				}
				break;
			}
			
			case down:
			{
				if(y == GameControl.WIDTH-1 || board[x][y+1].size()>1)
				{
					return;
				}
				else
				{
					move(Direction.up, x, y+1);
				}
				break;
			}
			
			case up:
			{
				if(y == 0 || board[x][y-1].size()>1)
				{
					return;
				}
				else
				{
					move(Direction.down, x, y-1);
				}
				break;
			}
		}
	}
}
