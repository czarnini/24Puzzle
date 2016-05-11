
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
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				Puzzel tmpPuzzel = board[i][j].pop();
				int newx = generator.nextInt(GameControl.WIDTH);
				int newy = generator.nextInt(GameControl.WIDTH);
				board[i][j].push(board[newx][newy].pop());
				board[newx][newy].push(tmpPuzzel);
			}
		}
		
		for ( int i = 0; i < GameControl.WIDTH; ++i)
		{
			for(int j=0; j<GameControl.WIDTH; ++j)
			{
				System.out.println(board[i][j].peek().getID());	
			}
		}
		
	}
}
