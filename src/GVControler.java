
public class GVControler
{
	View view;
	GameControl gameControl;
	GVControler (GameControl gameControl)
	{
		view = new View();
		this.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
	}
	void redraw (int i, int j, int ID)
	{
		view.redraw(i,j,ID);
	}
	public void randomize()
	{
		gameControl.randomize();
	}

	public void solve() throws InterruptedException
	{
		gameControl.solve();
	}

	public void pause() {
		gameControl.pause();
	}

	public void unPause() {
		gameControl.unPause();
	}
	public void move(Direction where, int i, int j)
	{
		gameControl.move(where, i, j);
	}
	
	public void setClockTick(int i)
	{
		gameControl.setClockTick(i);
	}
	public void updateSteps() {
		view.updateSteps();
	}
	public void done() {
		view.done();		
	}
}
