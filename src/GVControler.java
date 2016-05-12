import javax.swing.Timer;

public class GVControler
{
	View view;
	static GameControl gameControl;
	Timer timer;
	
	GVControler (GameControl gameControl, View view)
	{
		this.view = view;
		GVControler.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
	}
	/*nie wiem czy uzyj�*/
	void set(int i, int j)
	{
		view.setToMove(i,j);
	}
	/*nie wiem czy uzyj�*/
	void put (int i, int j)
	{
		view.put(i,j);
	}
	void redraw (int i, int j, int ID)
	{
		view.redraw(i,j,ID);
	}
	public static void randomize()
	{
		gameControl.randomize();
	}

	public static void solve() {
		// TODO Auto-generated method stub
		
	}

	public static void pause() {
		// TODO Auto-generated method stub
		
	}

	public static void unPause() {
		// TODO Auto-generated method stub
		
	}
	public void move(Direction where, int i, int j)
	{
		gameControl.move(where, i, j);
	}
}
