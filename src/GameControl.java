import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
* Funkcjonalno�� klasy:
* 1. Dodaj klocki do planszy
* 2. Mieszaj klocki
* 3. Resetuj ustawienie planszy (?)
* 4. Wykonaj ruch
*/
public class GameControl {
	
	public final  static  int   WIDTH = 5;
	private Stack<Puzzel> [][] board;
	

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

	public void Randomize()
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
	}
	
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
