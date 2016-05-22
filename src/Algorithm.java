import java.awt.Point;
import java.util.ArrayList;

/**
 * Klasa reprezentuj¹ca algorytm, którego celem jest wyznaczenie najkrótszej œcie¿ki (sekwencji ruchów prawo/lewo/góra/dó³
 * na podstawie otrzymanych z zewn¹trz:  
 * wspó³rzêdnych startowych
 * wspó³rzêdnych koñcowych
 */
public class Algorithm 
{
	/**
	 * Pocz¹tek trasy
	 */
	private Point start;
	
	/**
	 * Koniec trasy
	 */
	private Point end;
	
	
	Algorithm()
	{
		start = new Point();
		end = new Point();
	}
	/**
	 * Ustawia pocz¹tek trasy
	 * @param start - wspó³rzêdnie X,Y pocz¹tku trasy
	 */
	public void setStart(Point start)
	{
		this.start = start;
	}
	
	/**
	 * Ustawia koniec trasy
	 * @param end - wspó³rzêdnie X,Y koñca trasy
	 */
	public void setEnd(Point end)
	{
		this.end = end;
	}
	
	/**
	 * Algorytm najpierw porusza siê w poziomie, a¿ do odpowiedniej wartoœci wsp. X.
	 * Nastêpnie porusza siê w pione, a¿ dojdzie do odpowiedniej wartoœci Y.
	 * @return Rozwi¹zanie w postaci listy kierunków 
	 */
	public ArrayList<Direction> solve()
	{
		ArrayList<Direction> solution = new ArrayList<Direction>();
		/** Obs³uga ruchu prawo-lewo*/
		while (start.x != end.x)
		{
			if(start.x < end.x)
			{
				solution.add(Direction.right);
				++start.x;
			}
			else if(start.x > end.x)
			{
				solution.add(Direction.left);
				--start.x;
			}	
		}
		/** Obs³uga ruchu góra-dó³*/
		while (start.y != end.y)
		{
			if(start.y < end.y)
			{
				solution.add(Direction.down);
				++start.y;
			}
			else if(start.y > end.y)
			{
				solution.add(Direction.up);
				--start.y;
			}	
		}
		return solution;
	}
}
