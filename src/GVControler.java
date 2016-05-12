import javax.swing.Timer;

public class GVControler
{
	View view;
	GameControl gameControl;
	Timer timer;
	
	GVControler (GameControl gameControl, View view)
	{
		this.view = view;
		this.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
	}
	
	public static void randomize()
	{
		//TODO
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
}
