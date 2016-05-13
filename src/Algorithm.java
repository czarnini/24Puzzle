import java.awt.Point;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.LeafElement;

/**
 * Klasa reprezentuj¹ca algorytm, którego celem jest wyznaczenie najkrótszej œcie¿ki (sekwencji ruchów prawo/lewo/góra/dó³
 * na podstawie otrzymanych z zewn¹trz:  
 * wspó³rzêdnych startowych
 * wspó³rzêdnych koñcowych
 * 
 */
public class Algorithm 
{
	private Point start;
	private Point end;
	
	
	Algorithm()
	{
		start = new Point();
		end = new Point();
	}
	
	public void setStart(Point start)
	{
		this.start = start;
	}
	
	public void setEnd(Point end)
	{
		this.end = end;
	}
	
	
	
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
