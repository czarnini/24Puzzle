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
	
	public void gameStart()
	{
		view.createFrame();
	}
}
