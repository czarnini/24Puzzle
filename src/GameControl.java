
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
* Funkcjonalnoœæ klasy:
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
		
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				if(!board[i][j].isEmpty())
				System.out.println(board[i][j].peek().getID());	
				else
					System.out.println("NULLLLLLLLLLLLL");
			}
		}
		
	}

	public void move(Direction where, int x, int y)
	{
		switch(where)
		{
			case left:
			{
				break;
			}
			case right:
			{
				break;
			}
			case up:
			{
				break;
			}
			case down:
			{
				break;
			}
		}
	}

}
