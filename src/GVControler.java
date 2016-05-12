import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GVControler implements ActionListener
{
	View view;
	static GameControl gameControl;
	static Timer timer;
	static int clockTick = 1000; //(ms)
	GVControler (GameControl gameControl, View view)
	{
		this.view = view;
		GVControler.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
		timer = new Timer (clockTick, this);
	}
	void redraw (int i, int j, int ID)
	{
		view.redraw(i,j,ID);
	}
	public static void randomize()
	{
		gameControl.randomize();
	}

	public void solve()
	{
		timer.start();
		//TODO: tu na pewno cos jeszcze bedzie...
	}

	public static void pause() {
		timer.stop();
	}

	public static void unPause() {
		timer.start();
	}
	public void move(Direction where, int i, int j)
	{
		gameControl.move(where, i, j);
	}
	
	public void setClockTick(int i)
	{
		clockTick = i;
		System.out.println("clockTick: "+i);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO tu bêdzie zewnêtrzne wyjœcie pod tytu³em "zrób nastêpny krok":
		//powiedz grze zeby zrobila krok
		//spytaj gre czy ukladanka jest rozwiazana
		//jezeli TAK: view.done(); timer.stop();
		
	}
}
