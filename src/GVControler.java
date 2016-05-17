/** 
 * Klasa kontrolera miêdzy widokiem gry a modelem gry. Zajmuje siê ³¹czeniem widoku z gr¹ (aby stanowi³y 2 niezalezne modu³y)
 * oraz przekazywaniem poleceñ miêdzy widokiem a gr¹*/
public class GVControler
{
	View view;	/** Klasa widoku*/
	GameControl gameControl; /** Klasa modelu gry*/
	/**
	 * Konstruktor klasy, ³¹cz¹cy ze sob¹ powi¹zane klasy
	 * @param gameControl - model gry
	 */
	GVControler (GameControl gameControl)
	{
		view = new View();
		this.gameControl = gameControl;
		view.linkController(this);
		gameControl.linkController(this);
	}
	/**
	 * metoda przesy³aj¹ca do widoku polecenie przerywowania pozycji kafelka
	 * @param i - docelowa pozycja X kafelka
	 * @param j - docelowa pozycja Y kafelka
	 * @param ID - ID przesuwanego kafelka
	 */
	void redraw (int i, int j, int ID)
	{
		view.redraw(i,j,ID);
	}
	/**
	 * metoda przesy³aj¹ca do modelu polecenie z guzika, przerysowanie planszy
	 */
	public void randomize()
	{
		gameControl.randomize();
	}
	/**
	 * metoda przesy³aj¹ca do modelu polecenie z guzika, rozwi¹zanie puzzli
	 * @throws InterruptedException - wyj¹tek dla metody sleep
	 */
	public void solve() throws InterruptedException
	{
		gameControl.solve();
	}
	/**
	 * metoda przesy³aj¹ca do modelu polecenie z guzika, zatrzymanie rozwi¹zania
	 */
	public void pause() {
		gameControl.pause();
	}
	/**
	 * metoda przesy³aj¹ca do modelu polecenie z guzika, wznowienie rozwi¹zania
	 */
	public void unPause() {
		gameControl.unPause();
	}
	/**
	 * metoda przesy³aj¹ca do modelu polecenie zmiany szybkoœci wykonywania kolejnych kroków
	 * @param i - szybkoœæ wykonywania kolejnych kroków przekazana przez widok, w ms
	 */
	public void setClockTick(int i)
	{
		gameControl.setClockTick(i);
	}
	/**
	 * metoda przesy³aj¹ca do widoku polecenie zwiêkszenia licznika kroków
	 */
	public void updateSteps()
	{
		view.updateSteps();
	}
	/**
	 * metoda przesy³aj¹ca do widoku informacjê ¿e program zakoñczy³ rozwi¹zywanie ³amig³ówki
	 */
	public void done()
	{
		view.done();		
	}
}