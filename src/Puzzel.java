
public class Puzzel {
	private int id;
	private int posx;
	private int posy;
	
	Puzzel(int id)
	{
		this.id = id;
	}

	public void setx(int x)
	{
		posx = x;
	}
	public int gety()
	{
		return posy;
	}
	
	public void sety(int y)
	{
		posy = y;
	}
	public int getx()
	{
		return posx;
	}
	
	public int getID()
	{
		return id;
	}
}
