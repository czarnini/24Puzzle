
import java.util.ArrayList;
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
		
		for ( int i = 0,  j = 0; i < GameControl.WIDTH*GameControl.WIDTH-1; ++i)
		{
			board [i%GameControl.WIDTH][j] = new Stack<Puzzel>() ;
			
			++j;
			
			if(i%GameControl.WIDTH == GameControl.WIDTH-1)
			{
				j=0;
			}
		}
	}
}
