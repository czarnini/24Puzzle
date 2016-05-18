import java.awt.Point;
import java.util.ArrayList;

/**
 * Klasa reprezentuj�ca algorytm, kt�rego celem jest wyznaczenie najkr�tszej �cie�ki (sekwencji ruch�w prawo/lewo/g�ra/d�
 * na podstawie otrzymanych z zewn�trz:  
 * wsp�rz�dnych startowych
 * wsp�rz�dnych ko�cowych
 */
public class Algorithm 
{
	/**
	 * Pocz�tek trasy
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
	 * Ustawia start
	 * @param start
	 */
	public void setStart(Point start)
	{
		this.start = start;
	}
	
	/**
	 * Ustawia koniec trasy
	 * @param end
	 */
	public void setEnd(Point end)
	{
		this.end = end;
	}
	
	/**
	 * Algorytm najpierw porusza si� w poziomie, a� do odpowiedniej warto�ci wsp. X.
	 * Nast�pnie porusza si� w pione, a� dojdzie do odpowiedniej warto�ci Y.
	 * @return Rozwi�zanie w postaci listy kierunk�w 
	 */
	public ArrayList<Direction> solve()
	{
		ArrayList<Direction> solution = new ArrayList<Direction>();
		//prawo lewo
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
		//gora dol
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
