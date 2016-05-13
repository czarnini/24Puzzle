import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GVControler implements ActionListener
{
	View view;
	GameControl gameControl;
	Timer timer;
	int clockTick = 1000; //(ms)
	GVControler (GameControl gameControl, View view)
	{
		this.view = view;
		this.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
		timer = new Timer (clockTick, this);
	}
	void redraw (int i, int j, int ID)
	{
		view.redraw(i,j,ID);
	}
	public void randomize()
	{
		gameControl.randomize();
	}

	public void solve()
	{
		timer.start();
		//TODO: tu na pewno cos jeszcze bedzie...
	}

	public void pause() {
		timer.stop();
	}

	public void unPause() {
		timer.start();
	}
	public void move(Direction where, int i, int j)
	{
		gameControl.move(where, i, j);
	}
	
	public void setClockTick(int i)
	{
		clockTick = i;
		timer.setDelay(clockTick);
		System.out.println("clockTick: "+i);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("BBBB"+clockTick);
		// TODO tu bêdzie zewnêtrzne wyjœcie pod tytu³em "zrób nastêpny krok":
		//powiedz grze zeby zrobila krok
		//spytaj gre czy ukladanka jest rozwiazana
		//jezeli TAK: view.done(); timer.stop();
		
	}
}
